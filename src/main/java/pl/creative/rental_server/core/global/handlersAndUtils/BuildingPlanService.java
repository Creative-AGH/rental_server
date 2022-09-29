package pl.creative.rental_server.core.global.handlersAndUtils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.creative.rental_server.db.entities.BuildingPlan;
import pl.creative.rental_server.db.repository.BuildingPlanRepository;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class BuildingPlanService {
    private final FileUploader fileUploader;
    private final BuildingPlanRepository buildingPlanRepository;

    private static String createBuildingPath(String id, String fileName) {
        return String.format("buildingPlan/%s_%s", id, fileName);
    }

    public List<String> getLinksToAllBuildingPlans() {
        return StreamSupport
                .stream(buildingPlanRepository.findAll().spliterator(), false)
                .map(BuildingPlan::getLink).toList();
    }

    public BuildingPlan createBuildingPlan(String encodedFile, String fileName) {

        BuildingPlan buildingPlan = buildingPlanRepository.save(new BuildingPlan());//usedForGenerateId
        Long id = buildingPlan.getId();

        String buildingPath = createBuildingPath(id.toString(), fileName);
        fileUploader.uploadBuildingPlan(encodedFile, buildingPath);
        buildingPlan.setLink(buildingPath);
        return buildingPlanRepository.save(buildingPlan);
    }

}
