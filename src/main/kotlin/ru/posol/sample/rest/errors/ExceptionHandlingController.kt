package ru.posol.sample.rest.errors

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import ru.posol.sample.service.dto.ErrorsDTO
import ru.posol.sample.service.dto.FieldDTO

/**
 * class for custom error response at validation input data
 */
@ControllerAdvice
class ExceptionHandlingController {

    val msg = "request body is not correct"

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun invalidInput(ex: MethodArgumentNotValidException): ResponseEntity<ErrorsDTO> {
        val errors: List<FieldDTO?> = ex.bindingResult.fieldErrors.
                map { fieldError -> FieldDTO(fieldError.field, fieldError.defaultMessage) } ?: listOf()
        return ResponseEntity<ErrorsDTO>(ErrorsDTO(errors), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun invalidInput(ex: HttpMessageNotReadableException): ResponseEntity<ErrorsDTO> {
        val errors: List<FieldDTO?> = listOf(FieldDTO("", msg))
        return ResponseEntity<ErrorsDTO>(ErrorsDTO(errors), HttpStatus.BAD_REQUEST)
    }

}