package ru.posol.sample.repository

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import ru.posol.sample.domain.Log
import ru.posol.sample.domain.LogLevel

/**
 * Spring Data JPA repository for the Log entity.
 */
@Repository
interface LogRepository : PagingAndSortingRepository<Log, Long> {

    fun findByLevel(level: LogLevel): Iterable<Log>

    fun findByAuthor(level: LogLevel): Iterable<Log>
}