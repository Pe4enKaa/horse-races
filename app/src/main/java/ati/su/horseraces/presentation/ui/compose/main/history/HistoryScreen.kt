package ati.su.horseraces.presentation.ui.compose.main.history

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ati.su.horseraces.domain.common.core.UIComponent
import ati.su.horseraces.domain.race_result.model.RaceResult
import ati.su.horseraces.presentation.ui.compose.custom_components.DefaultScreenUI
import ati.su.horseraces.presentation.ui.compose.main.history.view_model.HistoryEvent
import ati.su.horseraces.presentation.ui.compose.main.history.view_model.HistoryState
import kotlinx.coroutines.flow.Flow
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun HistoryScreen(
    state: HistoryState,
    errors: Flow<UIComponent>,
    events: (HistoryEvent) -> Unit,
) {
    DefaultScreenUI(
        errors = errors,
        progressBarState = state.progressBarState,
        titleToolbar = "История",
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(state.raceResult) { raceResult ->
                RaceResultItem(raceResult = raceResult)
                Spacer(Modifier.height(8.dp))
            }
        }
    }
}
@Composable
private fun RaceResultItem(raceResult: RaceResult) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            val dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm", Locale.getDefault())
            val winnerHorse = raceResult.winnerHorseEnum
            val winnerFinishPosition = winnerHorse?.let {
                raceResult.participantsResults.firstOrNull { p -> p.horseEnum == it }?.finishTimeMs?.toInt()
            } ?: "N/A"

            Text(
                text = "Гонка от: ${raceResult.timeStartRace.format(dateFormat)}",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(Modifier.height(8.dp))
            Divider()
            Spacer(Modifier.height(8.dp))

            SectionTitle("🏆 Победитель")
            if (winnerHorse != null) {
                Text(
                    text = "${winnerHorse.displayName}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            } else {
                Text(
                    text = "Победитель не определен",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.error
                )
            }
            Spacer(Modifier.height(16.dp))

            if (raceResult.participantsResults.isNotEmpty()) {
                SectionTitle("Таблица лидеров")
                raceResult.participantsResults
                    .sortedBy { it.finalPosition }
                    .forEachIndexed { index, participant ->
                        val isWinner = participant.horseEnum == winnerHorse
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                        ) {
                            val rankText = "${participant.finalPosition}."
                            val horseNameText = participant.horseEnum.displayName
                            val distanceCoveredText = "${participant.finishTimeMs/1000} c"

                            Text(
                                text = "$rankText $horseNameText",
                                modifier = Modifier.weight(1f),
                                fontWeight = if (isWinner) FontWeight.Bold else FontWeight.Normal,
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Text(
                                text = distanceCoveredText,
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = if (isWinner) FontWeight.Bold else FontWeight.Normal
                            )
                        }
                    }
                Spacer(Modifier.height(16.dp))
            }


            SectionTitle("Условия гонки")
            Text("Погода: ${raceResult.raceConditions.weatherCondition.displayName}", style = MaterialTheme.typography.bodySmall)
            Text("Трек: ${raceResult.raceConditions.trackCondition.displayName}", style = MaterialTheme.typography.bodySmall)
            Text("Навык жокея: ${String.format(Locale.getDefault(), "%.1f", raceResult.raceConditions.jockeySkill)}", style = MaterialTheme.typography.bodySmall)
            Spacer(Modifier.height(16.dp))

            Text(
                text = "Начало: ${raceResult.timeStartRace.format(dateFormat)} | Конец: ${raceResult.timeCompleteRace.format(dateFormat)}",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = "ID гонки: ${raceResult.id}",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleSmall,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}