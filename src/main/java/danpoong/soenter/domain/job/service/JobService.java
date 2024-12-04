package danpoong.soenter.domain.job.service;

import danpoong.soenter.domain.job.dto.JobDTO.JobResponse.PostJobResponse;
import danpoong.soenter.domain.job.dto.JobDTO.JobResponse.GetUserJobsResponse;
import danpoong.soenter.domain.job.dto.JobDTO.JobResponse.GetJobResponse;
import danpoong.soenter.domain.job.dto.JobDTO.JobRequest.PostJobRequest;

import java.util.List;

public interface JobService {
    PostJobResponse createJob(PostJobRequest jobRequest, String userId);
    GetUserJobsResponse getUserJobs(String userId);
    List<GetJobResponse> getAllJobs();
}
