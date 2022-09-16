package pl.creative.rental_server.core.features.moderator.account.dto;

import org.mapstruct.Mapper;
import pl.creative.rental_server.db.entities.Account;

@Mapper(componentModel = "spring", uses = AccountMapper.class)
public interface AccountMapper {
    GetAccountDto mapAccountToGetAccountDto(Account account);
}
