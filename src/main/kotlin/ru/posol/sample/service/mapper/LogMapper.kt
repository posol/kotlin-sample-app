package ru.posol.sample.service.mapper

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ru.posol.sample.domain.LogLevel
import ru.posol.sample.domain.Log
import ru.posol.sample.service.dto.IdDto
import ru.posol.sample.service.dto.LogDto
import ru.posol.sample.utils.DateConverter

/**
 * Mapper for the entity Log and its DTO LogDto.
 */
@Component
class LogMapper {

   @Autowired
   lateinit var dateConverter: DateConverter

   fun toIdDto(log: Log): IdDto = IdDto(log.id)

   fun toDto(log: Log): LogDto = LogDto(dateConverter.dateToIsoStr(log.dt), log.level.toString(), log.message)

   fun toDtos(logs: List<Log>): List<LogDto> = logs.map{ log  -> toDto(log) }

   fun toEntity(logDto: LogDto, author: String): Log = Log(author, dateConverter.convertIsoStrToDate(logDto.dt), LogLevel.valueOf(logDto.level), logDto.message)

}