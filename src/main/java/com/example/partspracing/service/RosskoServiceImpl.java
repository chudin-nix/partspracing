package com.example.partspracing.service;

import com.example.partspracing.PartDtoMapper;
import com.example.partspracing.entity.PartsContainer;
import com.example.partspracing.RestClient;
import com.example.partspracing.XmlParser;
import com.example.partspracing.entity.PartDto;
import com.example.partspracing.entity.RosskoPartDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Qualifier("rossko")
public class RosskoServiceImpl implements PartService {

    private final static String REQUEST = """
            <SOAP-ENV:Envelope
               xmlns:SOAP-ENV = "http://schemas.xmlsoap.org/soap/envelope/"
               SOAP-ENV:encodingStyle = "http://www.w3.org/2001/12/soap-encoding">

               <SOAP-ENV:Body xmlns:m = "http://api.rossko.ru/">
                  <m:GetSearch>
                     <m:KEY1>1796fcc3f052e989072daf7cd0b82a64</m:KEY1>\s
                     <m:KEY2>5733a156f02e290e9ba9c994376b0c97</m:KEY2>
                     <m:text>partNumber</m:text>\s
                     <m:delivery_id>000000001</m:delivery_id>\s
                  </m:GetSearch>
               </SOAP-ENV:Body>
            </SOAP-ENV:Envelope>
            """;
    private final RestClient restClient;
    private XmlParser xmlParser = new XmlParser();

    @Autowired
    private PartDtoMapper partDtoMapper;

    public RosskoServiceImpl(RestClient restClient, XmlParser xmlParser) {
        this.restClient = restClient;
        this.xmlParser = xmlParser;
    }

    @Value("${rossko.url}")
    private String url;

    @Override
    public List<PartDto> getParts(String partNumber) {
        ResponseEntity<String> response = restClient.post(url, REQUEST.replaceAll("partNumber", partNumber), String.class);
        String xmlResponse = response.getBody();
        int start = xmlResponse.indexOf("<ns1:PartsList>");
        if (start >= 0) {
            int end = xmlResponse.indexOf("</ns1:PartsList>") + "</ns1:PartsList>".length();
            String xml = xmlResponse.substring(start, end);
            PartsContainer container = xmlParser.parse(xml.replaceAll("ns1:", ""), PartsContainer.class);
            List<RosskoPartDto> rosskoParts = container != null ? container.getParts() : Collections.emptyList();

            List<PartDto> parts = new ArrayList<>();
            for (RosskoPartDto rosskoPart : rosskoParts) {
                for (int i = 0; i < partDtoMapper.toPartDto(rosskoPart).size(); i++) {
                    parts.add(partDtoMapper.toPartDto(rosskoPart).get(i));
                }
            }
            return parts;
        } else {
            return Collections.emptyList();
        }
    }
}