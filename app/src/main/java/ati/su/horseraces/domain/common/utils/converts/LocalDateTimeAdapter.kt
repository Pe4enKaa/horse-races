package ati.su.horseraces.domain.common.utils.converts

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonNull
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class LocalDateTimeAdapter : JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?,
    ): LocalDateTime? {
        if (json == null) {
            return null
        }
        val utcLocalDateTime = LocalDateTime.parse(json.asString, formatter)
        val utcZonedDateTime = utcLocalDateTime.atZone(ZoneOffset.UTC)
        val localZonedDateTime = utcZonedDateTime.withZoneSameInstant(ZoneId.systemDefault())
        return localZonedDateTime.toLocalDateTime()
    }

    override fun serialize(
        src: LocalDateTime?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?,
    ): JsonElement {

        if (src == null) {
            return JsonNull.INSTANCE
        }
        val localZonedDateTime = src.atZone(ZoneId.systemDefault())
        val utcZonedDateTime = localZonedDateTime.withZoneSameInstant(ZoneOffset.UTC)
        return JsonPrimitive(formatter.format(utcZonedDateTime.toLocalDateTime()))
    }
}
