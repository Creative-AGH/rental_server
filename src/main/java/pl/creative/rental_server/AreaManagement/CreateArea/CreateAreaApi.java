package pl.creative.rental_server.AreaManagement.CreateArea;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

public interface CreateAreaApi {
    @PostMapping("/admin/area/create")
    ResponseEntity<?> createNewArea();
}
