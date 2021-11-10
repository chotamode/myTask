package cz.project.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Review {

    @Basic(optional = false)
    @Column
    String review;

    @Basic(optional = false)
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    Date date = new Date();

    @Basic(optional = false)
    @Column
    Integer stars;

    @ManyToOne(optional = false)
    @JoinColumn(name="user_id", nullable = false)
    private User owner;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
