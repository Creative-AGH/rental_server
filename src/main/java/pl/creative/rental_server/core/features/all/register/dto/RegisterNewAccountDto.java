package pl.creative.rental_server.core.features.all.register.dto;

import lombok.Data;

@Data
public class RegisterNewAccountDto {
//    @NotNull(message = "Email can not be null")
//    @NotEmpty(message = "Email can not be empty")
//    @NotBlank(message = "Email can not be null or empty")
//    @Size(max = 255, message = "Email must be shorter than {max} signs")
//    @Email(message = "Email has to meet all email criteria")
    private String email;
//    @NotNull(message = "Name can not be null")
//    @NotEmpty(message = "Name can not be empty")
//    @NotBlank(message = "Name can not be null or empty")
//    @Size(max = 255, message = "Name must be shorter than {max} signs")
    private String name;
//    @NotNull(message = "Surname can not be null")
//    @NotEmpty(message = "Surname can not be empty")
//    @NotBlank(message = "Surname can not be null or empty")
//    @Size(max = 255, message = "Surname must be shorter than {max} signs")
    private String surname;
//
//    @NotNull(message = "Password can not be null")
//    @NotEmpty(message = "Password can not be empty")
//    @Size(min = 8,max = 255, message = "Password must be longer than {min} signs and shorter than {max} signs")
    private String password;
}
