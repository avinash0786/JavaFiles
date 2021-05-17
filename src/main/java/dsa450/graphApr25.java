package dsa450;

import CP_training.DP;

import java.util.*;

/*a graph has nodes and the edges which connect those verted
--Graph can be represented in 2 ways
01: Adjacency Lis
02: Adjacency Matrix

Graph can be:: cyclic/ acyclic
            :: Directed/ un-directed
            :: weighted/ un-weighted
---min no of edges of connected graph :n-1
    maximum no of edges n(n-1)/2

 */
//GRAPH LEARN 25 APRIL 2021
public class graphApr25 {
    public static void main(String[] args) {
        /*
                           -(2)-----(4)
                       -     |        |
                    (1)       |       |
                     |  -     |       |
                     |      - |        |
                    (0)       (3) ----(5)

         */
        ArrayList<ArrayList<Integer>> g1=new ArrayList<>();
        g1.add(new ArrayList<>(Arrays.asList(1)));
        g1.add(new ArrayList<>(Arrays.asList(0,3,2)));
        g1.add(new ArrayList<>(Arrays.asList(1,3,4)));
        g1.add(new ArrayList<>(Arrays.asList(1,2,5)));
        g1.add(new ArrayList<>(Arrays.asList(2,5)));
        g1.add(new ArrayList<>(Arrays.asList(3,4)));
        BFS(g1,0);
//        DFS(g1,0,new boolean[6]);
        shortestDistanceBFS(g1,0);
        System.out.println("Det cycle: "+cycleDetectUnDirDFS(g1));
        //all paths from source to destination
        for(List<Integer> ss:allPathDest(g1,0,5))
            System.out.println(ss);

        //---------GRAPH 02 ----------------
        System.out.println("Graph02: 6 nodes complex");
        ArrayList<ArrayList<Integer>> g2=new ArrayList<>();

        g2.add(new ArrayList<>(Arrays.asList(1,2)));
        g2.add(new ArrayList<>(Arrays.asList(2,3)));
        g2.add(new ArrayList<>(Collections.singletonList(6)));
        g2.add(new ArrayList<>(Arrays.asList(6,4)));
        g2.add(new ArrayList<>(Arrays.asList(5,6)));
        g2.add(new ArrayList<>(Arrays.asList(2)));
        g2.add(new ArrayList<>(Arrays.asList(5)));
        BFS(g2,0);
        int[][] dijGraph=new int[][]{
                {0,2,4,0,0,0,0},
                {0,0,1,5,0,0,0},
                {0,0,0,0,0,0,5},
                {0,0,0,0,7,0,6},
                {0,0,0,0,0,7,3},
                {0,0,1,0,0,0,0},
                {0,0,0,0,0,2,0},
        };
        System.out.println(Arrays.toString(dijkstra(dijGraph,0)));
        System.out.println("min step to kill: "+knightMinStep(6,7,new int[]{3,3},new int[]{2,6}));
    }
    //BREADTH FIRST SEARCH: O(V+E)  sc: O(V)
    //uses Queue
    public static void BFS(ArrayList<ArrayList<Integer>> graph,int source){
        boolean[] visited=new boolean[graph.size()];
        Queue<Integer> que=new LinkedList<>();
        visited[source]=true;
        que.add(source);
        ArrayList<Integer> bfsOut=new ArrayList<>();
        //for a node we traverse all its unvisited child first and than the
        //unvisited nodes of their child by using queue
        while (!que.isEmpty()){
            int par=que.poll();
            bfsOut.add(par);
            for (int child : graph.get(par)) {
                if (!visited[child]){
                    visited[child]=true;
                    que.add(child);
                }
            }
        }
        System.out.println(bfsOut);
    }
    //DEPTH FIRST SEARCH:: recursively go to each node and print it
    //worst case :: O(V+E)  sc: (V)  uses stack
    public static void DFS(ArrayList<ArrayList<Integer>> graph,int source,boolean[] vis){
        vis[source]=true;
        System.out.print(source+" ");
        for(int u:graph.get(source))
            if (!vis[u])
                DFS(graph,u,vis);
    }

