package ru.bmstu.testapp.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import ru.bmstu.testapp.session.UserSession;

@Aspect
@Component
public class RoleCheckAspect {

    private final UserSession userSession;

    public RoleCheckAspect(UserSession userSession) {
        this.userSession = userSession;
    }

    @Before("@annotation(ru.bmstu.testapp.aspect.RequiresTeacher)")
    public void checkTeacherRole() {
        String role = userSession.getCurrentRole();
        if (role == null || !"teacher".equalsIgnoreCase(role)) {
            throw new SecurityException("Access denied: this operation requires TEACHER role.");
        }
    }
}
