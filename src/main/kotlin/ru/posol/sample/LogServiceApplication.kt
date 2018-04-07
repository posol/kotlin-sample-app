package ru.posol.sample

import com.google.common.base.Predicates
import org.hibernate.dialect.H2Dialect
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import ru.posol.sample.service.LogService
import ru.posol.sample.service.dto.LogDto
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2


@SpringBootApplication
@EnableSwagger2
class LogServiceApplication {

    val logger = LoggerFactory.getLogger("ru.posol.sample.LogServiceApplication")

    @Bean
    open fun api(): Docket = Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.any())
            //скрывает лишнюю инфу
            .paths(Predicates.not(PathSelectors.regex("/error.*")))
            .build()

    @Bean
    @ConditionalOnClass(H2Dialect::class)
    fun init(service: LogService): CommandLineRunner {
        return CommandLineRunner {
            service.createNewLog(LogDto(dt = "1997-07-16T19:20:30+01:00", level = "INFO", message = "error"), user = "user")
            service.createNewLog(LogDto(dt = "1989-03-27T19:20:30+01:00", level = "INFO", message = "error"), user = "user")
            service.createNewLog(LogDto(dt = "2001-07-16T19:20:30+01:00", level = "INFO", message = "error"), user = "user")
            service.createNewLog(LogDto(dt = "2018-01-16T19:20:30+01:00", level = "INFO", message = "error"), user = "admin")
        }
    }

}


fun main(args: Array<String>) {
    runApplication<LogServiceApplication>(*args)
}