    //shortest path, using BFS
    public static void shortestDistanceBFS(ArrayList<ArrayList<Integer>> graph,int source){
        int[] dist=new int[graph.size()];
        boolean[] visited=new boolean[graph.size()];
        dist[source]=0;
        Queue<Integer> que=new LinkedList<>();
        que.add(source);
        visited[source]=true;
        while (!que.isEmpty()){
            int par=que.poll();         //parent node
            for(int i:graph.get(par)){      //geeting child of this parent node
                if (!visited[i]){
                    dist[i]=1+dist[par];        //computing dist of child using it parent
                    visited[i]=true;
                    que.add(i);     //adding this child to queue to process next
                }
            }
        }
        System.out.println(Arrays.toString(dist));
    }

    //Cycle detection in un-directed graph
    //using DFS traversal and passing parent node to detech cycle
    public static boolean cycleDetectUnDirDFS(ArrayList<ArrayList<Integer>> graph){
        boolean[] visited=new boolean[graph.size()] ;
        for (int i = 0; i < graph.size(); i++) {
            if (!visited[i]){
                if (detCyDFSUtil(graph,visited,i,-1))
                    return true;
            }
        }
        return false;
    }
    private static boolean detCyDFSUtil(ArrayList<ArrayList<Integer>> graph,boolean[] vis,int source,int parent){
        vis[source]=true;
        for(int i:graph.get(source)){
            if (!vis[i]){
                if (detCyDFSUtil(graph,vis,i,source))
                    return true;
            }//if this child which is already visited is not the parent then there is a looop
            //we can only visit a parent again, not any other node
            else if (vis[i] && i!=parent)
                return true;
        }
        return false;
    }

    //Cycle detection in Directed Graph,
    // we use a extra array to represent a current stack nodes and we do backtrack
    //We use a recursionStack array to know which element of current path we are visiting,
    protected boolean detCycDir(int source,boolean[] visited, boolean[] recStk,ArrayList<ArrayList<Integer>> graph){    // cycle detection in directed graph
        visited[source]=true;
        recStk[source]=true;
        for (int u:graph.get(source)){
            //if u is not visited and there is loop below so return true
            if (!visited[u] && detCycDir(u,visited,recStk,graph))
                return true;
            else if (visited[u] && recStk[u]) //  if it is visited and is available in the rec stack as a ancestor
                return true;
        }
        //until the adjacent of source are processed the source is in recStack after that it is removed i.e false
        recStk[source]=false;
        return false;
    }
    public boolean detCycleDirMain(ArrayList<ArrayList<Integer>> graph){
        boolean[] visited=new boolean[graph.size()];
        boolean[] recStack=new boolean[graph.size()];
        for (int i = 0; i < graph.size(); i++) {
            if (!visited[i])
                if (detCycDir(i,visited,recStack,graph))
                    return true;
                /*
                only traverse for non visited nodes and check loop by calling function and return true if it is true
                 */
        }
        return false;
    }
    //bipertite graph check: BFS
    //graph nodes are of 2 colors, and the adjacent node colors should not be same
    //odd no of nodes cycle is not BiPertitie graph
    // even no of nodes cycle may be bipertite graph
    public static boolean checkBipertite(ArrayList<ArrayList<Integer>> graph){
        int[] color=new int[graph.size()];
        // -1: un-visited  0: color1   1: color 1
        Arrays.fill(color,-1);
        Queue<Integer> que=new LinkedList<>();
        que.add(0);
        color[0]=1;
        while (!que.isEmpty()){
            int par=que.poll();
            for(int i:graph.get(par)){
                // if cur node is not visited we set it to opposite color of its parent
                if (color[i]==-1){
//                    color[i]=(color[par]==1)?0:1;
                    color[i]=1-color[par];
                    que.add(i);
                }
                //if cur node is visited and it is equal to its parent node which is adjacent return false
                else if (color[i]==color[par])
                    return false;
            }
        }
        return true;
    }

    //--------------------------QUESTIONS-----------------------

