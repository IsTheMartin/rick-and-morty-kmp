package com.mrtnmrls.rickandmortykmp.presentation.navigation

import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavController

val LocalNavController = compositionLocalOf<NavController> {
    (error("No nav controller found"))
}
