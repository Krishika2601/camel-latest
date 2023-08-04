package com.camel.assignment;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CamelApplication {

    public static void main(String[] args) {
        SpringApplication.run(CamelApplication.class, args);
    }
    @Bean
    public CamelContext camelContext() {
        return new DefaultCamelContext();
    }
    @Bean
    public ProducerTemplate producerTemplate(CamelContext camelContext) {
        return camelContext.createProducerTemplate();
    }
}