//package com.example.movie_app.navigation
//
//enum class MovieScreens{
//
//    HomeScreen,
//    DetailsScreen,
//    SecondScreen;
//    companion object {
//
//        fun fromRoute ( route: String? ): MovieScreens = when ( route?.substringBefore( '/' ) ) {
//
//            HomeScreen.name -> HomeScreen
//            DetailsScreen.name -> DetailsScreen
//            SecondScreen.name -> SecondScreen
//            null -> HomeScreen
//            else -> throw IllegalArgumentException ( "Route $route is not recognise" )
//
//        }
//
//    }
//
//}

package com.example.movie_app.navigation

sealed class MovieScreen(val route: String) {

    object HomeScreen : MovieScreen("home_screen")
    object DetailsScreen : MovieScreen("details_screen")
    object SecondScreen : MovieScreen("second_screen")

    companion object {
        fun fromRoute(route: String?): MovieScreen = when (route?.substringBefore('/')) {
            HomeScreen.route -> HomeScreen
            DetailsScreen.route -> DetailsScreen
            SecondScreen.route -> SecondScreen
            null -> HomeScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }
    }
}