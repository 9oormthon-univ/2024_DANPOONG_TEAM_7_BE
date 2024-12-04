package danpoong.soenter.domain.job.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class JobDTO {
    public static class JobRequest {
        @Builder
        @Getter
        @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class PostJobRequest {
            @NotNull
            private String title;
            private String field;
            private String duty;
            private String region;
            private MultipartFile image;

            private String salary;
            private String workPeriod;
            private String workDays;
            private String workHours;
            private String jobType;
            private String employmentType;
            private String benefits;

            private String deadline;
            private String requiredPeriod;
            private String education;
            private String preference;

            private String detailAddress;

            private String manager;
            private String phone;
            private String email;
            private String website;
        }
    }

    public static class JobResponse {
        @Builder
        @Getter
        public static class PostJobResponse {
            private Long jobId;
            private String title;
            private String enterpriseName;
            private String image;
            private String field;
            private String duty;
            private String region;
        }

        @Builder
        @Getter
        public static class GetJobResponse {
            private Long jobId;
            private String title;
            private String enterpriseName;
            private String field;
            private String duty;
            private String region;
            private String image;

            private String salary;
            private String workPeriod;
            private String workDays;
            private String workHours;
            private String jobType;
            private String employmentType;
            private String benefits;

            private String deadline;
            private String requiredPeriod;
            private String education;
            private String preference;

            private String detailAddress;

            private String manager;
            private String phone;
            private String email;
            private String website;
        }

        @Builder
        @Getter
        public static class GetUserJobsResponse {
            private List<GetJobResponse> jobs;
        }
    }
}