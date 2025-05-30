package ru.bmstu.testapp.service.impl;

import org.springframework.stereotype.Service;
import ru.bmstu.testapp.service.AuditService;

import java.time.LocalDateTime;

@Service
public class AuditServiceImpl implements AuditService {
    @Override
    public void log(String message) {
        System.out.println(LocalDateTime.now() + " AUDIT: " + message);
    }
}