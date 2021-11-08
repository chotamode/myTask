package cz.project.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class AcceptanceMessage {

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
    private String message;
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer price;

//    @ManyToOne(optional = false)
//    @JoinColumn(name="user_id")
//    User author;
}
