package pl.creative.rental_server.accountManagement.dto;

import org.mapstruct.Mapper;
import pl.creative.rental_server.entities.Account;

@Mapper(componentModel = "spring", uses = AccountMapper.class)
public interface AccountMapper {
    GetAccountDto mapAccountToGetAccountDto(Account account);
}
