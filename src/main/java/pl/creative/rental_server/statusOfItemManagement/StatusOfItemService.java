package pl.creative.rental_server.statusOfItemManagement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.creative.rental_server.entities.StatusOfItem;
import pl.creative.rental_server.statusOfItemManagement.dto.GetStatusOfItemDto;
import pl.creative.rental_server.statusOfItemManagement.dto.StatusOfItemMapper;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatusOfItemService {
    private final StatusOfItemMapper statusOfItemMapper;

    public List<GetStatusOfItemDto> getAllStatusOfItem() {
        log.info("Getting all status of item");
        List<GetStatusOfItemDto> statusOfItemList = new ArrayList<>();
        StatusOfItem[] values = StatusOfItem.values();
        for (StatusOfItem value : values) {
            statusOfItemList.add(statusOfItemMapper.mapStatusOfItemToGetStatusOfItemDto(value));
        }
        return statusOfItemList;
    }
}
