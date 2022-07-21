package pl.creative.rental_server.AreaManagement;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

public interface AreaApi {
    @PostMapping("/admin/area/create")
    ResponseEntity<?> createNewArea();
}
