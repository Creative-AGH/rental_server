package pl.creative.rental_server.db.entities;

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
@Table(name = "category")
public class Category {
    @Id
    @NotNull
    @NotEmpty
    @Column(unique = true)
    private String id; //This id is generated in CategoryService class
    @NotNull
    @NotEmpty
    @Column(unique = true) //who is checking it, when will it throw an exception?
    // we can get all names in service while adding new type and check it
    private String categoryName;
    private String description;
    @ManyToMany(mappedBy = "categories")
    private List<Item> itemsCreatedFromThisCategory = new ArrayList<>();

    public Category(String id, String categoryName, String description) {
        this.id = id;
        this.categoryName = categoryName;
        this.description = description;
    }

    public List<Item> getItems() {
        return itemsCreatedFromThisCategory;
    }

}
