package cz.project.demo.service;

import cz.project.demo.dao.CommentDao;
import cz.project.demo.dao.TaskDao;
import cz.project.demo.dao.UserDao;
import cz.project.demo.exception.CommentException;
import cz.project.demo.model.Comment;
import cz.project.demo.security.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentService {

    private CommentDao commentDao;
    private UserDao userDao;
    private TaskDao taskDao;
    private SecurityUtils securityUtils;

    public CommentService(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Transactional
    public void createComment(String text, Long userId) {
        Comment comment = new Comment();
        comment.setComment(text);
        comment.setAuthor(securityUtils.getCurrentUser());
        comment.setUser(userDao.find(userId));
        commentDao.persist(comment);
    }

    @Transactional
    public void updateComment(Long comment_id, String text){
        Comment comment = commentDao.find(comment_id);
        if(comment.getAuthor() == securityUtils.getCurrentUser()){
            comment.setComment(text);
            commentDao.update(comment);
        }else{
            throw new CommentException("You are not allowed to redact this comment");
        }
    }

    @Transactional
    public void deleteComment(Long comment_id){
        Comment comment = commentDao.find(comment_id);
        if(comment.getAuthor() == securityUtils.getCurrentUser() || comment.getUser() == securityUtils.getCurrentUser()){
            commentDao.remove(comment);
        }else{
            throw new CommentException("You are not allowed to delete this comment");
        }
    }

    @Transactional
    public List<Comment> getAllComments(Long taskId) {
        return taskDao.find(taskId).getComments();
    }

    @Transactional
    public Comment getCommentById(Long taskId, Long comment_id){
        return getAllComments(taskId).stream()
                .filter(c -> c.getId().equals(comment_id))
                .findAny()
                .orElseThrow(() -> new CommentException("No such comment"));
    }
}
