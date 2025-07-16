package ati.su.horseraces.domain.common.enums

enum class TrackCondition(override val id: Long, override val displayName: String, val speedModifier: Float) : DisplayableEnum {
    DRY(1, "Сухая", 1.05f),
    SOFT(2, "Мягкая", 0.98f),
    MUDDY(3, "Грязная", 0.9f),
    HEAVY(4, "Тяжелая", 0.85f);

    companion object {
        private val idMap = TrackCondition.entries.associateBy { it.id }

        fun fromId(id: Long): TrackCondition? = idMap[id]
        fun fromName(name: String): TrackCondition? {
            return TrackCondition.entries.firstOrNull {
                it.displayName.equals(name, ignoreCase = true)
            }
        }
    }
}