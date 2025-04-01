package org.mik.first.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ResetConfig {


    @Bean
    RestTemplate resetTemplate(){
        return new RestTemplate();
    }
}
