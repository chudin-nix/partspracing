package com.example.partspracing.service;

import com.example.partspracing.XmlParser;
import com.example.partspracing.rest.ApacheRestClient;
import com.example.partspracing.entity.BrandContainer;
import com.example.partspracing.entity.BrandDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.github.benmanes.caffeine.cache.Cache;

import java.util.Collections;
import java.util.List;

@Service
@Qualifier("dictionary")
public class DictionaryServiceImpl implements DictionaryService {

    @Autowired
    private ApacheRestClient apacheRestClient;

    @Value("${emex.url.for.brand}")
    private String urlForBrands;

    private XmlParser xmlParser = new XmlParser();

    private static final String ALL_BRANDS_CACHE_KEY = "allBrands";


    private final static String REQUEST_FOR_BRAND_NAME = """
            <?xml version="1.0" encoding="utf-8"?>
            <soap12:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://www.w3.org/2003/05/soap-envelope">
              <soap12:Body>
                    <GetMakesDict xmlns="http://tempuri.org/">
                        <login>2987925</login>
                        <password>c8ed241f</password>
                    </GetMakesDict>
              </soap12:Body>
            </soap12:Envelope>
            """;

    @Override
    public List<BrandDto> getBrands() {

        String xmlResponse = apacheRestClient.post(REQUEST_FOR_BRAND_NAME, urlForBrands);
        int start = xmlResponse.indexOf("<GetMakesDictResult>");
        List<BrandDto> emexBrands;
        if (start >= 0) {
            int end = xmlResponse.indexOf("</GetMakesDictResult>") + "</GetMakesDictResult>".length();
            String xml = xmlResponse.substring(start, end);
            BrandContainer container = xmlParser.parse(xml, BrandContainer.class);
            emexBrands = container != null ? container.getBrands() : Collections.emptyList();
        } else {
            emexBrands = Collections.emptyList();
        }

        return emexBrands;
    }
}
