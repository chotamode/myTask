package cz.project.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.Nullable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @ManyToOne(optional = false)
    @JoinColumn(name="sender_id")
    private User sender;

    @ManyToOne(optional = false)
    @JoinColumn(name="receiver_id")
    private User receiver;

    @Basic(optional = false)
    @Column(nullable = false)
    private String content;

    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    @Enumerated
    private MessageStatus status;

}
