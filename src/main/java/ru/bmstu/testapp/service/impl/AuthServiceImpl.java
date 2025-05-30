package ru.bmstu.testapp.service.impl;

import org.springframework.stereotype.Service;
import ru.bmstu.testapp.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private String fullName;
    private String role;

    @Override
    public void login(String firstName, String lastName, String role) {
        this.fullName = firstName + " " + lastName;
        this.role = role.toLowerCase();
    }

    @Override
    public String getCurrentUserFullName() {
        return fullName;
    }

    @Override
    public String getCurrentUserRole() {
        return role;
    }

    @Override
    public boolean isTeacher() {
        return "teacher".equalsIgnoreCase(role);
    }
}
