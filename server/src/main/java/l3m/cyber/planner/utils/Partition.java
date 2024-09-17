package l3m.cyber.planner.utils;

import lombok.Getter;

import java.util.ArrayList;

public abstract class Partition {

    // nb elements à partitionner (nb sommets)
    @Getter
    protected int nbElem;

    // nb parties souhaitées dans la partition (nb livreurs)
    protected int k;

    // élément qui devra se trouver dans toutes les parties de la partition
    protected int elemSpecial;
    // liste des sommets
    protected ArrayList<Integer> elems;
    // partie de la partition
    @Getter
    protected ArrayList<ArrayList<Integer>> parties;

    public Partition(ArrayList<Integer> elems, int k, int elemSpecial) {
        this.elems = elems;
        this.nbElem = elems.size();
        this.k = k;
        this.elemSpecial = elemSpecial;
        partitionVide();
    }

    public Partition(int n, int k, int elemSpecial) {
        this(Auxiliaire.integerList(n), k, elemSpecial);

    }

    public Partition(int n, int k) {
        this(n, k, 0);

    }


    /*
        alloue la mémoire nécessaire pour parties (liste de k listes), et met l'élément spécial dans chacune des parties.
        Autrement dit, à la fin, chaque partie est un singleton contenant l'élément spécial. Sera appelé dans le constructeur.
     */
    public void partitionVide() {
        parties = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < k; i++) {
            ArrayList<Integer> partie = new ArrayList<Integer>();
            partie.add(elemSpecial);
            parties.add(partie);
        }
    }

    public ArrayList<Integer> getPartie(int i) {
        return parties.get(i);
    }


    public abstract void partitionne(Double[][] distances);

    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < k; i++) {
            res += "Partie " + i + " : " + parties.get(i) + "\n";
        }
        return res;
    }
}
