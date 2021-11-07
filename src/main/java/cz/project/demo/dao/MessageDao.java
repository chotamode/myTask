package cz.project.demo.dao;

import cz.project.demo.model.Message;
import org.springframework.stereotype.Repository;

@Repository
public class MessageDao extends BaseDao{
    public MessageDao() {
        super(Message.class);
    }
}
