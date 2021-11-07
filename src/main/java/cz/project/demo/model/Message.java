package cz.project.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.Nullable;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Message {

    @Id
    private String chatId;
    @Basic(optional = false)
    @Column(nullable = false)
    private String senderId;
    @Basic(optional = false)
    @Column(nullable = false)
    private String recipientId;
    @Basic(optional = false)
    @Column(nullable = false)
    private String senderName;
    @Basic(optional = false)
    @Column(nullable = false)
    private String recipientName;
    @Basic(optional = false)
    @Column(nullable = false)
    private String content;
    @Basic(optional = false)
    @Column(nullable = false)
    private Date timestamp;

    @Enumerated
    private MessageStatus status;

    @ManyToOne(optional = false)
    @JoinColumn(name="user_id")
    private User owner;
}
