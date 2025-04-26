package com.example.partspracing.service;

import com.example.partspracing.entity.PartDto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
@Qualifier("mikado")
public class MikadoServiceImpl implements PartService {

    private final RestTemplate defaultRestTemplate;

    private final RestTemplate restTemplateWithoutRedirects;

    private final CookieManager cookieManager;

    private static final String LOGIN_URL = "https://www.mikado-parts.ru/office/login.asp";
    private static final String SUBMIT_URL = "https://www.mikado-parts.ru/office/SECURE.asp";
    private static final String LOGIN_FIELD = "CODE";
    private static final String PASSWORD_FIELD = "PASSWORD";

    @Value("${mikado.login}")
    private String login;

    @Value("${mikado.password}")
    private String password;

    @Value("${mikado.url}")
    private String baseUrl;

    @Autowired
    public MikadoServiceImpl(RestTemplate restTemplate, @Qualifier("restTemplateWithoutRedirects") RestTemplate restTemplateWithoutRedirects, CookieManager cookieManager) {
        this.defaultRestTemplate = restTemplate;
        this.restTemplateWithoutRedirects = restTemplateWithoutRedirects;
        this.cookieManager = new CookieManager();
        this.cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        CookieHandler.setDefault(cookieManager);
    }

    @Override
    public List<PartDto> getParts(String partNumber) {
        if (!authenticate()) {
            return Collections.emptyList();
        }

        String searchUrl = baseUrl + "/office/SearchCodeG.asp?CODE=" + URLEncoder.encode(partNumber, StandardCharsets.UTF_8);
        String response = defaultRestTemplate.getForObject(searchUrl, String.class);

        return parsePartsFromResponse(response);
    }

    private boolean authenticate() {
        String authUrl = baseUrl + "office/SECURE.asp";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("User-Agent", "Mozilla/5.0");

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("CODE", login);
        formData.add("PASSWORD", password);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(formData, headers);

        try {
            ResponseEntity<String> response = restTemplateWithoutRedirects.postForEntity(
                    authUrl,
                    request,
                    String.class
            );

            if (response.getStatusCode().is3xxRedirection()) {
                String redirectedUrl = response.getHeaders().getFirst("Location");
                System.out.println("Получен редирект на URL: " + redirectedUrl);
                return true;
            } else {
                return !response.getBody().contains("Ошибка авторизации");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private List<PartDto> parsePartsFromResponse(String html) {
        List<PartDto> parts = new ArrayList<>();

        try {
            Document doc = Jsoup.parse(html);
            Elements rows = doc.selectXpath("//table[@id='maintbl']");
            Elements supplier = doc.selectXpath("//*[@onclick='if(!working) pClick(this)']//td//following-sibling::td");


            if (rows == null) {
                throw new RuntimeException("Форма поиска не найдена на странице");
            }
            for (Element row : rows) {
                PartDto part = new PartDto();
                part.setId(row.select("td:nth-child(1)").text());
                part.setName(row.select("td:nth-child(2)").text());
                part.setPrice(row.select("td:nth-child(3)").text());
                parts.add(part);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return parts;
    }
}
