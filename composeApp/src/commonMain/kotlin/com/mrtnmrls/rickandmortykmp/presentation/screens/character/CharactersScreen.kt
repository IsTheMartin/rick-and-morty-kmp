package com.mrtnmrls.rickandmortykmp.presentation.screens.character

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.mrtnmrls.rickandmortykmp.domain.model.Character
import com.mrtnmrls.rickandmortykmp.presentation.navigation.LocalNavController
import com.mrtnmrls.rickandmortykmp.presentation.navigation.Screen
import com.mrtnmrls.rickandmortykmp.presentation.navigation.Screen.*
import com.mrtnmrls.rickandmortykmp.presentation.screens.character.preview.CharacterStateParameterProvider
import com.mrtnmrls.rickandmortykmp.presentation.utils.statusColor
import com.mrtnmrls.rickandmortykmp.presentation.viewmodels.character.CharacterSideEffect
import com.mrtnmrls.rickandmortykmp.presentation.viewmodels.character.CharacterState
import com.mrtnmrls.rickandmortykmp.presentation.viewmodels.character.CharacterViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun CharactersScreen(
    showSnackBar: (String) -> Unit
) {
    val viewModel = koinViewModel<CharacterViewModel>()
    val state = viewModel.container.stateFlow.collectAsStateWithLifecycle().value
    val navController = LocalNavController.current

    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is CharacterSideEffect.NavigateToCharacterDetail -> {
                navController.navigate(CharactersDetail(sideEffect.id))
            }

            is CharacterSideEffect.ShowSnackBar -> {
                showSnackBar(sideEffect.message)
            }
        }
    }

    CharacterContent(
        state = state,
        onCharacterClick = viewModel::onCharacterClicked,
        onLoadNextPage = viewModel::loadNextPage
    )
}

@Composable
private fun CharacterContent(
    state: CharacterState,
    onCharacterClick: (Int) -> Unit,
    onLoadNextPage: () -> Unit
) {
    val listState = rememberLazyListState()
    val shouldLoadMore = remember {
        derivedStateOf {
            val lastVisibleItem =
                listState.layoutInfo.visibleItemsInfo.lastOrNull() ?: return@derivedStateOf false

            lastVisibleItem.index >= listState.layoutInfo.totalItemsCount - 3
        }
    }

    LaunchedEffect(shouldLoadMore.value) {
        if (shouldLoadMore.value) {
            onLoadNextPage()
        }
    }

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
        if (state.characters.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                state = listState,
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    items = state.characters,
                    key = { it.id }
                ) { character ->
                    CharacterItem(
                        character = character,
                        onClick = onCharacterClick
                    )
                }
                if (state.isLoadingNextPage) {
                    item {
                        Box(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CharacterItem(
    character: Character,
    onClick: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable {
                onClick(character.id)
            },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                model = character.image,
                contentDescription = character.name,
                modifier = Modifier.width(100.dp)
                    .clip(androidx.compose.foundation.shape.RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.weight(1f)
                    .padding(start = 8.dp)
            ) {
                Text(
                    text = character.name,
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                )

                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(character.statusColor())
                    )
                    Text(
                        text = "${character.status} - ${character.species}",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = Color.DarkGray
                        ),
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }

                Text(
                    text = "Last known location",
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(top = 8.dp)
                )

                Text(
                    text = character.location,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onSurface,
                    ),
                    maxLines = 1
                )
            }
        }
    }
}

@Preview
@Composable
private fun CharacterContentPreview(
    @PreviewParameter(CharacterStateParameterProvider::class) state: CharacterState,
) {
    MaterialTheme {
        CharacterContent(
            state = state,
            onCharacterClick = {},
            onLoadNextPage = {}
        )
    }
}