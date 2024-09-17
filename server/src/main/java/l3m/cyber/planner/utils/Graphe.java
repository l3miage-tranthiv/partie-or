package l3m.cyber.planner.utils;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Graphe implements Cloneable{


    // nombre de sommets
    private int nbSommets;

    // matrice d'adjacence du graphe remplie de 0 et de 1 symmétrique
    private int[][] adj;

    // matrice des poids des arêtes; NB : Une arête qui n'existe pas aura poids 0.0
    // poids = null si graphe sans pondération

    @Getter
    private Double[][] poidsA;

    // nom des sommets
    // voir le github pour + d'infos
    @Getter
    private ArrayList<Integer> nomSommets;


    /*différents constructeurs de Graphe, notamment Graphe(int n) qui crée un graphe avec n sommets,
    nommés 0 à n-1 et aucune arête;
     un constructeur qui crée un graphe (non-pondéré)
     avec la matrice d'adjacence et le nom des sommets,
     ou au contraire avec une matrice de poids
     et le nom des sommets mais sans matrice d'adjacence
      (il faut alors construire la matrice d'adjacence en fonction des poids
       : 0 = non-arête, poids strictement positif = arête).
     */

    public Graphe(int[][] adj,ArrayList<Integer> nomSommets) {
        this.adj = adj;
        this.nomSommets = nomSommets;
        this.nbSommets = nomSommets.size();
    }


    // crée un graphe avec une matrice de poids et le nom des sommets mais sans matrice d'adjacence
    public Graphe(Double[][] poidsA, ArrayList<Integer> nomSommets) {
        this.poidsA = poidsA;
        this.nbSommets = nomSommets.size();
        this.nomSommets = nomSommets;
        this.adj = new int[nbSommets][nbSommets];
        for (int i = 0; i < nbSommets; i++) {
            for (int j = 0; j < nbSommets; j++) {
                if (poidsA[i][j] > 0) {
                    adj[i][j] = 1;
                }
                else {
                    adj[i][j] = 0;
                }
            }
        }

    }

    // crée un graphe non pondéré avec la matrice et le nom des sommets
    public Graphe(ArrayList<Integer> nomSommets) {
        this.nomSommets = nomSommets;
        this.nbSommets = nomSommets.size();
        this.adj = new int[nbSommets][nbSommets];
//        for (int i = 0; i < nbSommets; i++) {
//            for (int j = 0; j < nbSommets; j++) {
//                ajouterArete(i,j);
//            }
//        }
//        pondereAretest();

    }

    // crée un graphe avec n sommets, nommés 0 à n-1 et aucune arête
    public Graphe(int n) {
        this(Auxiliaire.integerList(n));
    }

    // crée un graphe non pondéré
    public Graphe(Double[][] poidsA,int n) {
        this(poidsA, Auxiliaire.integerList(n));
    }

    public void pondereAretest() {
        // on transforme un graphe non-pondéré (poidsA==null)en un graphe pondéré, avec poinds 1 par défaut sur toutes les aretes axistantes
        if (poidsA == null) {
            poidsA = new Double[nbSommets][nbSommets];
        }
        for (int i = 0; i < nbSommets; i++) {
            for (int j = 0; j < nbSommets; j++) {
                if (adj[i][j] == 1) {
                    poidsA[i][j] = 1.0;
                } else {
                    poidsA[i][j] = 0.0;
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < nbSommets; i++) {
            res.append("Sommet ").append(nomSommets.get(i)).append(" : ");
            for (int j = 0; j < nbSommets; j++) {
                res.append(adj[i][j]).append(" ");
            }
            res.append("\n");
        }
        return res.toString();
    }

    // ajouter une arete entre les sommets i et j
    public void ajouterArete(int i, int j) {
        if (i!=j) {
            adj[i][j] = 1;
            adj[j][i] = 1;
        }
        else {
            adj[i][j] = 0;
            adj[j][i] = 0;
        }

    }

    // ajouter une arete entre les sommets i et j avec un poids
    public void ajouterArete(int i, int j, double poids) {
        ajouterArete(i,j);
        poidsA[i][j] = poids;
        poidsA[j][i] = poids;
    }

    //ajuster poids à une arete entre les sommets i et j ??? à revoir !!!
    public void ajusterPoids(int i, int j) {
        poidsA[i][j] = 1.0;
        poidsA[j][i] = 1.0;
    }

    // supprimer une arete entre les sommets i et j
    public void retirerArete(int i, int j) {
        adj[i][j] = 0;
        adj[j][i] = 0;
        poidsA[i][j] = 0.0;
        poidsA[j][i] = 0.0;
    }

    public boolean voisins (int i, int j) {
        return adj[i][j] == 1;
    }

    //d'une méthode estConnexe, qui s'obtient facilement à partir d'un parcours classique de graphe (en largeur ou en profondeur).
    public boolean estConnexe() {
        // initialisation d'un tableau pour suivre les sommets visités
        boolean[] visite = new boolean[nbSommets];
        visite[0] = true; // marquer le premier sommet comme visité (le dépôt ici)
        // initialiser les autres sommets comme non visités
        for (int i = 1; i < nbSommets; i++) {
            visite[i] = false;
        }

        // on intialise une liste pour gérer les sommets à visiter
        ArrayList<Integer> file = new ArrayList<Integer>();
        file.add(0); // ajouter le premier sommet à la file

        while (!file.isEmpty()) {
            // on enleve le premier élément de la liste et le traiter
            int sommet = file.removeFirst();

            // on parcour tous les sommets pour vérifier s'ils sont adjacents et non visités
            for (int i = 0; i < nbSommets; i++) {
                if (adj[sommet][i] == 1 && !visite[i]) { // si le sommet est adjacent et non visité
                    visite[i] = true; // on marque le sommet comme visité
                    file.add(i); // on ajoute le sommet à la file pour le visiter plus tard
                }
            }
        }

        // on vérifie si tous les sommets ont été visités
        int i = file.size();
        while (i < nbSommets && visite[i]) {
            i++;
        }
        return i == nbSommets; // si tous les sommets ont été visités, alors notre graphe est connexe
    }

    // List<Triplet> listeAretes renvoie la liste des arêtes du graphe,
    // sous forme de triplets (i, j, poids) où i et j sont les sommets de l'arête et poids est le poids de l'arête.
    public List<Triplet> listeAretes() {
        List<Triplet> aretes = new LinkedList<Triplet>();
        for (int i = 0; i < nbSommets; i++) {
            for (int j = i + 1; j < nbSommets; j++) {
                if (adj[i][j] == 1) {
                    aretes.add(new Triplet(i, j, poidsA[i][j]));
                }
            }
        }
        return aretes;
    }


    @Override
    public Object clone()  {
        Graphe g = null;
        try {
            g = (Graphe) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        g.adj = new int[nbSommets][nbSommets];
        for (int i = 0; i < nbSommets; i++) {
            System.arraycopy(adj[i], 0, g.adj[i], 0, nbSommets);
        }
        if (poidsA != null) {
            g.poidsA = new Double[nbSommets][nbSommets];
            for (int i = 0; i < nbSommets; i++) {
                System.arraycopy(poidsA[i], 0, g.poidsA[i], 0, nbSommets);
            }
        }
        return g;
    }

    /*
    aretesTriees(boolean croissant) qui renvoie
    la liste des arêtes du graphe sous la forme d'une List<Triplet>
     dans laquelle les arêtes seront triées par ordre croissant de poids
     (si croissant est vrai),
     et par ordre décroissant de poids sinon.
     Vous pouvez utiliser les méthodes Collections.sort et/ou
     Collections.reverseOrder() après en avoir consulté
     la documentation sur javadoc. */
    public List<Triplet> aretesTriees(boolean croissant) {
        List<Triplet> aretes = listeAretes();
        if (croissant) {
            Collections.sort(aretes);
        } else {
            aretes.sort(Collections.reverseOrder());
        }
        return aretes;
    }



    // ArrayList<Integer> parcoursProfondeur(int debut) qui renvoie la liste des sommets visités lors d'un parcours en profondeur du graphe en partant du sommet debut.
    public ArrayList<Integer> parcoursProfondeur(int debut) {
        // On crée un tableau pour suivre les sommets déjà visités
        boolean[] visite = new boolean[nbSommets];
        for (int i = 0; i < nbSommets; i++) {
            visite[i] = false; // on initialise tous les sommets comme non visités
        }
        // liste pour stocker l'ordre des sommets visités
        ArrayList<Integer> res = new ArrayList<Integer>();
        // appel récursif de la fonction auxiliaire de parcours en profondeur
        parcoursProfondeur(debut, visite, res);
        return res;
    }

    // void parcoursProfondeur(int sommet, boolean[] visite, ArrayList<Integer> res) qui remplit res avec le parcours en profondeur du graphe en partant du sommet sommet,
//en utilisant le tableau visite pour marquer les sommets déjà visités.
    private void parcoursProfondeur(int sommet, boolean[] visite, ArrayList<Integer> res) {
        visite[sommet] = true;
        res.add(nomSommets.get(sommet));
        for (int i = 0; i < nbSommets; i++) {
            if (voisins(sommet,i) && !visite[i]) {
                parcoursProfondeur(i, visite, res);
            }
        }
    }

    // Graphe KruskalInverse() qui renvoie un arbre couvrant de poids minimum du graphe,
//obtenu par l'algorithme de Kruskal, mais en retirant les arêtes qui ne sont pas dans l'arbre couvrant de poids minimum.
    public Graphe KruskalInverse()  {
        // prendre une copie de notre graphe
        Graphe T = (Graphe) this.clone();
        // On trie les arêtes par poids décroissant
        List<Triplet> aretes = T.aretesTriees(false);
        // Pour chaque arête,
        int sommet1 = 0;
        int sommet2 = 0;
        double poids = 0.0;
        for (Triplet arete : aretes) {
            sommet1 = arete.getC1();
            sommet2 = arete.getC2();
            poids = arete.getPoids();
            T.retirerArete(sommet1, sommet2);
            if (!T.estConnexe()) {
                T.ajouterArete(sommet1, sommet2, poids);
            }
        }
        return T;
    }

public Graphe Kruskal() {
    //On commence par prendre un graphe T qui a le même nombre de sommets que le graphe courant, mais aucune arête. Pour l'instant, le graphe T n'a pas assez d'arêtes pour être connexe, le but va être de rajouter les bonnes arêtes pour le rendre connexe à coût minimum.

    Graphe T = new Graphe(nbSommets);
    //On trie les arêtes du graphe courant par poids croissant.
    List<Triplet> aretes = aretesTriees(true);
    //On crée une structure d'Union-Find pour gérer les composantes connexes
    UnionFind uf = new UnionFind(nbSommets);
    //Pour chaque arête dans cet ordre : on se demande si l'ajout de l'arête à T va créer un cycle; si oui, on ne l'ajoute pas et on passse à la suivante; sinon, on l'ajoute à T et on passe à la suivante.
    for (Triplet arete : aretes) {
        int sommet1 = arete.getC1();
        int sommet2 = arete.getC2();
        if (uf.find(sommet1) != uf.find(sommet2)) {
            T.ajouterArete(sommet1, sommet2);
            uf.union(sommet1, sommet2);
        }
    }
    return T;
}

    /*une méthode ArrayList<Integer> tsp(int debut), qui calcule et renvoie un cycle hamiltonien du graphe courant (liste de tous les sommets, dans un certain ordre, commençant par debut). */
    public ArrayList<Integer> tsp(int debut) {
        Graphe T = KruskalInverse();
        ArrayList<Integer> parcours = T.parcoursProfondeur(debut);
        ArrayList<Integer> res = new ArrayList<>();
        for (int sommet : parcours) {
            if (!res.contains(sommet)) {
                res.add(sommet);       // Ajouter chaque sommet une seule fois
            }
        }
        return res;
    }

    
    //tsp2
    public ArrayList<Integer> tsp2(int debut) {
        Graphe T = Kruskal();
        ArrayList<Integer> res = new ArrayList<>();
        ArrayList<Integer> parcours = T.parcoursProfondeur(debut);
        for (int sommet : parcours) {
            if (!res.contains(sommet)) {
                res.add(nomSommets.get(sommet)); // Ajouter chaque sommet une seule fois
            }
        }

        return res;
    }



}
