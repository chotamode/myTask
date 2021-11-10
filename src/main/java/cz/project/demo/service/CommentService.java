package cz.project.demo.service;

import cz.project.demo.dao.CommentDao;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    CommentDao commentDao;

    public CommentService(CommentDao commentDao) {
        this.commentDao = commentDao;
    }
}
