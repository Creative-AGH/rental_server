package pl.creative.rental_server.statusOfItemManagement;

import pl.creative.rental_server.entities.StatusOfItem;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;


//source https://www.baeldung.com/jpa-persisting-enums-in-jpa
/*
With @Enumerated(EnumType.STRING),
we can safely add new enum values or change our enum's order
 */
@Converter(autoApply = true)
public class StatusOfItemConverter implements AttributeConverter<StatusOfItem,String>{
    @Override
    public String convertToDatabaseColumn(StatusOfItem statusOfItem) {
        if (statusOfItem == null) {
            return null;
        }
        return statusOfItem.getCode();
    }

    @Override
    public StatusOfItem convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(StatusOfItem.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
