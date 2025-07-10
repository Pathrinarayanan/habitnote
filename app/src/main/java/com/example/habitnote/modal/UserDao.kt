package com.example.habitnote.modal

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM habitTable")
    fun getAll(): List<Habit>

    @Insert
    fun addHabit(habit : Habit)

    @Query("UPDATE habitTable set title = :title, subTitle = :subtitle where id=  :id")
    fun updateHabit(title : String, subtitle: String, id : Int)

    @Query("DELETE FROM habitTable where id = :id")
    fun deleteHabit(id : Int)
}