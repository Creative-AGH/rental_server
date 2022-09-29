package pl.creative.rental_server.core.features.admin.buildingplan;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.creative.rental_server.core.global.handlersAndUtils.BuildingPlanService;

@RestController
@Data
public class BuildingPlanManageController {
    private final BuildingPlanService buildingPlanService;

    @PostMapping("/admin/buildingPlan/insert")
    public ResponseEntity<?> insertBuildingPlan(@RequestBody BuildPlanDto buildPlanDto) {
        return ResponseEntity.ok(buildingPlanService.createBuildingPlan(buildPlanDto.getPlanEncodedBase64(), buildPlanDto.getFileName()));

    }
}
