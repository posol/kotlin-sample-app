package ru.posol.sample;


import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import ru.posol.sample.repository.LogRepository;
import ru.posol.sample.service.LogService;
import ru.posol.sample.service.dto.IdDto;
import ru.posol.sample.service.dto.LogDto;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RestTests {

    private static final Logger logger = LoggerFactory.getLogger(RestTests.class);

    private static final String ROOT_URL = "http://localhost:8080/api";

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    LogService logService;

    @Autowired
    LogRepository logRepository;


    @Before
    public void init() {
        logService.createNewLog(new LogDto("1997-07-16T19:20:30+01:00", "INFO", "error"), "user");
        logService.createNewLog(new LogDto("1989-03-27T19:20:30+01:00", "INFO", "error"), "user");
        logService.createNewLog(new LogDto("2001-07-16T19:20:30+01:00", "INFO", "error"), "user");
        logService.createNewLog(new LogDto("2018-01-16T19:20:30+01:00", "INFO", "error"), "admin");
    }

    @After
    public void close() {
        logRepository.deleteAll();
    }

    @Test
    public void testGetAllLogs() {
        ResponseEntity<LogDto[]> respEntity = restTemplate.withBasicAuth("admin", "admin").
                getForEntity(ROOT_URL + "/logs", LogDto[].class);

        assertThat(respEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(respEntity.getBody()).isNotNull();

        List<LogDto> logs = Arrays.asList(respEntity.getBody());
        logger.info(logs.toString());
    }


    @Test
    public void testCreateLog() {
        LogDto log = new LogDto("1997-07-16T19:20:30+01:00", "INFO", "test");
        HttpEntity<LogDto> request = new HttpEntity<>(log);

        ResponseEntity<IdDto> respEntity = restTemplate.withBasicAuth("user", "user").
                exchange(ROOT_URL + "/logs", HttpMethod.POST, request, IdDto.class);

        assertThat(respEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(respEntity.getBody()).isNotNull();
        logger.info(respEntity.getBody().toString());
    }


    @Test()
    public void test403() {
        ResponseEntity<String> respEntity = restTemplate.withBasicAuth("other", "other").
                getForEntity(ROOT_URL + "/logs", String.class);

        assertThat(respEntity.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
        assertThat(respEntity.getBody()).isNotNull();
        logger.info(respEntity.getBody().toString());
    }


    @Test()
    public void test401() {
        ResponseEntity<String> respEntity = restTemplate.withBasicAuth("vasya", "vasya").
                getForEntity(ROOT_URL + "/logs", String.class);

        assertThat(respEntity.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
        assertThat(respEntity.getBody()).isNotNull();
        logger.info(respEntity.getBody().toString());
    }
    //FIXME  Добавиь тесты jpa, даты, validations

}
