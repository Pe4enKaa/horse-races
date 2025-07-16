package ati.su.horseraces.domain.common.enums

enum class GeneticsInfluence(override val id: Long, override val displayName: String, val speedModifier: Float) : DisplayableEnum {
    NORMAL(1, "Обычная", 1.0f),
    GOOD(2, "Хорошая", 1.05f),
    EXCEPTIONAL(3, "Исключительная", 1.1f);

    companion object {
        private val idMap = GeneticsInfluence.entries.associateBy { it.id }
        private val nameMap = GeneticsInfluence.entries.associateBy { it.displayName.lowercase() }

        fun fromId(id: Long): GeneticsInfluence? = idMap[id]
        fun fromName(name: String): GeneticsInfluence? = nameMap[name.lowercase()]
    }
}