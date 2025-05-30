package ru.bmstu.testapp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.bmstu.testapp.domain.Student;
import ru.bmstu.testapp.service.impl.AuditServiceImpl;
import ru.bmstu.testapp.service.impl.StudentServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

public class StudentServiceTest {

    private StudentService studentService;

    @BeforeEach
    public void setup() {
        studentService = new StudentServiceImpl(new AuditServiceImpl());
    }

    @Test
    public void testAddStudent() {
        studentService.addStudent(new Student("Test", "User", 5));
        assertEquals(1, studentService.getAllStudents().size());
    }

    @Test
    public void testRemoveStudent() {
        studentService.addStudent(new Student("Test", "User", 5));
        studentService.removeStudent("Test User");
        assertEquals(0, studentService.getAllStudents().size());
    }

    @Test
    public void testUpdateTokens() {
        studentService.addStudent(new Student("Test", "User", 5));
        studentService.updateTokens("Test User", 3);
        assertEquals(8, studentService.getAllStudents().get(0).getTokens());
    }
}
