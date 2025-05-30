package ru.bmstu.testapp.service.impl;

import org.springframework.stereotype.Service;
import ru.bmstu.testapp.aspect.RequiresTeacher;
import ru.bmstu.testapp.domain.Student;
import ru.bmstu.testapp.service.AuditService;
import ru.bmstu.testapp.service.StudentService;

import java.util.*;

@Service
public class StudentServiceImpl implements StudentService {

    private final Map<String, Student> studentMap = new HashMap<>();
    private final AuditService auditService;

    public StudentServiceImpl(AuditService auditService) {
        this.auditService = auditService;
    }

    @Override
    public void loadInitialStudents(List<Student> students) {
        for (Student s : students) {
            studentMap.put(s.getFullName(), s);
        }
    }

    @Override
    @RequiresTeacher
    public void addStudent(Student student) {
        studentMap.put(student.getFullName(), student);
        auditService.log("Added student: " + student.getFullName());
    }

    @Override
    @RequiresTeacher
    public void removeStudent(String fullName) {
        studentMap.remove(fullName);
        auditService.log("Removed student: " + fullName);
    }

    @Override
    public void updateTokens(String fullName, int delta) {
        Student student = studentMap.get(fullName);
        if (student != null) {
            student.setTokens(student.getTokens() + delta);
            auditService.log("Updated tokens for " + fullName + ": " + delta);
        }
    }

    @Override
    public List<Student> getAllStudents() {
        return new ArrayList<>(studentMap.values());
    }
}
