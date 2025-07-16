package ati.su.horseraces.presentation.ui.compose.custom_components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ati.su.horseraces.domain.race.model.UiRaceResult

@Composable
fun RaceResultDialog(
    raceResult: UiRaceResult,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                "Результаты гонки",
                style = MaterialTheme.typography.headlineSmall
            )
        },
        text = {
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                // Блок с победителем
                item {
                    SectionTitle("🏆 Победитель")
                    Text(
                        text = "${raceResult.winnerHorseName} (${raceResult.winnerFinishTime})",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(Modifier.height(16.dp))
                }

                // Блок с таблицей результатов
                item {
                    SectionTitle("Таблица лидеров")
                }
                items(raceResult.participantsDetails) { participant ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${participant.finalPosition}. ${participant.horseName}",
                            modifier = Modifier.weight(1f),
                            fontWeight = if (participant.finalPosition == "1") FontWeight.Bold else FontWeight.Normal
                        )
                        Text(
                            text = participant.finishTime,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = if (participant.finalPosition == "1") FontWeight.Bold else FontWeight.Normal
                        )
                    }
                }

                // Блок с условиями гонки
                item {
                    Spacer(Modifier.height(16.dp))
                    SectionTitle("Условия гонки")
                    Text("Погода: ${raceResult.raceConditions.weatherConditionName}", style = MaterialTheme.typography.bodySmall)
                    Text("Трек: ${raceResult.raceConditions.trackConditionName}", style = MaterialTheme.typography.bodySmall)
                    Text("Навык жокея: ${raceResult.raceConditions.jockeySkill}", style = MaterialTheme.typography.bodySmall)
                    Spacer(Modifier.height(16.dp))
                }

                // Время проведения
                item {
                    Text(
                        text = "ID гонки: ${raceResult.id} | ${raceResult.timeStartRace} - ${raceResult.timeCompleteRace}",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Отлично")
            }
        }
    )
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