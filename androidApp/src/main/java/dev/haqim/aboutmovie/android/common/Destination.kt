package dev.haqim.aboutmovie.android.common

import androidx.navigation.NavType
import androidx.navigation.navArgument

interface Destination {
    val title: String
    val route: String
    val routeWithArgs: String
}

object Home: Destination{
    override val title: String
        get() = "Movies"
    override val route: String
        get() = "home"
    override val routeWithArgs: String
        get() = route
}

object Detail: Destination{
    override val title: String
        get() = "Movie Detail"
    override val route: String
        get() = "detail"
    override val routeWithArgs: String
        get() = "$route/{movieId}"
    
    val args = listOf(
        navArgument("movieId"){
            type = NavType.IntType
        }
    )
}

val movieDestinations = listOf(
    Home, Detail
)