package com.example.partspracing.rest;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ApacheRestClient implements RestClient {

    public String post(String request, String url) {
        try {
            final HttpPost httpPost = new HttpPost(url);
            StringEntity entity = new StringEntity(request);
            httpPost.setEntity(entity);
            httpPost.setHeader("Content-type", "application/soap+xml; charset=utf-8");
            try (CloseableHttpClient client = HttpClients.createDefault();
                 CloseableHttpResponse response = client.execute(httpPost)) {
                return IOUtils.toString(response.getEntity().getContent());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
