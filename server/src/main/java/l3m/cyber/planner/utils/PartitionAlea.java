package l3m.cyber.planner.utils;

public class PartitionAlea extends Partition{


    public PartitionAlea(int n,int k){
        super(n,k);
    }


    /*

        La méthode `partitionne` est mise en œuvre pour distribuer chaque élément entre les groupes.
        Pour chaque élément (sauf élément spécial qui doit être inclus dans tous les groupes),
        on tire un nombre aléatoire qui détermine dans quel groupe (ou partie) l'élément sera placé.

     */
    @Override
    public void partitionne(Double[][] distances){
        for (int i=0; i<nbElem; i++) {
            if(elems.get(i) != elemSpecial) {
                int aleatoire = (int) (Math.random()*k);
                parties.get(aleatoire).add(elems.get(i));
            }
        }
    }
}
