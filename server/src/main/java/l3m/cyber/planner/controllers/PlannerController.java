package l3m.cyber.planner.controllers;

// Aucune modification necessaire dans ce fichier

import l3m.cyber.planner.endpoints.PlannerEndpoints;
import l3m.cyber.planner.requests.PlannerParameter;
import l3m.cyber.planner.responses.PlannerResult;
import l3m.cyber.planner.services.PlannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;


@Controller
@RequiredArgsConstructor
public class PlannerController implements PlannerEndpoints {
	private final PlannerService plannerService;

    @Override
    public PlannerResult planif(PlannerParameter params) {

            return plannerService.getResult(params);

    }
}
