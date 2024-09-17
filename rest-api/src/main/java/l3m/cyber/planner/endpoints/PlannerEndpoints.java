package l3m.cyber.planner.endpoints;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import l3m.cyber.planner.requests.PlannerParameter;
import l3m.cyber.planner.responses.PlannerResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/planner")
@Tag(name = "Planner endpoints")
public interface PlannerEndpoints {

    @Operation(description = "Appeler le planner afin d'avoir une planification")
    @ApiResponse(responseCode = "200", description = "La planification calculer par le web service")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/planif")
    PlannerResult planif(@RequestBody PlannerParameter params);
}
