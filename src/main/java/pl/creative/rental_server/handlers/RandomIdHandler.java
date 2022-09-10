package pl.creative.rental_server.handlers;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import pl.creative.rental_server.entities.JwtToken;

import java.util.UUID;
import java.util.regex.Pattern;

@Service
public class RandomIdHandler {
    public <T extends CrudRepository<V, String>, V> String generateUniqueIdFromTable(T repository) {
        String uuid;
        do {
            uuid = UUID.randomUUID().toString();
        } while (repository.findById(uuid).isPresent());
        return uuid;
    }
}
