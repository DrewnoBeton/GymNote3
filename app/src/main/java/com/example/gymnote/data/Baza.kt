package com.example.gymnote.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Cwiczenie::class], version = 1)
abstract class Baza: RoomDatabase() {
    abstract val cwiczeniaDAO: CwiczeniaDAO

    //zeby sie baza tylko jeden raz otworzyla
    companion object{
        @Volatile
        private var INSTANCE: Baza? = null
        fun getInstance(context: Context): Baza
        {
            synchronized(this)
            {
               var instance = INSTANCE
               if(instance == null)
               {
                   instance = Room.databaseBuilder(context.applicationContext, Baza::class.java,"baza").build()
               }
                return instance
            }
        }
    }

}