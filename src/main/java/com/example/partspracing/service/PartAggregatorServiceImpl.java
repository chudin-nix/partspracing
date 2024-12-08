package com.example.partspracing.service;

import com.example.partspracing.entity.PartDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Qualifier("aggregation")
public class PartAggregatorServiceImpl implements PartService{
    @Autowired
    @Qualifier("services")
    private List<PartService> services;
    @Override
    public List<PartDto> getParts(String partNumber) {
        List<PartDto> aggregatedParts = new ArrayList<>();
        for (PartService service : services) {
            aggregatedParts.addAll(service.getParts(partNumber));
        }
        return aggregatedParts;
    }
}