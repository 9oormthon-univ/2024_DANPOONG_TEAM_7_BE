package danpoong.soenter.domain.review.service;

import danpoong.soenter.domain.enterprise.entity.Enterprise;
import danpoong.soenter.domain.enterprise.entity.Visit;
import danpoong.soenter.domain.enterprise.repository.EnterpriseRepository;
import danpoong.soenter.domain.enterprise.repository.VisitRepository;
import danpoong.soenter.domain.review.converter.ReviewConverter;
import danpoong.soenter.domain.review.dto.ReviewDTO.ReviewResponse.MyReviewsWrapperResponse;
import danpoong.soenter.domain.review.dto.ReviewDTO.ReviewResponse.GetMyReviewResponse;
import danpoong.soenter.domain.review.entity.Review;
import danpoong.soenter.domain.review.entity.TagList;
import danpoong.soenter.domain.review.repository.ReviewRepository;
import danpoong.soenter.domain.review.repository.TagListRepository;
import danpoong.soenter.domain.user.entity.User;
import danpoong.soenter.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import danpoong.soenter.domain.review.dto.ReviewDTO.ReviewRequest.PostReviewRequest;
import danpoong.soenter.domain.review.dto.ReviewDTO.ReviewResponse.PostReviewResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final VisitRepository visitRepository;
    private final UserRepository userRepository;
    private final EnterpriseRepository enterpriseRepository;
    private final TagListRepository tagListRepository;

    @Transactional
    public PostReviewResponse createReview(PostReviewRequest postReviewRequest, String userId) {
        User user = userRepository.findById(Long.valueOf(userId))
                .orElseThrow(() -> new RuntimeException("존재하지 않는 사용자입니다."));
        Enterprise enterprise = enterpriseRepository.findById(postReviewRequest.getEnterpriseId())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 기업입니다."));

        // Visit 기록 저장
        Visit visit = ReviewConverter.toVisit(user, enterprise, postReviewRequest.getVisitDate());
        visitRepository.save(visit);

        // Review 저장
        Review review = ReviewConverter.toReview(user, enterprise, postReviewRequest);
        reviewRepository.save(review);

        // TagList 저장
        postReviewRequest.getTagNumbers().forEach(tagNum -> {
            TagList tag = ReviewConverter.toTagList(review, tagNum);
            tagListRepository.save(tag);
        });

        // 저장된 TagList 개수 계산 후 Review의 tagNum 업데이트
        int tagCount = postReviewRequest.getTagNumbers().size();
        review.updateTagNum(tagCount);
        reviewRepository.save(review);

        return ReviewConverter.toReviewResponse(review);
    }

    @Transactional(readOnly = true)
    public MyReviewsWrapperResponse getMyReviews(String userId) {
        User user = userRepository.findById(Long.valueOf(userId))
                .orElseThrow(() -> new RuntimeException("존재하지 않는 사용자입니다."));

        List<Review> reviews = reviewRepository.findByUser(user);

        List<GetMyReviewResponse> reviewResponses = reviews.stream()
                .map(review -> ReviewConverter.toMyReviewsResponse(review, tagListRepository.findByReview(review)))
                .collect(Collectors.toList());

        return MyReviewsWrapperResponse.builder()
                .userName(user.getName())
                .totalReviewCount(reviews.size())
                .reviews(reviewResponses)
                .build();
    }

    @Transactional
    public PostReviewResponse updateReview(Long reviewId, PostReviewRequest postReviewRequest, String userId) {
        User user = userRepository.findById(Long.valueOf(userId))
                .orElseThrow(() -> new RuntimeException("존재하지 않는 사용자입니다."));

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 리뷰입니다."));

        if (!review.getUser().getUserId().equals(user.getUserId())) {
            throw new RuntimeException("사용자가 작성한 리뷰가 아닙니다.");
        }

        // 리뷰 내용 수정
        review.updateContent(postReviewRequest.getTitle(), postReviewRequest.getContent());
        tagListRepository.deleteAll(tagListRepository.findByReview(review)); // 기존 태그 삭제

        // 새로운 태그 저장
        postReviewRequest.getTagNumbers().forEach(tagNum -> {
            TagList tag = ReviewConverter.toTagList(review, tagNum);
            tagListRepository.save(tag);
        });

        review.updateTagNum(postReviewRequest.getTagNumbers().size()); // 태그 개수 업데이트
        reviewRepository.save(review);

        return ReviewConverter.toReviewResponse(review);
    }
}
