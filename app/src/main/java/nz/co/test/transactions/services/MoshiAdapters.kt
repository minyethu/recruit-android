package nz.co.test.transactions.services

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.math.BigDecimal
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object OffsetDateTimeAdapter {
    @ToJson
    fun toJson(value: LocalDateTime): String {
        return DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss").format(value)
    }

    @FromJson
    fun fromJson(value: String): LocalDateTime {
        return LocalDateTime.parse(value)
    }
}

object BigDecimalAdapter {
    @FromJson
    fun fromJson(string: String) = BigDecimal(string)
    @ToJson
    fun toJson(value: BigDecimal) = value.toString()
}