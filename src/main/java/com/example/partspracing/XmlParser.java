package com.example.partspracing;

import com.example.partspracing.entity.EmexPartDto;
import com.example.partspracing.entity.PartDto;
import com.example.partspracing.entity.RosskoPartDto;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.stereotype.Component;

import java.io.StringReader;
import java.util.Collections;
import java.util.List;
@Component
public class XmlParser {
    public <T> T parse(String xml, Class<T> type) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(type);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            T partsContainer = (T) jaxbUnmarshaller.unmarshal(new StringReader(xml));
            return partsContainer;
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }
}