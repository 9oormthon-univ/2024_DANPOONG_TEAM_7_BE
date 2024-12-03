package danpoong.soenter.domain.program.controller;

import danpoong.soenter.base.ApiResponse;
import danpoong.soenter.domain.program.service.ProgramService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import danpoong.soenter.domain.program.dto.ProgramDTO.ProgramRequest.PostProgramRequest;
import danpoong.soenter.domain.program.dto.ProgramDTO.ProgramResponse.PostProgramResponse;
import danpoong.soenter.domain.program.dto.ProgramDTO.ProgramResponse.GetProgramResponse;
import danpoong.soenter.domain.program.dto.ProgramDTO.ProgramResponse.GetUserProgramsResponse;

import java.util.List;

@RestController
@RequestMapping("/api/programs")
@RequiredArgsConstructor
@Tag(name = "Program Controller", description = "프로그램 관련 API")
public class ProgramController {

    private final ProgramService programService;

    @PostMapping("/admin")
    @Operation(summary = "프로그램 등록 API", description = "사용자가 특정 기업의 프로그램을 등록합니다.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON400", description = "잘못된 요청입니다.")
    public ApiResponse<PostProgramResponse> createProgram(@ModelAttribute @Valid PostProgramRequest programRequest,  Authentication authentication) {
        PostProgramResponse response = programService.createProgram(programRequest, authentication.getName());
        return ApiResponse.onSuccess(response);
    }

    @GetMapping
    @Operation(summary = "전체 프로그램 조회 API", description = "등록된 모든 프로그램을 조회합니다.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공")
    public ApiResponse<List<GetProgramResponse>> getAllPrograms() {
        List<GetProgramResponse> response = programService.getAllPrograms();
        return ApiResponse.onSuccess(response);
    }

    @GetMapping("/admin")
    @Operation(summary = "사용자의 프로그램 조회 API", description = "사용자가 등록한 기업의 모든 프로그램을 조회합니다.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON400", description = "잘못된 요청입니다.")
    public ApiResponse<GetUserProgramsResponse> getUserPrograms(Authentication authentication) {
        GetUserProgramsResponse response = programService.getUserPrograms(authentication.getName());
        return ApiResponse.onSuccess(response);
    }
}