    //Q1: All paths from source to destination
    static List<List<Integer>> res;
    static boolean[] visitedDFS;
    public static List<List<Integer>> allPathDest(ArrayList<ArrayList<Integer>> graph, int source, int dest){
        res=new ArrayList<>();
        List<Integer> temp=new ArrayList<>();
        temp.add(source);
        visitedDFS=new boolean[graph.size()];
        allPathDFSUtil(graph,temp,source,dest);
        return res;
    }
    private static void allPathDFSUtil(ArrayList<ArrayList<Integer>> graph,List<Integer> temp, int source, int dest){
        if (source==dest){
            res.add(new ArrayList<>(temp));
            return;
        }
        visitedDFS[source]=true;
        for(int a:graph.get(source)){
            if (!visitedDFS[a]){
                temp.add(a);
                allPathDFSUtil(graph,temp,a,dest);
                temp.remove(temp.size()-1);
            }
        }
    }

    //word search in the matrix
    public static boolean wordSearch(char[][] board,String word){
        DP_WORDSEARCH=new Boolean[board.length+1][board[0].length+1];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j]==word.charAt(0) && wordSUtilMain(board,word,i,j,0))
                    return true;
            }
        }
        return false;
    }
    static Boolean[][] DP_WORDSEARCH;
    public static boolean wordSUtilMain(char[][] board,String word,int i,int j, int index){
        if (index==word.length())       // if we found match of last character
            return true;
        if (DP_WORDSEARCH[i][j]!=null)
            return DP_WORDSEARCH[i][j];
        //if out index gets outside the board or we dont found the matching char return false
        if (i==-1 || i==board.length || j==-1 || j==board[0].length || board[i][j]!=word.charAt(index))
            return false;
        char temp=board[i][j];
        board[i][j]='*';    //for backtrack
        //chech all the 4 sides
        boolean found=  wordSUtilMain(board,word,i+1,j,index+1) ||
                        wordSUtilMain(board,word,i,j+1,index+1) ||
                        wordSUtilMain(board,word,i-1,j,index+1) ||
                        wordSUtilMain(board,word,i,j-1,index+1);

        board[i][j]=temp;   // do backtrack
        return DP_WORDSEARCH[i][j]=found;   //return answer
    }

    //keys ans locks
    static boolean[] roomVisited;
    public static boolean keysAndRooms(ArrayList<ArrayList<Integer>> graph,int entryRoom){
        roomVisited=new boolean[graph.size()];
        Queue<Integer> que=new LinkedList<>();
        que.add(entryRoom);
        roomVisited[entryRoom]=true;
        while (!que.isEmpty()){
            int roomNow=que.poll();
            for(int i:graph.get(roomNow)){
                if (!roomVisited[i]){
                    roomVisited[i]=true;
                    que.add(i);
                }
            }
        }
        for (boolean b : roomVisited) {
            if (!b)         //if any room is not visited, we return false, that we cant visit all rooms with this key
                return false;
        }
        return true;
    }
    //we only need to check the visitedroom array contains all true or not
    private static void keyRoomDFS(ArrayList<ArrayList<Integer>> graph,int source){
        roomVisited[source]=true;
        for(int i:graph.get(source)){
            if (!roomVisited[i])
                keyRoomDFS(graph,i);
        }
    }

    static int perimeter=0;
    //finding the perimeter of a island represented by binary grid
    public static int islandPerimeter(int[][] grid){
        int n=grid.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j]==1){
                    dfsGrid(grid,i,j);
                    break;
                }
            }
        }
        return perimeter;
    }
    public static void dfsGrid(int[][] grid,int i,int j){
        //if we reach a water side i.e grid[i][j]==0 or we move to a point outside the grid, so we inc the perimeter
        if (i<0 || i>=grid.length || j<0 || j>=grid.length || grid[i][j]==0){
            perimeter++;
            return;
        }
        if (grid[i][j]==-1)
            return;
        grid[i][j]=-1;
        dfsGrid(grid,i+1,j);
        dfsGrid(grid,i-1,j);
        dfsGrid(grid,i,j+1);
        dfsGrid(grid,i,j-1);
    }
    //coloring all same color connected grid to given colour
    //we only need to color those points having the color equal to the index provided int the input
    /// and if we find a element having color equal the color of index given by the input , we paint it to given color
    public static int[][] floodFill(int[][] grid,int x,int y, int color){
        if (grid[x][y]==color)
            return grid;
        floofFillDFSUtil(grid,x,y,grid[x][y],color);
        return grid;
    }
    private static void floofFillDFSUtil(int[][] grid,int x,int y, int oldColor, int newColor){
        if (x>=grid.length || x<0 || y>=grid[0].length || y<0 || grid[x][y]!=oldColor)
            return;
        grid[x][y]=newColor;
        floofFillDFSUtil(grid,x-1,y,oldColor,newColor);
        floofFillDFSUtil(grid,x+1,y,oldColor,newColor);
        floofFillDFSUtil(grid,x,y-1,oldColor,newColor);
        floofFillDFSUtil(grid,x,y+1,oldColor,newColor);
    }

    //sorrounded region mark to x
    //given a grid of X and O we need to convert all O to X which are sorrounded by X
    public static char[][] sorroundRegion(char[][] grid){
        if (grid.length==0)
            return grid;
        for (int i = 0; i < grid[0].length; i++) {      //doing boundry traversal to find non-sorrounded elements
            if (grid[0][i]=='O')
                srundRgnDFS(grid,0,i);
            if (grid[grid.length-1][i]=='O')
                srundRgnDFS(grid,grid.length-1,i);
        }
        for (int i = 0; i < grid.length; i++) {     //  //doing boundry traversal to find non-sorrounded elements
            if (grid[i][0]=='O')
                srundRgnDFS(grid,0,i);
            if (grid[i][grid.length-1]=='O')
                srundRgnDFS(grid,grid.length-1,i);
        }
        //marking all non-boundry to X and earlier boundry marked # back to O
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j]=='O')
                    grid[i][j]='X'; //changing sorrounded region to X
                if (grid[i][j]=='#')
                    grid[i][j]='O'; //changed boundry conected region back to initial state
            }
        }
        return grid;
    }

    private static void srundRgnDFS(char[][] grid,int x,int y){
        if (x>=grid.length || x<0 || y>=grid[0].length || y<0 || grid[x][y]!='O')
            return;
        grid[x][y]='#';     //marking boundry connected O to # so that we can change later on
        srundRgnDFS(grid,x+1,y);
        srundRgnDFS(grid,x-1,y);
        srundRgnDFS(grid,x,y+1);
        srundRgnDFS(grid,x,y-1);
    }

    //word ladder
    //min no of steps required to make start string to end when we can only make a char cange to the words already gicen
    public static int wordLadder(String start, String end, List<String> words){
        if (!words.contains(end))
            return 0;
        HashMap<String ,Boolean> vMap=new HashMap<>();
        for (String word : words) {
            vMap.put(word,false);
        }
        Queue<String> que=new LinkedList<>();
        int len=1;      //doing BFS to get min path kind of
        que.add(start);
        while (!que.isEmpty()){
            int size=que.size();
            for (int i = 0; i < size; i++) {
                String w=que.poll();
                if (w.equals(end))
                    return len;
                wordMatch(vMap,que,w);
            }
            len++;
        }
        return 0;
    }
    private static void wordMatch(HashMap<String ,Boolean> vMap,Queue<String> que,String str){

        for (int i = 0; i < str.length(); i++) {
            char[] arr=str.toCharArray();
            for (int j = 0; j < 26; j++) {
                char c=(char) ('a'+j);
                arr[i]=c;
                String s=new String(arr);
                //if this is a valid word and is not visited so this is a child of the parent word
                if (vMap.containsKey(s) && !vMap.get(s)){
                    que.add(s);
                    vMap.put(s,true);
                }
            }
        }
    }

    //rotten oranges:; finding the min no of unit time reuqired for a rotten orange to rot all good oranges
    //as we need to find the min unti time we will do the BFS
    public static int rottenOranges(int[][] grid){
        if (grid.length==0)
            return 0;
        int rows=grid[0].length;
        int cols=grid.length;
        int ans=0;
        Queue<Pair> que=new LinkedList<>();
        int total=0;
        int rotten=0;
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                if (grid[i][j]==1 || grid[i][j]==2)
                    total++;
                if (grid[i][j]==2)
                    que.add(new Pair(i,j));
            }
        }
        if (total==0)
            return 0;
        //queue contains oranges going to be rotten
        while (!que.isEmpty()){
            int size=que.size();
            rotten+=size;       //incrementing all the rotten oranges, which are in queue which will be visited this iter
            if (rotten==total)
                return ans; //if all oranges are now rotten
            ans++;      //incrementing time
            for (int i = 0; i < size; i++) {
                Pair p=que.poll();      //current coordinate, we will check all 4 directions
                if (p.x+1<cols && grid[p.x][p.y]==1){
                    grid[p.x+1][p.y]=2;
                    que.add(new Pair(p.x+1,p.y));
                }

                if (p.x-1>=0 && grid[p.x-1][p.y]==1){
                    grid[p.x-1][p.y]=2;
                    que.add(new Pair(p.x-1,p.y));
                }

                if (p.y+1<rows && grid[p.x][p.y+1]==1){
                    grid[p.x][p.y+1]=2;
                    que.add(new Pair(p.x,p.y+1));
                }

                if (p.y-1>=0 && grid[p.x][p.y-1]==1){
                    grid[p.x][p.y-1]=2;
                    que.add(new Pair(p.x,p.y-1));
                }
            }
        }
        return -1;
    }

    //TOPOLOGICAL SORTING  for DAG  using DFS
    //  it is the linear ordering of vertices such that if there is a edge from u->v,
    // then u appears before v
    // we put first the element to stack which is no child,and thereafter elements with most child
    public static int[] topoSort(int n,ArrayList<ArrayList<Integer>> graph){
        Stack<Integer> stk=new Stack<>();
        int[] vis=new int[n];
        for (int i = 0; i < n; i++) {
            if (vis[i]==0)
                findTopSort(graph,stk,vis,i);
        }
        int[] topSort=new int[n];
        int index=0;
        while (!stk.isEmpty())
            topSort[index++]=stk.pop();
        return topSort;
    }
    private static void findTopSort(ArrayList<ArrayList<Integer>> graph,Stack<Integer> stk,int[] vis, int n){
        vis[n]=1;       //visit the source
        for (int i:graph.get(n)){   //call dfs for its non-visited child
            if (vis[i]==0)
                findTopSort(graph,stk,vis,i);
        }
        stk.push(n);    // we are pushing this element at the last of dfs so that all its dependent are pust before it
        //and we get the sorting
    }

    //topological sort kahn's algo BFS
    public static int[] topoSortBFSkahnsAlgo(ArrayList<ArrayList<Integer>> graph){
        int n=graph.size();
        int[] indegree=new int[n];
        int[] topoSort=new int[n];
        for (int i = 0; i < n; i++) {
            for(int j:graph.get(i))
                indegree[j]++;
        }

        Queue<Integer> que=new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i]==0)
                que.add(i);
        }   //adding initally 0 depencency nodes to query

        int index=0;
        while (!que.isEmpty()){
            int par=que.poll();
            topoSort[index++]=par;
            for(int i:graph.get(par)){
                if (--indegree[i]==0)       //if we found a element with 0 dependency we add to queue
                    que.add(i);
            }
        }
        return topoSort;
    }

    // we can use this kahn's algo to detect cycle bcoz kahn's alog works only for the acyclic directed graph
    // we will count no of elements we popped and al last if count==n
    // we return true/false depending on count==n

    //shortest distance in a Directed Acyclic Graph
    public static int[] shortestDistDAC(int[][] graph,int src){
        //do a topological sort and then find the shortest path for DAG
        int n=graph.length;
        int[] indegree=new int[n];
        int[] topoSort=new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (graph[i][j]!=0)
                    indegree[j]++;
            }
        }
        Queue<Integer> que=new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i]==0)
                que.add(i);
        }
        System.out.println(Arrays.toString(indegree));
        int idx=0;
        while (!que.isEmpty()){
            int par=que.poll();
            topoSort[idx++]=par;
            for (int i = 0; i < n; i++) {
                //for each child we decrement its indegree and if
                // indegree becomes 0  we add it to queue
                if (graph[par][i]!=0 && --indegree[i]==0)
                    que.add(i);
            }
        }
        System.out.println(Arrays.toString(topoSort));
        int[] dist=new int[n];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[src]=0;
        //we  go to each element in out topo sort
        //and go to their childrens and calculate their min dist
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (graph[i][j]!=0 && dist[i]!=Integer.MAX_VALUE){
                    dist[j]=Math.min(dist[j],dist[i]+graph[i][j]);
                }
            }
        }
        System.out.println(Arrays.toString(dist));
        return dist;
    }
    //DIJKSTRA ALGO:: shortest path --no -ve weights only positive weights
    public static int[] dijkstra(int[][] adjMat,int src){
        int v=adjMat.length;
        int[] dist=new int[v];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[src]=0;
        boolean[] fin=new boolean[v];

        for (int count=0;count<v-1;count++){    // O(v-1)*v    O(v^2)
            int u=-1;
            //finding the min among the not finalized nodes/un-visited node
            //can use a priority queue here to get min dist node easily
            for (int i = 0; i < v; i++) {   //  finding the minimum vertex node
                if (!fin[i] && (u==-1 || dist[i]<dist[u]))
                    u=i;
            }
//            System.out.println(Arrays.toString(dist));
//            System.out.println("Count: "+count+" min index: "+u+" val: "+dist[u]);
            fin[u]=true;        //visit/finalize the min node
            for (int i = 0; i < v; i++) {
                //  relax operation, minimize the distance of all adjacent of u which are not finalized/not-visited
                if (adjMat[u][i]!=0 && !fin[i])
                    //cur node  dist == dist [parent/source] + cur node weights
                    dist[i]=Math.min(dist[i],dist[u]+adjMat[u][i]);
            }
//            System.out.println();
        }
        //returning min dist of all nodes from source node
        return dist;
    }

    //cheapest flight with max k stops
    //flight contains array of array   0:source 1:destination 2:cost to reach
    public static int minCostFlight(int[][] flights,int n,int source,int dest, int stops){
        Map<Integer,ArrayList<Pair>> map=new HashMap<>();
        // pair  x:node , y: cost to reach
        for(int[] flt:flights){
            if (map.containsKey(flt[0])){
                map.get(flt[0]).add(new Pair(flt[1],flt[2]));
            }
            else {
                ArrayList<Pair> ff=new ArrayList<>();
                ff.add(new Pair(flt[1],flt[2]));
                map.put(flt[0],ff);
            }
        }
        PriorityQueue<Integer[]> pq=new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
//        PriorityQueue<Integer[]> pq=new PriorityQueue<>((o1, o2) -> o1[1]-o2[1]);

//        PriorityQueue<Integer[]> pq=new PriorityQueue<>(new Comparator<Integer[]>() {
//            @Override
//            public int compare(Integer[] o1, Integer[] o2) {
//                return o1[1]-o2[1];
//            }
//        });
        pq.offer(new Integer[]{source,0,stops});    ///adding source node in priority queue
        while (!pq.isEmpty()){
            Integer[] cur=pq.poll();
            int curNode=cur[0];
            int cost=cur[1];
            int stopLeft=cur[2];
            if (curNode==dest)
                return cost;
            if (stopLeft>0){
                if (!map.containsKey(curNode))
                    continue;//if there are no outgoing adj node we continue, we we cannot go from there
                //if there are adjacent/reachable node from cur node, we add them to queue, with value
                // nextDestination,costToReach,stopsLeft
                for(Pair nextDest:map.get(curNode)){
                    pq.add(new Integer[]{nextDest.x, nextDest.y+cost,stopLeft-1});
                }
            }
        }
        return -1;
    }

    //knights on chess board
    //given a chess board of m*n board
    //initial cooridinate of horse/knight which move 2 1/2 step
    //find min no of step required to reach the destination
    public static int knightMinStep(int m,int n,int [] source,int [] dest){
        boolean[][] visited=new boolean[m+1][n+1];
        int[] dx=new int[]{-1,1,2,2,1,-1,-2,-2};
        int[] dy=new int[]{-2,-2,-1,1,2,2,1,-1};
        Queue<Pair> que=new LinkedList<>();
        que.add(new Pair(source[0],source[1]));
        visited[source[0]][source[1]]=true;
        int fx=dest[0];     //destination x coordinate
        int fy=dest[1];     //destination y coordinate
        int steps=0;
        while (!que.isEmpty()){
            int size=que.size();
            for (int c = 0; c < size; c++) {
                Pair cur=que.poll();
                if (cur.x==fx && cur.y==fy){
                    return steps;
                }
                for (int i = 0; i < 8; i++) {
                    if (isValidChess(m,n,cur.x+dx[i],cur.y+dy[i]) && !visited[cur.x+dx[i]][cur.y+dy[i]]){
                        que.add(new Pair(cur.x+dx[i], cur.y+dy[i]));
                        visited[cur.x+dx[i]][cur.y+dy[i]]=true;
                    }
                }
            }
            steps++;
        }
        return -1;
    }
    private static boolean isValidChess(int n,int m,int a,int b){
//        System.out.println("check valid: "+a+" "+b);
        return a >= 0 && b >= 0 && a <=n && b <=m;
    }

    //minimum spanning tree:  no-cycle
    //tree with min weight
    //2 algo  1:Kruskal's algo 2:Prim's algo


    public static void minimumSpanningTreeKruskal(Edges[] edg,int v,int e){
        Arrays.sort(edg);   //sorting to take the min weight edge first
        Edges[] out=new Edges[v-1];
        int[] parent=new int[v];
        for (int i = 0; i < v; i++) {
            parent[i]=i;
        }
        int k=0;
        for (int i = 0; i < e; i++) {
            if (k==v-1)
                break;
            Edges curEdge=edg[i];
            int srcParent=findParentKruskal(curEdge.src,parent);
            int destParent=findParentKruskal(curEdge.dest,parent);

            if (srcParent!=destParent){
                out[k]=curEdge;
                k++;
                parent[srcParent]=destParent;
            }
        }
    }
    private static int findParentKruskal(int v,int[] parent){
        if (parent[v]==v)
            return v;
        return findParentKruskal(parent[v],parent);
    }

    //prims algo
    public static int primMST(int[][] graph,int V)
    {
        int[] key=new int[V];int res=0;     //storing min weight to reach the node i
        Arrays.fill(key,Integer.MAX_VALUE);
        boolean[] mSet=new boolean[V];  //marking visied/marked
        int[] parent=new int[V];        //to store the min weight edge path
        Arrays.fill(parent,-1);
        key[0]=0;

        for (int count = 0; count < V ; count++){
            int u = -1;
            for(int i=0;i<V;i++) {
                if (!mSet[i] && (u == -1 || key[i] < key[u]))
                    u = i;
            }

            mSet[u] = true;
            res+=key[u];
            //similar to dijkstr just we need to keep track of parent nodes
            // as we get the min path dist
            for (int v = 0; v < V; v++) {
                if (graph[u][v] != 0 && !mSet[v] && graph[u][v]<key[v]) {
                    parent[v]=u;
                    key[v] = graph[u][v];
                }
            }
        }
        for (int i = 0; i < V; i++) {
            System.out.println(parent[i]+" -> "+graph[i][parent[i]]);
        }
        return res;
    }

    //kosaraju strongly connected component algo
    //strongly connected means we can reach each node back and forth
    
    //1.order vertices in decreasing order of their finish time in dfs
    //2. reverse all the edges in the graph
    //3. do dfs in th order recieved from step 01
    //for each vertices print all reahable verices as one strongly connected components

    //the first step ensures that we go from the sink to source side
    //when we reverse the graph the strongly connected component remains strongly connected
    public static void kosarajuAlgo(int[][] graph){

    }

    //finding the friend circle in a binary matrix
    //finding the no of connected components


    //***************************************************************************************************************
}
/*
public static int findMinCoins(int[] coins, int x) {
	Integer[][] dp = new Integer[coins.length][4];
	return findMinCoinsHelper(coins, 0, x, 3);
}

public static int findMinCoinsHelper(int[] coins, int i, int x, int skipsLeft) {
	if(i >= coins.length) {
		return 0;
	}

	if(dp[i][skipsLeft] != null) return dp[i][skipsLeft];

	if(i == 0 || i == coins.length - 1)
		return coins[i] + findMinCoins(coins, i + 1);

    int option1 = coins[i] + findMinCoins(coins, i + 1, 3);
    int option2 = (skipsLeft > 0) ? x + findMinCoins(coins, i + 1, skipsLeft - 1) : Integer.MAX_VALUE;

    dp[i][skipsLeft] = Math.min(option1, option2);
    return dp[i][skipsLeft];
}
 */

class Edges implements Comparable<Edges>{
    int src;
    int dest;
    int weight;
    public Edges(int s,int d,int w){
        this.src=s;
        this.dest=d;
        this.weight=w;
    }
    public int compareTo(Edges o) {
        return this.weight-o.weight;
    }

    @Override
    public String toString() {
        return "["+this.src+","+this.dest+","+this.weight+"]";
    }
}