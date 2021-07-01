package com.flamexander.spring.security.cookbook.dao.controllers;

import com.flamexander.spring.security.cookbook.dao.entities.User;
import com.flamexander.spring.security.cookbook.dao.services.TestService;
import com.flamexander.spring.security.cookbook.dao.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class DemoController {
    private final UserService userService;
    private final TestService testService;

    @GetMapping("/")
    public String homePage() {
        return "home";
    }

    @GetMapping("/unsecured")
    public String usecuredPage() {
        return "unsecured";
    }

    @GetMapping("/auth_page")
    public String authenticatedPage() {
        return "authenticated";
    }

    @GetMapping("/admin")
    // @PreAuthorize("hasRole('ADMIN')")
    public String adminPage() {
        return "admin";
    }

    @GetMapping("/user_info")
    public String daoTestPage(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("Unable to find user by username: " + principal.getName()));
        return "Authenticated user info: " + user.getUsername() + " : " + user.getEmail();
    }

    @GetMapping("/test/create-user")
    @PreAuthorize("hasAuthority('CREATE_USER')")
    public String createUser() {
        return "create user";
    }

    @GetMapping("/test/delete-account")
    @PreAuthorize("hasAuthority('DELETE_ACCOUNT')")
    public String deleteAccount() {
        return "delete account";
    }

    @GetMapping("/test/read-all-messages")
    @PreAuthorize("hasAuthority('READ_ALL_MESSAGES')")
    public String readAllMessages() {
        return "read all messages";
    }

    @GetMapping("/test/secured-by-role")
    public String securedByRoleMethod() {
        return testService.SecuredByRoleMethod();
    }

    @GetMapping("/test/secured-by-authority")
    public String SecuredByAuthorityMethod() {
        return testService.SecuredByAuthorityMethod();
    }

    @GetMapping("/get-user")
    public User getUser(Principal principal) {
        return userService.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("Unable to find user by username: " + principal.getName()));
    }

    @GetMapping("/test/calc-data")
    public int calcData(int a, int b) {
        return a + b;
    }
}
