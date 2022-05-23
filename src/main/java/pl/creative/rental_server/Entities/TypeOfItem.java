package pl.creative.rental_server.Entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
@Entity
@Data
public class TypeOfItem {
    @Id
    @GeneratedValue
    @NotNull
    @NotEmpty
    @Column(unique = true)
    private String id;
    @NotNull
    @NotEmpty
    @Column(unique = true)
    private String nameOfType;
    @NotNull
    @NotEmpty

    private String description;
    @OneToMany(fetch = FetchType.EAGER)
    List<Item>  itemsCreatedFromThisType;



}
