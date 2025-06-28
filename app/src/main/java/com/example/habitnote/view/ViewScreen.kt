package com.example.habitnote.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.navigation.NavHostController
import com.example.habitnote.modal.Habit
import com.example.habitnote.ui.theme.blue
import com.example.habitnote.ui.theme.dark_violet
import com.example.habitnote.ui.theme.green
import com.example.habitnote.ui.theme.greyDark
import com.example.habitnote.ui.theme.greyWhite
import com.example.habitnote.ui.theme.orange
import com.example.habitnote.ui.theme.pink
import com.example.habitnote.ui.theme.red
import com.example.habitnote.ui.theme.rose
import com.example.habitnote.ui.theme.violet
import com.example.habitnote.ui.theme.yellow
import com.example.habitnote.viewmodel.MainViewModel


@Composable
fun ViewScreen(controller: NavHostController, viewmodel: MainViewModel, id : Int) {

    val data = viewmodel.data.value.find {
        it.id == id
    }
    Scaffold(
        Modifier,
        topBar = {
            Row (
                Modifier.background(Color.White).padding(horizontal = 30.dp, vertical = 30.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = null,
                    Modifier.padding(end = 25.dp).clickable{
                        controller.popBackStack()
                    },
                    tint = Color(0xffFFB347)
                )
                Text(" Note", Modifier, fontSize = 24.sp,
                    fontWeight = FontWeight.W600
                )

            }
        }
    ){
        Column(Modifier.padding(it).fillMaxSize()
            .background(data?.color ?: Color.White)
            .padding(horizontal = 20.dp, vertical = 40.dp)
        ) {

            Text(data?.title?:"", Modifier, fontWeight = FontWeight.W600,
                        fontSize = 48.sp)

            Text(data?.subTitle ?:"", Modifier, fontWeight = FontWeight.W400,
                        fontSize = 23.sp)

        }
    }
}




