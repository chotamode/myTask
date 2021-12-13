package cz.project.demo.rest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/")
    public String home(){
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
    }

    @GetMapping("/user")
    public String user(){
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
    }

}
