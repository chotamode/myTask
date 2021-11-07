package cz.project.demo.service;

import cz.project.demo.dao.UserDao;
import cz.project.demo.model.Role;
import cz.project.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service

public class UserService {

    private final UserDao dao;

    final User currentUser = new User(); // singleton simulating logged-in user

    @Autowired
    public UserService(UserDao dao) {
        this.dao = dao;
    }

    @Transactional
    public void persist(User user) {
        Objects.requireNonNull(user);
        if (user.getRole() == null) {
            user.setRole(Role.USER);
        }
        dao.persist(user);
    }

    @Transactional(readOnly = true)
    public boolean exists(String username) {
        return dao.findByUsername(username) != null;
    }

    public User getCurrentUser() {
        return currentUser;
    }


}
