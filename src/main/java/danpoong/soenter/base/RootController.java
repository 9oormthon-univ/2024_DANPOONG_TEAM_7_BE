package danpoong.soenter.base;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Test 컨트롤러")
public class RootController {

    @Operation(summary = "Test API", description = "Test용 API")
    @GetMapping("/health")
    public ApiResponse<String> healthCheck() {
        return ApiResponse.onSuccess("I'm healthy!");
    }
}
