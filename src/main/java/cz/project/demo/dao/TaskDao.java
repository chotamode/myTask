package cz.project.demo.dao;

import cz.project.demo.model.Task;
import cz.project.demo.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class TaskDao extends BaseDao{

    public TaskDao() {
        super(Task.class);
    }

    public List<Task> findAllTasksByAuthor(User author) {
        try {
            return em.createNamedQuery("Task.findAllTasksByAuthor", Task.class).setParameter("owner", author)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }



}
