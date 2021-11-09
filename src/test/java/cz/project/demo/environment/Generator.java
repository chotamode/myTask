package cz.project.demo.environment;

import cz.project.demo.model.Task;
import cz.project.demo.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generator {

    private static final Random RAND = new Random();

    public static int randomInt() {
        return RAND.nextInt();
    }

    public static boolean randomBoolean() {
        return RAND.nextBoolean();
    }

    public static User generateUser() {
        List<Task> tasks = new ArrayList<>();
        final User user = new User();
        user.setFirstName("FirstName" + randomInt());
        user.setLastName("LastName" + randomInt());
        user.setUsername("username" + randomInt() + "@kbss.felk.cvut.cz");
        user.setPassword(Integer.toString(randomInt()));
        user.setTasks(tasks);

        return user;
    }

    public static Task generateTask(User user) {
        final Task t = new Task();
        t.setOwner(user);
        t.setTask("Task" + randomInt());
        t.setPrice(randomInt());
        t.setName("Some Name" + randomInt());
        t.setRemoved(false);
        return t;
    }
}

