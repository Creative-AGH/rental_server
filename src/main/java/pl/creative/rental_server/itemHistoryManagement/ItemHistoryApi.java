package pl.creative.rental_server.itemHistoryManagement;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import pl.creative.rental_server.itemHistoryManagement.dto.GetItemHistoryDto;

import java.util.List;

public interface ItemHistoryApi {

    @ApiOperation(value = "Getting all item history")
    @GetMapping("/moderator/itemHistory")
    ResponseEntity<List<GetItemHistoryDto>> getAllItemHistory();

}
