package ru.posol.sample.service.dto

import ru.posol.sample.service.validators.IsEnumValue
import ru.posol.sample.service.validators.IsIsoDate
import javax.validation.constraints.NotBlank

/**
 * A DTO for the Log entity.
 */
data class LogDto(

        @get: NotBlank(message = "field required")
        @get: IsIsoDate(message = "value is not ISO date format")
        val dt: String = "",
        @get: NotBlank(message = "field required")
        @get: IsEnumValue(message = "value is not ENUM")
        val level: String = "",
        @get: NotBlank(message = "field required")
        val message: String = ""

)