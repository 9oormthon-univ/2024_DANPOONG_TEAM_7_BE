package danpoong.soenter.domain.job.converter;

import danpoong.soenter.domain.enterprise.entity.Enterprise;
import danpoong.soenter.domain.job.dto.JobDTO.JobRequest.PostJobRequest;
import danpoong.soenter.domain.job.dto.JobDTO.JobResponse.PostJobResponse;
import danpoong.soenter.domain.job.dto.JobDTO.JobResponse.GetJobResponse;
import danpoong.soenter.domain.job.dto.JobDTO.JobResponse.GetUserJobsResponse;
import danpoong.soenter.domain.job.entity.Job;
import danpoong.soenter.domain.user.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class JobConverter {

    public static Job toJob(Enterprise enterprise, PostJobRequest requestDto, String imageUrl, User user) {
        return Job.builder()
                .user(user)
                .enterprise(enterprise)
                .title(requestDto.getTitle())
                .field(requestDto.getField())
                .duty(requestDto.getDuty())
                .region(requestDto.getRegion())
                .image(imageUrl)
                .salary(requestDto.getSalary())
                .workPeriod(requestDto.getWorkPeriod())
                .workDays(requestDto.getWorkDays())
                .workHours(requestDto.getWorkHours())
                .jobType(requestDto.getJobType())
                .employmentType(requestDto.getEmploymentType())
                .benefits(requestDto.getBenefits())
                .deadline(requestDto.getDeadline())
                .requiredPeriod(requestDto.getRequiredPeriod())
                .education(requestDto.getEducation())
                .preference(requestDto.getPreference())
                .detailAddress(requestDto.getDetailAddress())
                .manager(requestDto.getManager())
                .phone(requestDto.getPhone())
                .email(requestDto.getEmail())
                .website(requestDto.getWebsite())
                .build();
    }

    public static PostJobResponse toPostJobResponse(Job job) {
        return PostJobResponse.builder()
                .jobId(job.getJobId())
                .title(job.getTitle())
                .field(job.getField())
                .duty(job.getDuty())
                .region(job.getRegion())
                .image(job.getImage())
                .build();
    }

    public static GetJobResponse toGetJobResponse(Job job, Enterprise enterprise) {
        return GetJobResponse.builder()
                .jobId(job.getJobId())
                .title(job.getTitle())
                .enterpriseName(enterprise.getName())
                .field(job.getField())
                .duty(job.getDuty())
                .region(job.getRegion())
                .image(job.getImage())
                .salary(job.getSalary())
                .workPeriod(job.getWorkPeriod())
                .workDays(job.getWorkDays())
                .workHours(job.getWorkHours())
                .jobType(job.getJobType())
                .employmentType(job.getEmploymentType())
                .benefits(job.getBenefits())
                .deadline(job.getDeadline())
                .requiredPeriod(job.getRequiredPeriod())
                .education(job.getEducation())
                .preference(job.getPreference())
                .detailAddress(job.getDetailAddress())
                .manager(job.getManager())
                .phone(job.getPhone())
                .email(job.getEmail())
                .website(job.getWebsite())
                .build();
    }

    public static GetUserJobsResponse toGetUserJobsResponse(List<Job> jobs) {
        List<GetJobResponse> jobResponses = jobs.stream()
                .map(job -> {
                    Enterprise enterprise = job.getEnterprise();
                    return toGetJobResponse(job, enterprise);
                })
                .collect(Collectors.toList());

        return GetUserJobsResponse.builder()
                .jobs(jobResponses)
                .build();
    }
}