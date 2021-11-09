package cz.project.demo.dao;

import cz.project.demo.MyTaskApplication;
import cz.project.demo.environment.Generator;
import cz.project.demo.model.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan(basePackageClasses = MyTaskApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BaseDaoTest {

    @Autowired
    private TestEntityManager em;

    @Autowired
    private CategoryDao sut;

    @Test
    public void persistSavesSpecifiedInstance() {
        final Category cat = generateCategory();
        sut.persist(cat);
        assertNotNull(cat.getId());

        final Category result = em.find(Category.class, cat.getId());
        assertNotNull(result);
        assertEquals(cat.getId(), result.getId());
        assertEquals(cat.getName(), result.getName());
    }

    private static Category generateCategory() {
        final Category cat = new Category();
        cat.setName("Test category " + Generator.randomInt());
        return cat;
    }
}