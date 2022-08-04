package pl.creative.rental_server.placeManagement.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class InnerPlaceDto {
    private final InputPlaceDto inputPlaceDto;
    private final String placeId;
}
