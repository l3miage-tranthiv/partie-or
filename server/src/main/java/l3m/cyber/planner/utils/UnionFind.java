package l3m.cyber.planner.utils;

import java.util.Arrays;

public class UnionFind {
    private int nb;
    private int[] parent;
    private int[] rang;

    public UnionFind(int nb) {
        this.nb = nb;
        this.parent = new int[nb];
        this.rang = new int[nb];
        for (int i = 0; i < nb; i++) {
            this.parent[i] = i;
            this.rang[i] = 0;
        }
    }

    public int find(int x) {
        if (this.parent[x] != x) {
            this.parent[x] = find(this.parent[x]);
        }
        return this.parent[x];
    }

    public void union(int x, int y) {
        int i = find(x);
        int j = find(y);
        if (i != j) {
            if (this.rang[i] < this.rang[j]) {
                this.parent[i] = j;
            } else {
                this.parent[j] = i;
                if (this.rang[i] == this.rang[j]) {
                    this.rang[i]++;
                }
            }
        }

        

    }
    
    public String toString() {
            return "UnionFind{" +
                    "nb=" + nb +
                    ", parent=" + Arrays.toString(parent) +
                    ", rang=" + Arrays.toString(rang) +
                    '}';
        }


}
