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

    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public User() {
        this.role = Role.GUEST;
    }

    @Basic
    @Column
    private String firstName;

    @Basic
    @Column
    private String lastName;

    @Basic(optional = false)
    @Column(nullable = false, unique = true)
    private String nickname;

    @Basic(optional = false)
    @Column(nullable = false)
    private String password;

    @Basic
    @Column
    private Integer streetNumber;
    @Basic
    @Column
    private String streetName;
    @Basic
    @Column
    private String suburb;
    @Basic
    @Column
    private String city;
    @Basic
    @Column
    private String state;
    @Basic
    @Column
    private Integer postcode;

    @OneToMany
    List<Task> tasks = new ArrayList<Task>();

    @Enumerated(EnumType.STRING)
    private Role role;


    public void addTask(Task task){
        Objects.requireNonNull(task);
        tasks.add(task);
    }

    public void removeTask(Task task){
        Objects.requireNonNull(task);
        tasks.remove(task);
    }

    @Override
    public String toString() {
        return "User{" +
                firstName + " " + lastName +
                "(" + nickname + ")}";
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





//    @OneToMany(mappedBy = "user_entity")
//    private List<Comment> comments;



}
