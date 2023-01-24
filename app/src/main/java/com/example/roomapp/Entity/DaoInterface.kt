package com.example.roomapp.Entity

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DaoInterface {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(vararg users: User)

    @Query("select * from users_table")
     fun getAll(): LiveData<List<User>>

    @Delete
    suspend fun delete(user: User)

    @Update
    suspend fun update(user: User)
}