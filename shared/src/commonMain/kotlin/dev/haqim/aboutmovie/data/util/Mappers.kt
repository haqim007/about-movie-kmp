package dev.haqim.aboutmovie.data.util

import dev.haqim.aboutmovie.data.remote.MovieResponse
import dev.haqim.aboutmovie.domain.model.Movie

internal fun MovieResponse.toMovie() = Movie(
    id,
    title,
    imageUrl = getImageUrl(this.posterImage),
    releaseDate = releaseDate,
    description = overview
)

private fun getImageUrl(posterImage: String) = 
    "https://image.tmdb.org/t/p/w500/$posterImage"