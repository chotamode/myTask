package cz.project.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
@Getter
@Setter
public class Schedule {

    @Id
    private Long id;

    @OneToMany
    List<Task> tasks;

    @OneToOne
    User user;
}
