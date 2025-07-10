package com.example.habitnote.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.habitnote.MainApplication
import com.example.habitnote.modal.Habit

class MainViewModel : ViewModel() {
    var addColor : Color  by mutableStateOf(Color.White)
    var data : MutableState<List<Habit>> = mutableStateOf(emptyList())

    private var habitDao = MainApplication.habitDB.userDao()

    fun getHabits(): List<Habit>{
        val items = habitDao.getAll()
        data.value =items
        return items
    }

    fun addHabit(habit : Habit){
        habitDao.addHabit(habit)
        data.value =getHabits()
    }
    fun editHabit(habit : Habit){
        habitDao.updateHabit(habit.title, habit.subTitle, habit.id)
        data.value = getHabits()
    }
    fun deleteHabit(id : Int){
        habitDao.deleteHabit(id)
        data.value = getHabits()
    }
}