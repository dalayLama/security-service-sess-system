package com.sess.security.config;

import com.sess.security.utils.ErrorsCodes;
import com.sess.security.components.message.InMemoryMessageService;
import com.sess.security.components.message.Locale;
import com.sess.security.components.message.Message;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class MessageServiceConfiguration {

    @Bean
    public InMemoryMessageService memoryMessageService(
            @Qualifier("notFoundMessage") Message notFoundMessage,
            @Qualifier("notFoundErrorMessage") Message notFoundErrorMessage
    ) {
        return new InMemoryMessageService(Locale.RU, notFoundMessage, notFoundErrorMessage);
    }

    @Bean(name = "notFoundMessage")
    public Message notFoundMessage() {
        return new Message(Map.of(
                Locale.RU, "Неизвестная операция"
        ));
    }

    @Bean(name = "notFoundErrorMessage")
    public Message notFoundErrorMessage() {
        return new Message(ErrorsCodes.UNKNOWN_ERROR, Map.of(
                Locale.RU, "Неизвестная ошибка"
        ));
    }

}
