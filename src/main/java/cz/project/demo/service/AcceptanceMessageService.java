package cz.project.demo.service;

import cz.project.demo.dao.AcceptanceMessageDao;
import cz.project.demo.dao.MessageDao;
import cz.project.demo.model.AcceptanceMessage;
import cz.project.demo.model.Message;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AcceptanceMessageService {

    private AcceptanceMessageDao dao;

    public AcceptanceMessageService(AcceptanceMessageDao dao) {
        this.dao = dao;
    }

    @Transactional
    public void persist(AcceptanceMessage message) {
        dao.persist(message);
    }

}
