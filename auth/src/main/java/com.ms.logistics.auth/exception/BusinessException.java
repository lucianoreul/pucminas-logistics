package com.ms.logistics.auth.exception;

import com.ms.logistics.auth.util.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

public class BusinessException extends Exception {

    private static final long serialVersionUID = 1L;

    private String code;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public BusinessException(String code) {
        this(code, LocaleContextHolder.getLocale());
    }

    public BusinessException(String code, Locale locale) {
        this(code, locale, null);
    }

    public BusinessException(String code, Locale locale, Exception e) {
        super(MessageUtil.findMessage(code, locale), e);
        this.code = code;

        logger.error(this.getMessage());
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
