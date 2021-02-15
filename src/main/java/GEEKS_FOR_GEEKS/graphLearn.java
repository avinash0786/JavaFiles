package GEEKS_FOR_GEEKS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class graphLearn {
    public static void main(String[] args) {
        graph g1=new graph(5);
        g1.addEdge(0,1);
        g1.addEdge(0,2);
        g1.addEdge(2,1);
        g1.addEdge(3,1);
        g1.showAdjList();

        System.out.println();
        graph g2=new graph(5);
        g2.addEdge(0,1);
        g2.addEdge(0,2);
        g2.addEdge(1,2);
        g2.addEdge(2,1);
        g2.addEdge(1,3);
        g2.addEdge(2,3);
        g2.addEdge(4,3);
        g2.addEdge(3,4);
        g2.addEdge(3,3);
        g2.addEdge(4,2);
        g2.showAdjList();
        g2.BFS(4);
        System.out.println();
        System.out.println("DFS trav source : 0");
        g2.DFSrec(0,new boolean[5]);
        System.out.println("Shortest dist: ");
        g2.shortestDist(0);

    }
}
class graph{
    private final ArrayList<ArrayList<Integer>> adj;
    graph(int n){
        adj= new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
    }
    public void addEdge(int u, int v){
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
            }
            else if (u!=parent) //  if the node is visited and its not the parent then loop is there
                return true;
        }
        return false;
    }
    public boolean cycleDetDFS(){
        boolean[] visited=new boolean[adj.size()];
        for (int i = 0; i < adj.size(); i++) {
            if (!visited[i]){
                if (detectDFS(i,visited,-1))        //if true return true
                    return true;
            }
        }
        return false;
    }
    protected boolean detCycDir(int source,boolean[] visited, boolean[] recStk){
        visited[source]=true;
        recStk[source]=true;
        for (int u:adj.get(source)){
            /*
            if u is not visited and there is loop below so return true
             */
            if (!visited[u] && detCycDir(u,visited,recStk))
                return true;
            else if (recStk[u]) //  if it is visited and is available in the rec stack as a ancestor
                return true;
        }
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
    public void topologicalSortBFS(){
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
        /*
        when in-degree of a vertex is 0 is means there are no dependencies for
        this vertex now;

        when we process a vertex we reduce the in-degree of their adjacent by 1 so by doing
        this we are reducing its dependency from their own(as this node is now processed)
        we the node which is processed reduces the dependency form their adjacents

        and when a node has depencency i.e in-degree as 0 we enqueue it to the queue
         */
        while (!que.isEmpty()){
            int u=que.poll();
            System.out.print(u+" ");

            for(int x:adj.get(u)){
                if (--inDeg[x]==0)
                    que.add(x);
            }
        }
    }

}
