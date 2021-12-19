package cz.project.demo.service;

import cz.project.demo.dao.AcceptanceMessageDao;
import cz.project.demo.dao.TaskDao;
import cz.project.demo.dao.UserDao;
import cz.project.demo.exception.AuthorizationException;
import cz.project.demo.exception.NotFoundException;
import cz.project.demo.exception.TaskException;
import cz.project.demo.model.AcceptanceMessage;
import cz.project.demo.model.Category;
import cz.project.demo.model.Task;
import cz.project.demo.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class TaskService {

    private final TaskDao taskDao;
    private final AcceptanceMessageDao acceptanceMessageDao;
    private final UserDao userDao;
    private final SecurityUtils securityUtils;

    @Autowired
    public TaskService(TaskDao taskDao, AcceptanceMessageDao acceptanceMessageDao, UserDao userDao, SecurityUtils securityUtils) {
        this.taskDao = taskDao;
        this.acceptanceMessageDao = acceptanceMessageDao;
        this.userDao = userDao;
        this.securityUtils = securityUtils;
    }

    @Transactional(readOnly = true)
    public List<Task> findAll() {
        return taskDao.findAll();
    }

    @Transactional(readOnly = true)
    public List<Task> findAllByCategory(Category category) {
        return taskDao.findAllByCategory(category);
    }

    @Transactional(readOnly = true)
    public Task find(Long id) {
        return taskDao.find(id);
    }

    @Transactional
    public void persist(Task task) {
        taskDao.persist(task);
    }

    @Transactional
    public void update(Task task) {
        taskDao.update(task);
    }

    @Transactional
    public void addAcceptanceMessage(AcceptanceMessage message, Task task){
        acceptanceMessageDao.persist(message);
        task.addAcceptanceMessage(message);
        taskDao.update(task);
    }

    @Transactional
    public void approveMessage(Long taskId, Long messageId){
        Task task = taskDao.find(taskId);
        AcceptanceMessage message = acceptanceMessageDao.find(messageId);

        Objects.requireNonNull(task.getOwner());

        if(!task.getOwner().getUsername().equals(securityUtils.getCurrentUser().getUsername())){
            throw new AuthorizationException("Only owner can see acceptanceMessages");
        }

        task.setPerformer(message.getSender());
        taskDao.update(task);
    }

    @Transactional
    public List<AcceptanceMessage> getAcceptanceMessages(Long taskId){
        Task task = taskDao.find(taskId);

        Objects.requireNonNull(task.getOwner());

        if(!task.getOwner().getUsername().equals(securityUtils.getCurrentUser().getUsername())){
            throw new AuthorizationException("Only owner can see acceptanceMessages");
        }

        return taskDao.find(taskId).getAcceptanceMessages();
    }

    @Transactional
    public AcceptanceMessage getAcceptanceMessageById(Long taskId, Long messageId) {
        Task task = taskDao.find(taskId);

        AcceptanceMessage acceptanceMessage = getAcceptanceMessages(taskId).get(Math.toIntExact(messageId));

        if(acceptanceMessage == null){
            throw new NotFoundException("Acceptance message not found");
        }

        return getAcceptanceMessages(taskId).stream()
                .filter(c -> c.getId().equals(messageId))
                .findAny()
                .orElseThrow(() -> new TaskException("Acceptance message not found"));
    }

    @Transactional
    public void sendAcceptanceMessage(AcceptanceMessage message, Long taskId){
        Task task = taskDao.find(taskId);
        message.setSender(securityUtils.getCurrentUser());

        if(message.getSender() == task.getOwner()){
            throw new TaskException("message.getSender() == task.getOwner()");
        }

        addAcceptanceMessage(message, task);
    }

    @Transactional
    public void createTask(Task task){
        task.setOwner(userDao.findByUsername(getName()));
//        task.setOwner(SecurityUtils.getCurrentUser());
        taskDao.persist(task);
    }

    @Transactional
    public String getName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        return name;
    }

    @Transactional
    public void taskCompletedPerformer(Long taskId, String review, Integer performerStars){
        Task task = taskDao.find(taskId);

        String username = securityUtils.getCurrentUser().getUsername();

        if(task.getPerformer() == null|| !task.getPerformer().getUsername().equals(username)){
            throw new AuthorizationException("Only owner or performer can do completion approval");
        }

        task.setPerformerApprovedCompletion(true);
        task.setPerformerStars(performerStars);
        taskDao.update(task);
    }

    @Transactional
    public void taskCompletedOwner(Long taskId, String review, Integer ownerStars){
        Task task = taskDao.find(taskId);

        String username = securityUtils.getCurrentUser().getUsername();

        if(!task.getOwner().getUsername().equals(username)){
            throw new AuthorizationException("Only owner or performer can do completion approval");
        }

        task.setOwnerApprovedCompletion(true);
        task.setOwnerStars(ownerStars);
        taskDao.update(task);
    }
}
