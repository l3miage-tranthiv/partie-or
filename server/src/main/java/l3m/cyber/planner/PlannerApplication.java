package l3m.cyber.planner;

// Aucune modification necessaire dans ce fichier

import l3m.cyber.planner.requests.PlannerParameter;
import l3m.cyber.planner.utils.Graphe;
import l3m.cyber.planner.utils.PartitionAlea;
import l3m.cyber.planner.utils.PartitionKCentre;
import l3m.cyber.planner.utils.Planner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class PlannerApplication {

	public static void main(String[] args) throws CloneNotSupportedException {
		SpringApplication.run(PlannerApplication.class, args);


		// PartitionKCentre partitionKCentre = new PartitionKCentre(7,2);
		Double[][] dist = {
				{ 0.0 , 2.5, 3.0  , 0.1   , 17.0 , 15.5, 8.2},
				{2.5 , 0.0  , 1.0  , 5.25, 18.0 , 3.5 , 12.0},
				{3.0   , 1.0  , 0.0  , 0.0   , 3.4, 9.9 , 14.0},
				{0.1   , 5.25, 0.0 , 0.0   , 7.7, 8.8 , 6.8},
				{17.0  , 18.0 , 3.4, 7.7 , 0.0  , 2.0   , 2.2},
				{15.5, 3.5, 9.9, 8.8 , 2.0  , 0.0   , 3.3},
				{8.2 , 12.0 , 14.0 , 6.8 , 2.2, 3.3 , 0.0}
		};


		Double[][] dist1= {
				{0.0, 2.0, 3.0},
				{2.0,0.0, 1.0},
				{3.0,1.0,0.0}
		};

		PlannerParameter plannerParameter = new PlannerParameter(dist,2,0);
		Planner planner = new Planner(plannerParameter);
		// partitionne les livraisons entre livreurs
		planner.divise();
		// calcul la tournée de chaque livreur
		planner.calculeTournee();
		// calcul de la longueur de chaque tournée
		planner.calculeLongTournees();
		planner.result();

		System.out.println("Tournee pour livreur 1 " + planner.getTournees().get(0));
		System.out.println("Tournee pour livreur 2 " + planner.getTournees().get(1));


		System.out.println("LongTournee du livreur 1 "+ planner.getLongTournees().getFirst());
		System.out.println("LongTournee du livreur 2 "+ planner.getLongTournees().get(1));








	}

}
