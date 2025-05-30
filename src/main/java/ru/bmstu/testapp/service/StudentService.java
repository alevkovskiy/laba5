package ru.bmstu.testapp.service;

import ru.bmstu.testapp.domain.Student;

import java.util.List;

public interface StudentService {
    void loadInitialStudents(List<Student> students);
    void addStudent(Student student);
    void removeStudent(String fullName);
    void updateTokens(String fullName, int delta);
    List<Student> getAllStudents();
}
