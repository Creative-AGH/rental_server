package pl.creative.rental_server.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    @JoinTable(name = "typeOfItem_item") //the owner of relation
    private List<TypeOfItem> typesOfItem = new ArrayList<>();
    //    @Enumerated
//    StatusOfItem statusOfItem; //TODO it is next step
//    @PastOrPresent
    private LocalDateTime dateOfCreation;

    @ManyToOne
    @JoinColumn(name = "itemTemplate_id") //the owner of relation
    private ItemTemplate itemTemplate;

    public Item(String id, String name) {
        this.id = id;
        this.name = name;
    }


//    @ManyToMany //TODO here have to be @ManyToOne
//    Set<Place> places = new HashSet<>(); //TODO Adam implements this functionality
//    @OneToMany
//    Set<RentHistory> history = new HashSet<>(); //FIXME we dont know if we do that with aspect concept of programming
//    @OneToMany
//    List<UrlToItem> urlsToItem = new ArrayList<>(); //FIXME we dont know if we will have another external service to use

    public void addTypeOfItemToTypeOfItemIds(TypeOfItem typeOfItem) {
        typesOfItem.add(typeOfItem);
    }

    public List<TypeOfItem> getTypesOfItem() {
        return typesOfItem;
    }


}
