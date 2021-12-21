package cz.project.demo.service;

import cz.project.demo.dao.CommentDao;
import cz.project.demo.dao.TaskDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {

    @Mock
    private CommentDao commentDaoMock;

    @InjectMocks
    private CommentService sut;

    @BeforeEach
    public void setUp() {
//        this.sut = new CommentService(commentDaoMock);
    }

}
