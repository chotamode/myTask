package cz.project.demo.service;

import cz.project.demo.dao.AcceptanceMessageDao;
import cz.project.demo.model.AcceptanceMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AcceptanceMessageService {

    private final AcceptanceMessageDao dao;

    public AcceptanceMessageService(AcceptanceMessageDao dao) {
        this.dao = dao;
    }

    @Transactional
    public void persist(AcceptanceMessage message) {
        dao.persist(message);
    }

    public AcceptanceMessage find(Long message_id) {
        return dao.find(message_id);
    }
}
