package danpoong.soenter.domain.program.service;

import danpoong.soenter.base.s3.S3Service;
import danpoong.soenter.domain.enterprise.entity.Enterprise;
import danpoong.soenter.domain.enterprise.repository.EnterpriseRepository;
import danpoong.soenter.domain.program.converter.ProgramConverter;
import danpoong.soenter.domain.program.dto.ProgramDTO.ProgramRequest.PostProgramRequest;
import danpoong.soenter.domain.program.dto.ProgramDTO.ProgramResponse.PostProgramResponse;
import danpoong.soenter.domain.program.dto.ProgramDTO.ProgramResponse.GetProgramResponse;
import danpoong.soenter.domain.program.dto.ProgramDTO.ProgramResponse.GetUserProgramsResponse;

import danpoong.soenter.domain.program.entity.Program;
import danpoong.soenter.domain.program.repository.ProgramRepository;
import danpoong.soenter.domain.user.entity.User;
import danpoong.soenter.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProgramServiceImpl implements ProgramService {

    private final ProgramRepository programRepository;
    private final UserRepository userRepository;
    private final EnterpriseRepository enterpriseRepository;
    private final S3Service s3Service;

    @Transactional
    public PostProgramResponse createProgram(PostProgramRequest programRequest, String userId) {
        User user = userRepository.findById(Long.valueOf(userId))
                .orElseThrow(() -> new RuntimeException("존재하지 않는 사용자입니다."));

        Enterprise enterprise = user.getEnterprise();

        String imageUrl = null;
        if (programRequest.getImage() != null && !programRequest.getImage().isEmpty()) {
            imageUrl = s3Service.uploadFile(programRequest.getImage());
        }

        Program program = ProgramConverter.toProgram(enterprise, programRequest, imageUrl, user);
        programRepository.save(program);

        return ProgramConverter.toProgramResponse(program);
    }

    @Transactional(readOnly = true)
    public GetUserProgramsResponse getUserPrograms(String userId) {
        User user = userRepository.findById(Long.valueOf(userId))
                .orElseThrow(() -> new RuntimeException("존재하지 않는 사용자입니다."));

        Enterprise enterprise = user.getEnterprise();

        List<Program> programs = programRepository.findByEnterprise(enterprise);

        List<GetProgramResponse> programResponses = programs.stream()
                .map(ProgramConverter::toGetProgramResponse)
                .collect(Collectors.toList());

        return GetUserProgramsResponse.builder()
                .programs(programResponses)
                .build();
    }

    @Transactional(readOnly = true)
    public List<GetProgramResponse> getAllPrograms() {
        List<Program> programs = programRepository.findAll();

        return programs.stream()
                .map(ProgramConverter::toGetProgramResponse)
                .collect(Collectors.toList());
    }
}