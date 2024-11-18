package danpoong.soenter.domain.review.controller;

import danpoong.soenter.base.ApiResponse;
import danpoong.soenter.domain.review.dto.ReviewDTO.ReviewResponse.GetEnterpriseReviewResponse;
import danpoong.soenter.domain.review.dto.ReviewDTO.ReviewResponse.MyReviewsWrapperResponse;
import danpoong.soenter.domain.review.dto.ReviewDTO.ReviewResponse.PostReviewResponse;
import danpoong.soenter.domain.review.dto.ReviewDTO.ReviewRequest.PostReviewRequest;
import danpoong.soenter.domain.review.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
@Tag(name = "Review Controller", description = "리뷰 관련 API")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    @Operation(summary = "리뷰 등록 API", description = "사용자가 특정 기업에 대한 리뷰를 등록합니다.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON400", description = "잘못된 요청입니다.")
    public ApiResponse<PostReviewResponse> createReview(@RequestBody @Valid PostReviewRequest postReviewRequest, Authentication authentication) {
        PostReviewResponse response = reviewService.createReview(postReviewRequest, authentication.getName());
        return ApiResponse.onSuccess(response);
    }

    @GetMapping()
    @Operation(summary = "내가 쓴 리뷰 조회 API", description = "사용자가 작성한 모든 리뷰를 조회합니다.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON400", description = "잘못된 요청입니다.")
    public ApiResponse<MyReviewsWrapperResponse> getMyReviews(Authentication authentication) {
        MyReviewsWrapperResponse response = reviewService.getMyReviews(authentication.getName());
        return ApiResponse.onSuccess(response);
    }

    @PutMapping("/{reviewId}")
    @Operation(summary = "리뷰 수정 API", description = "사용자가 작성한 리뷰를 수정합니다.")
    public ApiResponse<PostReviewResponse> updateReview(
            @PathVariable Long reviewId,
            @RequestBody @Valid PostReviewRequest postReviewRequest,
            Authentication authentication) {
        PostReviewResponse response = reviewService.updateReview(reviewId, postReviewRequest, authentication.getName());
        return ApiResponse.onSuccess(response);
    }

    @DeleteMapping("/{reviewId}")
    @Operation(summary = "리뷰 삭제 API", description = "사용자가 작성한 리뷰를 삭제합니다.")
    public ApiResponse<String> deleteReview(@PathVariable Long reviewId, Authentication authentication) {
        reviewService.deleteReview(reviewId, authentication.getName());
        return ApiResponse.onSuccess(null);
    }

    @GetMapping("/{enterpriseId}/enterprises")
    @Operation(summary = "기업 리뷰 조회 API", description = "특정 기업에 대한 모든 리뷰를 조회합니다.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON400", description = "잘못된 요청입니다.")
    public ApiResponse<List<GetEnterpriseReviewResponse>> getEnterpriseReviews(@PathVariable Long enterpriseId, Authentication authentication) {
        List<GetEnterpriseReviewResponse> response = reviewService.getEnterpriseReviews(enterpriseId, authentication.getName());
        return ApiResponse.onSuccess(response);
    }
}