package com.example.partspracing.config;

import com.example.partspracing.service.PartService;
import org.apache.hc.client5.http.classic.HttpClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.List;
import java.util.function.Supplier;

@Configuration
public class Config {
    @Bean("services")
    public List<PartService> services(@Qualifier("rossko") PartService rosskoService,
                                      @Qualifier("forum") PartService forumService,
                                      @Qualifier("local") PartService localService,
                                       @Qualifier("emex") PartService emexService,
                                      @Qualifier("mikado") PartService mikadoService) {
        return List.of(rosskoService, forumService, localService, emexService, mikadoService);
    }

    @Bean
    public RestTemplate restTemplateWithoutRedirects() {
        HttpClient httpClient = HttpClientBuilder.create()
                .disableRedirectHandling()
                .build();

        HttpComponentsClientHttpRequestFactory factory =
                new HttpComponentsClientHttpRequestFactory(httpClient);

        return new RestTemplate(factory);
    }

    @Bean
    @Qualifier("aggregation")
    public CookieManager aggregationCookieManager() {
        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        CookieHandler.setDefault(cookieManager);
        return cookieManager;
    }
}
