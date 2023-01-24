package com.example.roomapp.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users_table")
class User(
    @PrimaryKey(autoGenerate = true)
    val Id: Int,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "email") val email: String?)
