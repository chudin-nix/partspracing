package com.example.partspracing.service;

import com.example.partspracing.entity.BrandDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("dictionary_proxy")
public class DictionaryServiceCacheable implements DictionaryService {

    private final DictionaryService dictionaryService;

    public DictionaryServiceCacheable(@Qualifier("dictionary") DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @Override
    @Cacheable(value = "brandsCache", key = "'allBrands'")
    public List<BrandDto> getBrands() {
        List<BrandDto> brands = dictionaryService.getBrands();
        return brands;
    }
}
