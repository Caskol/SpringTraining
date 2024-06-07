package org.caskol.warcraft_database.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageUtils {
    private static MessageSource messageSource;
    @Autowired
    public MessageUtils(MessageSource messageSource) {
        this.messageSource=messageSource;
    }

    public static String getMessage(String code){
        return messageSource.getMessage(code,null, LocaleContextHolder.getLocale());
    }
}
