package cz.project.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Review {
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }

    @Basic(optional = false)
    @Column(nullable = false)
    String review;
    Date date;
    Integer stars;

    @ManyToOne(optional = false)
    @JoinColumn(name="user_id", nullable = false)
    private User owner;
}
