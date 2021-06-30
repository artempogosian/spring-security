package com.flamexander.spring.security.cookbook.dao.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestService {
    @Secured({"ROLE_ADMIN"})
    public String SecuredByRoleMethod() {
        return "role admin";
    }

    @Secured({"READ_ALL_MESSAGES"})
    public String SecuredByAuthorityMethod() {
        return "read all messages";
    }
}
