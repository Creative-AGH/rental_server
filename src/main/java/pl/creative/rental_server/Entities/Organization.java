package pl.creative.rental_server.Entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Organization {
    @Id
    private String id;
    @ManyToMany
    Set<Account> accounts=new HashSet<>();
    @ManyToMany
    List<Area> areas=new ArrayList<>();

}
