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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import coil3.network.ktor3.KtorNetworkFetcherFactory
import com.mrtnmrls.rickandmortykmp.presentation.model.NavigationBarItemModel
import com.mrtnmrls.rickandmortykmp.presentation.navigation.Screen
import com.mrtnmrls.rickandmortykmp.presentation.screens.CharactersScreen
import com.mrtnmrls.rickandmortykmp.presentation.screens.EpisodesScreen
import com.mrtnmrls.rickandmortykmp.presentation.screens.LocationsScreen

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
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        val items = listOf(
            NavigationBarItemModel(
                icon = Icons.Default.Person,
                label = "Personajes",
                route = Screen.Characters
            ),
            NavigationBarItemModel(
                icon = Icons.Default.LocationOn,
                label = "Lugares",
                route = Screen.Locations
            ),
            NavigationBarItemModel(
                icon = Icons.Default.Movie,
                label = "Episodios",
                route = Screen.Episodes
            )
        )

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = Color.White,
            bottomBar = {
                NavigationBar {
                    items.forEach { item ->
                        NavigationBarItem(
                            selected = currentDestination?.hierarchy?.any {
                                it.hasRoute(item.route::class)
                            } == true,
                            label = {
                                Text(text = item.label)
                            },
                            onClick = {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.label
                                )
                            }
                        )
                    }
                }
            }
        ) { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = Screen.Characters,
                modifier = Modifier.padding(paddingValues)
            ) {
                composable<Screen.Characters> {
                    CharactersScreen()
                }
                composable<Screen.Locations> {
                    LocationsScreen()
                }
                composable<Screen.Episodes> {
                    EpisodesScreen()
                }
            }
        }
    }
}