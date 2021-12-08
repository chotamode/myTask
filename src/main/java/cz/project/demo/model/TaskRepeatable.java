package cz.project.demo.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.DayOfWeek;
import java.time.Month;

@Entity
public class TaskRepeatable extends Task {

    @Basic(optional = false)
    @Column(nullable = false)
    Integer day;
    @Basic(optional = false)
    @Column(nullable = false)
    Month month;
    @Basic(optional = false)
    @Column(nullable = false)
    DayOfWeek dayOfWeek;

}
