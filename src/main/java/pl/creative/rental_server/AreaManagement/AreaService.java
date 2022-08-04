package pl.creative.rental_server.AreaManagement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.creative.rental_server.Entities.Area;
import pl.creative.rental_server.Handlers.RandomIdHandler;
import pl.creative.rental_server.Repository.AreaRepository;

@Service
@RequiredArgsConstructor
public class AreaService {

    private final AreaRepository areaRepository;
    public void createArea() {//If we would like to provide some informations with that area make version with DTO
        areaRepository.save(new Area(0));

    }
}
