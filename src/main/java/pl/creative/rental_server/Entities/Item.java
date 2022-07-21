package pl.creative.rental_server.Entities;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Item {
    @Id
    @Column(unique = true)
    private String id;
    @NotBlank(message = "Name may not be empty or null")
    private String name;


    @ManyToMany
    @NotNull
    @JoinTable(name = "category_item") //the owner of relation
    private List<Category> categories = new ArrayList<>();
    //    @ManyToOne
//    @Nullable
//    Account borrowedBy;
//    @Enumerated
//    StatusOfItem statusOfItem;
    @PastOrPresent
    LocalDateTime dateOfCreate;
//    @ManyToMany
//    Set<Place> places = new HashSet<>();
//    @OneToMany
//    Set<RentHistory> history = new HashSet<>();
//    @OneToMany
//    List<UrlToItem> urlsToItem = new ArrayList<>();

}
