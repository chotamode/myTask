package cz.project.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "user_entity")
@Getter
@Setter
@NamedQueries({
        @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.nickname = :nickname")
})
public class User {

    @Basic(optional = false)
    @Column(nullable = false)
    private String firstName;

    @Basic(optional = false)
    @Column(nullable = false)
    private String lastName;

    @Basic(optional = false)
    @Column(nullable = false, unique = true)
    private String nickname;

    @Basic(optional = false)
    @Column(nullable = false)
    private String password;

    public User() {
        this.role = Role.GUEST;
    }


    @OneToMany
    List<Task> tasks = new ArrayList<Task>();

    public void addTask(Task task){
        Objects.requireNonNull(task);
        tasks.add(task);
    }

    public void removeTask(Task task){
        Objects.requireNonNull(task);
        tasks.remove(task);
    }

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Role role;

//    @OneToMany(mappedBy = "user_entity")
//    private List<Comment> comments;

    @Override
    public String toString() {
        return "User{" +
                firstName + " " + lastName +
                "(" + nickname + ")}";
    }

}
