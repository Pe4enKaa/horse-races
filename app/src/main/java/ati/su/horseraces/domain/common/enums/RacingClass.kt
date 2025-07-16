package ati.su.horseraces.domain.common.enums

enum class RacingClass(override val id: Long, override val displayName: String, val speedModifier: Float) : DisplayableEnum {
    CLASS_D(1, "Класс D", 0.9f),
    CLASS_C(2, "Класс C", 1.0f),
    CLASS_B(3, "Класс B", 1.1f),
    CLASS_A(4, "Класс A", 1.2f),
    CLASS_S(5, "Класс S", 1.3f);

    companion object {
        private val idMap = RacingClass.entries.associateBy { it.id }
        private val nameMap = RacingClass.entries.associateBy { it.displayName.lowercase() }

        fun fromId(id: Long): RacingClass? = idMap[id]
        fun fromName(name: String): RacingClass? = nameMap[name.lowercase()]
    }
}