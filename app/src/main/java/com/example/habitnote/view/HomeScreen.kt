package com.example.habitnote.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.navigation.NavHostController
import com.example.habitnote.R
import com.example.habitnote.modal.BottomBarModal
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
fun HomeScreen(controller: NavHostController, viewmodel: MainViewModel) {
    var isGrid by remember { mutableStateOf(true) }
    var openColorTray by remember { mutableStateOf(false) }
    Scaffold(
        Modifier,
        topBar = {
            Row(
                Modifier.fillMaxWidth()
                    .background(Color.White)
                    .padding(horizontal = 20.dp)
                    .padding(vertical = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Notes",Modifier, fontWeight = FontWeight.W600,
                    fontSize = 36.sp)
                Spacer(Modifier.weight(1f))
                Row {
                    Image(
                        painter = painterResource(R.drawable.choose_color_icon),
                        contentDescription = null,
                        Modifier.clickable{
                            openColorTray = !openColorTray
                        }
                    )
                    Spacer(Modifier.width(20.dp))
                    Image(painter =
                        if(isGrid)
                        painterResource(R.drawable.grid_icon)
                        else painterResource(R.drawable.grid_off),
                        contentDescription = null,
                        Modifier.clickable{
                            isGrid  = !isGrid
                        }
                        )
                }
            }
        },
        bottomBar = {
            val data =listOf(
                BottomBarModal(R.drawable.notes_icon, "Notes"),
                BottomBarModal(R.drawable.help_icon, "Help"),
                BottomBarModal(R.drawable.person_icon, "Me")
            )
            Row (
                Modifier.fillMaxWidth().wrapContentHeight()
                    .background(greyWhite, RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                    .padding(horizontal = 20.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                data.forEach {
                    Column(
                        Modifier, horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(it.img),
                            contentDescription = null
                        )
                        Text(it.text)
                    }
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    controller.navigate("add")
                },
                Modifier,
                containerColor = Color(0xffFFB347),
                elevation = FloatingActionButtonDefaults.elevation(8.dp)
            ) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = null,
                    Modifier,
                    tint = Color.White
                )
            }
        }
    ) {
        Box(Modifier.padding(it).fillMaxSize()
            .background(Color.White).clickable{
                openColorTray = false
            }) {


            if (viewmodel.data.value.isEmpty()) {
                Box(
                    Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(R.drawable.home_icon),
                            contentDescription = null
                        )
                        Text(
                            "Create your first Note!",
                            Modifier.padding(top = 10.dp), fontSize = 20.sp
                        )
                    }
                }
            }
            if(viewmodel.data.value.isNotEmpty()){
                Column(
                    Modifier,
                ) {
                    if (!isGrid) {
                        LazyColumn(
                            Modifier.padding(horizontal = 20.dp),
                            verticalArrangement = Arrangement.spacedBy(25.dp)
                        ) {
                            itemsIndexed(viewmodel.data.value) { index, data->
                                ListItem(data, onClick = {
                                    controller.navigate("view/${it}")
                                },
                                    onEdit = {
                                        controller.navigate("edit/${it}")
                                    },
                                    onDelete = {
                                        viewmodel.deleteHabit(it)
                                    }
                                )
                            }
                        }
                    }
                    if (isGrid) {
                        LazyVerticalStaggeredGrid(
                            columns = StaggeredGridCells.Fixed(2),
                            Modifier.padding(horizontal = 20.dp),
                            horizontalArrangement = Arrangement.spacedBy(20.dp),
                            verticalItemSpacing = 20.dp
                        ) {
                            itemsIndexed(viewmodel.data.value) { index,data->
                                ListItem(data, onClick = {
                                    controller.navigate("view/${it}")
                                },
                                    onEdit = {
                                        controller.navigate("edit/${it}")
                                    },
                                    onDelete = {
                                        viewmodel.deleteHabit(it)
                                    }
                                )
                            }
                        }
                    }
                }
            }
            if(openColorTray) {
                Popup(onDismissRequest = {
                    openColorTray = false
                }) {
                    Surface(
                        Modifier.padding(8.dp)
                            .shadow(8.dp, RoundedCornerShape(20.dp)),
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        FilterScreen()
                    }
                }
            }
        }
    }
}
@Preview
@Composable
fun FilterScreen(){
    Column(Modifier.fillMaxWidth().padding(30.dp).clip(RoundedCornerShape(20.dp))) {
        Text("Filter by color",Modifier, fontSize = 24.sp)
        Row(
            Modifier.fillMaxWidth().padding(top = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                Modifier.size(width = 100.dp, height = 40.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.White)
                    .border(1.dp,Color.Black, RoundedCornerShape(20.dp))
            )
            ColorItem(red)
            ColorItem(orange)
        }
        Row(
            Modifier.fillMaxWidth().padding(top = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ColorItem(yellow)
          ColorItem(green)
          ColorItem(blue)

        }
        Row(
            Modifier.fillMaxWidth().padding(top = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ColorItem(pink)
          ColorItem(violet)
          ColorItem(dark_violet)

        }
        Row(
            Modifier.fillMaxWidth().padding(top = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ColorItem(rose)
          ColorItem(greyWhite)
          ColorItem(greyDark)

        }
    }
}
@Composable
fun ColorItem(color: Color){
    Box(
        Modifier.size(width = 100.dp, height = 40.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(color)
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListItem(data: Habit, onClick:(Int) ->Unit, onEdit:(Int)->Unit, onDelete:(Int)->Unit) {
    var showDropdown by remember{mutableStateOf(false)}
    Row(Modifier.fillMaxWidth()
        .background(data.color, RoundedCornerShape(16.dp))
        .padding(horizontal = 20.dp, vertical = 30.dp)
        .combinedClickable(
            onClick = {
                onClick(data.id)
            },
            onLongClick = {
                showDropdown = true
            }
        )
    ) {
        DropdownMenu(showDropdown, onDismissRequest = {showDropdown= false}) {
            DropdownMenuItem(text = {
                Text(
                    "Edit"
                )
                 },onClick={
                     onEdit(data.id)

            })
            DropdownMenuItem(
                text = { Text("Delete")},
                onClick={ onDelete(data.id) }
            )
        }
        Text(
            data.title,
            Modifier,
            fontSize = 24.sp
        )

    }
}
