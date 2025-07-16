package ati.su.horseraces.presentation.ui.compose.main.race

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import ati.su.horseraces.domain.common.core.UIComponent
import ati.su.horseraces.domain.race.model.RaceParticipant
import ati.su.horseraces.domain.race.model.UiRaceResult
import ati.su.horseraces.presentation.ui.compose.custom_components.ChangeBarColors
import ati.su.horseraces.presentation.ui.compose.custom_components.DefaultScreenUI
import ati.su.horseraces.presentation.ui.compose.custom_components.RaceResultDialog
import ati.su.horseraces.presentation.ui.compose.main.LocalBottomBarState
import ati.su.horseraces.presentation.ui.compose.main.race.view_model.RaceEvent
import ati.su.horseraces.presentation.ui.compose.main.race.view_model.RaceState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow

@Composable
fun RaceScreen(
    state: RaceState,
    errors: Flow<UIComponent>,
    events: (RaceEvent) -> Unit,
) {
    val bottomBarState = LocalBottomBarState.current
    val longestRaceTime = remember(state.currentHorsesInRace) {
        state.currentHorsesInRace.maxOfOrNull { it.finishTimeMs } ?: 0L
    }

    LaunchedEffect(state.isRaceInProgress, state.showResultDialog) {
        if (state.isRaceInProgress) {
            bottomBarState.hide()
        } else {
            bottomBarState.show()
        }
    }

    LaunchedEffect(state.isRaceInProgress, longestRaceTime) {
        if (state.isRaceInProgress && longestRaceTime > 0) {
            delay(longestRaceTime)
            events(RaceEvent.RaceAnimationFinished)
        }
    }

    DefaultScreenUI(
        errors = errors,
        titleToolbar = "Скачки",
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(Modifier.height(16.dp))

            RaceTrack(
                participants = state.currentHorsesInRace,
                isRaceInProgress = state.isRaceInProgress
            )

            Spacer(Modifier.height(16.dp))

            Spacer(Modifier.weight(1f))

            Button(
                onClick = { events(RaceEvent.StartRaceClick) },
                enabled = !state.isRaceInProgress,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(vertical = 4.dp)
            ) {
                Text(
                    text = if (state.simulatedRaceResult == null) "СТАРТ" else "РЕСТАРТ",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )
            }
        }

        if (state.showResultDialog && state.simulatedRaceResult != null) {
            RaceResultDialog(
                raceResult = state.simulatedRaceResult,
                onDismiss = { events(RaceEvent.DismissResultDialog) }
            )
        }
    }
}

@Composable
fun RaceTrack(participants: List<RaceParticipant>, isRaceInProgress: Boolean) {
    val horseSize = 30.dp
    var trackWidthPx by remember { mutableStateOf(0f) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .onSizeChanged { trackWidthPx = it.width.toFloat() }
            .clip(RoundedCornerShape(12.dp))
            .background(Color.Gray.copy(alpha = 0.2f))
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        participants.forEach { participant ->
            HorseLane(
                participant = participant,
                isRaceInProgress = isRaceInProgress,
                trackWidthPx = trackWidthPx,
                horseSize = horseSize.value
            )
        }
    }
}

@Composable
private fun HorseLane(
    participant: RaceParticipant,
    isRaceInProgress: Boolean,
    trackWidthPx: Float,
    horseSize: Float
) {
    val density = LocalDensity.current
    val horseSizePx = with(density) { horseSize.dp.toPx() }
    val targetXOffset = (trackWidthPx - horseSizePx).coerceAtLeast(0f)

    val animatedXOffset = remember(participant.horseEnum.id) { Animatable(0f) }

    LaunchedEffect(isRaceInProgress, targetXOffset) {
        if (isRaceInProgress && participant.finishTimeMs > 0 && targetXOffset > 0) {
            animatedXOffset.animateTo(
                targetValue = targetXOffset,
                animationSpec = tween(
                    durationMillis = participant.finishTimeMs.toInt(),
                    easing = LinearEasing
                )
            )
        } else {
            val finalPosition = if (participant.finalPosition > 0 && targetXOffset > 0) targetXOffset else 0f
            animatedXOffset.snapTo(finalPosition)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(horseSize.dp)
            .background(Color.Black.copy(alpha = 0.2f), RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.CenterStart
    ) {
        Box(
            modifier = Modifier
                .height(horseSize.dp)
                .width(with(density) { animatedXOffset.value.toDp() + (horseSize / 2).dp })
                .background(participant.horseEnum.color.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
        )
        Box(
            modifier = Modifier
                .offset { IntOffset(x = animatedXOffset.value.toInt(), y = 0) }
                .size(horseSize.dp)
                .background(participant.horseEnum.color, CircleShape)
                .border(1.dp, Color.White.copy(alpha = 0.8f), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = participant.horseEnum.displayName.first().toString(),
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}