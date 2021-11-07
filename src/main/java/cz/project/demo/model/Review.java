package cz.project.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Review {

    @Basic(optional = false)
    @Column(nullable = false)
    String review;
    @Basic(optional = false)
    @Column(nullable = false)
    Date date;
    @Basic(optional = false)
    @Column(nullable = false)
    Integer stars;

    @ManyToOne(optional = false)
    @JoinColumn(name="user_id", nullable = false)
    private User owner;
    @Id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
