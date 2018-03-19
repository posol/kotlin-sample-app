package ru.posol.sample.utils

import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import jdk.nashorn.tools.ShellFunctions.input
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.ZonedDateTime

/**
 * Utility class for data convert to ISO format
 */
@Component
class DateConverter {

    fun dateToIsoStr( date: OffsetDateTime ) : String = date.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)

    fun convertIsoStrToDate( isoDate: String ) : OffsetDateTime  = OffsetDateTime.parse(isoDate,  DateTimeFormatter.ISO_OFFSET_DATE_TIME)

}