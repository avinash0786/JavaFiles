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

