package danpoong.soenter.domain.program.converter;

import danpoong.soenter.domain.enterprise.entity.Enterprise;
import danpoong.soenter.domain.program.dto.ProgramDTO.ProgramRequest.PostProgramRequest;
import danpoong.soenter.domain.program.dto.ProgramDTO.ProgramResponse.PostProgramResponse;
import danpoong.soenter.domain.program.dto.ProgramDTO.ProgramResponse.GetProgramResponse;
import danpoong.soenter.domain.program.dto.ProgramDTO.ProgramResponse.GetUserProgramsResponse;
import danpoong.soenter.domain.program.entity.Program;
import danpoong.soenter.domain.user.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class ProgramConverter {
    public static Program toProgram(Enterprise enterprise, PostProgramRequest requestDto, String imageUrl, User user) {
        return Program.builder()
                .user(user)
                .enterprise(enterprise)
                .title(requestDto.getTitle())
                .field(requestDto.getField())
                .time(requestDto.getTime())
                .region(requestDto.getRegion())
                .image(imageUrl)
                .content(requestDto.getContent())
                .build();
    }

    public static PostProgramResponse toProgramResponse(Program program) {
        return PostProgramResponse.builder()
                .programId(program.getProgramId())
                .title(program.getTitle())
                .field(program.getField())
                .time(program.getTime())
                .region(program.getRegion())
                .image(program.getImage())
                .content(program.getContent())
                .build();
    }

    public static GetProgramResponse toGetProgramResponse(Program program) {
        return GetProgramResponse.builder()
                .programId(program.getProgramId())
                .enterpriseName(program.getEnterprise().getName())
                .title(program.getTitle())
                .field(program.getField())
                .time(program.getTime())
                .region(program.getRegion())
                .image(program.getImage())
                .content(program.getContent())
                .build();
    }

    public static GetUserProgramsResponse toGetUserProgramsResponse(List<Program> programs) {
        List<GetProgramResponse> programResponses = programs.stream()
                .map(ProgramConverter::toGetProgramResponse)
                .collect(Collectors.toList());

        return GetUserProgramsResponse.builder()
                .programs(programResponses)
                .build();
    }
}
