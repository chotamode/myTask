package cz.project.demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.project.demo.dao.TaskDao;

import cz.project.demo.environment.Generator;
import cz.project.demo.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

/**
 * This test does not use Spring.
 * <p>
 * It showcases how services can be unit tested without being dependent on the application framework or database.
 */
@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskDao taskDaoMock;

    @InjectMocks
    private TaskService sut;

    @BeforeEach
    public void setUp() {
        this.sut = new TaskService(taskDaoMock);
    }

/*
    @Test
    public void removeSetsRemovedStatusOnTask() {
        final Task task = Generator.generateTask(Generator.generateUser(), new Review(), new Address());
        assertFalse(task.isRemoved());
        sut.remove(task);

        final ArgumentCaptor<Task> captor = ArgumentCaptor.forClass(Task.class);
        verify(taskDaoMock).update(captor.capture());
        assertTrue(captor.getValue().isRemoved());
    }*/

    /*
    @Test
    public void setCompletedSetsCompletedAndSetsReview(){
        final User user = Generator.generateUser();
        final Task task = Generator.generateTask(user, new Review(), new Address());
        assertFalse(task.isCompleted());
        sut.setCompleted(task);

        final ArgumentCaptor<Task> captor = ArgumentCaptor.forClass(Task.class);
        verify(taskDaoMock).update(captor.capture());
        assertTrue(captor.getValue().isCompleted());
        assertEquals(review, captor.getValue().getReview());
    }*/

    /*
    @Test
    public void addComment(){
        final User user = Generator.generateUser();
        final Task task = Generator.generateTask(user, new Review(), new Address());
        final Comment comment =Generator.generateComment(Generator.generateUser());
        assertTrue(task.getComments().isEmpty());

        sut.addComment(comment, task);

        final ArgumentCaptor<Task> captor = ArgumentCaptor.forClass(Task.class);
        verify(taskDaoMock).update(captor.capture());
        assertEquals(comment, captor.getValue().getComments().get(0));
    }*/
}

