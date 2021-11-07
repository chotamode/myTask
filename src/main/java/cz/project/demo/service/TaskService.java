package cz.project.demo.service;

import cz.project.demo.dao.TaskDao;
import cz.project.demo.model.Category;
import cz.project.demo.model.Comment;
import cz.project.demo.model.Review;
import cz.project.demo.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class TaskService {

    private final TaskDao dao;

    @Autowired
    public TaskService(TaskDao dao) {
        this.dao = dao;
    }

    @Transactional(readOnly = true)
    public List<Task> findAll() {
        return dao.findAll();
    }

    @Transactional(readOnly = true)
    public List<Task> findAll(Category category) {
        return dao.findAll(category);
    }

    @Transactional(readOnly = true)
    public Task find(Integer id) {
        return dao.find(id);
    }

    @Transactional
    public void persist(Task task) {
        dao.persist(task);
    }

    @Transactional
    public void update(Task task) {
        dao.update(task);
    }

    @Transactional
    public void remove(Task task) {
        Objects.requireNonNull(task);
        task.setRemoved(true);
        dao.update(task);
    }

    @Transactional
    public void addComment(Comment comment, Task task){
        Objects.requireNonNull(task);
        Objects.requireNonNull(comment);
        task.addComment(comment);
        dao.update(task);
    }

    @Transactional
    public void setCompleted(Review review, Task task){
        Objects.requireNonNull(task);
        Objects.requireNonNull(review);
        task.setCompleted(true);
        dao.update(task);
    }
}
