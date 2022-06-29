package pl.creative.rental_server.CategoryMagagement.FillData;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FillCategoryDataController {
    private final FillCategoryDataService fillCategoryDataService;

    @PostMapping("/add/category")
    public ResponseEntity<?> addCategory(@RequestBody FillCategoryDto dto){
        fillCategoryDataService.addCategory(dto);
        return ResponseEntity.ok().build();
    }
}
