package com.mrtnmrls.rickandmortykmp.presentation.screens.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mrtnmrls.rickandmortykmp.presentation.model.NavigationBarItemModel
import com.mrtnmrls.rickandmortykmp.presentation.navigation.Screen
import com.mrtnmrls.rickandmortykmp.presentation.screens.character.CharactersScreen
import com.mrtnmrls.rickandmortykmp.presentation.screens.episode.EpisodesScreen
import com.mrtnmrls.rickandmortykmp.presentation.screens.location.LocationsScreen
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import rick_and_morty_kmp.composeapp.generated.resources.Res
import rick_and_morty_kmp.composeapp.generated.resources.home_screen_characters
import rick_and_morty_kmp.composeapp.generated.resources.home_screen_episodes
import rick_and_morty_kmp.composeapp.generated.resources.home_screen_locations

@Composable
fun HomeScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val items = listOf(
        NavigationBarItemModel(
            icon = Icons.Default.Person,
            label = stringResource(Res.string.home_screen_characters),
            route = Screen.Characters
        ),
        NavigationBarItemModel(
            icon = Icons.Default.LocationOn,
            label = stringResource(Res.string.home_screen_locations),
            route = Screen.Locations
        ),
        NavigationBarItemModel(
            icon = Icons.Default.Movie,
            label = stringResource(Res.string.home_screen_episodes),
            route = Screen.Episodes
        )
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White,
        snackbarHost = {
            SnackbarHost(snackBarHostState)
        },
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
                CharactersScreen(
                    showSnackBar = { message ->
                        scope.launch { snackBarHostState.showSnackbar(message = message) }
                    }
                )
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