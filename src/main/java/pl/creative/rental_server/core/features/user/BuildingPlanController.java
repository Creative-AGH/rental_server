package pl.creative.rental_server.core.features.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.creative.rental_server.core.global.handlersAndUtils.BuildingPlanService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BuildingPlanController {

    private final BuildingPlanService buildingPlanService;

    @GetMapping("/user/getAllBuildingPlans")
    public List<String> getAllBuildingPlans() {
        return buildingPlanService.getLinksToAllBuildingPlans();
    }
}
