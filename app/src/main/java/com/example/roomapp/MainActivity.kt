package com.example.roomapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomapp.Entity.User
import com.example.roomapp.Entity.Userdatabase

class MainActivity : AppCompatActivity() {
    lateinit var edtname:EditText
    lateinit var edtemail:EditText
    lateinit var btnSave: Button
    lateinit var btnShow: Button
    lateinit var rv:RecyclerView
    lateinit var db:Userdatabase
    lateinit var mUserViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()
        edtname=findViewById(R.id.nameID)
        edtemail=findViewById(R.id.emailID)
        btnSave=findViewById(R.id.btnsaveID)
        rv=findViewById(R.id.rsID)



   rv.layoutManager=LinearLayoutManager(this)

        //Show Data
        val adapter=RvAdapter(this)
        mUserViewModel=ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAlldata.observe(this, Observer { user ->
            adapter.setData(user)
        })
        rv.adapter=adapter

        btnSave.setOnClickListener{
            mUserViewModel=ViewModelProvider(this).get(UserViewModel::class.java)

            var name=edtname.text.toString()
            var email =edtemail.text.toString()

            if(checkFields(name,email)==true){
             val user = User(0, name,email)
            mUserViewModel.AddUser(user)
                Toast.makeText(this,"Succsfuly",Toast.LENGTH_LONG).show()
        }else{
                Toast.makeText(this,"some fields are Empty!",Toast.LENGTH_LONG).show()
            }
        }


    }
    fun checkFields( name:String,email:String):Boolean{
        return  !(TextUtils.isEmpty(name)&&TextUtils.isEmpty(email))
    }
}