package danpoong.soenter.domain.review.service;
import danpoong.soenter.domain.review.dto.ReviewDTO.ReviewResponse.MyReviewsWrapperResponse;
import danpoong.soenter.domain.review.dto.ReviewDTO.ReviewRequest.PostReviewRequest;
import danpoong.soenter.domain.review.dto.ReviewDTO.ReviewResponse.PostReviewResponse;

import java.util.List;

public interface ReviewService {
    public PostReviewResponse createReview(PostReviewRequest postReviewRequest, String userId);
    public MyReviewsWrapperResponse getMyReviews(String userId);
    public PostReviewResponse updateReview(Long reviewId, PostReviewRequest postReviewRequest, String userId);
}
