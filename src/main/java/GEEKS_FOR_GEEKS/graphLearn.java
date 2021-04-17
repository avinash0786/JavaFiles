package GEEKS_FOR_GEEKS;

import java.util.*;

public class graphLearn {
    public static void main(String[] args) {
//        graph g1=new graph(5);
//        g1.addEdge(0,1);
//        g1.addEdge(0,2);
//        g1.addEdge(2,1);
//        g1.addEdge(3,1);
//        g1.showAdjList();
//
//        System.out.println();
//        graph g2=new graph(5);
//        g2.addEdge(0,1);
//        g2.addEdge(0,2);
//        g2.addEdge(1,2);
//        g2.addEdge(2,1);
//        g2.addEdge(1,3);
//        g2.addEdge(2,3);
//        g2.addEdge(4,3);
//        g2.addEdge(3,4);
//        g2.addEdge(3,3);
//        g2.addEdge(4,2);
//        g2.showAdjList();
//        g2.BFS(0);
//        System.out.println();
//        System.out.println("DFS trav source : 0");
//        g2.DFSrec(0,new boolean[5]);
//        System.out.println("Shortest dist: ");
//        g2.shortestDist(0);
//        graph gdir=new graph(6);
//        gdir.addEdgeDirected(0,1);
//        gdir.addEdgeDirected(0,4);
//        gdir.addEdgeDirected(1,2);
//        gdir.addEdgeDirected(2,3);
//        gdir.addEdgeDirected(4,2);
//        gdir.addEdgeDirected(4,5);
//        gdir.addEdgeDirected(5,3);
//        gdir.showAdjList();
//        System.out.println("Cycle det DFS: "+gdir.detCycleDirMain());
//        gdir.topologicalSortBFS();
        int ajdMat[][] = new int[][] {
                { 0, 5, 10, 0},
                { 5, 0, 3, 20 },
                { 10, 3, 0, 2 },
                { 0, 20, 2, 0 }
        };
        System.out.println(Arrays.toString(graph.dijkstra(ajdMat,0)));
        PriorityQueue<Integer> pq=new PriorityQueue<>(Comparator.reverseOrder());
        pq.add(90);
        pq.add(12);
        pq.add(23);
        pq.add(3);
        System.out.println(pq.peek());
    }
}
class graph {
    private final ArrayList<ArrayList<Integer>> adj;
    private boolean directed=false;
    graph(int n){
        adj= new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
    }
    public void addEdge(int u, int v){
        if (directed){  //in graph is directed, add a directed edge only
            addEdgeDirected(u,v);
            return;
        }
        if (u==v){
            System.out.println("No self loop: "+u+"-"+v+" allowed !");
            return;
        }
        if (adj.get(u).contains(v) || adj.get(v).contains(u)) {
            System.out.println("link: "+u+"-"+v+" already exits !");
            return;
        }
        //Already link present
        adj.get(u).add(v);
        adj.get(v).add(u);
    }
    public void addEdgeDirected(int from, int to){
        directed=true;
        if (from==to){
            System.out.println("No self loop: "+from+"-"+to+" allowed !");
            return;
        }
        if (adj.get(from).contains(to)) {
            System.out.println("link: "+from+"-"+to+" already exits !");
            return;
        }
        //Already link present
        adj.get(from).add(to);
    }
    public void showAdjList(){
        int i=0;
        for (ArrayList<Integer> integers : adj) {
            System.out.println("Node- "+i+" "+integers);
            i++;
        }
    }
    public int noNodes(){return adj.size();}

    public void BFS(int source){        //  O(v+e)
        boolean[] visited=new boolean[adj.size()];
        Queue<Integer> q=new LinkedList<>();
        visited[source]=true;
        q.add(source);
        System.out.println("BFS Trav source: "+source);
        while (!q.isEmpty()){
            int u=q.poll();
            System.out.print(u+" ");
            for (int v : adj.get(u)) {
                if (!visited[v]){
                    visited[v]=true;
                    q.add(v);
                }
            }
        }
    }
    protected void BFS(int source,boolean[] visited){        //  O(v+e)
        Queue<Integer> q=new LinkedList<>();
        visited[source]=true;
        q.add(source);
        System.out.println("BFS Trav source: "+source);
        while (!q.isEmpty()){
            int u=q.poll();
            System.out.print(u+" ");
            for (int v : adj.get(u)) {
                if (!visited[v]){
                    visited[v]=true;
                    q.add(v);
                }
            }
        }
    }
    //Counting and traversing BFS of different disconnected graphs
    int BFSDisCon(){
        boolean[] visited=new boolean[adj.size()];
        int count=0;
        for (int i = 0; i < adj.size(); i++) {
            if (!visited[i]){
                BFS(i,visited);
                count++;
            }
        }
        return count;
    }

