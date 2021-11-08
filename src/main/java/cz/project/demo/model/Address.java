package cz.project.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Address {
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }

    @Basic(optional = false)
    @Column(nullable = false)
    private Integer streetNumber;
    @Basic(optional = false)
    @Column(nullable = false)
    private String streetName;
    @Basic(optional = false)
    @Column(nullable = false)
    private String suburb;
    @Basic(optional = false)
    @Column(nullable = false)
    private String city;
    @Basic(optional = false)
    @Column(nullable = false)
    private String state;
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer postcode;

}
