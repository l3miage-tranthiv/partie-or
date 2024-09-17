package l3m.cyber.planner.utils;

import l3m.cyber.planner.requests.PlannerParameter;
import l3m.cyber.planner.responses.PlannerResult;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


public class Planner{

    private Double[][] distances;

    private int k;

    private  int debut;

    @Getter
    private Partition p ;

    @Getter
    @Setter
    private ArrayList<ArrayList<Integer>> tournees ;

    @Getter
    private ArrayList<Double> longTournees;

    public Planner(PlannerParameter param){
        this(param.matrix(), param.k(), param.start());
    }

    public Planner(Double[][] distances, int k, int debut){
        this.distances = distances;
        this.k = k ;
        this.debut = debut;
    }

    public Planner(){
        this(null, 0, 0);
    }


    
    public PlannerResult result(){
        return new PlannerResult(tournees, longTournees);
    }

    public void divise() {
        this.p = new PartitionKCentre(this.distances.length, this.k);
        p.partitionne(distances);
        tournees = new ArrayList<ArrayList<Integer>>();
        for (int i=0; i<k; i++) {
            tournees.add(p.getPartie(i));
        }
    }


    public Double[][] getSousMatrice(List<Integer> selec) {
        int n = selec.size();
        Double[][] sousMatrice = new Double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sousMatrice[i][j] = distances[selec.get(i)][selec.get(j)];
            }
        }
        return sousMatrice;
    }

    public ArrayList<Integer> calculeUneTournee(ArrayList<Integer> select)  {
        Double[][] matrice1 = getSousMatrice(select);
        Graphe graphe = new Graphe(matrice1,select);
        return graphe.tsp(0);
    }

    public void calculeTournee()  {

        for (int i = 0; i < tournees.size(); i++) {
            ArrayList<Integer> listElem = tournees.get(i);
            ArrayList<Integer> tournee = calculeUneTournee(listElem);
            tournees.set(i, tournee);
        }
    }
    public void calculeLongTournees() {
        longTournees = new ArrayList<Double>();
        for (ArrayList<Integer> listElem : tournees) {
            Double longueur = 0.0;
            for (int j = 0; j < listElem.size() - 1; j++) {
                longueur += distances[listElem.get(j)][listElem.get(j + 1)];
            }
            longueur += distances[listElem.getLast()][listElem.getFirst()];
            longTournees.add(longueur);
        }

    }
    
}