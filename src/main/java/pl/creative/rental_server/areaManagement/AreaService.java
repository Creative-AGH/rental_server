package pl.creative.rental_server.areaManagement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.creative.rental_server.entities.Area;
import pl.creative.rental_server.repository.AreaRepository;

@Service
@RequiredArgsConstructor
public class AreaService {

    private final AreaRepository areaRepository;
    public void createArea() {//If we would like to provide some informations with that area make version with DTO
        areaRepository.save(new Area(0));

    }
}
