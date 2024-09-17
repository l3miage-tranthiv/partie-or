package l3m.cyber.planner;

import l3m.cyber.planner.utils.PartitionKCentre;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class PartitionKCentreTest {


    @Test
    public void partitionneKCentreTest() {

        PartitionKCentre partitionKCentre = new PartitionKCentre(7,2);
        Double[][] dist = {
                { 0.0 , 2.5, 3.0  , 0.1   , 17.0 , 15.5, 8.2},
                {2.5 , 0.0  , 1.0  , 5.25, 18.0 , 3.5 , 12.0},
                {3.0   , 1.0  , 0.0  , 0.0   , 3.4, 9.9 , 14.0},
                {0.1   , 5.25, 0.0 , 0.0   , 7.7, 8.8 , 6.8},
                {17.0  , 18.0 , 3.4, 7.7 , 0.0  , 2.0   , 2.2},
                {15.5, 3.5, 9.9, 8.8 , 2.0  , 0.0   , 3.3},
                {8.2 , 12.0 , 14.0 , 6.8 , 2.2, 3.3 , 0.0}
        };
        partitionKCentre.partitionne(dist);

        List<Integer> partie1 = Arrays.asList(0,1,2,3);
        List<Integer> partie2 = Arrays.asList(0,4,5,6);

        assertEquals(4, partitionKCentre.getPartie(1).size());
        assertEquals(4, partitionKCentre.getPartie(0).size());

        assertEquals(partie1,partitionKCentre.getPartie(1));
        assertEquals(partie2,partitionKCentre.getPartie(0));

    }
}
