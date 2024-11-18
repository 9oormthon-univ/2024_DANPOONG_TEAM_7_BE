package danpoong.soenter.domain.enterprise;

import danpoong.soenter.base.ApiResponse;
import danpoong.soenter.domain.enterprise.entity.Enterprise;
import danpoong.soenter.domain.enterprise.entity.Region;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/enterprises")
public class EnterpriseController {

    private final EnterpriseService enterpriseService;

    @GetMapping("/{region}")
    public ApiResponse<List<Enterprise>> getEnterprisesByRegion(@PathVariable("region") Region region) {
        return enterpriseService.getEnterprisesByRegion(region);
    }
}