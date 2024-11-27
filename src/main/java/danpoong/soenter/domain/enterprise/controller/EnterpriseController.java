package danpoong.soenter.domain.enterprise.controller;

import danpoong.soenter.base.ApiResponse;
import danpoong.soenter.domain.enterprise.dto.EnterpriseDTO.EnterpriseResponse.GetEnterpriseResponse;
import danpoong.soenter.domain.enterprise.dto.VisitDTO.VisitResponse.GetVisitedEnterpriseResponse;
import danpoong.soenter.domain.enterprise.service.EnterpriseService;
import danpoong.soenter.domain.enterprise.entity.Enterprise;
import danpoong.soenter.domain.enterprise.entity.Region;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/enterprises")
@Tag(name = "Enterprise 컨트롤러", description = "기업 정보 관련 API")
public class EnterpriseController {

    private final EnterpriseService enterpriseService;

    @Operation(summary = "지역별 기업 조회", description = "특정 지역에 속한 기업 목록을 반환합니다.")
    @GetMapping("/{region}")
    public ApiResponse<List<GetEnterpriseResponse>> getEnterprisesByRegion(@PathVariable("region") Region region) {
        try {
            return enterpriseService.getEnterprisesByRegion(region);
        } catch (IllegalArgumentException e) {
            return ApiResponse.onFailure("400", "Invalid region value. Please check the request.", null);
        }    }

    @GetMapping("/users/visit")
    @Operation(summary = "사용자가 방문한 기업 조회 API", description = "사용자가 방문한 모든 기업을 조회합니다.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON400", description = "잘못된 요청입니다.")
    public ApiResponse<List<GetVisitedEnterpriseResponse>> getVisitedEnterprises(Authentication authentication) {
        List<GetVisitedEnterpriseResponse>response = enterpriseService.getVisitedEnterprises(authentication.getName());
        return ApiResponse.onSuccess(response);
    }

    @Operation(summary = "지역 및 도시별 기업 조회", description = "특정 지역과 도시에 속한 기업 목록을 반환합니다.")
    @GetMapping("/{region}/{city}")
    public ApiResponse<List<GetEnterpriseResponse>> getEnterprisesByRegionAndCity(
            @PathVariable("region") Region region,
            @PathVariable("city") String city) {
        try {
            return enterpriseService.getEnterprisesByRegionAndCity(region, city);
        } catch (IllegalArgumentException e) {
            return ApiResponse.onFailure("400", "Invalid region or city value. Please check the request.", null);
        }
    }

}