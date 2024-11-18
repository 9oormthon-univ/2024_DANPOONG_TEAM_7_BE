package danpoong.soenter.domain.review.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

public class ReviewDTO {
    public static class ReviewRequest {

        @Builder
        @Getter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class PostReviewRequest {
            @NotNull
            private Long enterpriseId;
            private String title;
            private String content;
            private LocalDate visitDate;
            private List<Integer> tagNumbers;

        }
    }

    public static class ReviewResponse {
        @Getter
        @Builder
        public static class PostReviewResponse {
            private Long reviewId;
            private String title;
            private String content;
            private LocalDate createAt;
        }
    }
}
