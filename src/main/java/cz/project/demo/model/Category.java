package cz.project.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
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
