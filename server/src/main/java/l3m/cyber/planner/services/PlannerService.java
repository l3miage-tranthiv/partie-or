package l3m.cyber.planner.services;

import l3m.cyber.planner.utils.Planner;
import l3m.cyber.planner.requests.PlannerParameter;
import l3m.cyber.planner.responses.PlannerResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlannerService {

    public PlannerResult getResult(PlannerParameter params)  {
        Planner pl = new Planner(params);
        pl.divise();
        pl.calculeTournee();
        pl.calculeLongTournees();
        return pl.result();
    }

}
