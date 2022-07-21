package pl.creative.rental_server.AreaManagement;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AreaController implements AreaApi {

    private final AreaService areaService;
    @Override
    public ResponseEntity<?> createNewArea() {//If we would like to provide some informations with that area make version with DTO
        areaService.createArea();
        return ResponseEntity.ok().build();
    }
}
