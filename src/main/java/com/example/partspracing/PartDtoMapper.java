package com.example.partspracing;

import com.example.partspracing.entity.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class PartDtoMapper {


    public PartDto toPartDto(ForumAutoPartDto forumAutoPartDto) {

        return PartDto.builder()
                .id(forumAutoPartDto.getArt())
                .name(forumAutoPartDto.getName())
                .company(forumAutoPartDto.getBrand())
                .count(forumAutoPartDto.getNum())
                .shippingDate(forumAutoPartDto.getdDeliv())
                .source("ForumAuto")
                .price(String.valueOf(forumAutoPartDto.getPrice()))
                .build();
    }

    public List<PartDto> toPartDto(RosskoPartDto rosskoPartDto) {
        List<PartDto> partDtos = new ArrayList<>();

        partDtos.add(PartDto.builder()
                .id(rosskoPartDto.getPartNumber())
                .name(rosskoPartDto.getName())
                .company(rosskoPartDto.getBrand())
                .count(rosskoPartDto.getCount())
                .shippingDate(rosskoPartDto.getDelivery())
                .source("Rossko")
                .price(String.valueOf(rosskoPartDto.getPrice()))
                .build());

        if (rosskoPartDto.getStocks() != null) {
            Arrays.stream(rosskoPartDto.getStocks())
                    .forEach(stock -> {
                        PartDto stockPartDto = PartDto.builder()
                                .id(stock)
                                .name(rosskoPartDto.getName())
                                .company(rosskoPartDto.getBrand())
                                .count(rosskoPartDto.getCount())
                                .shippingDate(rosskoPartDto.getDelivery())
                                .source("Rossko")
                                .price(String.valueOf(rosskoPartDto.getPrice()))
                                .build();
                        partDtos.add(stockPartDto);
                    });
        }

        return partDtos;
    }

    public PartDto toPartDto(EmexPartDto emexPartDto) {
        return PartDto.builder()
                  .id(emexPartDto.getDetailNum())
                  .name(emexPartDto.getDetailNameRus())
                  .company(emexPartDto.getMakeName())
                  .count(emexPartDto.getQuantity())
                  .shippingDate(emexPartDto.getADDays())
                  .source("Emex")
                  .price(String.valueOf(emexPartDto.getResultPrice()))
                  .build();
    }

    public PartDto toPartDto(MikadoPartDto mikadoPartDto) {
        return PartDto.builder()
                .id(mikadoPartDto.getPartArticle())
                .name(mikadoPartDto.getPartName())
                .company(mikadoPartDto.getPartCompany())
                .count(mikadoPartDto.getPartCount())
                .shippingDate(mikadoPartDto.getShippingDate())
                .source("Mikado")
                .price(String.valueOf(mikadoPartDto.getPrice()))
                .build();
    }
}