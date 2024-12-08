package com.example.partspracing.repository;

import com.example.partspracing.entity.PartDto;

import java.io.IOException;
import java.util.List;

public interface Repository {
    List<PartDto> findByArticle(String article) throws IOException;
}
