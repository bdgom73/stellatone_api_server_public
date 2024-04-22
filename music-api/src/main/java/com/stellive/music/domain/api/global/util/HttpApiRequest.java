package com.stellive.music.domain.api.global.util;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class HttpApiRequest {

    public static  <T> T get(String url, HttpHeaders headers, Class<T> responseType) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<T> exchange = restTemplate.exchange(
                url,
                HttpMethod.GET,
                requestEntity,
                responseType
        );

        return exchange.getBody();
    }

    public static  <T> T get(String url, HttpHeaders headers, ParameterizedTypeReference<T> responseType) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<T> exchange =
                restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        requestEntity,
                        responseType
                );
        return exchange.getBody();
    }

    public static <T, V> T post(String url, V data, HttpHeaders headers, Class<T> responseType) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<V> requestEntity = new HttpEntity<>(data, headers);
        ResponseEntity<T> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                responseType
        );

        return responseEntity.getBody();
    }

    public static <T, V> T post(String url, V data, HttpHeaders headers, ParameterizedTypeReference<T> responseType) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<V> requestEntity = new HttpEntity<>(data, headers);
        ResponseEntity<T> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                responseType
        );

        return responseEntity.getBody();
    }

}
