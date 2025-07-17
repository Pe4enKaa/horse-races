package ati.su.horseraces.domain.common.utils.converts

import ati.su.horseraces.data.race_result.local.entity.RaceConditionsEmbedded
import ati.su.horseraces.data.race_result.local.entity.RaceParticipantEntity
import ati.su.horseraces.data.race_result.local.entity.RaceResultEntity
import ati.su.horseraces.domain.common.enums.HorseEnum
import ati.su.horseraces.domain.common.enums.TrackCondition
import ati.su.horseraces.domain.common.enums.WeatherCondition
import ati.su.horseraces.domain.race.model.RaceConditions
import ati.su.horseraces.domain.race.model.RaceParticipant
import ati.su.horseraces.domain.race_result.model.RaceResult
import ati.su.horseraces.domain.race_result.model.RaceResultWithParticipants

object RaceResultMapper {
    fun mapToDomain(pojo: RaceResultWithParticipants): RaceResult {
        val participantsResults = pojo.participants.map { mapToDomain(it) }
        val raceConditions = mapToDomain(pojo.raceResult.raceConditions)
        val timeStartRace = pojo.raceResult.timeStartRace
        val timeCompleteRace = pojo.raceResult.timeCompleteRace

        return RaceResult(
            id = pojo.raceResult.id,
            participantsResults = participantsResults,
            raceConditions = raceConditions,
            timeStartRace = timeStartRace,
            timeCompleteRace = timeCompleteRace
        )
    }

    fun mapToEntities(domain: RaceResult): Pair<RaceResultEntity, List<RaceParticipantEntity>> {
        val raceResultEntity = RaceResultEntity(
            id = domain.id,
            raceConditions = mapToEmbedded(domain.raceConditions),
            timeStartRace = domain.timeStartRace,
            timeCompleteRace = domain.timeCompleteRace
        )

        val participantEntities = domain.participantsResults.map {
            mapToEntity(it, raceResultEntity.id)
        }
        return Pair(raceResultEntity, participantEntities)
    }

    private fun mapToDomain(entity: RaceParticipantEntity): RaceParticipant {
        val horseEnum = HorseEnum.fromId(entity.horseEnumId)
            ?: error("HorseEnum not found for ID: ${entity.horseEnumId}")
        return RaceParticipant(
            horseEnum = horseEnum,
            initialSpeed = horseEnum.baseSpeed.toDouble(),
            currentSpeed = horseEnum.baseSpeed.toDouble(),
            distanceCovered = 0.0,
            finishTimeMs = entity.finishTimeMs,
            finalPosition = entity.finalPosition,
            currentProgress = 0f
        )
    }

    private fun mapToEntity(domain: RaceParticipant, raceId: String): RaceParticipantEntity {
        return RaceParticipantEntity(
            raceId = raceId,
            horseEnumId = domain.horseEnum.id,
            finishTimeMs = domain.finishTimeMs,
            finalPosition = domain.finalPosition
        )
    }

    private fun mapToDomain(embedded: RaceConditionsEmbedded): RaceConditions {
        val weather = WeatherCondition.fromId(embedded.weatherCondition)
            ?: error("WeatherCondition not found for ID: ${embedded.weatherCondition}")
        val track = TrackCondition.fromId(embedded.trackCondition)
            ?: error("TrackCondition not found for ID: ${embedded.trackCondition}")
        return RaceConditions(
            weatherCondition = weather,
            trackCondition = track,
            jockeySkill = embedded.jockeySkill
        )
    }

    private fun mapToEmbedded(domain: RaceConditions): RaceConditionsEmbedded {
        return RaceConditionsEmbedded(
            weatherCondition = domain.weatherCondition.id,
            trackCondition = domain.trackCondition.id,
            jockeySkill = domain.jockeySkill
        )
    }
}