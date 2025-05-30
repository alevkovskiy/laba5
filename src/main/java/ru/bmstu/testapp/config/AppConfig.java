package ru.bmstu.testapp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("ru.bmstu.testapp")
@EnableAspectJAutoProxy
public class AppConfig {
}
