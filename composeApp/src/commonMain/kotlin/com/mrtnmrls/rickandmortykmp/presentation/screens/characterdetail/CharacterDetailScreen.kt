package com.mrtnmrls.rickandmortykmp.presentation.screens.characterdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Transgender
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
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
import com.mrtnmrls.rickandmortykmp.presentation.screens.character.preview.CharacterStateParameterProvider
import com.mrtnmrls.rickandmortykmp.presentation.utils.statusColor
import com.mrtnmrls.rickandmortykmp.presentation.viewmodels.characterdetail.CharacterDetailSideEffect
import com.mrtnmrls.rickandmortykmp.presentation.viewmodels.characterdetail.CharacterDetailState
import com.mrtnmrls.rickandmortykmp.presentation.viewmodels.characterdetail.CharacterDetailViewModel
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.orbitmvi.orbit.compose.collectSideEffect
import rick_and_morty_kmp.composeapp.generated.resources.Res
import rick_and_morty_kmp.composeapp.generated.resources.character_detail_screen_back
import rick_and_morty_kmp.composeapp.generated.resources.character_detail_screen_gender
import rick_and_morty_kmp.composeapp.generated.resources.character_detail_screen_location
import rick_and_morty_kmp.composeapp.generated.resources.character_detail_screen_species

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen() {
    val viewModel = koinViewModel<CharacterDetailViewModel>()
    val state = viewModel.container.stateFlow.collectAsStateWithLifecycle().value
    val navController = LocalNavController.current

    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            CharacterDetailSideEffect.NavigateToBack -> navController.popBackStack()
        }
    }

    CharacterDetailContent(
        state = state,
        onNavigateBack = viewModel::navigateBack
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CharacterDetailContent(
    state: CharacterDetailState,
    onNavigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            CharacterDetailTopBar(
                characterName = state.character?.name.orEmpty(),
                onNavigateBack = onNavigateBack
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.White)
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            state.error?.let { message ->
                Text(
                    modifier = Modifier.fillMaxWidth()
                        .padding(12.dp)
                        .align(Alignment.Center),
                    text = message
                )
            }

            state.character?.let { character ->
                CharacterDetail(character)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CharacterDetailTopBar(
    characterName: String,
    onNavigateBack: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = characterName,
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
            IconButton(
                onClick = onNavigateBack,
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = stringResource(Res.string.character_detail_screen_back)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White
        )
    )
}

@Composable
private fun CharacterDetail(character: Character) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
        ) {
            AsyncImage(
                model = character.image,
                contentDescription = character.name,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            )
                        )
                    )
                    .padding(24.dp)
            ) {
                Text(
                    text = character.name,
                    color = Color.White,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )

                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(14.dp)
                            .clip(CircleShape)
                            .background(character.statusColor())
                    )
                    Text(
                        text = "${character.status} - ${character.species}",
                        color = Color.White,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(start = 6.dp)
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            InfoCard(
                icon = Icons.Default.Transgender,
                title = stringResource(Res.string.character_detail_screen_gender),
                description = character.gender
            )

            InfoCard(
                icon = Icons.Default.Person,
                title = stringResource(Res.string.character_detail_screen_species),
                description = character.species
            )

            InfoCard(
                icon = Icons.Default.LocationOn,
                title = stringResource(Res.string.character_detail_screen_location),
                description = character.location
            )
        }
    }
}

@Composable
private fun InfoCard(
    icon: ImageVector,
    title: String,
    description: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF5F5F5)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(32.dp),
                imageVector = icon,
                contentDescription = title,
                tint = MaterialTheme.colorScheme.primary
            )
            Column(
                modifier = Modifier.weight(1f)
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = title,
                    color = Color.Gray,
                    fontSize = 14.sp
                )
                Text(
                    text = description,
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Preview
@Composable
private fun CharacterDetailScreenPreview(
    @PreviewParameter(CharacterStateParameterProvider::class) state: CharacterDetailState
) {
    MaterialTheme {
        Surface {
            CharacterDetailContent(
                state = state,
                onNavigateBack = {}
            )
        }
    }
}