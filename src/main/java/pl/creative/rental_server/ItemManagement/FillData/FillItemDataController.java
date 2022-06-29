package pl.creative.rental_server.ItemManagement.FillData;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.creative.rental_server.Entities.Category;

@Controller
@RequiredArgsConstructor
public class FillItemDataController {
    private final FillItemDataService fillItemDataService;

    @PostMapping("/add/{categoryId}/item")
    ResponseEntity<?> addItem(@RequestBody FillItemDto dto, @PathVariable Category categoryId) {
        fillItemDataService.addItem(dto, categoryId);
        return ResponseEntity.ok().build();
    }

}
