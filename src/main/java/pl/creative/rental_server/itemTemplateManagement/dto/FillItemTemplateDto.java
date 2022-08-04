package pl.creative.rental_server.itemTemplateManagement.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class FillItemTemplateDto {
    @NotBlank(message = "Name may not be empty or null")
    private String name;
    private String description;

}
