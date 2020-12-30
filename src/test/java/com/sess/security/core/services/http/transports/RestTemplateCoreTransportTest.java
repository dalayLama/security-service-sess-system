package com.sess.security.core.services.http.transports;

import org.junit.jupiter.api.Test;

class RestTemplateCoreTransportTest {

    private static final String ANY_IP = "any_ip";

    private static final int ANY_PORT = 999;

    private static final String ANY_URL = "any_url";

    @Test
    public void shouldReturnExpectedException() {
//        MessageService messageService = spy(new InMemoryMessageService(defaultLocale, notFoundMessage, notFoundErrorMessage));
//        SimpleErrorHandler errorHandler = spy(new SimpleErrorHandler(new ObjectMapper(), messageService));
//        Error expectedError = ErrorBuilder.newBuilder(false)
//                .addMessage("code1", "message 1")
//                .addMessage("code2", "message 2")
//                .build();
//
//        RestTemplateBuilder builder = mock(RestTemplateBuilder.class);
//
//        RestTemplate rt = spy(new RestTemplate());
//        MockRestServiceServer mockServer =
//                MockRestServiceServer.bindTo(rt).build();
//
//        Object anyObject = "any_object";
//        HttpEntity<String> entity = new HttpEntity<>("tets");
//
//        when(builder.build()).thenReturn(rt);
//
//        when(rt.exchange(
//                ArgumentMatchers.anyString(),
//                ArgumentMatchers.any(HttpMethod.class),
//                ArgumentMatchers.any(),
//                ArgumentMatchers.<Class<String>>any())
//        ).thenThrow(new CoreServiceException(HttpStatus.BAD_REQUEST, expectedError));
//        RestTemplateCoreTransport transport = new RestTemplateCoreTransport(
//                ANY_IP,
//                ANY_PORT,
//                errorHandler,
//                builder
//        );

//        CoreServiceException thrown = assertThrows(
//                CoreServiceException.class,
//                () -> transport.exchange(ANY_URL, HttpMethod.POST, anyObject, RegisteredCoreUser.class)
//        );

//        verify(transport).exchange(ANY_URL, HttpMethod.POST, anyObject, RegisteredCoreUser.class);
//        assertThat(thrown.getResponse()).isSameAs(expectedError);
//        assertThat(thrown.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST);

    }

}