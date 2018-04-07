package ru.posol.sample.rest


import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import ru.posol.sample.service.LogService
import ru.posol.sample.service.dto.IdDto
import ru.posol.sample.service.dto.LogDto
import javax.validation.Valid

/**
 * REST controller for managing Log.
 */
@RestController
@RequestMapping("/api")
class LogController {

    val logger = LoggerFactory.getLogger(LogController::class.java)

    @Autowired
    lateinit var logService: LogService

    @GetMapping("/logs")
    fun findAll(@RequestParam(value = "page", defaultValue = "0") page: Int,
                @RequestParam(value = "size", defaultValue = "25") size: Int): List<LogDto> =
            logService.findAll(page, size)


    @PostMapping("/logs")
    fun createNewLog(@Valid @RequestBody logDto: LogDto): ResponseEntity<IdDto> =
            ResponseEntity.ok(logService.createNewLog(logDto, SecurityContextHolder.getContext().authentication.name))

}