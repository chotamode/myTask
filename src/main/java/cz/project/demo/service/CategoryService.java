package cz.project.demo.service;


import cz.project.demo.dao.CategoryDao;
import cz.project.demo.dao.TaskDao;
import cz.project.demo.model.Category;
import cz.project.demo.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class CategoryService {

    private final CategoryDao categoryDao;

    private final TaskDao taskDao;

    @Autowired
    public CategoryService(CategoryDao categoryDao, TaskDao taskDao) {
        this.categoryDao = categoryDao;
        this.taskDao = taskDao;
    }

    @Transactional(readOnly = true)
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    @Transactional(readOnly = true)
    public Category find(Integer id) {
        return categoryDao.find(id);
    }

    @Transactional
    public void persist(Category category) {
        Objects.requireNonNull(category);
        categoryDao.persist(category);
    }

    @Transactional
    public void addTask(Category category, Task task) {
        Objects.requireNonNull(category);
        Objects.requireNonNull(task);
        task.addCategory(category);
        taskDao.update(task);
    }

    @Transactional
    public void removeTask(Category category, Task task) {
        Objects.requireNonNull(category);
        Objects.requireNonNull(task);
        task.removeCategory(category);
        taskDao.update(task);
    }

}