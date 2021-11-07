package cz.project.demo.dao;

import cz.project.demo.model.Category;

public class CategoryDao extends BaseDao<Category>{
    public CategoryDao() {
        super(Category.class);
    }
}
