package com.example.partspracing.service;

import ch.qos.logback.core.util.StringUtil;
import com.example.partspracing.PartDtoMapper;
import com.example.partspracing.RestClient;
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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

@Service
@Qualifier("forum")
public class ForumAutoServiceImpl implements PartService{

    private final RestClient restClient;
    @Autowired
    private PartDtoMapper partDtoMapper;
    @Value("${forumAuto.url}")
    private String url;
    private ObjectMapper objectMapper = new ObjectMapper();
    public ForumAutoServiceImpl(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public List<PartDto> getParts(String partNumber) {
        if (!StringUtils.isEmpty(partNumber)) {
            ResponseEntity<String> response = restClient.get(url.replaceAll("partNumber", partNumber), String.class);
            if (response.getStatusCode()== HttpStatusCode.valueOf(200)) {
                try {
                    ForumAutoPartDto[] result = objectMapper.readValue(response.getBody(), ForumAutoPartDto[].class);
                    List<PartDto> parts = Stream.of(result).map(part->partDtoMapper.toPartDto(part)).toList();
                    return parts;//Arrays.asList(response.getBody());
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
