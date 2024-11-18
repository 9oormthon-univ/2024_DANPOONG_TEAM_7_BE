package danpoong.soenter.domain.enterprise;

import danpoong.soenter.base.ApiResponse;
import danpoong.soenter.domain.enterprise.entity.Enterprise;
import danpoong.soenter.domain.enterprise.entity.Region;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/enterprises")
@Tag(name = "Enterprise 컨트롤러", description = "기업 정보 관련 API")
public class EnterpriseController {

    private final EnterpriseService enterpriseService;

    @Operation(summary = "지역별 기업 조회", description = "특정 지역에 속한 기업 목록을 반환합니다.")
    @GetMapping("/{region}")
    public ApiResponse<List<Enterprise>> getEnterprisesByRegion(@PathVariable("region") Region region) {
        return enterpriseService.getEnterprisesByRegion(region);
    }
}