package pl.creative.rental_server.Entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Category {
    @Id
    @Column(unique = true)
    private String id;

}
