package cz.project.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class AcceptanceMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic
    @Column
    private String message;

    @Basic(optional = false)
    @Column(nullable = false)
    private Integer price;

//    @ManyToOne(optional = false)
//    @JoinColumn(name="user_id")
//    User author;
}
