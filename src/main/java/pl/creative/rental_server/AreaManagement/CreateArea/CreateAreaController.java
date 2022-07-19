package pl.creative.rental_server.AreaManagement.CreateArea;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CreateAreaController implements CreateAreaApi {

    private final CreateAreaService createAreaService;
    @Override
    public ResponseEntity<?> createNewArea() {//If we would like to provide some informations with that area make version with DTO
        createAreaService.createArea();
        return ResponseEntity.ok().build();
    }
}
