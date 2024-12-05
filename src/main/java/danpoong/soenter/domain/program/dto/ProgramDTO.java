package danpoong.soenter.domain.program.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ProgramDTO {
    public static class ProgramRequest {
        @Builder
        @Getter
        @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class PostProgramRequest {
            @NotNull
            private String title;
            private String field;
            private String time;
            private String region;
            private MultipartFile image;
            private String content;
        }
    }

    public static class ProgramResponse {
        @Getter
        @Builder
        public static class PostProgramResponse {
            private Long programId;
            private String title;
            private String field;
            private String time;
            private String region;
            private String image;
            private String content;
        }

        @Getter
        @Builder
        public static class GetProgramResponse {
            private Long programId;
            private Long enterpriseId;
            private String enterpriseName;
            private String title;
            private String field;
            private String time;
            private String region;
            private String image;
            private String content;
        }

        @Getter
        @Builder
        public static class GetUserProgramsResponse {
            private List<GetProgramResponse> programs;
        }
    }
}