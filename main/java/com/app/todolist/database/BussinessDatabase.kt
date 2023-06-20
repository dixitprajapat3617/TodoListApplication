package com.app.todolist.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.todolist.dao.BussinessDao
import com.app.todolist.model.BussinessData

@Database(entities = [BussinessData::class], version = 1)
abstract class BussinessDatabase:RoomDatabase() {
    abstract fun bussinessdao():BussinessDao

}