package pl.creative.rental_server.rentHistoryManagement;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.creative.rental_server.rentHistoryManagement.dto.GetRentHistoryDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RentHistoryController {
    private final RentHistoryService rentHistoryService;
    @GetMapping("/moderator/rentHistory")
    ResponseEntity<List<GetRentHistoryDto>> getAllRentHistory(){
        List<GetRentHistoryDto> listOfGetRentHistoryDto = rentHistoryService.getAllRentHistory();
        return ResponseEntity.ok(listOfGetRentHistoryDto);
    }
}
