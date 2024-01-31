package dev.haqim.aboutmovie.android

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.haqim.aboutmovie.android.common.Detail
import dev.haqim.aboutmovie.android.common.Home
import dev.haqim.aboutmovie.android.common.MovieAppBar
import dev.haqim.aboutmovie.android.common.movieDestinations
import dev.haqim.aboutmovie.android.detail.DetailScreen
import dev.haqim.aboutmovie.android.detail.DetailViewModel
import dev.haqim.aboutmovie.android.home.HomeScreen
import dev.haqim.aboutmovie.android.home.HomeViewModel
import dev.haqim.aboutmovie.domain.usecase.GetMovieUseCase
import org.koin.androidx.compose.get
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun MovieApp() {
    val navController = rememberNavController()
    val systemUiController = rememberSystemUiController()
    val scaffoldState = rememberScaffoldState()
    
    val isSystemDark = isSystemInDarkTheme()
    val statusBarColor = if (isSystemDark) MaterialTheme.colors.primaryVariant else Color.Transparent
    SideEffect {
        systemUiController.setStatusBarColor(statusBarColor, darkIcons = isSystemDark)
    }
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = movieDestinations.find { 
        backStackEntry?.destination?.route == it.route || backStackEntry?.destination?.route == it.routeWithArgs
    } ?: Home
    
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            MovieAppBar(currentScreen = currentScreen, ableToNavigateBack = navController.previousBackStackEntry != null) {
                navController.navigateUp()
            }
        }
    ) {innerPadding ->
        NavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
            startDestination = Home.routeWithArgs
        ){
            composable(Home.routeWithArgs){
                val homeViewModel: HomeViewModel = koinViewModel()
                HomeScreen(
                    uiState = homeViewModel.uiState.collectAsState().value, 
                    loadNextMovies = {
                       homeViewModel.loadMovies(it)
                    }, 
                    navigateToDetail = {
                        navController.navigate(
                            "${Detail.route}/${it.id}"
                        )
                    }
                )
            }

            composable(Detail.routeWithArgs){
                val movieId = it.arguments?.getString("movieId") ?: "0"
                val viewModel: DetailViewModel = koinViewModel(
                    parameters = { 
                        parametersOf(movieId.toInt()) 
                    }
                )
                DetailScreen(
                    uiState = viewModel.uiState.collectAsState().value,
                )
            }
        }
    }
    
}