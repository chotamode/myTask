package cz.project.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@NamedQueries({
        @NamedQuery(name = "Message.findBySender", query = "SELECT m FROM Message m WHERE m.sender.username = :username"),
        @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
})
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private User receiver;

    @Basic
    @Column(nullable = false)
    private String content;

    @Basic
    @Column(nullable = false)
    private LocalDateTime timestamp = LocalDateTime.now();

}
