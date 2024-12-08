package com.example.partspracing.config;

import com.example.partspracing.service.PartService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
public class Config {
    @Bean("services")
    public List<PartService> services(@Qualifier("rossko") PartService rosskoService,
                                      @Qualifier("forum") PartService forumService,
                                      @Qualifier("local") PartService localService ,
                                      /* @Qualifier("emex") PartService emexService, */
                                      @Qualifier("mikado") PartService mikadoService) {
        return List.of(rosskoService, forumService, localService,/* emexService,*/ mikadoService);
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
