package ru.bmstu.testapp.service;

import ru.bmstu.testapp.domain.Student;

import java.util.List;

public interface CsvService {
    List<Student> loadStudents();
    void saveStudents(List<Student> students);
}
