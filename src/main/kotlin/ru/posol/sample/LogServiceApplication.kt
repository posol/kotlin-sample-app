package ru.posol.sample

import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import ru.posol.sample.service.LogService
import ru.posol.sample.service.dto.LogDto


@SpringBootApplication
class LogServiceApplication {

    val logger = LoggerFactory.getLogger("ru.posol.sample.LogServiceApplication")


}


fun main(args: Array<String>) {
    runApplication<LogServiceApplication>(*args)
}