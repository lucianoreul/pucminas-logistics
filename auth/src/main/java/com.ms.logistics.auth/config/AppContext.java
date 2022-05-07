package com.ms.logistics.auth.config;

import org.springframework.context.ApplicationContext;

public class AppContext {

    private AppContext() {
        throw new IllegalStateException(AppContext.class.getName());
    }

    private static ApplicationContext ctx;

    public static void loadApplicationContext(ApplicationContext appCtx) {
        ctx = appCtx;
    }

    public static <T> T getBean(Class<T> clazz) {
        return ctx.getBean(clazz);
    }
}
