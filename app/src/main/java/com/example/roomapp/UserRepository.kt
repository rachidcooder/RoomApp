package com.example.roomapp

import androidx.lifecycle.LiveData
import com.example.roomapp.Entity.DaoInterface
import com.example.roomapp.Entity.User

class UserRepository(private val userDao:DaoInterface) {

    val readAlldata : LiveData<List<User>> =userDao.getAll()

    suspend fun addUser(user:User){
        userDao.insertAll(user)
    }
    suspend fun UpdateUser(user:User){
        userDao.update(user)
    }
    suspend fun deletUser(user: User){
        userDao.delete(user)
    }

}