package ru.posol.sample

import org.hibernate.dialect.H2Dialect
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.context.annotation.Bean
import ru.posol.sample.service.LogService
import ru.posol.sample.service.dto.LogDto


@SpringBootApplication
class LogServiceApplication {

    val logger = LoggerFactory.getLogger("ru.posol.sample.LogServiceApplication")

    @Bean
    @ConditionalOnClass(H2Dialect::class)
    fun init(service: LogService): CommandLineRunner {
        return CommandLineRunner {

            service.createNewLog(LogDto("1997-07-16T19:20:30+01:00", "INFO", "error"),"user")
            service.createNewLog(LogDto("1989-03-27T19:20:30+01:00", "INFO", "error"),"user")
            service.createNewLog(LogDto("2001-07-16T19:20:30+01:00", "INFO", "error"),"user")
            service.createNewLog(LogDto("2018-01-16T19:20:30+01:00", "INFO", "error"),"admin")
        }
    }

}


fun main(args: Array<String>) {
    runApplication<LogServiceApplication>(*args)
}