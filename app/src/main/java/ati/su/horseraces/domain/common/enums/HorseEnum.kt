package ati.su.horseraces.domain.common.enums

import androidx.compose.ui.graphics.Color

enum class HorseEnum(
    override val id: Long,
    override val displayName: String,
    val baseSpeed: Float,
    val racingClass: RacingClass,
    val ageCategory: HorseAgeCategory,
    val stamina: HorseStamina,
    val genetics: GeneticsInfluence,
    val color: Color
) : DisplayableEnum {
    LIGHTNING_BOLT(
        id = 1,
        displayName = "Молниеносный",
        baseSpeed = 1.4f,
        racingClass = RacingClass.CLASS_S,
        ageCategory = HorseAgeCategory.THREE_YEARS,
        stamina = HorseStamina.HIGH,
        genetics = GeneticsInfluence.EXCEPTIONAL,
        color = Color(0xFFE91E63)
    ),
    THUNDERHOOF(
        id = 2,
        displayName = "Громокопыт",
        baseSpeed = 1.2f,
        racingClass = RacingClass.CLASS_A,
        ageCategory = HorseAgeCategory.FOUR_YEARS,
        stamina = HorseStamina.ELITE,
        genetics = GeneticsInfluence.GOOD,
        color = Color(0xFF2196F3)
    ),
    SHADOWFAX(
        id = 3,
        displayName = "Теневой Ветер",
        baseSpeed = 1.0f,
        racingClass = RacingClass.CLASS_B,
        ageCategory = HorseAgeCategory.FIVE_PLUS,
        stamina = HorseStamina.AVERAGE,
        genetics = GeneticsInfluence.NORMAL,
        color = Color(0xFF9E9E9E)
    ),
    SPIRIT(
        id = 4,
        displayName = "Дух Свободы",
        baseSpeed = 1.1f,
        racingClass = RacingClass.CLASS_B,
        ageCategory = HorseAgeCategory.THREE_YEARS,
        stamina = HorseStamina.HIGH,
        genetics = GeneticsInfluence.GOOD,
        color = Color(0xFFFFC107)
    ),
    WILDFIRE(
        id = 5,
        displayName = "Дикий Огонь",
        baseSpeed = 1.3f,
        racingClass = RacingClass.CLASS_A,
        ageCategory = HorseAgeCategory.TWO_YEARS,
        stamina = HorseStamina.AVERAGE,
        genetics = GeneticsInfluence.EXCEPTIONAL,
        color = Color(0xFFFF5722)
    ),
    COMET(
        id = 6,
        displayName = "Комета",
        baseSpeed = 0.9f,
        racingClass = RacingClass.CLASS_C,
        ageCategory = HorseAgeCategory.FIVE_PLUS,
        stamina = HorseStamina.LOW,
        genetics = GeneticsInfluence.NORMAL,
        color = Color(0xFF673AB7)
    ),
    SILVER_STREAM(
        id = 7,
        displayName = "Серебряный Ручей",
        baseSpeed = 1.15f,
        racingClass = RacingClass.CLASS_A,
        ageCategory = HorseAgeCategory.FOUR_YEARS,
        stamina = HorseStamina.HIGH,
        genetics = GeneticsInfluence.GOOD,
        color = Color(0xFF00BCD4)
    ),
    GOLDEN_SUN(
        id = 8,
        displayName = "Золотое Солнце",
        baseSpeed = 1.05f,
        racingClass = RacingClass.CLASS_B,
        ageCategory = HorseAgeCategory.THREE_YEARS,
        stamina = HorseStamina.AVERAGE,
        genetics = GeneticsInfluence.NORMAL,
        color = Color(0xFFFFEB3B)
    ),
    IRON_WILL(
        id = 9,
        displayName = "Железная Воля",
        baseSpeed = 0.98f,
        racingClass = RacingClass.CLASS_C,
        ageCategory = HorseAgeCategory.FIVE_PLUS,
        stamina = HorseStamina.ELITE,
        genetics = GeneticsInfluence.NORMAL,
        color = Color(0xFF795548)
    ),
    STAR_GAZER(
        id = 10,
        displayName = "Звездочет",
        baseSpeed = 1.25f,
        racingClass = RacingClass.CLASS_S,
        ageCategory = HorseAgeCategory.TWO_YEARS,
        stamina = HorseStamina.LOW,
        genetics = GeneticsInfluence.EXCEPTIONAL,
        color = Color(0xFF607D8B)
    ),
    OCEAN_BREEZE(
        id = 11,
        displayName = "Океанский Бриз",
        baseSpeed = 1.08f,
        racingClass = RacingClass.CLASS_B,
        ageCategory = HorseAgeCategory.FOUR_YEARS,
        stamina = HorseStamina.AVERAGE,
        genetics = GeneticsInfluence.GOOD,
        color = Color(0xFF009688)
    ),
    FIREBIRD(
        id = 12,
        displayName = "Жар-птица",
        baseSpeed = 1.35f,
        racingClass = RacingClass.CLASS_A,
        ageCategory = HorseAgeCategory.THREE_YEARS,
        stamina = HorseStamina.HIGH,
        genetics = GeneticsInfluence.EXCEPTIONAL,
        color = Color(0xFFF44336)
    );

    companion object {
        private val idMap = HorseEnum.entries.associateBy { it.id }
        private val nameMap = HorseEnum.entries.associateBy { it.displayName.lowercase() }

        fun fromId(id: Long): HorseEnum? = idMap[id]
        fun fromName(name: String): HorseEnum? = nameMap[name.lowercase()]
    }
}