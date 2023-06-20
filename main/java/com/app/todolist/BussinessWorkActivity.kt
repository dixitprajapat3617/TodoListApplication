package com.app.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.app.todolist.database.BussinessDatabase
import com.app.todolist.databinding.ActivityBussinessWorkBinding
import com.app.todolist.model.BussinessData
import com.app.todolist.model.User
import com.google.android.material.textfield.TextInputLayout

class BussinessWorkActivity : AppCompatActivity() {
    lateinit var database: BussinessDatabase
    lateinit var binding: ActivityBussinessWorkBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBussinessWorkBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.floatingButtonSaved.setOnClickListener {

            val title = binding.etTittle.text.toString().trim()
            val description = binding.etDaliyWork.text.toString().trim()

            if (title.isEmpty() || description.isEmpty()) {
                setStrokeColor(binding.tiltittle, R.color.red)
                setStrokeColor(binding.desTil, R.color.red)
                Toast.makeText(this, "Title and Description cannot be blank", Toast.LENGTH_SHORT)
                    .show()
            } else {
                setStrokeColor(binding.tiltittle, R.color.white)
                setStrokeColor(binding.desTil, R.color.white)
                inserUser(title, description)
                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
                onBackPressed()
            }


        }







    }

    private fun inserUser(ctitle: String, cdescription: String) {
        var user= User(tittle = ctitle, description = cdescription)

        try {
            database.bussinessdao().inserUser(user)
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
            onBackPressed()

        }catch (e:Exception){
            e.printStackTrace()
        }

    }

    private fun setStrokeColor(view: TextInputLayout, colorResId: Int) {
        val color = ContextCompat.getColor(this, colorResId)
        view.boxStrokeColor = color

    }
}
