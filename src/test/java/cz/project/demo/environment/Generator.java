package cz.project.demo.environment;

import cz.project.demo.model.*;

import java.util.ArrayList;
import java.util.Date;
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
        user.setNickname("username" + randomInt() + "@kbss.felk.cvut.cz");
        user.setPassword(Integer.toString(randomInt()));
        user.setTasks(tasks);
        user.setRole(Role.GUEST);

        return user;
    }

    public static Task generateTask(User user, Review review, Address address) {
        final Task t = new Task();
        t.setOwner(user);
        t.setTask("Task" + randomInt());
        t.setPrice(randomInt());
        t.setName("Some Name" + randomInt());
        t.setRemoved(false);
        t.setAddress(address);
        t.setReview(review);
        return t;
    }

    public static Review generateReview(User user){
        final Review r = new Review();
        r.setStars(randomInt());
        r.setReview("review" + randomInt());
        r.setOwner(user);
        r.setDate(new Date());
        return r;
    }

    public static Comment generateComment(User user){
        final Comment c = new Comment();
        c.setComment("xd lmao!!!hehe");
        c.setDate(new Date());
        c.setAuthor(user);
        return c;
    }
}

