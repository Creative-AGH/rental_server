package pl.creative.rental_server.Entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

@Entity
@Data
public class Item {
    @Id
    @Column(unique = true)
    private String id;

    @ManyToOne
    Account borrowedBy;

    @Enumerated
    StatusOfItem statusOfItem;
    @PastOrPresent
    LocalDateTime dateOfCreate;


}
