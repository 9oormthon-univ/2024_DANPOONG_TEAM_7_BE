package danpoong.soenter.domain.review.converter;

import danpoong.soenter.domain.enterprise.entity.Enterprise;
import danpoong.soenter.domain.enterprise.entity.Visit;
import danpoong.soenter.domain.review.dto.ReviewDTO.ReviewResponse.PostReviewResponse;
import danpoong.soenter.domain.review.dto.ReviewDTO.ReviewRequest.PostReviewRequest;
import danpoong.soenter.domain.review.entity.Review;
import danpoong.soenter.domain.review.entity.TagList;
import danpoong.soenter.domain.user.entity.User;

import java.time.LocalDate;

public class ReviewConverter {
    public static Visit toVisit(User user, Enterprise enterprise, LocalDate visitDate) {
        return Visit.builder()
                .user(user)
                .enterprise(enterprise)
                .visitDate(visitDate)
                .build();
    }

    public static Review toReview(User user, Enterprise enterprise, PostReviewRequest requestDto) {
        return Review.builder()
                .user(user)
                .enterprise(enterprise)
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .createAt(LocalDate.now())
                .build();
    }

    public static TagList toTagList(Review review, Integer tagNum) {
        return TagList.builder()
                .review(review)
                .tagNum(tagNum)
                .build();
    }

    public static PostReviewResponse toReviewResponseDto(Review review) {
        return PostReviewResponse.builder()
                .reviewId(review.getReviewId())
                .title(review.getTitle())
                .content(review.getContent())
                .createAt(review.getCreateAt())
                .build();
    }
}
