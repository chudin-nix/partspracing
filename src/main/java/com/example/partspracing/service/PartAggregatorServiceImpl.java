package com.example.partspracing.service;

import com.example.partspracing.entity.PartDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Qualifier("aggregation")
public class PartAggregatorServiceImpl implements PartService{

    @Autowired
    @Qualifier("services")
    private List<PartService> services;

    @Autowired
    @Qualifier("local")
    private LocalServiceImpl localService;

    @Override
    public List<PartDto> getParts(String partNumber, String brand) {
        List<PartDto> aggregatedParts = new ArrayList<>();

        for (PartService service : services) {
            aggregatedParts.addAll(service.getParts(partNumber, brand));
        }

        aggregatedParts.sort((p1, p2) -> {
            if (p1.getPriority() == 1 && p2.getPriority() != 1) {
                return -1;
            } else if (p1.getPriority() != 1 && p2.getPriority() == 1) {
                return 1;
            } else {
                return 0;
            }
        });

        return aggregatedParts;
    }
}