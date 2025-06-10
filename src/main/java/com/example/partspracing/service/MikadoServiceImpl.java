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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Qualifier("mikado")
public class MikadoServiceImpl implements PartService {

    private final RestTemplate defaultRestTemplate;

    private final RestTemplate restTemplateWithoutRedirects;

    private final CookieManager cookieManager;

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
    public List<PartDto> getParts(String partNumber, String brand) {
        if (!authenticate()) {
            return Collections.emptyList();
        }

        String searchUrl = baseUrl + "office/SearchCodeG.asp?CODE=" + URLEncoder.encode(partNumber, StandardCharsets.UTF_8);
        String response = defaultRestTemplate.getForObject(searchUrl, String.class);
        String responseWithBrands = getBrandsSubpage(response, brand);

        return parsePartsFromResponse(responseWithBrands);
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

            Elements rows = doc.selectXpath("//tr[@onclick='if(!working) pClick(this)']");

            if (rows.isEmpty()) {
                throw new RuntimeException("Данные о деталях не найдены на странице");
            }

            for (Element row : rows) {
                PartDto part = new PartDto();

                part.setId(row.select("td:first-child a").text());
                part.setName(row.select("td:nth-child(3)").text());
                part.setCompany(row.select("td:nth-child(2)").text());
                part.setCount(row.select("td:nth-child(5)").text());
                part.setShippingDate(row.select("td:nth-child(5)").text());
                part.setPrice(row.select("td:nth-child(4)").text());
                part.setSource("Mikado");
                parts.add(part);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return parts;
    }

    public String getBrandsSubpage(String html, String brand) {
        Document doc = Jsoup.parse(html);

        Elements rowBrands = doc.selectXpath("//a[contains(@onclick, \"flip1('rs\")]");

        String newURL = null;
        for (Element brandElement : rowBrands) {
            String currentBrandText = brandElement.text().replace("&nbsp;", "").trim();

            if (currentBrandText.equalsIgnoreCase(brand)) {
                String onclickValue = brandElement.attr("onclick");
                Pattern pattern = Pattern.compile("flip1\\('([^']+)'\\);");
                Matcher matcher = pattern.matcher(onclickValue);
                if (matcher.find()) {
                    newURL = matcher.group(1);
                }
            }
        }
        return newURL;
    }
}
