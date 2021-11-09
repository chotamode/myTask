package cz.project.demo.service;

import cz.project.demo.dao.TaskDao;
import cz.project.demo.environment.Generator;
import cz.project.demo.model.Task;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

/**
 * This test does not use Spring.
 * <p>
 * It showcases how services can be unit tested without being dependent on the application framework or database.
 */
@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private TaskDao productDaoMock;

    @InjectMocks
    private TaskService sut;

    @Test
    public void removeSetsRemovedStatusOnTask() {
        final Task task = Generator.generateTask(Generator.generateUser());
        assertFalse(task.isRemoved());
        sut.remove(task);

        final ArgumentCaptor<Task> captor = ArgumentCaptor.forClass(Task.class);
        verify(productDaoMock).update(captor.capture());
        assertTrue(captor.getValue().isRemoved());
    }
}

