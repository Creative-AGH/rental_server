package pl.creative.rental_server.db.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    @Id
    @NotNull
    private String id;

//    @NotNull
//    @JsonIgnore
    String itemId; //the owner of relation

//    @JsonProperty("itemId")
//    public String getItemId() {
//        return itemId.getId();
//    }

    @NotNull
    @NotBlank
    private String link;
}
