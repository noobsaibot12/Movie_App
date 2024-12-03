package com.example.movie_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movie_app.navigation.MovieNavigation
import com.example.movie_app.ui.theme.Movie_AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            MyApp {

                MovieNavigation()

            }

        }
    }
}

@Composable
fun MyApp ( content: @Composable () -> Unit ) {

    Movie_AppTheme {

        content()

    }

}

@Composable
fun Demo () {

    Surface (

        modifier = Modifier
            .height( 100.dp )

    ) {

        Text ( text = "Hello" )

    }

}

//@Preview(showBackground = true)
@Composable
fun AppPreview() {

    MyApp {

        MovieNavigation()

    }

}