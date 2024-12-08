package com.example.partspracing.service;

import com.example.partspracing.entity.PartDto;
import com.example.partspracing.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
@Service
@Qualifier("local")
public class LocalServiceImpl implements PartService{
    @Autowired
    private Repository repository;
    @Override
    public List<PartDto> getParts(String partNumber) {
        try {
            return repository.findByArticle(partNumber);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
