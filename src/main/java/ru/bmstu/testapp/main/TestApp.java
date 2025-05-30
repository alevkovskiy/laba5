package ru.bmstu.testapp.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.bmstu.testapp.config.AppConfig;
import ru.bmstu.testapp.domain.Student;
import ru.bmstu.testapp.service.*;
import ru.bmstu.testapp.session.UserSession;

import java.util.List;
import java.util.Scanner;

public class TestApp {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        AuthService authService = context.getBean(AuthService.class);
        StudentService studentService = context.getBean(StudentService.class);
        CsvService csvService = context.getBean(CsvService.class);
        UserSession userSession = context.getBean(UserSession.class);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter role (student/teacher): ");
        String role = scanner.nextLine();

        userSession.setSession(firstName, lastName, role);

        authService.login(firstName, lastName, role);

        List<Student> loaded = csvService.loadStudents();
        studentService.loadInitialStudents(loaded);


        while (true) {
            System.out.println("\nOptions: add / remove / update / list / exit");
            String command = scanner.nextLine();

            try {
                switch (command) {
                    case "add" -> {
                        System.out.print("First name: ");
                        String fn = scanner.nextLine();
                        System.out.print("Last name: ");
                        String ln = scanner.nextLine();
                        System.out.print("Tokens: ");
                        int tokens = Integer.parseInt(scanner.nextLine());
                        studentService.addStudent(new Student(fn, ln, tokens));
                    }
                    case "remove" -> {
                        System.out.print("Full name: ");
                        studentService.removeStudent(scanner.nextLine());
                    }
                    case "update" -> {
                        System.out.print("Full name: ");
                        String name = scanner.nextLine();
                        System.out.print("Token delta (+/-): ");
                        int delta = Integer.parseInt(scanner.nextLine());
                        studentService.updateTokens(name, delta);
                    }
                    case "list" -> {
                        studentService.getAllStudents()
                                .forEach(s -> System.out.printf("%s: %d tokens%n", s.getFullName(), s.getTokens()));
                    }
                    case "exit" -> {
                        csvService.saveStudents(studentService.getAllStudents());
                        context.close();
                        return;
                    }
                    default -> System.out.println("Unknown command");
                }
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
    }
}
