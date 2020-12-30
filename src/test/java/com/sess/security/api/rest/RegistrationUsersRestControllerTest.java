package com.sess.security.api.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sess.security.TestUtils;
import com.sess.security.exceptions.Error;
import com.sess.security.exceptions.ErrorBuilder;
import com.sess.security.exceptions.ErrorMessage;
import com.sess.security.models.user.RegisteredCoreUser;
import com.sess.security.models.user.TelegramUser;
import com.sess.security.users.registration.RegistrationManager;
import com.sess.security.users.registration.exceptions.ValidateException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class RegistrationUsersRestControllerTest {

    private static final RegisteredCoreUser REGISTERED_CORE_USER = TestUtils.createRegisteredCoreUser();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RegistrationManager registrationManager;

    @Test
    public void shouldRegisterUser() throws Exception {
        when(registrationManager.telegramRegister(any(TelegramUser.class))).thenReturn(REGISTERED_CORE_USER);
        String json = createJsonTelegramUser();

        MvcResult mvcResult = mockMvc
                .perform(post("/api/users/telegram")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andReturn();

        String jsonResult = mvcResult.getResponse().getContentAsString();
        verify(registrationManager).telegramRegister(any(TelegramUser.class));
        assertThat(jsonResult).isEqualTo(objectMapper.writeValueAsString(REGISTERED_CORE_USER));
    }

    @Test
    public void shouldReturnValidateErrors() throws Exception {
        Error error = ErrorBuilder.newBuilder(false)
                .addMessage("1", "message 1")
                .addMessage("2", "message 2")
                .build();

        when(registrationManager.telegramRegister(any(TelegramUser.class))).thenThrow(new ValidateException(error));
        String json = createJsonTelegramUser();

        MvcResult mvcResult = mockMvc
                .perform(post("/api/users/telegram")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andReturn();
        Error responseError = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Error.class);
        List<ErrorMessage> listMessages = new ArrayList<>(responseError.getMessages());

        verify(registrationManager).telegramRegister(any(TelegramUser.class));
        assertThat(listMessages).containsExactlyInAnyOrderElementsOf(error.getMessages());
    }

    private String createJsonTelegramUser() throws JsonProcessingException {
        TelegramUser telegramUser = TestUtils.createTelegramUser();
        return objectMapper.writeValueAsString(telegramUser);
    }

}