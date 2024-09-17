package l3m.cyber.planner.utils;

public class PartitionKCentre extends Partition{


    public PartitionKCentre(int n, int k){
        super(n,k);
    }


    @Override
    public void partitionne(Double[][] distances){
        // tableau qui stock les sommets qui sont choisis comme casernes
        int[] casernes = new int[k];

        // le premier element doit être le dépôt
        casernes[0] = elemSpecial;

        // tableau qui stock l'indice de la caserne à laquelle chaque sommet est associée
        int[] caserneRef = new int[nbElem];

        // tableau de double
        double[] distanceMax = new double[nbElem];

        /*
         Au début caserneRef est remplie et contient que elemSpecial car notre caserne au début est le elemSpecial
         et que tous les sommets référencient vers l'élement spécial qui est le premier caserne et on va initialiser
         */

        for (int i=0; i<nbElem; i++) {
            caserneRef[i] = 0;
            distanceMax[i] = distances[elemSpecial][elems.get(i)];
        }

        /*

         - On cherche la caserne suivante elle sera donc le sommet qui se trouve le plus loin de l'actuel caserne
                    * on initialise un attribut max avec une valeur 0 ;
                    * on cherche dans la table distMax la caserne défavorisé
                    * on donne à max la valeur de l'indice de la plus longue distance
                    * on récupère l'indice de la longueur et on la cherche dans la table elems la caserne correspondante
         */

        for (int i=0 ; i<k; i++) {
            int max = 0;
            for (int j = 0; j<nbElem; j++) {
                if (distanceMax[j]>distanceMax[max]) {
                    max = j;
                }

            }
            casernes[i] = elems.get(max);
                   // * Après avoir récupérer toutes les casernes, on mets à jour la distMax entre les casernes et la caserne correspondante
            for (int j = 0; j<nbElem; j++) {
                double dist = distances[elems.get(max)][elems.get(j)];
                if( dist < distanceMax[j] ){
                    distanceMax[j] = dist;
                    caserneRef[j] = i;
                }

            }

        }

        /*
         - On remplit donc les parties après avoir trouvé toutes les casernes :
         */

//        // remplissage des parties
//        for (int i = 0; i < k; i++) {
//            parties.get(i).add(casernes[i]);
//        }
        for (int i = 1; i < nbElem; i++) {
            parties.get(caserneRef[i]).add(elems.get(i));
        }



    }
}
