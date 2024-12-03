package com.example.movie_app.navigation
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movie_app.screens.details.DetailScreen
import com.example.movie_app.screens.details.SecondScreen
import com.example.movie_app.screens.home.homes.HomeScreen

@Composable
fun MovieNavigation () {

    val navController = rememberNavController()

    NavHost(

        navController = navController,
        startDestination = MovieScreen.HomeScreen.route

    ) {

        composable(

            route = MovieScreen.HomeScreen.route

        ) {

            HomeScreen(

                navController = navController

            )

        }

        composable(

            route = MovieScreen.DetailsScreen.route+"/{movieId}",
            arguments = listOf(

                navArgument(

                    name = "movieId"

                ) {

                    type = NavType.StringType

                }

            )

        ) {

            backStackEntry ->

            DetailScreen(

                navController = navController,
                backStackEntry.arguments?.getString( "movieId" )

            )

        }

        composable(

            route = MovieScreen.SecondScreen.route+"/{movieId}",
            arguments = listOf(

                navArgument(

                    name = "movieId"

                ) {

                    type = NavType.StringType

                }

            )

        ) {

            SecondScreen(

                navController = navController,
                movieId = it.arguments?.getString( "movieId" )

            )

        }

    }

}