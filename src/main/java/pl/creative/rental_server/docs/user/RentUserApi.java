package pl.creative.rental_server.docs.user;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


public interface RentUserApi {

    @ApiOperation("Renting an item from default place by user")
    @PostMapping("/user/rent/defaultPlace/{itemId}")
    ResponseEntity<?> rentItemForMe(@PathVariable String itemId,
                                    @RequestBody(required = false) String commentToEvent);

    @ApiOperation("Returning an item by user and placing it to default place (0)")
    @PostMapping("/user/returnItem/{itemId}")
    ResponseEntity<?> iReturnItem(@PathVariable String itemId,
                                  @RequestBody(required = false) String commentToEvent);

    @ApiOperation("Returning an item by user and placing it to given place id")
    @PostMapping("/user/returnItem/{itemId}/{newPlaceId}")
    ResponseEntity<?> iReturnItemAndPlaceItAtGivenPlace(@PathVariable String itemId,
                                                        @PathVariable String newPlaceId,
                                                        @RequestBody(required = false) String commentToEvent);
}