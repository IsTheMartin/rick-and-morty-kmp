package com.mrtnmrls.rickandmortykmp.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import coil3.network.ktor3.KtorNetworkFetcherFactory
import com.mrtnmrls.rickandmortykmp.presentation.model.NavigationBarItemModel
import com.mrtnmrls.rickandmortykmp.presentation.navigation.LocalNavController
import com.mrtnmrls.rickandmortykmp.presentation.navigation.Screen
import com.mrtnmrls.rickandmortykmp.presentation.screens.character.CharactersScreen
import com.mrtnmrls.rickandmortykmp.presentation.screens.characterdetail.CharacterDetailScreen
import com.mrtnmrls.rickandmortykmp.presentation.screens.episode.EpisodesScreen
import com.mrtnmrls.rickandmortykmp.presentation.screens.home.HomeScreen
import com.mrtnmrls.rickandmortykmp.presentation.screens.location.LocationsScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
        setSingletonImageLoaderFactory { context ->
            ImageLoader.Builder(context)
                .components {
                    add(KtorNetworkFetcherFactory())
                }
                .build()
        }
        val navController = rememberNavController()
        CompositionLocalProvider(LocalNavController provides navController) {
            NavHost(
                navController = navController,
                startDestination = Screen.Home
            ) {
                composable<Screen.Home> {
                    HomeScreen()
                }

                composable<Screen.CharactersDetail> {
                    CharacterDetailScreen()
                }
            }
        }

    }
}