package cz.project.demo.service;

import cz.project.demo.dao.UserDao;
import cz.project.demo.environment.Generator;
import cz.project.demo.model.Role;
import cz.project.demo.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserDao userDaoMock;

    private UserService sut;

    @BeforeEach
    public void setUp() {
        this.sut = new UserService(userDaoMock);
    }

    @Test
    public void persistCreatesTaskForRegularUser() {
        final User user = Generator.generateUser();
        user.setRole(Role.USER);
        user.addTask(Generator.generateTask(user));
        sut.persist(user);

        final ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userDaoMock).persist(captor.capture());
        assertNotNull(captor.getValue().getTasks());
    }

    @Test
    public void persistCreatesCartForGuestUser() {
        final User user = Generator.generateUser();
        user.setRole(Role.GUEST);
        user.addTask(Generator.generateTask(user));
        sut.persist(user);

        final ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userDaoMock).persist(captor.capture());
        assertNotNull(captor.getValue().getTasks());
    }

    @Test
    public void persistSetsUserRoleToDefaultWhenItIsNotSpecified() {
        final User user = Generator.generateUser();
        user.setRole(null);
        sut.persist(user);

        final ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userDaoMock).persist(captor.capture());
        assertEquals(Role.USER, captor.getValue().getRole());
    }
}
