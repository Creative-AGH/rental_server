package pl.creative.rental_server.core.global.handlersAndUtils;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

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
