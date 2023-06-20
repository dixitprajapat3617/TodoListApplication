package com.app.todolist.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.app.todolist.adapter.PersonalAdapter

import com.app.todolist.database.UserDataBase
import com.app.todolist.databinding.FragmentPersonalBinding
import com.app.todolist.model.User


class PersonalFragment : Fragment() {
    lateinit var binding: FragmentPersonalBinding
    lateinit var database:UserDataBase
    lateinit var sadapter:PersonalAdapter
    var userlist= mutableListOf<User>()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentPersonalBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database = Room.databaseBuilder(requireActivity(), UserDataBase::class.java, name = "dixit.db")
            .allowMainThreadQueries().build()

        sadapter = PersonalAdapter(requireActivity(), userlist)
        binding.recyclerViewPersonal.layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerViewPersonal.adapter = sadapter
        updatelist()


    }

    private fun updatelist() {
        userlist = database.userdao().getAllItem()
        sadapter.setitem(userlist)

    }

    override fun onResume() {
        super.onResume()
        if (database != null) {
            updatelist()
        }
    }


}