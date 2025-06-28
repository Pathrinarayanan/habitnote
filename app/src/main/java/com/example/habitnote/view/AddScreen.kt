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
import androidx.compose.ui.tooling.preview.Preview
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
fun AddScreen(controller: NavHostController, viewmodel: MainViewModel) {
    var openColorTray  by remember { mutableStateOf(false) }
    var title by remember { mutableStateOf("") }
    var subTitle by remember { mutableStateOf("") }
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
                Text("Add Note", Modifier, fontSize = 24.sp,
                    fontWeight = FontWeight.W600
                    )
                Spacer(Modifier.weight(1f))
                Button(
                    onClick = {
                        viewmodel.addHabit(
                            Habit(
                                id = viewmodel.data.value.size+1 ,
                                title = title,
                                subTitle = subTitle,
                                color = viewmodel.addColor
                                )
                        )
                        title = ""
                        subTitle = ""
                        controller.popBackStack()
                    }
                ) {
                    Text("Save")
                }
                Icon(
                    Icons.Filled.MoreVert,
                    contentDescription = null,
                    Modifier.padding(start = 10.dp, end = 25.dp)
                        .clickable{
                            openColorTray = !openColorTray
                        },
                )
            }
        }
    ){
        Column(Modifier.padding(it).fillMaxSize()
            .background(viewmodel.addColor)
            .padding(horizontal = 20.dp, vertical = 40.dp)
        ) {
            if(openColorTray){
                Popup(onDismissRequest = {openColorTray =false}) {
                    Surface(
                        Modifier.padding(8.dp)
                            .shadow(8.dp, RoundedCornerShape(20.dp)),
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        ColorPicker(viewmodel)
                    }
                }
            }
            OutlinedTextField(
                value =  title,
                onValueChange = {
                    title = it
                },
                Modifier,
                placeholder = {
                    Text("Title", Modifier, fontWeight = FontWeight.W600,
                        fontSize = 48.sp)
                },
                textStyle = TextStyle(fontSize = 48.sp, fontWeight = FontWeight.W600),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                )
            )
            OutlinedTextField(
                value =  subTitle,
                onValueChange = {
                    subTitle = it
                },
                Modifier.padding(top =30.dp),
                placeholder = {
                    Text("Type something...", Modifier, fontWeight = FontWeight.W400,
                        fontSize = 23.sp)
                },
                textStyle = TextStyle(fontSize = 23.sp, fontWeight = FontWeight.W400),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                )
            )


        }
    }
}
@Composable
fun ColorBox(color: Color, onClick:()->Unit){
    Box(Modifier.size(40.dp).clip(CircleShape).background(color).clickable{
        onClick()
    })
}

@Composable
fun ColorPicker(viewmodel: MainViewModel) {
    Column(
        Modifier
            .fillMaxWidth().wrapContentHeight()
            .background(Color(0xfff1f1f))
            .padding(horizontal = 50.dp, vertical = 30.dp)
    ) {
        Text("Select Color", Modifier.padding(bottom = 20.dp), fontWeight = FontWeight.W600)
        Row (Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
            ColorBox(Color.White){
                viewmodel.addColor = Color.White
            }
            ColorBox(red){
                viewmodel.addColor = red
            }
            ColorBox(orange){
                viewmodel.addColor = orange
            }
            ColorBox(yellow){
                viewmodel.addColor = yellow
            }
            ColorBox(blue){
                viewmodel.addColor = blue
            }
        }
        Row (Modifier.fillMaxWidth().padding(top = 20.dp), horizontalArrangement = Arrangement.SpaceBetween){
            ColorBox(green){
                viewmodel.addColor = green
            }
            ColorBox(pink){
                viewmodel.addColor = pink
            }
            ColorBox(violet){
                viewmodel.addColor = violet
            }
            ColorBox(dark_violet){
                viewmodel.addColor = dark_violet
            }
            ColorBox(greyDark){
                viewmodel.addColor = greyDark
            }
        }
        Row (Modifier.fillMaxWidth().padding(top = 20.dp), horizontalArrangement = Arrangement.spacedBy(20.dp)){
            ColorBox(rose){
                viewmodel.addColor = rose
            }
            ColorBox(greyWhite){
                viewmodel.addColor = greyWhite
            }

        }
    }
}







