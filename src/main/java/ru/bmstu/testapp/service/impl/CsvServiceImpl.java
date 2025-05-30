package ru.bmstu.testapp.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import ru.bmstu.testapp.domain.Student;
import ru.bmstu.testapp.service.CsvService;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvServiceImpl implements CsvService {

    private final Resource resource;

    public CsvServiceImpl(@Value("classpath:students.csv") Resource resource) {
        this.resource = resource;
    }

    @Override
    public List<Student> loadStudents() {
        List<Student> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            reader.readLine(); // skip header
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                students.add(new Student(parts[0], parts[1], Integer.parseInt(parts[2])));
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read students CSV", e);
        }
        return students;
    }

    @Override
    public void saveStudents(List<Student> students) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(resource.getFile(), StandardCharsets.UTF_8))) {
            writer.println("firstName,lastName,tokens");
            for (Student s : students) {
                writer.println(s.getFirstName() + "," + s.getLastName() + "," + s.getTokens());
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to save students CSV", e);
        }
    }
}
