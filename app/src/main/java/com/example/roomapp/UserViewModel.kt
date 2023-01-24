package com.example.roomapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.roomapp.Entity.DaoInterface
import com.example.roomapp.Entity.User
import com.example.roomapp.Entity.Userdatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserViewModel(application :Application): AndroidViewModel(application) {

     val readAlldata: LiveData<List<User>>

    val repo:UserRepository
    init {
         val userdao=Userdatabase.getInstance(application).DaoInterface()
        repo= UserRepository(userdao)
        readAlldata=repo.readAlldata
    }
    fun AddUser(user:User){
        GlobalScope.launch(Dispatchers.IO) {
            repo.addUser(user)
        }
    }

    fun UpdatUser(user: User){
        GlobalScope.launch(Dispatchers.IO) {
            repo.UpdateUser(user)
        }}
    fun deletUser(user: User){
        GlobalScope.launch(Dispatchers.IO) {
            repo.deletUser(user)
        }}

}