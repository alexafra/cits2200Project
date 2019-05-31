package Labs.week7;

import Labs.CITS2200.Graph;
import Labs.CITS2200.Search;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class SearchImp implements Search {
    public SearchImp() { }

    /**
     * This method accepts a Graph and start node as input and returns a spanning tree for the connected component of
     * the graph that includes the start node.
     * It calculates the spanning tree using the Breadth First Search algorithm, starting from the start node.
     * It returns the spanning tree as a parent array, the value in array[i] is the parent to node i.
     * Any node without a parent ie. the start node and any node that is unreachable from the start node has its parent
     * set to -1.
     * @param var1 the Graph that we are getting the connected component tree from.
     * @param var2 the start node - the node we start our breadth first search algorithm from
     * @return a parent array representing the connected component tree - ie. a spanning tree of the subgraph that is
     * the connected component that includes the start node. The value in parent[index] is the parent of index.
     */
    @Override
    public int[] getConnectedTree(Graph var1, int var2) {
        //If given an empty graph
        if (var1.getNumberOfVertices() == 0) { return new int[0]; }

        int numVertices = var1.getNumberOfVertices();
        int[][] edgeMatrix = var1.getEdgeMatrix();
        int[] parent = new int[numVertices];
        int[] colour = new int [var1.getNumberOfVertices()];

        for (int i = 0; i < parent.length; i ++) {
            parent[i] = -1;
        }

        //this queue is the primary data structure used in this method.
        Queue<Integer> queue = new LinkedList<Integer>();

        queue.add(var2);
        colour[var2] = 1;

        while (!queue.isEmpty()) {
            int current = queue.remove();
            int[] adjacentNodes = edgeMatrix[current];
            for (int i = 0; i < numVertices; i ++) {
                if (adjacentNodes[i] == 1 && colour[i] == 0) {
                    parent[i] = current;
                    colour[i] = 1;
                    queue.add(i);
                }
            }
            colour[current] = 2;
        }
        return parent;
    }

    /**
     * This method accepts a Graph and start node as input and returns the minimum distance of each node from the
     * start node - the minimum number of edges between each node and the start node.
     * It achieves this using the Breadth First Search algorithm, starting from the start node.
     * @param var1 the Graph that we are traversing and analysing
     * @param var2 the start node - the node we start our breadth first search algorithm from and it is the node
     *             that every other node is compared to.
     * @return an array containing the distances of each node from the start node.
     * ie. distance[index] equals the distance of node index from the start node.
     */
    @Override
    public int[] getDistances(Graph var1, int var2) {
        //If given an empty graph
        if (var1.getNumberOfVertices() == 0) { return new int[0]; }

        int[][] edgeMatrix = var1.getEdgeMatrix();
        int numVertices = var1.getNumberOfVertices();
        int[] parent = new int[numVertices];
        int[] distances = new int[numVertices];
        int[] colour = new int[numVertices];

        for (int i = 0; i < parent.length; i ++) {
            distances[i] = -1;
        }

        //this queue is the primary data structure used in this method.
        Queue<Integer> queue = new LinkedList<Integer>();

        queue.add(var2);
        parent[var2] = -1;
        colour[var2] = 1;
        distances[var2] = 0;

        while (!queue.isEmpty()) {
            int current = queue.remove();
            int[] adjacentVertices = edgeMatrix[current];
            for (int i = 0; i < numVertices; i ++) {
                if (adjacentVertices[i] == 1 && colour[i] == 0) {
                    parent[i] = current;
                    colour[i] = 1;
                    distances[i] = distances[current] + 1;
                    queue.add(i);
                }
            }
            colour[current] = 2;
        }
        return distances;
    }

    //Have you got this covered for weighted graphs??????
    private int[][] DFSRecursive(Graph var1, int current, int[] colour, int[] parent, int [][] times) {
        int[][] edgeMatrix = var1.getEdgeMatrix();
        int numVertices = var1.getNumberOfVertices();

        times[current][0] = times[numVertices][0];
        times[numVertices][0] = times[numVertices][0] + 1;

        colour[current] = 1;
        int[] adjacentVertices = edgeMatrix[current];
        for (int i = 0; i < numVertices; i++) {
            if (adjacentVertices[i] == 1 && colour[i] == 0) { //You are still DFSing white nodes
                parent[i] = current;
                DFSRecursive(var1, i, colour, parent, times);
            }
        }
        colour[current] = 2;
        times[current][1] = times[numVertices][0];
        times[numVertices][0] = times[numVertices][0] + 1;

        return times;
    }

    public int[][] getTimes2(Graph var1, int var2) {
        if (var1.getNumberOfVertices() == 0) { return new int[0][]; }
        int numVertices = var1.getNumberOfVertices();
        int[] colour = new int[numVertices];
        int[] parent = new int[numVertices];
        int[][] hackyTimes = new int[numVertices + 1][2];
        int [][] times;
        hackyTimes[numVertices][0] = 0; //this cell will hold our time value. Hacky way to pass integer by reference

        times = Arrays.copyOf(DFSRecursive(var1, var2, colour, parent, hackyTimes), numVertices);


        return times;
    }

    /**
     * This method accepts a Graph and start node as input and traverses the graph from the start node using
     * the depth first search algorithm. When a node is first discovered using the algorithm, it is given a
     * discovery time, when all of its child nodes have been fully traversed, it is then given a finish time.
     * The combination of discovery and finish times of each node is returned. All non reachable nodes have their
     * start and finish time set to -1.
     * @param var1 the Graph that we are traversing and analysing
     * @param var2 the start node - the node we start our depth first search algorithm from. It will have a
     *             start time of 0, and the larges finish time.
     * @return a 2D array of size n x 2. array[i] indicates node i of the tree. array[i][0] stores the discovery
     * time of node i. array[i][1] stores the finish time of node i. All nodes that are not reachable from the start
     * node are given discovery and finish times of -1.
     */
   // @Override
    public int[][] getTimes(Graph var1, int var2) {
        //If given an empty graph.
        if (var1.getNumberOfVertices() == 0) { return new int[0][]; }
        int[][] edgeMatrix = var1.getEdgeMatrix();
        int numVertices = var1.getNumberOfVertices();
        int [][] times = new int[numVertices][2];
        int[] colour = new int[numVertices];
        int[] parent = new int[numVertices];
        int time = 0;

        //If a vertex is unreachable, it will be given a -1 start and finish time.
        for (int i = 0; i < numVertices; i ++) {
            times[i][0] = -1;
            times[i][1] = -1;
        }
        Stack<Integer> s = new Stack<Integer>();
        s.push(var2);

        while (!s.isEmpty()) {
            int current = s.pop();
            //white pop (colour is 0)  signals start time - first seen
            //grey pop (colour is 1) signals finish time
            //black pop (colour is 2) signals to ignore
            if (colour[current] == 0) {
                colour[current] = 1;
                s.push(current);
                int[] currentEdges = edgeMatrix[current];
                for (int i = 0; i <  numVertices; i++) {
                    if (currentEdges[i] == 1 && colour[i] == 0) {
                        parent[i] = current;
                        s.push(i);
                    }
                }
            } else if (colour[current] == 1) {
                colour[current] = 2;
            }
        }
        return times;
    }

    //Have you got this covered for weighted graphs??????
    private int[][] DFSRecursive2(Graph var1, int current, int[] colour, int[] parent, int [][] times) {
        int[][] edgeMatrix = var1.getEdgeMatrix();
        int numVertices = var1.getNumberOfVertices();

        colour[current] = 1;
        int[] adjacentVertices = edgeMatrix[current];
        for (int i = 0; i < numVertices; i++) {
            if (adjacentVertices[i] == 1 && colour[i] == 0) { //You are still DFSing white nodes
                DFSRecursive(var1, i, colour, parent, times);
            }
        }
        colour[current] = 2;

        return times;
    }
}
