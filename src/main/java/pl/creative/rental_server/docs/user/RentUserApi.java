package pl.creative.rental_server.docs.user;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;


public interface RentUserApi {

    @ApiOperation("Renting an item from default place by user")
    @PostMapping("/user/rent/defaultPlace/{itemId}")
    ResponseEntity<?> rentItemForMe(String itemId, String commentToEvent);

}
