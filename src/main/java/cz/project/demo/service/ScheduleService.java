package cz.project.demo.service;

import cz.project.demo.dao.ScheduleDao;
import cz.project.demo.dao.TaskDao;
import cz.project.demo.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleDao dao;

    @Autowired
    public ScheduleService(ScheduleDao dao) {
        this.dao = dao;
    }

}
