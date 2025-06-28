package com.example.habitnote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.habitnote.ui.theme.HabitNoteTheme
import com.example.habitnote.view.AddScreen
import com.example.habitnote.view.EditScreen
import com.example.habitnote.view.HomeScreen
import com.example.habitnote.view.ViewScreen
import com.example.habitnote.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    private val viewmodel : MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HabitNoteTheme {
                Scaffold(modifier = Modifier.fillMaxSize().padding(top =30.dp)) { innerPadding ->
                    val controller = rememberNavController()
                    NavHost(controller, startDestination = "home"){
                        composable ("home"){
                            HomeScreen(controller,viewmodel)
                        }
                        composable("add") {
                            AddScreen(controller, viewmodel)
                        }
                        composable("view/{id}") {
                            val id  = it.arguments?.getString("id")?.toInt() ?:0
                            ViewScreen(controller, viewmodel,id)
                        }
                        composable("edit/{id}") {
                            val id  = it.arguments?.getString("id")?.toInt() ?:0
                            EditScreen(controller, viewmodel,id)
                        }
                    }
                }
            }
        }
    }
}

