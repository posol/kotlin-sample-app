package ru.posol.sample.service

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.posol.sample.domain.Log
import ru.posol.sample.repository.LogRepository
import ru.posol.sample.service.dto.IdDto
import ru.posol.sample.service.dto.LogDto
import ru.posol.sample.service.mapper.LogMapper

/**
 * Service class for managing logs
 */
@Service
@Transactional
class LogService {

    val logger = LoggerFactory.getLogger(LogService::class.java)

    @Autowired
    lateinit var logRepository: LogRepository

    @Autowired
    lateinit var logMapper: LogMapper

    val sort = Sort(Sort.Direction.ASC, "dt")

    @Transactional(readOnly = true)
    fun findAll(page: Int, size: Int): List<LogDto> {
        val pageable = PageRequest.of(page, size, sort)
        val usersPage = logRepository.findAll(pageable)
        return logMapper.toDtos(usersPage.toList())
    }

    fun createNewLog(logDto: LogDto, user: String): IdDto {
        val newLog: Log = logRepository.save(logMapper.toEntity(logDto, user))
        return logMapper.toIdDto(newLog)
    }

}