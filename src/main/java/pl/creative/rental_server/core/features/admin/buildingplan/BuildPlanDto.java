package pl.creative.rental_server.core.features.admin.buildingplan;

import lombok.Data;

@Data
public class BuildPlanDto {
    private final String planEncodedBase64;
    private final String fileExtension;
}
