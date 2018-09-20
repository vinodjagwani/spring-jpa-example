package com.example.repository;

import com.example.Application;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import javax.transaction.Transactional;

/**
 * Created by vinodjagwani on 7/15/17.
 */
@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest(classes = AbstractRepositoryTest.IntegrationTest.class)
public class AbstractRepositoryTest
{

    @Configuration
    @EntityScan( basePackages = {"com.example.entity"} )
    @EnableAutoConfiguration(exclude = {
        WebMvcAutoConfiguration.class
    })
    protected static class IntegrationTest {
        public static void main(String[] args) {
            SpringApplication.run(Application.class, args);
        }
    }

    @Test
    public void emptyTest(){
        // Any generic test can be define here
    }



}
