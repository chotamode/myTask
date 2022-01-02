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
    public Category find(Long id) {
        return categoryDao.find(id);
    }

    @Transactional
    public void persist(Category category) {
        Objects.requireNonNull(category);
        categoryDao.persist(category);
    }

    @Transactional
    public void deleteCategory(Long id) {
        categoryDao.remove(categoryDao.find(id));
    }

    @Transactional
    public void addTask(Category category, Long taskId) { //not sure
        Objects.requireNonNull(category);
        Objects.requireNonNull(taskDao.find(taskId));
        taskDao.find(taskId).addCategory(category);
        taskDao.update(taskDao.find(taskId));
    }

    @Transactional
    public void removeTask(Long categoryId, Long taskId) { // need to be completed
        Objects.requireNonNull(categoryDao.find(categoryId));
        Objects.requireNonNull(taskDao.find(taskId));
        taskDao.find(taskId).removeCategory(categoryDao.find(categoryId));
        taskDao.update(taskDao.find(taskId));
    }

}
