package danpoong.soenter.domain.likes.controller;

import danpoong.soenter.base.ApiResponse;
import danpoong.soenter.domain.likes.dto.LikesDTO.LikesResponse.GetLikeResponse;
import danpoong.soenter.domain.likes.dto.LikesDTO.LikesRequest.AddLikeRequest;
import danpoong.soenter.domain.likes.service.LikesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/likes")
@RequiredArgsConstructor
@Tag(name = "Likes Controller", description = "즐겨찾기(좋아요) 관련 API")
public class LikesController {

    private final LikesService likesService;

    @PostMapping
    @Operation(summary = "즐겨찾기 추가 API", description = "특정 기업을 즐겨찾기에 추가합니다.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON400", description = "잘못된 요청입니다.")
    public ApiResponse<Void> addLike(@RequestBody @Valid AddLikeRequest request, Authentication authentication) {
        likesService.addLike(authentication.getName(), request.getEnterpriseId());
        return ApiResponse.onSuccess(null);
    }

    @GetMapping
    @Operation(summary = "즐겨찾기 조회 API", description = "사용자의 즐겨찾기 기업을 조회합니다.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON400", description = "잘못된 요청입니다.")
    public ApiResponse<List<GetLikeResponse>> getLikes(Authentication authentication) {
        List<GetLikeResponse> response = likesService.getLikes(authentication.getName());
        return ApiResponse.onSuccess(response);
    }

    @DeleteMapping("/{enterpriseId}")
    @Operation(summary = "즐겨찾기 삭제 API", description = "특정 기업을 즐겨찾기에서 삭제합니다.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON400", description = "잘못된 요청입니다.")
    public ApiResponse<Void> removeLike(@PathVariable Long enterpriseId, Authentication authentication) {
        likesService.removeLike(authentication.getName(), enterpriseId);
        return ApiResponse.onSuccess(null);
    }
}
