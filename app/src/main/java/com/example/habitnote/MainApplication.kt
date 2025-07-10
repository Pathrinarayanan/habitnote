package com.example.habitnote

import android.app.Application
import androidx.room.Room
import com.example.habitnote.modal.Database

class MainApplication : Application() {
    companion object{
        lateinit var habitDB : Database
    }

    override fun onCreate() {
        super.onCreate()
        habitDB = Room.databaseBuilder(applicationContext,
            name = "habitDatabase",
            klass = Database::class.java
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }
}