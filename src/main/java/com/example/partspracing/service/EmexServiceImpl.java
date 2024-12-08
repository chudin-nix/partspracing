package com.example.partspracing.service;

import com.example.partspracing.PartDtoMapper;
import com.example.partspracing.XmlParser;
import com.example.partspracing.entity.EmexPartDto;
import com.example.partspracing.entity.EmexPartsContainer;
import com.example.partspracing.entity.PartDto;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.xmlbeans.impl.common.IOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Qualifier("emex")
public class EmexServiceImpl implements PartService {

    private final static String REQUEST = """
            <?xml version="1.0" encoding="utf-8"?>
            <soap12:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://www.w3.org/2003/05/soap-envelope">
              <soap12:Body>
                <FindDetailAdv4 xmlns="http://tempuri.org/">
                  <login>2987925</login>
                  <password>c8ed241f</password>
                  <makeLogo>string</makeLogo>
                  <detailNum>partNumber</detailNum>
                  <substLevel>All</substLevel>
                  <substFilter>FilterOriginalAndReplacements</substFilter>
                  <deliveryRegionType>PRI</deliveryRegionType>
                </FindDetailAdv4>
              </soap12:Body>
            </soap12:Envelope>
                """;
    private final RestTemplate restTemplate;
    private  XmlParser xmlParser = new XmlParser();
    @Autowired
    private PartDtoMapper partDtoMapper;

    public EmexServiceImpl(RestTemplate restTemplate, XmlParser xmlParser) {
        this.xmlParser = xmlParser;
        this.restTemplate = restTemplate;
    }

    @Value("${emex.url}")
    private String url;

    @Override
    public List<PartDto> getParts(String partNumber) {
        String request = REQUEST.replaceAll("partNumber", partNumber);
        String xmlResponse = executePost(request);

        int start = xmlResponse.indexOf("<Details>");
        if (start >= 0) {
            int end = xmlResponse.indexOf("</Details>") + "</Details>".length();
            String xml = xmlResponse.substring(start, end);
            EmexPartsContainer container = xmlParser.parse(xml, EmexPartsContainer.class);
            List<EmexPartDto> emexParts = container != null ? container.getParts() : Collections.emptyList();

            List<PartDto> parts = new ArrayList<>();
            for (EmexPartDto emexPart : emexParts) {
                parts.add(partDtoMapper.toPartDto(emexPart));
            }
            return parts;
        } else {
            return Collections.emptyList();
        }
    }

    private String executePost(String request) {
        try {
            final HttpPost httpPost = new HttpPost(url);
            StringEntity entity = new StringEntity(request);
            httpPost.setEntity(entity);
            httpPost.setHeader("Content-type", "application/soap+xml; charset=utf-8");
            try (CloseableHttpClient client = HttpClients.createDefault();
                 CloseableHttpResponse response =  client.execute(httpPost)) {
                return IOUtils.toString(response.getEntity().getContent());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}