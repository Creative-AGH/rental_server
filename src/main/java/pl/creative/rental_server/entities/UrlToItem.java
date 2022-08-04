package pl.creative.rental_server.entities;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class UrlToItem  {
    @Id
    @NotNull
    private String id;
    @ManyToOne
    @NotNull
    private Item value;
    @NotNull
    @NotBlank
    private String link;
    @Nullable
    private String description;
    @NotNull
    private String name;
}
