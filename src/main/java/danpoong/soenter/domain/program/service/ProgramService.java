package danpoong.soenter.domain.program.service;

import danpoong.soenter.domain.program.dto.ProgramDTO.ProgramRequest.PostProgramRequest;
import danpoong.soenter.domain.program.dto.ProgramDTO.ProgramResponse.PostProgramResponse;
import danpoong.soenter.domain.program.dto.ProgramDTO.ProgramResponse.GetUserProgramsResponse;
import danpoong.soenter.domain.program.dto.ProgramDTO.ProgramResponse.GetProgramResponse;

import java.util.List;

public interface ProgramService {
    PostProgramResponse createProgram(PostProgramRequest programRequest, String userId);
    GetUserProgramsResponse getUserPrograms(String userId);
    List<GetProgramResponse> getAllPrograms();
}
