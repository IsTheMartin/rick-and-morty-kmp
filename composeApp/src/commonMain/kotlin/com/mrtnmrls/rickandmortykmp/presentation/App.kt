package com.mrtnmrls.rickandmortykmp.presentation

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import coil3.network.ktor3.KtorNetworkFetcherFactory
import com.mrtnmrls.rickandmortykmp.presentation.navigation.LocalNavController
import com.mrtnmrls.rickandmortykmp.presentation.navigation.Screen
import com.mrtnmrls.rickandmortykmp.presentation.screens.characterdetail.CharacterDetailScreen
import com.mrtnmrls.rickandmortykmp.presentation.screens.home.HomeScreen

@Composable
fun App() {
    MaterialTheme {
        setSingletonImageLoaderFactory { context ->
            ImageLoader
                .Builder(context)
                .components {
                    add(KtorNetworkFetcherFactory())
                }.build()
        }
        val navController = rememberNavController()
        CompositionLocalProvider(LocalNavController provides navController) {
            NavHost(
                navController = navController,
                startDestination = Screen.Home,
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
