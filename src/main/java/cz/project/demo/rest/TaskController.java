package cz.project.demo.rest;

import cz.project.demo.model.Task;
import cz.project.demo.rest.utils.RestUtils;
import cz.project.demo.service.TaskService;
import cz.project.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/tasks")
public class TaskController {

    private static final Logger LOG = LoggerFactory.getLogger(TaskController.class);

    private final TaskService taskService;
    private final UserService userService;
    private final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createTask(@RequestBody Task task){
        taskService.persist(task);
        LOG.debug("Created task {}.", task);
        final HttpHeaders headers = RestUtils.createLocationHeaderFromCurrentUri("/{id}", task.getId());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);

    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/{id}")
    public ResponseEntity<Task> takeOnTask(@PathVariable(value = "id") Long taskId){
        Task task = taskService.find(taskId);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        task.setPerformer(userService.findByUsername(authentication.getName()));
        taskService.update(taskService.find(taskId));
        return new ResponseEntity<Task>(HttpStatus.OK);
    }

}
