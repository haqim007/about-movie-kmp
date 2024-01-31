package dev.haqim.aboutmovie.data.remote

@kotlinx.serialization.Serializable
internal data class MoviesResponse(
    val results: List<MovieResponse>
)
