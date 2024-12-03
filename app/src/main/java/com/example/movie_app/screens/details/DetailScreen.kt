package com.example.movie_app.screens.details

import android.provider.MediaStore.Video
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import com.example.movie_app.models.getMovies
import com.example.movie_app.navigation.MovieScreen
import com.example.movie_app.widgets.MovieRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen (

    navController: NavController,
    movieId: String? = ""

) {

    val newMovieList = getMovies().filter {

        movie ->
        movie.id == movieId

    }

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

                },
                navigationIcon = {

                    IconButton(

                        onClick = {

                            navController.popBackStack()
                        },
                        colors = IconButtonDefaults.iconButtonColors(

                            contentColor = Color.Blue // Icon color

                        )

                    ) {

                        Icon(

                            imageVector = Icons.AutoMirrored.Filled.ArrowBack, // Correct ArrowBack icon
                            contentDescription = "Back Arrow", // Accessibility description
                            tint = Color.White // Ensure the icon has proper color

                        )
                    }

                }

            )

        },

        ) { paddingValues -> // Use paddingValues to avoid overlap

        Surface(

            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth()
                .fillMaxHeight(),
//            color = Color.Cyan

            ) {

            Column (

                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top

            ) {

                MovieRow(

                    movie = newMovieList.first()

                )

                Spacer( modifier = Modifier.height( 20.dp ) )
                HorizontalDivider(  )
                Text( modifier = Modifier.padding( 2.dp ) , text = "Screenshots"  )

                LazyRow {

                    items(

                        newMovieList[0].images

                    ) {

                        image ->
                        Card (

                            modifier = Modifier.padding( 12.dp ).size( 200.dp ),
                            elevation = CardDefaults.cardElevation( 5.dp ),
//                            colors = CardDefaults.cardColors( containerColor = Color.Green )


                        ) {

                            Box(
                                modifier = Modifier.fillMaxSize(), // Ensure Box takes full size of the Card
                                contentAlignment = Alignment.Center // Center the content inside the Box
                            ) {
                                Image(
                                    painter = rememberAsyncImagePainter(
                                        model = image,
                                        transform = AsyncImagePainter.DefaultTransform
                                    ),
                                    contentDescription = null,
                                    contentScale = ContentScale.Fit // Keeps the image scaled proportionally
                                )
                            }

                        }

                    }

                }

                Button (

                    onClick = {

                        navController.navigate(

                            route = MovieScreen.SecondScreen.route+"/${newMovieList[0].id}"

                        )

                    }

                ) {

                    Text( text = "Download!!" )

                }

            }

        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondScreen (

    navController: NavController,
    movieId: String?

) {

    val newMovieList = getMovies().filter {

        it.id == movieId

    }

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

                },
                navigationIcon = {

                    IconButton(

                        onClick = {

                            navController.popBackStack()
                        },
                        colors = IconButtonDefaults.iconButtonColors(

                            contentColor = Color.Blue // Icon color

                        )

                    ) {

                        Icon(

                            imageVector = Icons.AutoMirrored.Filled.ArrowBack, // Correct ArrowBack icon
                            contentDescription = "Back Arrow", // Accessibility description
                            tint = Color.White // Ensure the icon has proper color

                        )

                    }

                }

            )

        },

        ) { paddingValues -> // Use paddingValues to avoid overlap

        Surface(

            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),

            ) {

            Column (

                modifier = Modifier
                    .padding( 10.dp ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top

            ) {

                Text ( text = newMovieList[0].title , modifier = Modifier , fontSize = 40.sp)
                HorizontalDivider( modifier = Modifier.padding( vertical = 10.dp ) )

                    Surface (

                        modifier = Modifier.fillMaxWidth().padding( 10.dp ),
                        shape = RectangleShape,
                        color = Color.Green,

                    ) {

                        Box(

                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center

                        ) {

                            AndroidView(
                                modifier = Modifier.fillMaxSize(),
                                factory = { context ->
                                    WebView(context).apply {
                                        webViewClient = WebViewClient()
                                        settings.javaScriptEnabled = true
                                        settings.loadWithOverviewMode = true
                                        settings.useWideViewPort = true
                                        settings.cacheMode = WebSettings.LOAD_NO_CACHE
                                        loadUrl("https://www.youtube.com")
                                    }
                                }
                            )

                        }

                    }

            }

        }

    }

}
