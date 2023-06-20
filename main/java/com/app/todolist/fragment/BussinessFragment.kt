package com.app.todolist.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.app.todolist.adapter.BussinessAdapter
import com.app.todolist.adapter.PersonalAdapter

import com.app.todolist.database.UserDataBase
import com.app.todolist.databinding.BussinessWorkUiDesignBinding
import com.app.todolist.databinding.FragmentBussinessBinding
import com.app.todolist.model.User


class Bussiness : Fragment() {
    lateinit var dataBase: UserDataBase
    var userlist= mutableListOf<User>()
    lateinit var madapter:BussinessAdapter
    lateinit var binding: FragmentBussinessBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentBussinessBinding.inflate(inflater,container,false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBase = Room.databaseBuilder(requireActivity(), UserDataBase::class.java, name = "dixit.db")
            .allowMainThreadQueries().build()

        madapter = BussinessAdapter(requireActivity(), userlist)
        binding.recyclerViewBussiness.layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerViewBussiness.adapter = madapter
        updaterlist()


    }

    private fun updaterlist() {

            userlist = dataBase.userdao().getAllItem()
            madapter.setitem(userlist)

        }


    override fun onResume() {
            super.onResume()
            if (dataBase != null) {
                updaterlist()
            }
        }
    }


