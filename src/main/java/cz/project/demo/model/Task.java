package cz.project.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Task.findAllTasksByAuthor", query = "SELECT t FROM Task t WHERE t.owner = :owner"),
//        @NamedQuery(name = "Task.findByCategory", query = "SELECT t from Task t WHERE :category MEMBER OF t.categories AND NOT t.removed")
})
@Getter
@Setter
public class Task {

    @Basic(optional = false)
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    protected Date date = new Date();
    @Basic
    @Column
    String review;
    @Basic
    @Column
    Integer stars;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(optional = false)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;
    @OneToOne
    @JoinColumn(name = "performer_id")
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
    @Basic
    @Column
    private Integer streetNumber;
    @Basic
    @Column
    private String streetName;
    @Basic
    @Column
    private String suburb;
    @Basic
    @Column
    private String city;
    @Basic
    @Column
    private String state;
    @Basic
    @Column
    private Integer postcode;
    @OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<Comment>();
    @ManyToMany
    private List<Category> categories;
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<AcceptanceMessage> acceptanceMessages;

    public void setReview(String review, Date date, Integer stars) {
        this.review = review;
        this.date = date;
        this.stars = stars;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public void addCategory(Category category) {
        categories.add(category);
    }

    public void removeCategory(Category category) {
        categories.remove(category);
    }

}
