package com.mrtnmrls.rickandmortykmp.presentation.utils

import androidx.compose.ui.graphics.Color
import com.mrtnmrls.rickandmortykmp.domain.model.Character

fun Character.statusColor(): Color =
    when (this.status.lowercase()) {
        "alive" -> Color.Green
        "dead" -> Color.Red
        else -> Color.Gray
    }