    protected void DFSrec(int source,boolean[] visited){   //  O(v+e)
        visited[source]=true;
        System.out.print(source+" ");
        for(int u:adj.get(source))
            if (!visited[u])
                DFSrec(u,visited);
    }
    public void DFS(int source){
        int count=0;
        boolean[] visited=new boolean[adj.size()];
        DFSrec(source, visited);
    }

    //  For diconnected graph we need to call for their node
    //  as it is not connected and will not be called by link
    //   also returns the number of disconnected graph
    public int DFSdisCon(int v){
        int count=0;
        boolean[] visited=new boolean[v];
        for (int i = 0; i < v; i++) {
            if (!visited[i]) {
                DFSrec(i, visited);
                count++;
            }
        }
        return count;
    }
    public void shortestDist(int source){
        int[] dist=new int[adj.size()];
        boolean[] visited=new boolean[adj.size()];
        dist[source]=0;
        Queue<Integer> q=new LinkedList<>();
        q.add(source);
        visited[source]=true;
        while (!q.isEmpty()){
            int u=q.poll();
            for(int i:adj.get(u)){
                if (!visited[i]){
                    dist[i]=dist[u]+1;
                    visited[i]=true;
                    q.add(i);
                }
            }
        }
        System.out.println(Arrays.toString(dist));
    }
    protected boolean detectDFS(int source,boolean[] vis, int parent){
        vis[source]=true;
        for (int u:adj.get(source)){
            if (!vis[u]){
                if (detectDFS(u,vis,source))    // simple DFS and passing parent to lower runs
                    return true;
            }       //we can only visit a parent again not aby other node
            else if (u!=parent) //  if the node is visited and its not the parent then loop is there
                return true;
        }
        return false;
    }
    public boolean cycleDet_UD_DFS(){   //cycle detection in un-directed graph
        boolean[] visited=new boolean[adj.size()];
        for (int i = 0; i < adj.size(); i++) {
            if (!visited[i]){
                if (detectDFS(i,visited,-1))        //if true return true
                    return true;
            }
        }
        return false;
    }
    protected boolean detCycDir(int source,boolean[] visited, boolean[] recStk){    // cycle detection in directed graph
        visited[source]=true;
        recStk[source]=true;
        for (int u:adj.get(source)){
            //if u is not visited and there is loop below so return true
            if (!visited[u] && detCycDir(u,visited,recStk))
                return true;
            else if (recStk[u]) //  if it is visited and is available in the rec stack as a ancestor
                return true;
        }
        //until the adjacent of source are processed the source is in recStack after that it is removed i.e false
        recStk[source]=false;
        return false;
    }
    public boolean detCycleDirMain(){
        boolean[] visited=new boolean[adj.size()];
        boolean[] recStack=new boolean[adj.size()];
        for (int i = 0; i < adj.size(); i++) {
            if (!visited[i])
                if (detCycDir(i,visited,recStack))
                    return true;
                /*
                only traverse for non visited nodes and check loop by calling function and return true if it is true
                 */
        }
        return false;
    }
    public int[] topologicalSortBFS(){   //kahn's algo for topological sorting
        int[] inDeg=new int[adj.size()];
        //  Calculating indegree of all nodes
        for (int i = 0; i < adj.size(); i++) {
            for(int u:adj.get(i))
                inDeg[u]++;
        }
        Queue<Integer> que=new LinkedList<>();
        //   adding 0 indegree nodes to the queue
        for (int i = 0; i < inDeg.length; i++) {
            if (inDeg[i]==0)
                que.add(i);
        }
        int[] topoSort=new int[adj.size()];
        /*
        when in-degree of a vertex is 0 is means there are no dependencies for
        this vertex now;

        when we process a vertex we reduce the in-degree of their adjacent by 1 so by doing
        this we are reducing its dependency from their own(as this node is now processed)
        we the node which is processed reduces the dependency form their adjacents

        and when a node has depencency i.e in-degree as 0 we enqueue it to the queue
         */
        int i=0;
        while (!que.isEmpty()){
            int u=que.poll();
//            System.out.print(u+" ");
            topoSort[i++]=u;
            for(int x:adj.get(u)){
                if (--inDeg[x]==0)
                    que.add(x);
            }
        }
        System.out.println(Arrays.toString(topoSort));
        return topoSort;
    }
    /*
    we can use the kahn's algo to detect cycle also because it works only for acyclic graph and we use the knowledge to
    detect cycle
    we init a count variable and inc it when we pop a 0 dependency node
    and at last check the count!=v
    and return true/false
     */

