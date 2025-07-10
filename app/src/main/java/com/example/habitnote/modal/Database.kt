package com.example.habitnote.modal

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [Habit::class], version = 1)
@TypeConverters(ColorConverters::class)
abstract class Database : RoomDatabase(){
    abstract fun userDao(): UserDao
}