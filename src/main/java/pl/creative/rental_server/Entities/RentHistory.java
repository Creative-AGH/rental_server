package pl.creative.rental_server.Entities;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

@Entity
@Data
public class RentHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @ManyToOne
    @Nullable
    private Account borrowerAccount;
    @NotNull
    private String description;
    @PastOrPresent
    @NotNull
    private LocalDateTime borrowTime;
    @PastOrPresent
    private LocalDateTime giveBackTime;


}
