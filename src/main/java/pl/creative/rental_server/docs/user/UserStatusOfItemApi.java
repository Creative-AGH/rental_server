package pl.creative.rental_server.docs.user;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public interface UserStatusOfItemApi {

    @ApiOperation("Getting all status of item")
    @GetMapping("/getAllStatusOfItem")
    ResponseEntity<?> getAllStatusOfItem();
}
