package pl.creative.rental_server.core.features.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class RentUserController {

    @PostMapping("/user/rent/defaultPlace/{itemId}")
    public ResponseEntity<?> rentForMeFromDefaultPlace(String itemId)//TODO IMPLEMENT LOGIC
    {

        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info(name);
        return ResponseEntity.noContent().build();
    }
    //PREFER FIRST OPTION DUE TO SWAGGER UI
    @PostMapping("/user/rent/place/{itemId}")//TODO IMPLEMENT LOGIC
    public ResponseEntity<?> rentForMeFromPlace(String itemId, @CurrentSecurityContext
    SecurityContext securityContext)
    {
        log.info(securityContext.getAuthentication().toString());
        return ResponseEntity.noContent().build();
    }
    public ResponseEntity<?> iReturnItem(String itemId)//and place it to default place ("0")
    {
        return ResponseEntity.noContent().build();
    }
    public ResponseEntity<?> iReturnItemAndPlaceItAtPlace(String itemId,String placeId)//TODO IMPLEMENT LOGIC
    {
        return ResponseEntity.noContent().build();
    }

}
