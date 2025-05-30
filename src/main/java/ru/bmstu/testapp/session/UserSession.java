package ru.bmstu.testapp.session;

import org.springframework.stereotype.Component;

@Component
public class UserSession{

    private String currentUserFullName;
    private String currentRole;

    public void setSession(String firstName,String lastName, String role) {
        this.currentUserFullName = firstName + " " + lastName;
        this.currentRole = role.toLowerCase(); // нормализуем
    }

    public String getCurrentUserFullName() {
        return currentUserFullName;
    }

    public String getCurrentRole() {
        return currentRole;
    }

    public boolean isTeacher() {
        return "teacher".equalsIgnoreCase(currentRole);
    }
}
