package ati.su.horseraces.domain.common.enums

enum class HorseEnum(
    override val id: Long,
    override val displayName: String,
    val baseSpeed: Float,
    val racingClass: RacingClass,
    val ageCategory: HorseAgeCategory,
    val stamina: HorseStamina,
    val genetics: GeneticsInfluence
) : DisplayableEnum {
    LIGHTNING_BOLT(
        id = 1,
        displayName = "Молниеносный",
        baseSpeed = 1.4f,
        racingClass = RacingClass.CLASS_S,
        ageCategory = HorseAgeCategory.THREE_YEARS,
        stamina = HorseStamina.HIGH,
        genetics = GeneticsInfluence.EXCEPTIONAL
    ),
    THUNDERHOOF(
        id = 2,
        displayName = "Громокопыт",
        baseSpeed = 1.2f,
        racingClass = RacingClass.CLASS_A,
        ageCategory = HorseAgeCategory.FOUR_YEARS,
        stamina = HorseStamina.ELITE,
        genetics = GeneticsInfluence.GOOD
    ),
    SHADOWFAX(
        id = 3,
        displayName = "Теневой Ветер",
        baseSpeed = 1.0f,
        racingClass = RacingClass.CLASS_B,
        ageCategory = HorseAgeCategory.FIVE_PLUS,
        stamina = HorseStamina.AVERAGE,
        genetics = GeneticsInfluence.NORMAL
    ),
    SPIRIT(
        id = 4,
        displayName = "Дух Свободы",
        baseSpeed = 1.1f,
        racingClass = RacingClass.CLASS_B,
        ageCategory = HorseAgeCategory.THREE_YEARS,
        stamina = HorseStamina.HIGH,
        genetics = GeneticsInfluence.GOOD
    ),
    WILDFIRE(
        id = 5,
        displayName = "Дикий Огонь",
        baseSpeed = 1.3f,
        racingClass = RacingClass.CLASS_A,
        ageCategory = HorseAgeCategory.TWO_YEARS,
        stamina = HorseStamina.AVERAGE,
        genetics = GeneticsInfluence.EXCEPTIONAL
    ),
    COMET(
        id = 6,
        displayName = "Комета",
        baseSpeed = 0.9f,
        racingClass = RacingClass.CLASS_C,
        ageCategory = HorseAgeCategory.FIVE_PLUS,
        stamina = HorseStamina.LOW,
        genetics = GeneticsInfluence.NORMAL
    );
    companion object {
        private val idMap = HorseEnum.entries.associateBy { it.id }
        private val nameMap = HorseEnum.entries.associateBy { it.displayName.lowercase() }

        fun fromId(id: Long): HorseEnum? = idMap[id]
        fun fromName(name: String): HorseEnum? = nameMap[name.lowercase()]
    }
}