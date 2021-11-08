package cz.project.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Task.findAllTasksByAuthor", query = "SELECT t FROM Task t WHERE t.owner = :owner"),
//        @NamedQuery(name = "Task.findByCategory", query = "SELECT t from Task t WHERE :category MEMBER OF t.categories AND NOT t.removed")
})
@Getter
@Setter
public class Task {

    @OneToOne(optional = false)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User owner;

    @OneToOne(optional = false)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User performer;

    @Basic(optional = false)
    @Column(nullable = false)
    private String name;
    @Basic(optional = false)
    @Column(nullable = false)
    private String task;
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer price;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean removed;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean completed;

    @OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<Comment>();

    @ManyToMany
    private List<Category> categories;

    @OneToOne(optional = false)
    private Review review;

    @OneToOne(optional = false)
    private Address address;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<AcceptanceMessage> acceptanceMessages;

    @Id
    private Long id;

    public void addComment(Comment comment){
        comments.add(comment);
    }

    public void addCategory(Category category){
        categories.add(category);
    }

    public void removeCategory(Category category) {
        categories.remove(category);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
