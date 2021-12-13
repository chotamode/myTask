package cz.project.demo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;


@Entity
@Table(name = "users")
@Getter
@Setter
@NamedQueries({
        @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :nickname"),
        @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
})
public class User implements UserDetails {

    @OneToMany
    List<Task> tasks = new ArrayList<Task>();
    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Basic
    @Column
    private String firstName;

    @Basic
    @Column
    private String lastName;

    @Basic(optional = false)
    @Column(nullable = false, unique = true)
    private String username;

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

    @ManyToMany
    @JoinColumn
    private List<Role> roles;

    @Basic
    @Column
    private boolean nonExpired;
    @Basic
    @Column
    private boolean nonLocked;
    @Basic
    @Column
    private boolean credentialsNonExpired;
    @Basic
    @Column
    private boolean Enabled;

    public User() {
    }

    public User(String firstName, String lastName, String nickname, String password) {
        this.roles = new ArrayList<Role>();
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = nickname;
        this.password = password;
        this.nonExpired = true;
        this.nonLocked = true;
        this.credentialsNonExpired = true;
        this.Enabled = true;
    }

    public void addTask(Task task) {
        Objects.requireNonNull(task);
        tasks.add(task);
    }

    public void removeTask(Task task) {
        Objects.requireNonNull(task);
        tasks.remove(task);
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    @Override
    public String toString() {
        return "User{" +
                firstName + " " + lastName +
                "(" + username + ")}";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<Authority> list = new ArrayList<>();
        for (Role g:
             roles) {
            list.addAll(g.getAuthorities());
        }
        return list;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.nonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.nonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.Enabled;
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
