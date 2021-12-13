package cz.project.demo.service;

import cz.project.demo.dao.UserDao;
import cz.project.demo.model.Role;
import cz.project.demo.model.Task;
import cz.project.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class UserService implements UserDetailsService {

    private final UserDao dao;

    final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserDao dao, PasswordEncoder passwordEncoder) {
        this.dao = dao;
        this.passwordEncoder = passwordEncoder;
    }

    public void addTask(User user, Task toAdd) {
        Objects.requireNonNull(toAdd);
        Objects.requireNonNull(user);
        user.addTask(toAdd);
        dao.update(user);
    }

    public void removeTask(User user, Task toRemove) {
        Objects.requireNonNull(toRemove);
        Objects.requireNonNull(user);
        user.removeTask(toRemove);
        dao.update(user);
    }

    @Transactional
    public void persist(User user) {
        Objects.requireNonNull(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        dao.persist(user);
    }

    @Transactional(readOnly = true)
    public boolean exists(String username) {
        return dao.findByUsername(username) != null;
    }

    @Transactional
    public List<User> findAll(){
        return dao.findAll();
    }

    @Transactional
    public User findByUsername(String username){
        return dao.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return dao.findByUsername(s);
    }
}
