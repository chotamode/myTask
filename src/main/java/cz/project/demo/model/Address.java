package cz.project.demo.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
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
    private String streetName;
    private String suburb;
    private String city;
    private String state;
    private Integer postcode;

}
