package com.sess.security.core.services.http.transports;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

public interface HttpCoreTransport {

    <T> T get(String urn, Class<T> clazzResponse);

    <T> T post(String urn, Object object, Class<T> clazzResponse);

    <T> T post(String urn, Class<T> clazzResponse);

    <T> T put(String urn, Class<T> clazzResponse);

    <T> T put(String urn, Object requestObject, Class<T> clazzResponse);

    <T> T patch(String urn, Class<T> clazzResponse);

    <T> T patch(String urn, Object requestObject, Class<T> clazzResponse);

    <T> T exchange(String urn, HttpMethod method, ParameterizedTypeReference<T> type);

    <T> T exchange(String urn, HttpMethod method, Object objectRequest, ParameterizedTypeReference<T> type);

    <T> T exchange(String urn, HttpMethod method, Class<T> type);

    <T> T exchange(String urn, HttpMethod method, Object objectRequest, Class<T> type);

}
