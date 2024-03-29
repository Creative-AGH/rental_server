package pl.creative.rental_server.core.features.user.statusOfItem;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.creative.rental_server.core.features.user.statusOfItem.dto.GetStatusOfItemDto;
import pl.creative.rental_server.core.features.user.statusOfItem.dto.StatusOfItemMapper;
import pl.creative.rental_server.db.entities.StatusOfItem;

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
