package ati.su.horseraces.domain.common.enums

enum class HorseStamina(override val id: Long, override val displayName: String, val distanceModifier: Float) : DisplayableEnum {
    LOW(1, "Низкая", 0.9f),
    AVERAGE(2, "Средняя", 1.0f),
    HIGH(3, "Высокая", 1.1f),
    ELITE(4, "Элитная", 1.2f);

    companion object {
        private val idMap = HorseStamina.entries.associateBy { it.id }
        private val nameMap = HorseStamina.entries.associateBy { it.displayName.lowercase() }

        fun fromId(id: Long): HorseStamina? = idMap[id]
        fun fromName(name: String): HorseStamina? = nameMap[name.lowercase()]
    }
}