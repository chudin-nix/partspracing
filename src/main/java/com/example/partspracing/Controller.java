package com.example.partspracing;

import com.example.partspracing.entity.PartDto;
import com.example.partspracing.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class Controller {
    @Autowired
    @Qualifier("aggregation")
    private PartService partService;

    @GetMapping("/parts")
    public ResponseEntity<List<PartDto>> findParts(@RequestParam(required = false, defaultValue = "1") String partNumber) {
        HttpHeaders responseHeaders = new HttpHeaders();
//        responseHeaders.set("Access-Control-Allow-Origin", "*");
        return ResponseEntity.ok().headers(responseHeaders).body(partService.getParts(partNumber));
    }
}