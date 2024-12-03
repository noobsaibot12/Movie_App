package com.example.movie_app.widgets

import android.view.animation.Transformation
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter
import coil3.compose.LocalPlatformContext
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import com.example.movie_app.R
import com.example.movie_app.models.Movie
import com.example.movie_app.models.getMovies

@Preview
@Composable
fun MovieRow (movie: Movie = getMovies()[4] , onItemClick: (String) -> Unit = {} ) {

    var expanded by remember {

        mutableStateOf( false )

    }

    Card (

        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape( corner = CornerSize( 10.dp ) ),
        elevation = CardDefaults.cardElevation( 10.dp ),
//        colors = CardDefaults.cardColors( containerColor = Color.Red )
        
    ) {

        Row (

            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()

        ) {

            Surface (

                modifier = Modifier
                    .padding(10.dp)
                    .size(100.dp),
                shape = RectangleShape,
                shadowElevation = 10.dp,

            ) {

//                Icon(
//
//                    modifier = Modifier
//                        .clickable {
//
//                            onItemClick( movie.title )
//
//                        },
//                    imageVector = Icons.Rounded.AccountBox,
//                    contentDescription = "Image"
//                )

                Image(

                    painter = rememberAsyncImagePainter(

                        model = movie.images[0],
                        transform = AsyncImagePainter.DefaultTransform

                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .clickable {

                            onItemClick(movie.id)

                        }
                        .clip(shape = RectangleShape),
                    contentScale = ContentScale.Fit

                )

//                AsyncImage(
//                    model = movie.images[0],
//                    contentDescription = null,
//                    modifier = Modifier
//                        .clickable {
//
//                            onItemClick( movie.title )
//
//                        }
//                )



            }

            Spacer ( modifier = Modifier.width( 5.dp ))

            Column (

                modifier = Modifier
                    .padding(2.dp),
                verticalArrangement = Arrangement.Center

            ) {

                Text(

                    modifier = Modifier
                        .padding( 2.dp ),
                    style = MaterialTheme.typography.headlineLarge,
                    text = movie.title

                )
                Text(

                    modifier = Modifier,
                    text = "Genre : ${ movie.genre }",
                    fontSize = 14.sp

                )
                Text( modifier = Modifier, text = "Year : ${movie.year}", fontSize = 12.sp )
                Text(

                    modifier = Modifier,
                    text = "IMDB Rating : ${ movie.rating }",
                    fontSize = 14.sp

                )

                AnimatedVisibility( expanded ) {

                    Column (



                    ) {

                        Text(

                            buildAnnotatedString {

                                withStyle(

                                    style = SpanStyle(

                                        fontSize = 12.sp

                                    )

                                ) {

                                    append( "Plot: " )

                                }

                                withStyle(

                                    style = SpanStyle(
                                        
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Light

                                    )

                                ) {

                                    append(movie.plot)

                                }

                            },
                            modifier = Modifier
                                .padding(vertical = 2.dp)
                            
                        )

                        HorizontalDivider(

                            modifier = Modifier
                                .padding( vertical = 2.dp )
                                .padding( end = 2.dp  ),
                            color = Color.White,
                            thickness = 0.5.dp

                        )

                        Text(

                            modifier = Modifier,
                            text = "Actors : ${ movie.actors }",
                            fontSize = 12.sp

                        )
                        Text( text = "Director : ${movie.director}", fontSize = 12.sp )

                    }

                }

                Row (

                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start

                ) {

                    Spacer( modifier = Modifier.width( 30.dp ) )

                    Icon(

                        imageVector = if ( expanded ) Icons.Filled.KeyboardArrowUp
                        else Icons.Filled.KeyboardArrowDown ,
                        contentDescription = "Arrow Dowm!!",
                        modifier = Modifier
                            .size(25.dp)
                            .clickable {

                                expanded = !expanded

                            }

                    )

                }

            }

        }

    }

}