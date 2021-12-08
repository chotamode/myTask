package cz.project.demo;

import cz.project.demo.model.User;
import cz.project.demo.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class MyTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyTaskApplication.class, args);
    }

    @Bean
    CommandLineRunner init (UserService userService){
        return args -> {
            User user1 = new User("name1", "surname1", "nickname1", "password1");
            User user2 = new User("name2", "surname2", "nickname2", "password2");
            if(!userService.exists(user1.getNickname())){
                userService.persist(user1);
            }
            if(!userService.exists(user2.getNickname())){
                userService.persist(user2);
            }
        };
    }

}
