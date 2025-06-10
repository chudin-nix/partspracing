package com.example.partspracing.service;

import com.example.partspracing.PartDtoMapper;
import com.example.partspracing.rest.SpringRestClient;
import com.example.partspracing.entity.ForumAutoPartDto;
import com.example.partspracing.entity.PartDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.net.URLEncoder;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

@Service
@Qualifier("forum")
public class ForumAutoServiceImpl implements PartService{

    private final SpringRestClient restClient;
    @Autowired
    private PartDtoMapper partDtoMapper;

    @Value("${forumAuto.url}")
    private String url;

    private ObjectMapper objectMapper = new ObjectMapper();

    public ForumAutoServiceImpl(SpringRestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public List<PartDto> getParts(String partNumber, String brand) {
        if (!StringUtils.isEmpty(partNumber)) {
            String request = url.replaceAll("brand", URLEncoder.encode(brand))
                    .replaceAll("partNumber", partNumber);
            ResponseEntity<String> responseWithBrand = restClient.get(request, String.class);
            if (responseWithBrand.getStatusCode()== HttpStatusCode.valueOf(200)) {
                try {
                    ForumAutoPartDto[] result = objectMapper.readValue(responseWithBrand.getBody(), ForumAutoPartDto[].class);
                    List<PartDto> parts = Stream.of(result).map(part->partDtoMapper.toPartDto(part)).toList();
                    return parts;
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                    return Collections.emptyList();
                }
            } else {
                return Collections.emptyList();
            }
        } else {
            return Collections.emptyList();
        }
    }
}
