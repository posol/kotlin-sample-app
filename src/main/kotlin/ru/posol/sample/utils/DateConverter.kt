package ru.posol.sample.utils

import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

/**
 * Utility functions for data convert to ISO format
 */

fun dateToIsoStr(date: OffsetDateTime): String = date.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)

fun convertIsoStrToDate(isoDate: String): OffsetDateTime = OffsetDateTime.parse(isoDate, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
