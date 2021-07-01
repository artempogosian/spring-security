package com.flamexander.spring.security.cookbook.dao.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

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

    @GetMapping("/test/calc-data")
    public int calcData(int a, int b) {
        return a + b;
    }
}
