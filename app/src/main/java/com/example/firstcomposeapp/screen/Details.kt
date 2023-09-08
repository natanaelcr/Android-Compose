package com.example.firstcomposeapp.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.firstcomposeapp.API.RetrofitInstance
import com.example.firstcomposeapp.component.CustomTopAppBar
import com.example.firstcomposeapp.model.DetailResponse
import com.example.firstcomposeapp.model.Details
import com.example.firstcomposeapp.model.Genre
import com.example.firstcomposeapp.model.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Details(navController: NavHostController, movieId: Int) {
    Scaffold(
        topBar = {
            CustomTopAppBar(navController, "Details", true)
        },
        content = {
            Box(modifier = Modifier.fillMaxSize()) {
                DetailsView(navController, movieId = movieId)
            }
        }
    )
}

@Composable
fun GenresList(genres: List<Genre>) {
    Row(
        modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        for (genre in genres) {
            genre.name?.let { GenreChip(it) } // Use a custom Composable for genre chips
        }
    }
}

@Composable
fun GenreChip(genreName: String) {
    Box(
        modifier = Modifier.padding(4.dp),
        contentAlignment = Alignment.Center,
        content = {
            Surface(
                color = MaterialTheme.colors.primary,
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = genreName,
                    color = Color.White,
                    style = MaterialTheme.typography.caption,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                )
            }
        }
    )
}

@Composable
fun DetailsView(navController: NavHostController, movieId: Int) {
    val moviesDetails = remember { mutableStateOf<Details?>(null) }
    val isLoading = remember { mutableStateOf(true) }
//    val movieDetail = getMovieDetailById(movieId) // Replace this with actual fetching logic

    LaunchedEffect(Unit) {
        RetrofitInstance.apiService.getDetails(movieId).enqueue(object : Callback<Details> {
            override fun onResponse(call: Call<Details>, response: Response<Details>) {
                if (response.isSuccessful) {
//                    response.body().let {
//                        if (it!=null){
//                            moviesDetails.value = it
//                        }
//                    }
                    moviesDetails.value = response.body()
//                    moviesDetails.value = response.body()?.results ?: listOf() // Replace with the property you need
                    isLoading.value = false
//                    Log.i("Overview", response.body()?.overview.toString())
                }
            }
            override fun onFailure(call: Call<Details>, t: Throwable) {
                Log.e("Error kah maniez?", t.message.toString())
                isLoading.value = false
            }
        })
    }
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        content = {
            item{
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
    ) {
        Image(
            painter = rememberImagePainter(
                data = "https://image.tmdb.org/t/p/w500${moviesDetails.value?.posterPath}"
            ),
            contentDescription = "Movie Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
        )

        Text(
            text = "Release Date :" + moviesDetails.value?.releaseDate ?: "No title available",
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Left
        )

        Spacer(modifier = Modifier.height(12.dp))

//        GENRES
        if (!moviesDetails.value?.genres.isNullOrEmpty()) {
            GenresList(genres = moviesDetails.value?.genres ?: emptyList())
        }

        Spacer(modifier = Modifier.height(8.dp))

//        TITLE
        Text(
            text = moviesDetails.value?.title ?: "No title available",
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Left
        )

        Spacer(modifier = Modifier.height(8.dp))

//        OVERVIEW
        Text(
            text = moviesDetails.value?.overview ?: "No overview available",
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Justify
        )

        // Display other movie details here using Text components
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.navigateUp()
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Back to List")
            }
        }
    }
    })
}
