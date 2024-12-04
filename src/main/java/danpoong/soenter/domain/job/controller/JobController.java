package danpoong.soenter.domain.job.controller;

import danpoong.soenter.base.ApiResponse;
import danpoong.soenter.domain.job.dto.JobDTO.JobResponse.PostJobResponse;
import danpoong.soenter.domain.job.dto.JobDTO.JobResponse.GetUserJobsResponse;
import danpoong.soenter.domain.job.dto.JobDTO.JobResponse.GetJobResponse;
import danpoong.soenter.domain.job.dto.JobDTO.JobRequest.PostJobRequest;
import danpoong.soenter.domain.job.service.JobService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
@Tag(name = "Job Controller", description = "일자리 관련 API")
public class JobController {

    private final JobService jobService;

    @PostMapping("/admin")
    @Operation(summary = "일자리 등록 API", description = "사용자가 기업의 일자리를 등록합니다.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON400", description = "잘못된 요청입니다.")
    public ApiResponse<PostJobResponse> createJob(
            @ModelAttribute @Valid PostJobRequest jobRequest,
            Authentication authentication) {
        PostJobResponse response = jobService.createJob(jobRequest, authentication.getName());
        return ApiResponse.onSuccess(response);
    }

    @GetMapping
    @Operation(summary = "전체 일자리 조회 API", description = "등록된 모든 일자리를 조회합니다.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공")
    public ApiResponse<List<GetJobResponse>> getAllJobs() {
        List<GetJobResponse> response = jobService.getAllJobs();
        return ApiResponse.onSuccess(response);
    }

    @GetMapping("/admin")
    @Operation(summary = "사용자의 일자리 조회 API", description = "사용자가 등록한 기업의 모든 일자리를 조회합니다.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON400", description = "잘못된 요청입니다.")
    public ApiResponse<GetUserJobsResponse> getUserJobs(Authentication authentication) {
        GetUserJobsResponse response = jobService.getUserJobs(authentication.getName());
        return ApiResponse.onSuccess(response);
    }
}