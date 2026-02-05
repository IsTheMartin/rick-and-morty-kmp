package com.mrtnmrls.rickandmortykmp.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mrtnmrls.rickandmortykmp.presentation.viewmodels.CharacterViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CharactersScreen() {
    val viewModel = koinViewModel<CharacterViewModel>()
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Personajes")
    }
}