package ati.su.horseraces.domain.common.enums

enum class HorseAgeCategory(override val id: Long, override val displayName: String, val speedModifier: Float) : DisplayableEnum {
    TWO_YEARS(1, "2 года", 0.95f),
    THREE_YEARS(2, "3 года", 1.05f),
    FOUR_YEARS(3, "4 года", 1.0f),
    FIVE_PLUS(4, "5+ лет", 0.9f);

    companion object {
        private val idMap = HorseAgeCategory.entries.associateBy { it.id }
        private val nameMap = HorseAgeCategory.entries.associateBy { it.displayName.lowercase() }

        fun fromId(id: Long): HorseAgeCategory? = idMap[id]
        fun fromName(name: String): HorseAgeCategory? = nameMap[name.lowercase()]
    }
}