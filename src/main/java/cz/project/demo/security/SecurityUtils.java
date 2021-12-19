package cz.project.demo.security;

import cz.project.demo.model.User;
import cz.project.demo.security.model.UserDetails;
import cz.project.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SecurityUtils {

    private static SecurityContext sc = SecurityContextHolder.getContext();
    private static Authentication auth = sc.getAuthentication();
    private static UserService userService;

    public SecurityUtils(UserService userService) {
        SecurityUtils.userService = userService;
    }

    @Transactional
    public User getCurrentUser() {
        return userService.findByUsername(auth.getName());
    }
}
