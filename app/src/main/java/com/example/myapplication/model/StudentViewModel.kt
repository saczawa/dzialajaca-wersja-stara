package com.example.myapplication.model

import androidx.lifecycle.ViewModel
import com.example.myapplication.SortType
import androidx.lifecycle.viewModelScope
import com.example.myapplication.StudentEvent
import com.example.myapplication.StudentState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import com.example.myapplication.dao.StudentDao
import com.example.myapplication.entity.Student

@OptIn(ExperimentalCoroutinesApi::class)
class StudentViewModel(
    private val dao: StudentDao
): ViewModel() {

    private val _sortType = MutableStateFlow(SortType.FIRST_NAME)
    private val _students = _sortType
        .flatMapLatest { sortType ->
            when(sortType) {
                SortType.FIRST_NAME -> dao.getStudentsOrderedByFirstName()
                SortType.LAST_NAME -> dao.getStudentsOrderedByLastName()
                SortType.PHONE_NUMBER -> dao.getStudentsOrderedByPhoneNumber()
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state = MutableStateFlow(StudentState())
    val state = combine(_state, _sortType, _students) { state, sortType, students ->
        state.copy(
            students = students,
            sortType = sortType
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), StudentState())

    fun onEvent(event: StudentEvent) {
        when(event) {
            is StudentEvent.DeleteStudent -> {
                viewModelScope.launch {
                    dao.deleteStudent(event.student)
                }
            }
            StudentEvent.HideDialog -> {
                _state.update { it.copy(
                    isAddingStudent = false
                ) }
            }
            StudentEvent.SaveStudent -> {
                val firstName = state.value.firstName
                val lastName = state.value.lastName
                val phoneNumber = state.value.phoneNumber

                if(firstName.isBlank() || lastName.isBlank() || phoneNumber.isBlank()) {
                    return
                }

                val student = Student(
                    firstName = firstName,
                    lastName = lastName,
                    phoneNumber = phoneNumber
                )
                viewModelScope.launch {
                    dao.upsertStudent(student)
                }
                _state.update { it.copy(
                    isAddingStudent = false,
                    firstName = "",
                    lastName = "",
                    phoneNumber = ""
                ) }
            }
            is StudentEvent.SetFirstName -> {
                _state.update { it.copy(
                    firstName = event.firstName
                ) }
            }
            is StudentEvent.SetLastName -> {
                _state.update { it.copy(
                    lastName = event.lastName
                ) }
            }
            is StudentEvent.SetPhoneNumber -> {
                _state.update { it.copy(
                    phoneNumber = event.phoneNumber
                ) }
            }
            StudentEvent.ShowDialog -> {
                _state.update { it.copy(
                    isAddingStudent = true
                ) }
            }
            is StudentEvent.SortStudent -> {
                _sortType.value = event.sortType
            }
        }
    }
}