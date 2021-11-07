package cz.project.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@NamedQueries({
        @NamedQuery(name = "Comment.findAllCommentsByDate", query = "SELECT c FROM Comment c WHERE c.date = :date"),
        @NamedQuery(name = "Comment.findAllCommentsByAuthor", query = "SELECT c FROM Comment c WHERE c.author = :author")
})
public class Comment {
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
    String comment;
    Date date;

    @ManyToOne(optional = false)
    @JoinColumn(name="user_id")
    private User author;
}
