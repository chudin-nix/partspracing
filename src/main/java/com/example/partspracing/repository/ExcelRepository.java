package com.example.partspracing.repository;

import com.example.partspracing.entity.PartDto;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.wsdl.Part;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Component
public class ExcelRepository implements Repository{
    @Value("${excelFileName}")
    private String fileName;
    @Override
    public List<PartDto> findByArticle(String article) throws IOException {
        try(FileInputStream file = new FileInputStream(fileName)) {
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);
        List<PartDto> parts = new ArrayList<>();
        for (Row row : sheet) {
//            Cell cell = row.getCell(0);
//            String value = String.valueOf(cell != null ? cell.getStringCellValue() : "");
//            if (value.contains(article)) {
//                PartDto partDto = PartDto.builder().id(value).source("Excel").build();
//                parts.add(partDto);
//            }
        }
        return parts;
        }
    }
}
