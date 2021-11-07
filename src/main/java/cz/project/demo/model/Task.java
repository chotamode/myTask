package cz.project.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Task.findAllTasksByAuthor", query = "SELECT t FROM Task t WHERE t.owner = :owner")
})
public class Task {
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }

    @JsonIgnore
    @OneToOne(mappedBy = "task", optional = false)
    private User owner;

    @JsonIgnore
    @OneToOne(mappedBy = "task")
    private User performer;

    @Basic(optional = false)
    @Column(nullable = false)
    private String name;
    private String task;
    private Integer price;

    @OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true)
    @JoinColumn(name = "comment_id")
    private List<Comment> comments;

    @OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true)
    @JoinColumn(name = "category_id")
    private List<Category> categories;

    @OneToOne(optional = false)
    private Review review;

    @OneToOne(optional = false)
    private Address address;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true)
    @JoinColumn(name = "acceptance_messages_id")
    private List<AcceptanceMessage> acceptanceMessages;
}
