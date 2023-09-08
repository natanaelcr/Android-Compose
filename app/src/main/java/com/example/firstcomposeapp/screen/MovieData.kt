package com.example.firstcomposeapp.screen

import android.annotation.SuppressLint
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.firstcomposeapp.API.RetrofitInstance
import com.example.firstcomposeapp.Routes
import com.example.firstcomposeapp.component.CustomTopAppBar
import com.example.firstcomposeapp.model.Movie
import com.example.firstcomposeapp.model.MovieResponse
import com.example.firstcomposeapp.model.Upcoming
import com.example.firstcomposeapp.model.UpcomingResponse
import com.example.firstcomposeapp.ui.theme.FirstComposeAppTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeView(navController: NavHostController) {

    Scaffold(
        topBar = {
            CustomTopAppBar(navController, "HomeView", true)
        },
        content = {
            Box(modifier = Modifier.fillMaxSize()) {
                HomePage(navController)
            }
        }
    )
}

@Composable
fun MovieItem(movie: Movie, modifier: Modifier = Modifier,navController: NavHostController) {

    val painter = rememberImagePainter(
        data = "https://image.tmdb.org/t/p/w500${movie.poster_path}",
        builder = {
            fadeIn(animationSpec = tween(durationMillis = 500))
        }
    )

    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .padding(all = 8.dp)
            .width(150.dp)
            .height(250.dp)
            .clickable {
                // Navigate to the detail screen with the movie's itemId
                navController.navigate("${Routes.Details.route}/${movie.id}")
            }

    )
    {
        Image(
            painter = painter,
            contentDescription = "Movie Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )

    }
}

@Composable
fun PopularMoviesListItem(upcoming: Upcoming) {
    val painter = rememberImagePainter(
        data = "https://image.tmdb.org/t/p/w500${upcoming.posterPath}",
        builder = {
            fadeIn(animationSpec = tween(durationMillis = 500))
        }
    )

    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(all = 16.dp)
            .fillMaxWidth()
            .height(700.dp)
    ) {
        Column {
            Image(
                painter = painter,
                contentDescription = "Movie Image",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .height(550.dp)
                    .fillMaxWidth(),
                alignment = Alignment.TopCenter
            )
            Text(
                text = upcoming.title ?: "No title available",
                modifier = Modifier.padding(all = 8.dp),
                style = MaterialTheme.typography.h6
            )
            Text(
                text = upcoming.overview ?: "No overview available",
                modifier = Modifier.padding(all = 8.dp),
                maxLines = 5,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.caption
            )
        }
    }
}


@Composable
fun HomePage(navController: NavHostController) {
    val movies = remember { mutableStateOf(listOf<Movie>()) }
    val upcominglist = remember { mutableStateOf(listOf<Upcoming>())}
    val isLoading = remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        RetrofitInstance.apiService.getMovies("en-US", 1).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    movies.value = response.body()?.results ?: listOf()
                    isLoading.value = false
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                isLoading.value = false
            }
        })
        RetrofitInstance.apiService.getUpcoming("en-US", 1).enqueue(object : Callback<UpcomingResponse> {
            override fun onResponse(call: Call<UpcomingResponse>, response: Response<UpcomingResponse>) {
                if (response.isSuccessful) {
                    upcominglist.value = response.body()?.results ?: listOf()
                    isLoading.value = false
                }
            }

            override fun onFailure(call: Call<UpcomingResponse>, t: Throwable) {
                isLoading.value = false
            }
        })
    }

    LazyColumn {
        item {
            Text(
                text = "Now Playing",
                modifier = Modifier.padding(all = 12.dp),
                style = MaterialTheme.typography.h5
            )
        }

        if (isLoading.value) {
            item {
                // Show a loading indicator
            }
        } else {
            item {
                LazyRow {
                    items(movies.value) { movie ->
                        MovieItem(movie = movie, navController = navController)
                    }
                }
            }

            item {
                Text(
                    text = "Upcoming List",
                    modifier = Modifier.padding(all = 12.dp),
                    style = MaterialTheme.typography.h5
                )
            }

            items(upcominglist.value) { upcoming ->
                PopularMoviesListItem(upcoming = upcoming)
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun HomePagePreview() {
//    HomePage()
}