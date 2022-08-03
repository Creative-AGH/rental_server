package pl.creative.rental_server.ItemManagement.FillData;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.creative.rental_server.ItemManagement.dto.FillItemDto;

@Controller
@RequiredArgsConstructor
public class FillItemDataController {
    private final FillItemDataService fillItemDataService;

    @PostMapping("/add/{ItemTemplateId}/item")
    ResponseEntity<?> addItem(@RequestBody FillItemDto dto, @PathVariable String ItemTemplateId) {
        fillItemDataService.addItem(dto, ItemTemplateId);
        return ResponseEntity.ok().build();
    }

}
