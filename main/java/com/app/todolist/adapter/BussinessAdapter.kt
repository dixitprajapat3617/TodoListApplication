package com.app.todolist.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.app.todolist.database.UserDataBase
import com.app.todolist.databinding.BussinessWorkUiDesignBinding
import com.app.todolist.model.BussinessData

import com.app.todolist.model.User

class BussinessAdapter (var context: Context, var bussnesslist:MutableList<BussinessData>):RecyclerView.Adapter<BussinessAdapter.MyViewHolder>(){
    lateinit var binding: BussinessWorkUiDesignBinding
    lateinit var database:UserDataBase


    class MyViewHolder(var bind:BussinessWorkUiDesignBinding):RecyclerView.ViewHolder(bind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            binding= BussinessWorkUiDesignBinding.inflate(LayoutInflater.from(context),parent,false)
            return MyViewHolder(binding)
    }



    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var name=bussnesslist[position]
        holder.bind.uiTittle.text="${name.tittle}"
        holder.bind.uiDescription.text="${name.description}"
       /* holder.bind.carView.setOnLongClickListener {
            AlertDialog.Builder(context).apply {
                setTitle("Delete")
                setMessage("Are you sure you wnat to delete this item")
                setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, i ->
                    database = Room.databaseBuilder(context, UserDataBase::class.java, name = "dixit.db").allowMainThreadQueries().build()
                    var userDao=database.
                    userDao.deleterecord(name)
                    notifyItemRemoved(position)
                    bussnesslist.removeAt(position)

                })
                setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->

                })
            }.show()
            return@setOnLongClickListener true

        }*/

    }

    override fun getItemCount(): Int {
        return bussnesslist.size
    }
    fun setitem(daliylist: MutableList<User>){
        this.bussnesslist=bussnesslist
        notifyDataSetChanged()
    }
}