package com.example.movie_app.screens.home.homes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movie_app.models.Movie
import com.example.movie_app.models.getMovies
import com.example.movie_app.navigation.MovieScreen
import com.example.movie_app.widgets.MovieRow

//import com.example.movie_app.navigation.MovieScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen ( navController: NavController ) {

    Scaffold (

        modifier = Modifier
            .fillMaxSize(),
        topBar = {

            TopAppBar(

                colors = TopAppBarDefaults.topAppBarColors(

                    containerColor = Color.DarkGray

                ),
                title = {

                    Text( text = "Movies!!" )

                }

            )

        },

        ) { paddingValues -> // Use paddingValues to avoid overlap

        Box(modifier = Modifier.padding(paddingValues)) {

            MainContent( navController = navController )

        }

    }

}


@Composable
fun MainContent (

    navController: NavController,
    movieList: List<Movie> = getMovies()

) {

    Column (

        modifier = Modifier
            .padding( 10.dp )

    ) {

        LazyColumn {

            items(

                items = movieList

            ) {
                movieToPass ->

                MovieRow( movie = movieToPass ) { movie ->

                    navController.navigate(

                        route = MovieScreen.DetailsScreen.route+"/$movie"

                    )

                }

            }

        }

    }

}

