package com.example.partspracing.service;

import com.example.partspracing.entity.PartDto;

import java.util.List;

public interface PartService {
    List<PartDto> getParts(String partNumber);
}