    //shortest path in a directed acyclic graph
    public int[] shortestDistTopo(int source){
        int[] dist=new int[adj.size()];
        Arrays.fill(dist, -1);
        dist[source]=0;
        int[] toSort=topologicalSortBFS();
        for (int u : toSort) {
            for(int v:adj.get(u)){
                if (dist[v]>dist[u]){
                    dist[v]=dist[u];
                }
            }
        }
        return dist;
    }
    public static int[] dijkstra(int[][] adjMat,int src){
        int v=adjMat.length;
        int[] dist=new int[v];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[src]=0;
        boolean[] fin=new boolean[v];

        for (int count=0;count<v-1;count++){
            int u=-1;
            for (int i = 0; i < v; i++) {   //  finding the minimum vertex node
                if (!fin[i] && (u==-1 || dist[i]<dist[u]))
                    u=i;
            }
            fin[u]=true;
            for (int i = 0; i < v; i++) {       //  relax operation, minimize the distance of all adjacent of u
                if (adjMat[u][i]!=0 && !fin[i])
                    dist[i]=Math.min(dist[i],dist[u]+adjMat[u][i]);
            }
        }
        return dist;
    }

    protected void inOutRunner(int v){
        vis[v]=true;
        inTime[v]=timer++;
        for(int child:adj.get(v)){
            if (!vis[child])
                inOutRunner(child);
        }
        outTime[v]=timer++;
    }

    int timer=1;
    boolean[] vis;
    int[] inTime;
    int[] outTime;

    public void inOutMain(int v){
        vis=new boolean[adj.size()];
        inTime=new int[adj.size()];
        outTime=new int[adj.size()];
        inOutRunner(v);
        System.out.println(Arrays.toString(inTime));
        System.out.println(Arrays.toString(outTime));
    }
    /*
    ***  if a node lies in the subtree of another node or vice-versa let(x,y)
    * then the intime of the x node will be less than the intime of y node
    * and the out time of the node x will be greater than the node y
     */

 /*      MINIMUM SPANNING TREE
            PRIM'S ALGORITHM
       -- Prims algo segerigates the nodes in 2 i.e
       in MST and not in MST
       In MST consist node that form part of the MST

       we take the minimum dist node from no in MST set and link it to the MST
       no in MST is a priority queue to store the min dist node at top
     */

    //PRIMS ALGO-- MINIMUM SPANNING TREE
    // UNDIRECTED CONNECTED WEIGHTAGE GRAPH
    static final int V=4;
    public static int primMST(int[][] graph)
    {
        int[] key=new int[V];int res=0;
        Arrays.fill(key,Integer.MAX_VALUE);
        boolean[] mSet=new boolean[V];
        key[0]=0;

        for (int count = 0; count < V ; count++){
            int u = -1;
            for(int i=0;i<V;i++) {
                if (!mSet[i] && (u == -1 || key[i] < key[u]))
                    u = i;
            }

            mSet[u] = true;
            res+=key[u];

            for (int v = 0; v < V; v++) {
                if (graph[u][v] != 0 && !mSet[v])
                    key[v] = Math.min(key[v], graph[u][v]);
            }
        }
        return res;
    }
    /*
    KSORAJU ALGO STEPS
    1. find the order of vertices in decreasing order of their finish time
    2. reverse the edges
    --reversing the edge doesn't change the strongly connected components
    --reversing to ensure that we have both way connectivity

     */
    public static void kosarajuAlgo(int[][] ajdMat){
        int v=ajdMat.length;

    }
}
