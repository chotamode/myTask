package cz.project.demo.service;

import cz.project.demo.dao.TaskDao;
import cz.project.demo.dao.UserDao;
import cz.project.demo.model.Category;
import cz.project.demo.model.Comment;
import cz.project.demo.model.Task;
import cz.project.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class TaskService {

    private final UserDao userDao;
    private final TaskDao taskDao;

    @Autowired
    public TaskService(TaskDao taskDao, UserDao userDao) {
        this.userDao = userDao;
        this.taskDao = taskDao;
    }

    @Transactional(readOnly = true)
    public List<Task> findAll() {
        return taskDao.findAll();
    }

    @Transactional(readOnly = true)
    public List<Task> findAllByCategory(Category category) {
        return taskDao.findAllByCategory(category);
    }

    @Transactional(readOnly = true)
    public Task find(Long id) {
        return taskDao.find(id);
    }

    @Transactional
    public void persist(Task task) {
        User user = task.getOwner();
        user.addTask(task);
        userDao.persist(user);
        taskDao.persist(task);
    }

    @Transactional
    public void update(Task task) {
        taskDao.update(task);
    }

    @Transactional
    public void remove(Task task) {
        Objects.requireNonNull(task);
        task.setRemoved(true);
        taskDao.update(task);
    }

    @Transactional
    public void addComment(Comment comment, Task task) {
        Objects.requireNonNull(task);
        Objects.requireNonNull(comment);
        task.addComment(comment);
        taskDao.update(task);
    }

    @Transactional
    public void setCompleted(String review, LocalDateTime date, Integer stars, Task task) {
        Objects.requireNonNull(task);
        Objects.requireNonNull(review);
        task.setCompleted(true);
        task.setReview(review, date, stars);
        taskDao.update(task);
    }

}
