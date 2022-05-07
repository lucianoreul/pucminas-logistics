package com.ms.logistics.auth.util;

import org.apache.commons.codec.digest.DigestUtils;

public class CryptoUtil {

    private CryptoUtil() {
        throw new IllegalStateException(CryptoUtil.class.getName());
    }

    public static String hash(String value) {
        try {
            return DigestUtils.sha1Hex(value);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
