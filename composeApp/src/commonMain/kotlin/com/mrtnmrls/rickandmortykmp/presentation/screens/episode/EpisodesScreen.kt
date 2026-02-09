package com.mrtnmrls.rickandmortykmp.presentation.screens.episode

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Surface
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
import com.mrtnmrls.rickandmortykmp.domain.model.Episode
import com.mrtnmrls.rickandmortykmp.presentation.screens.episode.preview.EpisodeStateParameterProvider
import com.mrtnmrls.rickandmortykmp.presentation.viewmodels.episode.EpisodeState
import com.mrtnmrls.rickandmortykmp.presentation.viewmodels.episode.EpisodeViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun EpisodesScreen() {
    val viewModel = koinViewModel<EpisodeViewModel>()
    val state = viewModel.container.stateFlow.collectAsStateWithLifecycle().value

    EpisodeContent(state)
}

@Composable
private fun EpisodeContent(state: EpisodeState) {
    Box(
        modifier = Modifier.fillMaxSize()
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

        if (state.episodes.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                state.episodePerSeason.forEach { (season, episodes) ->
                    item {
                        val seasonHeader = when(season) {
                            "S01" -> "Season 1"
                            "S02" -> "Season 2"
                            "S03" -> "Season 3"
                            "S04" -> "Season 4"
                            "S05" -> "Season 5"
                            "S06" -> "Season 6"
                            else -> season
                        }

                        Text(
                            text = seasonHeader,
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                    items(items = episodes, key = { it.id }) { episode ->
                        EpisodeItem(episode)
                    }
                }
            }
        }
    }
}

@Composable
private fun EpisodeItem(episode: Episode) {
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
            modifier = Modifier.fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = episode.name,
                style = MaterialTheme.typography.titleMedium.copy(
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.SemiBold
                        )
                    ) {
                        append("Episode: ")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = Color.DarkGray,
                        )
                    ) {
                        append(episode.episode)
                    }
                }

            )
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.SemiBold
                        )
                    ) {
                        append("Air date: ")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = Color.DarkGray,
                        )
                    ) {
                        append(episode.airDate)
                    }
                }
            )
        }
    }
}

@Preview
@Composable
private fun EpisodeContentPreview(
    @PreviewParameter(EpisodeStateParameterProvider::class) state: EpisodeState
) {
    MaterialTheme {
        Surface {
            EpisodeContent(state)
        }
    }
}