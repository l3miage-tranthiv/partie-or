package l3m.cyber.planner.utils;

import java.util.ArrayList;

public class Auxiliaire {

    public final static ArrayList<Integer> integerList(int n) {
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i<n; i++){
            list.add(i);
        }

        return list;
    }

    public final static <T> boolean estCarree(T[][] matrice) {
        int n = matrice.length;
        int i = 0;
        while ( matrice[i].length == n && i<n )  {
            i++;
        }
        return (i==n);
    }

    public final static <T> boolean estCarreeSym(T[][] matrice) {
        if (!estCarree(matrice)) {
            return false;
        }
        int n = matrice.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (!matrice[i][j].equals(matrice[j][i])) {
                    return false;
                }
            }
        }
        return true;
    }

}
