package pl.creative.rental_server.entities;

import com.sun.istack.Nullable;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "item")
@NoArgsConstructor
public class Item {
    @Id
    @NotNull
    @NotEmpty
    @Column(unique = true)
    private String id;
    @NotNull
    @NotEmpty
    private String name;
    //    @Size(max = 511)
    private String description;

    @ManyToOne
    @Nullable
    Account borrowedBy; //if the item is not borrowed then the account is null
    @ManyToMany
    @JoinTable(name = "category_item") //the owner of relation
    private List<Category> categories = new ArrayList<>();
    //    @Enumerated(EnumType.STRING) //it is not necessary because we do that in other way
    StatusOfItem statusOfItem;
    @PastOrPresent
    private LocalDateTime dateOfCreation;

//    @ManyToOne
//    @JoinColumn(name = "itemTemplate_id") //the owner of relation
//    private ItemTemplate itemTemplate;

    //    @ToString.Exclude
    @ManyToOne //the item can have only one place
    @JoinColumn(name = "place_id") //the owner of relation //Item table has a record "place_id"
            Place place; //if the item is borrowed the place is null //if item is returned the place is "abstractPlace" with id 0

    public Item(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @OneToMany
    List<Image> images = new ArrayList<>(); //FIXME we dont know if we will have another external service to use
    @OneToMany(mappedBy = "item") //one item can have many of histories
    List<RentHistory> history = new ArrayList<>(); //FIXME we dont know if we do that with aspect concept of programming

    public void addCategoryToCategoryIds(Category category) {
        categories.add(category);
    }

    public void addPlaceToPlace(Place place) {
        this.place = place;
    }

    public List<Category> getCategory() {
        return categories;
    }


}
