package pl.creative.rental_server.entities;

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
    @NotEmpty//who is checking it, when will it throw an exception?
    // we can get all names in service while adding new type and check it
    private String name;

    //    @ManyToOne
//    @Nullable
//    Account borrowedBy;
    @ManyToMany
    @JoinTable(name = "category_item") //the owner of relation
    private List<Category> categories = new ArrayList<>();
//    @Enumerated(EnumType.STRING) //it is not necessary because we do that in other way
    StatusOfItem statusOfItem;
    @PastOrPresent
    private LocalDateTime dateOfCreation;

    @ManyToOne
    @JoinColumn(name = "itemTemplate_id") //the owner of relation
    private ItemTemplate itemTemplate;

    @ManyToOne //the item can have only one place
    @JoinColumn(name = "place_id") //the owner of relation
    Place place;

    public Item(String id, String name) {
        this.id = id;
        this.name = name;
    }

//    @OneToMany
//    List<RentHistory> history = new ArrayList<>(); //FIXME we dont know if we do that with aspect concept of programming
//    @OneToMany
//    List<UrlToItem> urlsToItem = new ArrayList<>(); //FIXME we dont know if we will have another external service to use

    public void addCategoryToCategoryIds(Category category) {
        categories.add(category);
    }

    public void addPlaceToPlace(Place place){
        this.place=place;
    }

    public List<Category> getCategory() {
        return categories;
    }

//    public void changePlaceOfItem(String placeId){
//        place.setId(placeId);
//    }


}
