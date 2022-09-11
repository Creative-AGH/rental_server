package pl.creative.rental_server.core.features.all.register.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.creative.rental_server.db.entities.Account;

@Mapper(componentModel = "spring", uses = RegisterMapper.class)
public interface RegisterMapper {
    @Mapping(source = "password", target = "password", ignore = true)
    Account mapRegisterNewAccountDtoToAccount(RegisterNewAccountDto registerNewAccountDto);
}
