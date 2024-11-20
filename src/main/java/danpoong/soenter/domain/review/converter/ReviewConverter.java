package danpoong.soenter.domain.review.converter;

import danpoong.soenter.domain.enterprise.entity.Enterprise;
import danpoong.soenter.domain.enterprise.entity.Visit;
import danpoong.soenter.domain.review.dto.ReviewDTO.ReviewResponse.GetEnterpriseReviewResponse;
import danpoong.soenter.domain.review.dto.ReviewDTO.ReviewResponse.GetMyReviewResponse;
import danpoong.soenter.domain.review.dto.ReviewDTO.ReviewResponse.PostReviewResponse;
import danpoong.soenter.domain.review.dto.ReviewDTO.ReviewRequest.PostReviewRequest;
import danpoong.soenter.domain.review.entity.Review;
import danpoong.soenter.domain.review.entity.TagList;
import danpoong.soenter.domain.user.entity.User;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

    public static PostReviewResponse toReviewResponse(Review review) {
        return PostReviewResponse.builder()
                .reviewId(review.getReviewId())
                .content(review.getContent())
                .createAt(review.getCreateAt())
                .build();
    }

    public static GetMyReviewResponse toMyReviewsResponse(Review review, List<TagList> tagList) {
        List<Integer> tagNumbers = tagList.stream()
                .map(TagList::getTagNum)
                .collect(Collectors.toList());

        return GetMyReviewResponse.builder()
                .reviewId(review.getReviewId())
                .enterpriseName(review.getEnterprise().getName())
                .enterpriseAddress(review.getEnterprise().getCity() + ", " + review.getEnterprise().getDistrict())
                .socialPurpose(review.getEnterprise().getSocialPurpose())
                .content(review.getContent())
                .createAt(review.getCreateAt())
                .tagCount(tagList.size())
                .tagNumbers(tagNumbers)
                .build();
    }

    public static GetEnterpriseReviewResponse toEnterpriseReviewResponse(Review review, List<TagList> tagList) {
        List<Integer> tagNumbers = tagList.stream()
                .map(TagList::getTagNum)
                .collect(Collectors.toList());

        return GetEnterpriseReviewResponse.builder()
                .reviewId(review.getReviewId())
                .userName(review.getUser().getName())
                .content(review.getContent())
                .createAt(review.getCreateAt())
                .tagCount(tagList.size())
                .tagNumbers(tagNumbers)
                .build();
    }
}
