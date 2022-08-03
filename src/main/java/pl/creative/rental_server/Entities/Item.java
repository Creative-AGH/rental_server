package pl.creative.rental_server.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "item")
public class Item{
    @Id
    @NotNull
    @NotEmpty
    @Column(unique = true)
    private String id;
    @NotNull
    @NotEmpty
    @Column(unique = true) //who is checking it, when will it throw an exception?
    // we can get all names in service while adding new type and check it
    private String name;

//    @ManyToOne
//    @Nullable
//    Account borrowedBy;
    @ManyToMany
    @JoinTable(name="typeOfItem_item") //the owner of relation
    private List<TypeOfItem> typesOfItem = new ArrayList<>();
//    @Enumerated
//    StatusOfItem statusOfItem; //TODO it is next step
//    @PastOrPresent
    private LocalDateTime dateOfCreation;

    @ManyToOne
    @JoinColumn(name = "itemTemplate_id") //the owner of relation
    private ItemTemplate itemTemplate;


//    @ManyToMany //TODO here have to be @ManyToOne
//    Set<Place> places = new HashSet<>(); //TODO Adam implements this functionality
//    @OneToMany
//    Set<RentHistory> history = new HashSet<>(); //FIXME we dont know if we do that with aspect concept of programming
//    @OneToMany
//    List<UrlToItem> urlsToItem = new ArrayList<>(); //FIXME we dont know if we will have another external service to use

    public void addTypeOfItemToTypeOfItemIds(TypeOfItem typeOfItem) {
        typesOfItem.add(typeOfItem);
    }

    public List<TypeOfItem> getTypesOfItem(){
        return typesOfItem;
    }





}
