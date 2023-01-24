package com.example.roomapp.Entity

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
const val DATABASE_NAME="DB_name"
@Database(entities = [User::class], version = 1)
abstract class Userdatabase: RoomDatabase() {
    abstract fun DaoInterface(): DaoInterface

    companion object{
        @Volatile
        private  var instance:Userdatabase?=null
        fun getInstance(context: Context):Userdatabase{

            return instance?: synchronized(Any()){
                instance?:buildDatabase(context).also{
                    instance=it
                }
            }
        }

        private fun buildDatabase(ctx: Context): Userdatabase {

            return  Room.databaseBuilder(
                ctx.applicationContext,
                Userdatabase::class.java, DATABASE_NAME
            ).build()
        }
    }

}