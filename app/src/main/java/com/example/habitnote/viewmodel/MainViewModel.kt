package com.example.habitnote.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.habitnote.modal.Habit

class MainViewModel : ViewModel() {
    var addColor : Color  by mutableStateOf(Color.White)
    var data : MutableState<List<Habit>> = mutableStateOf(emptyList())

    fun addHabit(habit : Habit){
        data.value =  listOf(habit)+ data.value
    }
    fun editHabit(habit : Habit){
        data.value =  data.value.map {
            if(it.id == habit.id) habit else it
        }
    }
    fun deleteHabit(id : Int){
        data.value = data.value.filterNot {
            it.id == id
        }
    }
}