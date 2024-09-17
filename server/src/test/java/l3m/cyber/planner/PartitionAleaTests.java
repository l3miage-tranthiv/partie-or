package l3m.cyber.planner;

import l3m.cyber.planner.utils.PartitionAlea;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class PartitionAleaTests {


    @Test
    void partitionneAleatoryTest() {

        PartitionAlea partitionAlea = new PartitionAlea(4,2);
        Double[][] dist = {
                { 0.0 , 2.5, 3.0  , 0.1   , 17.0 , 15.5, 8.2},
                {2.5 , 0.0  , 1.0  , 5.25, 18.0 , 3.5 , 12.0},
                {3.0   , 1.0  , 0.0  , 0.0   , 3.4, 9.9 , 14.0},
                {0.1   , 5.25, 0.0 , 0.0   , 7.7, 8.8 , 6.8},
                {17.0  , 18.0 , 3.4, 7.7 , 0.0  , 2.0   , 2.2},
                {15.5, 3.5, 9.9, 8.8 , 2.0  , 0.0   , 3.3},
                {8.2 , 12.0 , 14.0 , 6.8 , 2.2, 3.3 , 0.0}
        };
        partitionAlea.partitionne(dist);

        assertTrue(partitionAlea.getPartie(0).contains(0));
        assertTrue(partitionAlea.getPartie(1).contains(0));
        //assertEquals(0,(int) partitionAlea.);
        assertEquals(0, (int) partitionAlea.getPartie(0).getFirst());
        assertEquals(0, (int) partitionAlea.getPartie(1).getFirst());

    }










}
