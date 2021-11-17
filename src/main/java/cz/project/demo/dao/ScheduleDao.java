package cz.project.demo.dao;

import cz.project.demo.model.Schedule;
import org.springframework.stereotype.Repository;

@Repository
public class ScheduleDao extends BaseDao<Schedule>{

    protected ScheduleDao() {
        super(Schedule.class);
    }

}
