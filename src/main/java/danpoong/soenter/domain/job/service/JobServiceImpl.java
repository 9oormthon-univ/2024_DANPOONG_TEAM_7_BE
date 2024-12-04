package danpoong.soenter.domain.job.service;

import danpoong.soenter.base.s3.S3Service;
import danpoong.soenter.domain.enterprise.entity.Enterprise;
import danpoong.soenter.domain.job.converter.JobConverter;
import danpoong.soenter.domain.job.dto.JobDTO.JobRequest.PostJobRequest;
import danpoong.soenter.domain.job.dto.JobDTO.JobResponse.PostJobResponse;
import danpoong.soenter.domain.job.dto.JobDTO.JobResponse.GetJobResponse;
import danpoong.soenter.domain.job.dto.JobDTO.JobResponse.GetUserJobsResponse;
import danpoong.soenter.domain.job.entity.Job;
import danpoong.soenter.domain.job.repository.JobRepository;
import danpoong.soenter.domain.user.entity.User;
import danpoong.soenter.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final UserRepository userRepository;
    private final S3Service s3Service;

    @Transactional
    public PostJobResponse createJob(PostJobRequest jobRequest, String userId) {
        User user = userRepository.findById(Long.valueOf(userId))
                .orElseThrow(() -> new RuntimeException("존재하지 않는 사용자입니다."));

        Enterprise enterprise = user.getEnterprise();

        String imageUrl = null;
        if (jobRequest.getImage() != null && !jobRequest.getImage().isEmpty()) {
            imageUrl = s3Service.uploadFile(jobRequest.getImage());
        }

        Job job = JobConverter.toJob(enterprise, jobRequest, imageUrl, user);
        jobRepository.save(job);

        return JobConverter.toPostJobResponse(job);
    }

    @Transactional(readOnly = true)
    public GetUserJobsResponse getUserJobs(String userId) {
        User user = userRepository.findById(Long.valueOf(userId))
                .orElseThrow(() -> new RuntimeException("존재하지 않는 사용자입니다."));

        Enterprise enterprise = user.getEnterprise();

        List<Job> jobs = jobRepository.findByEnterprise(enterprise);
        List<GetJobResponse> jobResponses = jobs.stream()
                .map(job -> JobConverter.toGetJobResponse(job, job.getEnterprise()))
                .collect(Collectors.toList());

        return GetUserJobsResponse.builder()
                .jobs(jobResponses)
                .build();
    }

    @Transactional(readOnly = true)
    public List<GetJobResponse> getAllJobs() {
        List<Job> jobs = jobRepository.findAll();

        return jobs.stream()
                .map(job -> JobConverter.toGetJobResponse(job, job.getEnterprise()))
                .collect(Collectors.toList());
    }
}