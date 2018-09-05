package com.yabanya.commons.orm.repository;

import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AbstractRepositoryCrudIT {

    @SpringBootApplication
    @Import(RepositoryConfiguration.class)
    public static class RepositoryTestSpringBootApplication {

        public static void main(String[] args) {
            SpringApplication.run(RepositoryTestSpringBootApplication.class, args);
        }
    }
}
