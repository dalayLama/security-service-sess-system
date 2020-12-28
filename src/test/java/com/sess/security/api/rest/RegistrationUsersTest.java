package com.sess.security.api.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sess.security.TestUtils;
import com.sess.security.models.user.SecurityUser;
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

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class RegistrationUsersTest {

    private static final SecurityUser SECURITY_USER = TestUtils.createSecurityUser();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RegistrationManager registrationManager;

    @Test
    public void shouldRegisterUser() throws Exception {
        when(registrationManager.register(any(TelegramUser.class))).thenReturn(SECURITY_USER);
        String json = createJsonTelegramUser();

        mockMvc
                .perform(post("/api/users/telegram")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
                .andExpect(content().string(""))
                .andExpect(status().isCreated());

        verify(registrationManager).register(any(TelegramUser.class));
    }

    @Test
    public void shouldReturnValidateErrors() throws Exception {
        Set<String> validateMessages = Set.of(
                "message 1",
                "message 2"
        );
        when(registrationManager.register(any(TelegramUser.class))).thenThrow(new ValidateException(validateMessages));
        String json = createJsonTelegramUser();

        MvcResult mvcResult = mockMvc
                .perform(post("/api/users/telegram")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andReturn();
        String[] arrMessages = mvcResult.getResponse().getContentAsString().split(Pattern.quote("\n"));
        List<String> listMessages = Arrays.asList(arrMessages);

        verify(registrationManager).register(any(TelegramUser.class));
        assertThat(listMessages).containsExactlyInAnyOrderElementsOf(validateMessages);
    }

    private String createJsonTelegramUser() throws JsonProcessingException {
        TelegramUser telegramUser = TestUtils.createTelegramUser();
        return objectMapper.writeValueAsString(telegramUser);
    }

}