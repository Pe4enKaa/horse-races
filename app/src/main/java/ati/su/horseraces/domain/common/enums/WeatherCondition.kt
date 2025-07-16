package ati.su.horseraces.domain.common.enums

enum class WeatherCondition(override val id: Long, override val displayName: String, val speedModifier: Float) : DisplayableEnum {
    SUNNY(1, "Солнечно", 1.05f),
    CLOUDY(2, "Облачно", 1.0f),
    RAINY(3, "Дождливо", 0.9f),
    WINDY(4, "Ветрено", 0.95f);

    companion object {
        private val idMap = WeatherCondition.entries.associateBy { it.id }

        fun fromId(id: Long): WeatherCondition? = idMap[id]
        fun fromName(name: String): WeatherCondition? {
            return WeatherCondition.entries.firstOrNull {
                it.displayName.equals(name, ignoreCase = true)
            }
        }
    }
}