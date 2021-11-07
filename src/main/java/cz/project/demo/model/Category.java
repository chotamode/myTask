package cz.project.demo.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Category {

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
    private String name;

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                "}";
    }
}
