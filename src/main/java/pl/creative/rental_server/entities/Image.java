package pl.creative.rental_server.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
