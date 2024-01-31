package dev.haqim.aboutmovie.android.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.haqim.aboutmovie.android.R
import dev.haqim.aboutmovie.domain.model.Movie
import java.time.format.TextStyle

@Composable
fun MovieItemView(
    modifier: Modifier = Modifier,
    movie: Movie,
    onClick: (Movie) -> Unit,
) {
    Card(
        modifier = modifier
            .height(220.dp)
            .clickable {
                onClick(movie)
            },
        shape = RoundedCornerShape(8.dp)
    ) {
        Column {
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = movie.imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Inside,
                    error = painterResource(id = R.drawable.ic_error),
                    placeholder = painterResource(id = R.drawable.placeholder_img),
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(bottomStart = 2.dp, bottomEnd = 2.dp))
                )

//                //play button
//                Surface(
//                    color = Color.Black.copy(alpha = 0.6f),
//                    modifier = Modifier
//                        .size(50.dp),
//                    shape = CircleShape
//                ) {
//                    Image(
//                        painter = painterResource(id = R.drawable.play_button),
//                        contentDescription = null,
//                        modifier = Modifier.align(Alignment.Center)
//                    )
//
//                }
            }

            Column(
                modifier = Modifier
                    .padding(10.dp)
            ) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = modifier.height(4.dp))
                Text(
                    movie.releaseDate,
                    style = MaterialTheme.typography.caption
                )
            }

        }
    }

}

@Preview
@Composable
fun MovieItemView_Preview() {
    MovieItemView(
        movie = Movie(
            505642,
            "Black Panther: Wakanda Forever",
            "Queen Ramonda, Shuri, M’Baku, Okoye and the Dora Milaje fight to protect their nation from intervening world powers in the wake of King T’Challa’s death.  As the Wakandans strive to embrace their next chapter, the heroes must band together with the help of War Dog Nakia and Everett Ross and forge a new path for the kingdom of Wakanda.",
            "/sv1xJUazXeYqALzczSZ3O6nkH75.jpg",
            "2022-11-09"
        ), onClick = {})
}