package com.ms.logistics.auth.security;

import com.ms.logistics.auth.dto.LoggedUserDTO;
import com.ms.logistics.auth.security.authorization.Authorization;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    private static final String OPERATION_PATTERN = "%s-%s";

    public boolean allow(Object instance, String operation) {
        return allow(false, instance, operation);
    }

    public boolean allow(boolean custom, Object instance, String operation) {
        String wildcard = String.format(OPERATION_PATTERN, operation.toLowerCase(), "*");

        if (!custom) {
            Authorization auth = AnnotationUtils.findAnnotation(instance.getClass(), Authorization.class);

            if (auth != null) {
                operation = String.format(OPERATION_PATTERN, operation.toLowerCase(), auth.value().toLowerCase());
            }
        }

        String role = getCurrentRoles();

        return role.contains(wildcard) || role.contains(operation);
    }

    protected String getCurrentRoles() {
        return getCurrentUser().getRole();
    }


    public LoggedUserDTO getCurrentUser() {
        return (LoggedUserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
