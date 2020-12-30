package com.sess.security.components.message;

import com.sess.security.exceptions.ErrorMessage;

/**
 * The service which helping to provide messages which depend on the locale
 */
public interface MessageService {

    String say(MessageId messageId);

    ErrorMessage sayError(MessageId messageId);

    String say(Locale locale, MessageId messageId);

    ErrorMessage sayError(Locale locale, MessageId messageId);

}
