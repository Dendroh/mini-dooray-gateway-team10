package com.example.minidooraygateway.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.HiddenHttpMethodFilter;

import java.time.Duration;

@Configuration
public class WebConfig {

  @Bean
  public RestTemplate restTemplate (RestTemplateBuilder restTemplateBuilder) {
    return restTemplateBuilder.setConnectTimeout(Duration.ofSeconds(3L))
                              .setReadTimeout(Duration.ofSeconds(3L))
                              .build();
  }

  @Bean
  public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
    return new HiddenHttpMethodFilter();
  }
}
