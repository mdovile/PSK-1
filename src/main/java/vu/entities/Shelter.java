package vu.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Shelter.findAll", query = "select s from Shelter as s")
})
@Table(name = "SHELTER")
@Getter @Setter
public class Shelter {

    public Shelter(){
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private double donations;

    @OneToMany(mappedBy = "shelter")
    private List<Cat> cats = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shelter shelter = (Shelter) o;
        return Objects.equals(title, shelter.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
