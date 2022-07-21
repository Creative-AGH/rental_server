package pl.creative.rental_server.Entities;

import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Item {
    @Id
    @Column(unique = true)
    private String id;
    @NotBlank(message = "Name may not be empty or null")
    private String name;

    @ManyToOne
    @Nullable
    Account borrowedBy;
    @ManyToMany
//    @JoinTable(name = "category_item") //the owner of relation FIXME ( DO NOT COMPILE WITH IT )
    private List<TypeOfItem> categories = new ArrayList<>();
    @Enumerated
    StatusOfItem statusOfItem;
    @PastOrPresent
    LocalDateTime dateOfCreate;

    @ManyToMany
    Set<Place> places = new HashSet<>();

    @OneToMany
    Set<RentHistory> history = new HashSet<>();
    @OneToMany
    List<UrlToItem> urlsToItem = new ArrayList<>();

}
