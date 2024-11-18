package danpoong.soenter.domain.review.service;
import danpoong.soenter.domain.review.dto.ReviewDTO;
import danpoong.soenter.domain.review.dto.ReviewDTO.ReviewRequest.PostReviewRequest;

public interface ReviewService {
    public ReviewDTO.ReviewResponse.PostReviewResponse createReview(PostReviewRequest postReviewRequest, String userId);
}
