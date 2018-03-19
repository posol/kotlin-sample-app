package ru.posol.sample.domain


import java.time.OffsetDateTime
import javax.persistence.*

/**
 * A Log Entity.
 */
@Entity
@Table(name="logs")
data class Log(

        val author: String = "",
        val dt: OffsetDateTime  = OffsetDateTime.now(),
        val level: LogLevel = LogLevel.INFO,
        val message: String = "",
        @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
        val id: Long = -1
)