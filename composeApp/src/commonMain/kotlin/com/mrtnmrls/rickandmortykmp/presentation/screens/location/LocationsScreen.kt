package com.mrtnmrls.rickandmortykmp.presentation.screens.location

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mrtnmrls.rickandmortykmp.domain.model.Location
import com.mrtnmrls.rickandmortykmp.presentation.screens.location.preview.LocationStateParameterProvider
import com.mrtnmrls.rickandmortykmp.presentation.viewmodels.location.LocationState
import com.mrtnmrls.rickandmortykmp.presentation.viewmodels.location.LocationViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LocationsScreen() {
    val viewModel = koinViewModel<LocationViewModel>()
    val state = viewModel.container.stateFlow.collectAsStateWithLifecycle().value

    LocationContent(state)
}

@Composable
private fun LocationContent(state: LocationState) {
    Box(
        modifier = Modifier.fillMaxSize()
            .background(Color.White),
    ) {
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }

        state.errorMessage?.let { message ->
            Text(
                modifier = Modifier.fillMaxWidth()
                    .padding(12.dp)
                    .align(Alignment.Center),
                text = message
            )
        }

        if (state.locations.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    items = state.locations,
                    key = { it.id }
                ) { character ->
                    LocationItem(character)
                }
            }
        }
    }
}

@Composable
fun LocationItem(location: Location) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = location.name,
                style = MaterialTheme.typography.titleMedium.copy(
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            )

            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.SemiBold
                        )) {
                            append("Type: ")
                        }
                        withStyle(style = SpanStyle(
                            color = Color.DarkGray,
                        )) {
                            append(location.type)
                        }
                    }
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Dimension: ",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal
                    )
                )
                Text(
                    text = location.dimension,
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = Color.DarkGray,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal
                    )
                )
            }
        }
    }
}

@Preview
@Composable
private fun LocationsScreenPreview(
    @PreviewParameter(LocationStateParameterProvider::class) state: LocationState
) {
    MaterialTheme {
        LocationContent(state)
    }
}