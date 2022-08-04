package pl.creative.rental_server.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "itemTemplate")
public class ItemTemplate {
    @Id
    @Column(unique = true)
    private String id;
    @NotNull
    @NotBlank(message = "Name may not be empty or null")
    private String templateName;
    private String templateDescription;
    @OneToMany //one template can have many items, but item can have only one template
    List<Item> items = new ArrayList<>();


}
