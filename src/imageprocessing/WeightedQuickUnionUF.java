

package imageprocessing;



public class WeightedQuickUnionUF {
    private int[] id;  
    private int[] sz;    
    private int count;   
    
    
    public WeightedQuickUnionUF(int N) {
        count = N;
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    
    public int find(int p) {
        while (p != id[p])
            p = id[p];
        return p;
    }
    
    public int count() {
        return count;
    }

    
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;

        if   (sz[rootP] < sz[rootQ]) { id[rootP] = rootQ; sz[rootQ] += sz[rootP]; }
        else                         { id[rootQ] = rootP; sz[rootP] += sz[rootQ]; }
        count--;
    }
    
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }
    

}
