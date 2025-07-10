package com.example.habitnote.modal

import androidx.room.Entity
import androidx.room.PrimaryKey

import androidx.compose.ui.graphics.Color
import androidx.room.TypeConverters


@Entity(tableName = "habitTable")
@TypeConverters(ColorConverters::class)
data class Habit(
    @PrimaryKey
    val id: Int,
    val title: String,
    val subTitle: String,
    val color: Color
)
