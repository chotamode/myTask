package cz.project.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.Nullable;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Message {

    @Id
    private String id;
    private String chatId;
    private String senderId;
    private String recipientId;
    private String senderName;
    private String recipientName;
    private String content;
    private Date timestamp;

    @Enumerated
    private MessageStatus status;

    @ManyToOne(optional = false)
    @JoinColumn(name="user_id")
    private User owner;
}
