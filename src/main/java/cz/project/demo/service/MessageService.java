package cz.project.demo.service;

import cz.project.demo.dao.MessageDao;
import cz.project.demo.model.Message;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MessageService {

    private final MessageDao dao;

    public MessageService(MessageDao dao) {
        this.dao = dao;
    }

    @Transactional
    public void persist(Message message) {
        dao.persist(message);
    }

}
