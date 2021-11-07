package cz.project.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "USER_ENTITY")
@Getter
@Setter
@NamedQueries({
        @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username")
})
public class User {

    @Basic(optional = false)
    @Column(nullable = false)
    private String firstName;
    @Basic(optional = false)
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String username;
    @Basic(optional = false)
    @Column(nullable = false)
    private String password;

    @OneToMany
    List<Task> tasks;

//    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
//    private List<Address> address;
    /*
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Review> reviews;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Comment> comments;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    List<AcceptanceMessage> acceptanceMessages;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    List<Message> messages;*/

    @Id()
    @Column(insertable = false, updatable = false)
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private Role role;
}
