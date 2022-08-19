package pl.creative.rental_server.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class ItemHistory { //this entity has all the information about history like borrower_id, item_id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @Nullable
//    @JoinColumn(name = "borrowerAccount_id") //the owner of relation
    Account account;
//    @NotNull
    private String typeOfEvent;
    @Nullable
    private String commentToEvent;

//    @NotNull
//    @PastOrPresent
    private LocalDateTime timeOfEvent;

//    @ManyToOne //the specific history can have only one item
//    @JoinColumn(name = "item_id") //the owner of relation //ItemHistory table has a record "item_id"
//    Item item;
    String itemId; //the owner o relation

    @Column(length = 1023) //is a JPA annotation //Varchar (1023) // it doesn't provide validations
    @Size(max = 1023, message = "String detailsOfItem is to long") //it is a Java-standard annotation
//        @Length(max = 1023) //it is a specific to Hibernate
    String detailsOfItemBeforeEvent;

    public ItemHistory(Long id, String typeOfEvent, String detailsOfItem) {
        this.id = id;
        this.typeOfEvent = typeOfEvent;
        this.detailsOfItemBeforeEvent = detailsOfItem;
    }
}
