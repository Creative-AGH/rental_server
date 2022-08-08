package pl.creative.rental_server.accountManagement.dto;

import lombok.Data;

@Data
public class GetAccountDto {
    private Long id;
    private String name;
    private String surname;

}
