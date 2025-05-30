package ru.bmstu.testapp.service;

public interface AuthService {
    void login(String firstName, String lastName, String role);
    String getCurrentUserFullName();
    String getCurrentUserRole();
    boolean isTeacher();
}
