package com.mrtnmrls.rickandmortykmp

import androidx.compose.ui.window.ComposeUIViewController
import com.mrtnmrls.rickandmortykmp.di.initKoin
import com.mrtnmrls.rickandmortykmp.presentation.App

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin {  }
    }
) { App() }