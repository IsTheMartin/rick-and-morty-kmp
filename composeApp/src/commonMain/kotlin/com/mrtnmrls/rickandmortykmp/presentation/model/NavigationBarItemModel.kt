package com.mrtnmrls.rickandmortykmp.presentation.model

import androidx.compose.ui.graphics.vector.ImageVector
import com.mrtnmrls.rickandmortykmp.presentation.navigation.Screen

data class NavigationBarItemModel (
    val icon: ImageVector,
    val label: String,
    val route: Screen
)