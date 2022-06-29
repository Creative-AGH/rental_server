package pl.creative.rental_server.ItemManagement.FillData;

import lombok.Data;
import pl.creative.rental_server.Entities.Account;
import pl.creative.rental_server.Entities.StatusOfItem;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class FillItemDto {
    private String name;
    private StatusOfItem statusOfItem;

}
