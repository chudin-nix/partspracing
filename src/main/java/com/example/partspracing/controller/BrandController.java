package com.example.partspracing.controller;

import com.example.partspracing.entity.BrandDto;
import com.example.partspracing.service.DictionaryService;
import com.example.partspracing.service.DictionaryServiceCacheable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://10.8.0.3:8081/")
@RestController
public class BrandController {

    @Autowired
    @Qualifier("dictionary_proxy")
    private DictionaryServiceCacheable dictionaryServiceCacheable;

    @GetMapping("/getBrands")
    public ResponseEntity<List<BrandDto>> getBrands() {
        HttpHeaders responseHeaders = new HttpHeaders();
        List<BrandDto> brands = dictionaryServiceCacheable.getBrands();
        return ResponseEntity.ok().headers(responseHeaders).body(brands);
    }
}
