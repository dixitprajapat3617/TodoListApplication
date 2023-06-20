package com.app.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.app.todolist.adapter.PersonalAdapter
import com.app.todolist.databinding.ActivityHomePageBinding
import com.app.todolist.fragment.Bussiness
import com.app.todolist.fragment.PersonalFragment
import com.app.todolist.fragment.WorkFragment
import com.app.todolist.model.User
import com.google.firebase.auth.FirebaseAuth


class HomePageActivity : AppCompatActivity() {
    lateinit var mAuth: FirebaseAuth
    private var searchBarOpen = false

    lateinit var sadapter: PersonalAdapter
    var userlist = mutableListOf<User>()
    lateinit var binding: ActivityHomePageBinding
    lateinit var listadapter: ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolBar)

        mAuth=FirebaseAuth.getInstance()
        binding.toolbarTittle.setText("Personal")

        addFragement(PersonalFragment(),"Home")

        binding.bottomNavigation.setOnItemSelectedListener {
            return@setOnItemSelectedListener when(it.itemId){
                    R.id.bottam_personal->{
                        binding.toolbarTittle.setText("Presonal")
                        addFragement(PersonalFragment(),"personal")
                        true
                    }
                    R.id.bottom_bussiness->{
                        binding.toolbarTittle.setText("Bussiness")

                        addFragement(Bussiness(),"Bussiness")
                    true
                    }
                    R.id.bottam_work->{
                        binding.toolbarTittle.setText("Work")

                        addFragement(WorkFragment(),"Work")
                    true
                    }else->{
                        false
                    }
            }
        }




        var actionbar = supportActionBar
        actionbar?.setTitle("")
        binding.toolBar.title = ""


        binding.floatingButton.setOnClickListener {
            startActivity(Intent(this, DailyWorkActivity::class.java))
        }
        // database=Room.databaseBuilder(this,UserDataBase::class.java, name = "dixit.db").allowMainThreadQueries().build()






    }

    private fun addFragement(fragment: Fragment,tittle:String) {
        var manager=supportFragmentManager
        var transaction=manager.beginTransaction()
        transaction.replace(R.id.fragment_conainer,fragment)
        transaction.addToBackStack(tittle)
        transaction.commit()

    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_search -> {

                toggleSearch(item)


                true
            }

            R.id.menu_setting -> {
                true
            }
            R.id.menu_logout -> {

                mAuth.signOut()
                startActivity(Intent(this,LogInActivity::class.java))


                true
            }
            else -> super.onOptionsItemSelected(item)

        }



    }


    override fun onBackPressed() {
        if (searchBarOpen) {
            // Hide the search bar and collapse the action view
            binding.searchEditText.visibility = View.GONE
            binding.toolbarTittle.visibility=View.VISIBLE
            binding.imageView.visibility = View.VISIBLE
         //   binding.toolBar.title.visibility = View.VISIBLE
            searchBarOpen = false
            val searchItem = binding.toolBar.menu.findItem(R.id.menu_search)
            searchItem.collapseActionView()
        } else {

            super.onBackPressed()
        }
    }


    private fun toggleSearch(item: MenuItem) {
        if (searchBarOpen) {
            binding.searchEditText.visibility = View.GONE
         //   binding.spiner.visibility=View.VISIBLE
            binding.imageView.visibility=View.VISIBLE
            binding.toolbarTittle.visibility=View.VISIBLE
            searchBarOpen =false
        } else {
            binding.searchEditText.visibility = View.VISIBLE
           // binding.spiner.visibility=View.GONE
            binding.imageView.visibility=View.GONE
            binding.toolbarTittle.visibility=View.GONE
            searchBarOpen=true
        }
    }
}
