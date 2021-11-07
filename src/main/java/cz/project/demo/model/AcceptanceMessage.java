package cz.project.demo.model;

import javax.persistence.*;

@Entity
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
    private Integer price;

    @ManyToOne(optional = false)
    @JoinColumn(name="user_id")
    User author;
}
