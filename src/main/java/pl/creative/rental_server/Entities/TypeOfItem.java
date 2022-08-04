package pl.creative.rental_server.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "typeOfItem")
public class TypeOfItem {
    @Id
    @NotNull
    @NotEmpty
    @Column(unique = true)
    private String id; //This id is generated in TypeOfItemService class
    @NotNull
    @NotEmpty
    @Column(unique = true) //who is checking it, when will it throw an exception?
    // we can get all names in service while adding new type and check it
    private String nameOfType;
    private String description;
    @ManyToMany(mappedBy = "typesOfItem")
    private List<Item> itemsCreatedFromThisType = new ArrayList<>();

    public TypeOfItem(String id, String nameOfType, String description) {
        this.id = id;
        this.nameOfType = nameOfType;
        this.description = description;
    }

    public List<Item> getItems() {
        return itemsCreatedFromThisType;
    }

}
