package dev.haqim.aboutmovie.android.detail

import android.widget.Button
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.haqim.aboutmovie.android.R

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    uiState: DetailScreenState
) {
    Box(
        contentAlignment = Alignment.Center
    ){
        uiState.movie?.let { 
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colors.background)
            ) {
                AsyncImage(
                    model = it.imageUrl, 
                    contentDescription = "movie poster",
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .height(320.dp),
                )
                
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(20.dp)
                ) {
                    Text(
                        text = it.title,
                        style = MaterialTheme.typography.h5,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    Button(
                        onClick = {},
                        modifier = Modifier
                            .height(46.dp)
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.Red
                        ),
                        elevation = ButtonDefaults.elevation(
                            defaultElevation = 0.dp
                        )
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.play_button),
                            contentDescription = null,
                            tint = Color.White
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "Start watching now!")
                    }
                    
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        "Released in ${it.releaseDate}".uppercase(), 
                        style = MaterialTheme.typography.overline
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        it.description,
                        style = MaterialTheme.typography.body2
                    )

                }
            }
        }
        
        if (uiState.loading){
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator(color = Color.Red)
            }
        }
        
        if (!uiState.loading && uiState.errorMessage != null){
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    uiState.errorMessage,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
    
}