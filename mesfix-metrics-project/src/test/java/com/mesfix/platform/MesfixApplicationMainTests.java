package com.mesfix.platform;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class MesfixApplicationMainTest {

    @Autowired
    @Qualifier("corsConfigurer")
    private WebMvcConfigurer webMvcConfigurer;

    @Test
    void contextLoads() {
        assertNotNull(webMvcConfigurer, "WebMvcConfigurer bean should be loaded");
    }
}
