package pl.creative.rental_server.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class RentHistory { //this entity has all the information about history like borrower_id, item_id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @Nullable
    @JoinColumn(name = "borrowerAccount_id") //the owner of relation
    Account borrowerAccount;
//    @NotNull
    private String description;
//    @PastOrPresent
//    @NotNull
    private LocalDateTime timeOfRenting;
//    @PastOrPresent
    private LocalDateTime timeOfReturning;

    @ManyToOne //the specific history can have only one item
    @JoinColumn(name = "item_id") //the owner of relation //RentHistory table has a record "item_id"
    Item item;


}
