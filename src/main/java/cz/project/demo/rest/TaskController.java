package cz.project.demo.rest;

import cz.project.demo.model.AcceptanceMessage;
import cz.project.demo.model.Comment;
import cz.project.demo.model.Task;
import cz.project.demo.rest.utils.RestUtils;
import cz.project.demo.security.SecurityUtils;
import cz.project.demo.service.CommentService;
import cz.project.demo.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private static final Logger LOG = LoggerFactory.getLogger(TaskController.class);

    private final TaskService taskService;
    private final CommentService commentService;
    private final SecurityUtils securityUtils;

    public TaskController(TaskService taskService, CommentService commentService, SecurityUtils securityUtils) {
        this.taskService = taskService;
        this.commentService = commentService;
        this.securityUtils = securityUtils;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createTask(@RequestBody Task task){

        taskService.createTask(task);

        LOG.debug("Created task {}.", task);
        final HttpHeaders headers = RestUtils.createLocationHeaderFromCurrentUri("/{id}", task.getId());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);

    }

    @PostMapping("/{id}/acceptance_message")
    public ResponseEntity<Task> sendAcceptanceMessage(@PathVariable(value = "id") Long taskId,
                                                      @RequestBody AcceptanceMessage message){
        taskService.sendAcceptanceMessage(message, taskId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * @return a list of tasks that the user can perform that do not belong to him
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Task> getAllOthersTasks(){
        return taskService.findAll()
                .stream()
                .filter(t -> !t.getOwner().getUsername().equals(securityUtils.getCurrentUser().getUsername()))
                .collect(Collectors.toList());
    }

    /**
     * @return a list of tasks that belong to him
     */
    @GetMapping(value = "/my_tasks", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Task> getAllMyTasks(){
        return taskService.findAll()
                .stream()
                .filter(t -> t.getOwner().getUsername().equals(securityUtils.getCurrentUser().getUsername()))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}/acceptanceMessages", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AcceptanceMessage> getAcceptanceMessages(@PathVariable(value = "id") Long taskId){
        return taskService.getAcceptanceMessages(taskId);
    }

    @GetMapping(value = "/{id}/acceptanceMessages/{message_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public AcceptanceMessage getAcceptanceMessage(@PathVariable(value = "id") Long taskId,
                                                  @PathVariable(value = "message_id") Long message_id){

        taskService.getAcceptanceMessageById(taskId, message_id);

        return taskService.getAcceptanceMessageById(taskId, message_id);
    }

    @PutMapping("/{id}/acceptanceMessages/{message_id}")
    public ResponseEntity<Task> approveMessage(@PathVariable(value = "id") Long taskId,
                                           @PathVariable(value = "message_id") Long message_id){

        taskService.approveMessage(taskId, message_id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}/owner_completed")
    public ResponseEntity<Task> taskCompletedOwner(@PathVariable(value = "id") Long taskId,
                                                   @RequestBody String review,
                                                   @RequestBody Integer ownerStars){

        taskService.taskCompletedOwner(taskId, review, ownerStars);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}/performer_completed")
    public ResponseEntity<Task> taskCompletedPerformer(@PathVariable(value = "id") Long taskId,
                                                       @RequestBody String review,
                                                       @RequestBody Integer performerStars){

        taskService.taskCompletedPerformer(taskId, review, performerStars);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}/comments")
    public List<Comment> getAllComments(@PathVariable(value = "id") Long taskId){
        return commentService.getAllComments(taskId);
    }

    @GetMapping("/{id}/comments/{comment_id}")
    public Comment getAllComments(@PathVariable(value = "id") Long taskId, @PathVariable(value = "comment_id") Long commentId){
        return commentService.getCommentById(taskId, commentId);
    }

//    @PostMapping("/{id}/comments")
//    @DeleteMapping("/{id}/comments")
//    @PutMapping("/{id}/comments")
}
