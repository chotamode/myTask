package cz.project.demo.dao;

import cz.project.demo.MyTaskApplication;
import cz.project.demo.environment.Generator;
import cz.project.demo.model.Address;
import cz.project.demo.model.Review;
import cz.project.demo.model.Task;
import cz.project.demo.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan(basePackageClasses = MyTaskApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TaskDaoTest {

    @Autowired
    private TestEntityManager em;

    @Autowired
    private TaskDao sut;

    @Autowired
    private UserDao userDao;

    @Test
    public void findAllByAuthor(){
        final User user = Generator.generateUser();

        final Task task = Generator.generateTask(user, review, address);

        userDao.persist(user);
        sut.persist(task);

        Assert.assertEquals(task, sut.findAllTasksByAuthor(user).get(0));
    }
}
