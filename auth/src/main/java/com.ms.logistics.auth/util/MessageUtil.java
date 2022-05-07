package com.ms.logistics.auth.util;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class MessageUtil {

    private static final Logger LOGGER = Logger.getLogger(MessageUtil.class.getName());

    private MessageUtil() {
    }

    public static String findMessage(String code, Locale locale) {
        Locale.setDefault(Locale.ENGLISH);
        try {
            return ResourceBundle.getBundle("messages", locale).getString(code);
        } catch (Exception ex) {
            LOGGER.log(Level.ALL, ex.toString(), ex);
        }
        return code;
    }
}
