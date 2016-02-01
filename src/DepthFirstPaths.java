
import java.util.Stack;

import princeton.lib.In;
import princeton.lib.StdOut;

import ua.com.oka.lib.Graph;

public class DepthFirstPaths {

	private boolean[] marked;    
    private int[] edgeTo;        
    private final int s;         
    
    public DepthFirstPaths(Graph G, int s) {
        this.s = s;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        dfs(G, s);
    }
    
    /**
     * пошук в глибину
     * @param G - граф
     * @param v - dfs з вершини v
     */
    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }
    
    /**
     * Чи присутній шлях з v в s, що задана конструктором
     * @param v - вершина до чкої шукаємо шлях
     * @return true якщо є шлях, false якщо немає
     */
    public boolean hasPathTo(int v) {
        return marked[v];
    }
    
    /**
     * повертає шлях між s та v; null якщо шляху немає
     */
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x])
            path.push(x);
        path.push(s);
        return path;
    }
    
    private static final String testFile = "tinyCG.txt";
    
    public static void main(String[] args) {
        In in = new In(testFile);
        Graph G = new Graph(in);
        StdOut.println(G);
        int s = Integer.parseInt("0");
        DepthFirstPaths dfs = new DepthFirstPaths(G, s);

        for (int v = 0; v < G.V(); v++) {
            if (dfs.hasPathTo(v)) {
                StdOut.printf("%d to %d:  ", s, v);
                for (int x : dfs.pathTo(v)) {
                    if (x == s) StdOut.print(x);
                    else        StdOut.print("-" + x);
                }
                StdOut.println();
            }

            else {
                StdOut.printf("%d to %d:  not connected\n", s, v);
            }

        }
    }
}
