package com.sess.security.core.services.http.transports;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestTemplateCoreTransport implements HttpCoreTransport {

    private final String coreIpAddress;

    private final int corePort;

    private final RestTemplate restTemplate;

    public RestTemplateCoreTransport(
            @Value("app.services.core.id") String coreIpAddress,
            @Value("app.services.core.port") int corePort,
            CoreServiceErrorHandler errorHandler,
            RestTemplateBuilder builder) {
        this.coreIpAddress = coreIpAddress;
        this.corePort = corePort;
        this.restTemplate = builder
                .errorHandler(errorHandler)
                .build();
    }

    @Override
    public <T> T get(String urn, Class<T> clazzResponse) {
        return exchange(urn, HttpMethod.GET, clazzResponse);
    }


    @Override
    public <T> T post(String urn, Object object, Class<T> clazzResponse) {
        return exchange(urn, HttpMethod.POST, object, clazzResponse);
    }


    @Override
    public <T> T post(String urn, Class<T> clazzResponse) {
        return exchange(urn, HttpMethod.POST, clazzResponse);
    }


    @Override
    public <T> T put(String urn, Class<T> clazzResponse) {
        return exchange(urn, HttpMethod.PUT, clazzResponse);
    }


    @Override
    public <T> T put(String urn, Object requestObject, Class<T> clazzResponse) {
        return exchange(urn, HttpMethod.PUT, requestObject, clazzResponse);
    }


    @Override
    public <T> T patch(String urn, Class<T> clazzResponse) {
        return exchange(urn, HttpMethod.PATCH, clazzResponse);
    }

    @Override
    public <T> T patch(String urn, Object requestObject, Class<T> clazzResponse) {
        return exchange(urn, HttpMethod.PATCH, requestObject, clazzResponse);
    }

    @Override
    public <T> T exchange(String urn, HttpMethod method, Class<T> type) {
        return restTemplate.exchange(getUrl(urn), method, null, type).getBody();
    }

    @Override
    public <T> T exchange(String urn, HttpMethod method, Object objectRequest, Class<T> type) {
        return restTemplate.exchange(getUrl(urn), method, new HttpEntity<>(objectRequest), type).getBody();
    }


    @Override
    public <T> T exchange(String urn, HttpMethod method, ParameterizedTypeReference<T> type) {
        return restTemplate.exchange(getUrl(urn), method, null, type).getBody();
    }

    @Override
    public <T> T exchange(String urn, HttpMethod method, Object objectRequest, ParameterizedTypeReference<T> type) {
        return restTemplate.exchange(getUrl(urn), method, new HttpEntity<>(objectRequest), type).getBody();
    }

    private String getUrl(String urn) {
        return String.format("%s:%d/%s", coreIpAddress, corePort, urn);
    }

}
