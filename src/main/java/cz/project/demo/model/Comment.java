package cz.project.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@NamedQueries({
        @NamedQuery(name = "Comment.findAllCommentsByDate", query = "SELECT c FROM Comment c WHERE c.date = :date"),
//        @NamedQuery(name = "Comment.findAllCommentsByAuthor", query = "SELECT c FROM Comment c WHERE c.author = :author")
})
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic
    @Column(nullable = false)
    String comment;
    @Basic
    @Column(nullable = false)
    LocalDateTime date = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private Task task;
}
