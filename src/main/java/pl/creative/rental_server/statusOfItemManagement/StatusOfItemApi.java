package pl.creative.rental_server.statusOfItemManagement;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public interface StatusOfItemApi {

    @ApiOperation("Getting all status of item")
    @GetMapping("/getAllStatusOfItem")
    ResponseEntity<?> getAllStatusOfItem();
}
