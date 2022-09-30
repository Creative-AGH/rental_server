package pl.creative.rental_server.core.features.moderator.account.dto;

import lombok.Data;

@Data
public class GetAccountDto {
    private Long id;
    private String name;
    private String surname;

}
