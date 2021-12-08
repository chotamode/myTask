package cz.project.demo.model;

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
    @Basic(optional = false)
    @Column(nullable = false)
    String comment;
    @Basic(optional = false)
    @Column(nullable = false, name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    Date date;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User author;
}
