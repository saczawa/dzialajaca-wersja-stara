package com.example.myapplication.ui.student



import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapter.StudentAdapter
import com.example.myapplication.model.LessonList


class StudentFragment : Fragment() {

    private lateinit var newRecyclerview: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_student, container, false)

        val lessonsList = LessonList()

        newRecyclerview = view.findViewById(R.id.studentRecyclerView)
        newRecyclerview.setHasFixedSize(true)

        val adapter = StudentAdapter(mutableListOf()) // Tworzenie nowego adaptera z pustą listą lekcji
        newRecyclerview.adapter = adapter // Podpięcie adaptera pod RecyclerView
        newRecyclerview.layoutManager = LinearLayoutManager(requireContext())

        newRecyclerview.adapter = StudentAdapter(lessonsList.lessons)

        return view
    }
}