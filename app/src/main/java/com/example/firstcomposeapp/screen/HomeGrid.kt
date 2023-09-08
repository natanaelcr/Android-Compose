package com.example.firstcomposeapp.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.firstcomposeapp.Routes
import com.example.firstcomposeapp.component.CustomTopAppBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeGrid(navController: NavHostController) {

    Scaffold(
        topBar = {
            CustomTopAppBar(navController, "HomeGrid", true)
        },
        content = {
            Box(modifier = Modifier.fillMaxSize()) {
                LazyVerticalGridDemo(navController)
            }
        }
    )

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LazyVerticalGridDemo(navController: NavHostController) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(
            start = 12.dp,
            top = 16.dp,
            end = 12.dp,
            bottom = 16.dp
        ),
        content = {
            items(100) {index ->
                Card(
                    backgroundColor = Color(0xFF03DAC5),
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    elevation = 8.dp,

                    onClick = {
                        navController.navigate("${Routes.Detail.route}/$index")}
                ) {
                    Text(
                        text = "$index",
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        })
}
//
//@Composable
//fun ScaffoldWithTopBarForgotPass(navController: NavHostController) {
//    Scaffold(
//        topBar = {
//            CustomTopAppBar(navController, "Forgot Password", true)
//        }, content = {
//            Column(
//                modifier = Modifier
//                    .fillMaxSize(),
//                verticalArrangement = Arrangement.Center,
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Text(
//                    text = "Forgot Password",
//                    fontSize = 30.sp,
//                    color = Color.Black
//                )
//            }
//        })
//}