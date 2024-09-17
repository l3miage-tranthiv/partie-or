package l3m.cyber.planner.responses;

// Aucune modification necessaire dans ce fichier

import java.util.ArrayList;

public record PlannerResult(ArrayList<ArrayList<Integer>> tournees, ArrayList<Double> longTournees) {
    // Dans un type record, les getters sont créés automatiquement : tournees() et longTournees()


}
