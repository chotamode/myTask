package cz.project.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@NamedQueries({
        @NamedQuery(name = "Comment.findAllCommentsByDate", query = "SELECT c FROM Comment c WHERE c.date = :date"),
//        @NamedQuery(name = "Comment.findAllCommentsByAuthor", query = "SELECT c FROM Comment c WHERE c.author = :author")
})
@Getter
@Setter
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
    @Basic(optional = false)
    @Column(nullable = false, name = "date")
    Date date;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="user_entity_id")
//    private User author;
}
