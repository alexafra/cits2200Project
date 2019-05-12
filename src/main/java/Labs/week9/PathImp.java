package Labs.week9;

import Labs.CITS2200.Graph;
import Labs.CITS2200.Path;

import java.util.PriorityQueue;

/**
 * By Alexander Frazis 21988858
 * PathImp is a class that can calculate the minimum spanning tree weight of a graph
 * and can calculate the distance of every vertex in a graph from a specified source vertex.
 */
public class PathImp implements Path {

    /**
     * getMinSpanningTree returns the weight of the minimum spanning trees of this graph.
     * @param graph the graph that we want the weight of the minimum spanning tree for.
     * @return if there is a minimum spanning tree,
     * returns the weight of the minimum spanning tree, else returns -1
     */
    public int getMinSpanningTree (Graph graph) {
        int numVertices = graph.getNumberOfVertices();

        int[][] edgeMatrix = graph.getEdgeMatrix();
        int[] colour = new int[numVertices];
        for (int i = 0; i < colour.length; i ++) {
            colour[i] = 0;
        }
        int length = 0;


        int start = 0;
        PriorityQueue<PQElement> pq = new PriorityQueue<PQElement>();
        pq.add(new PQElement (start, 0));
        while (pq.size() != 0) {
            PQElement current = pq.remove();
            if (colour[current.element] == 1) { //If you return a black, already visited node, skip.
                continue;
            }
            length += current.priority;
            colour[current.element] = 1;

            int[] adjacentNodes = edgeMatrix[current.element];
            for (int i = 0; i < numVertices; i ++) {
                if (adjacentNodes[i] != 0 && colour[i] != 1) { //It is an adjacent node and isnt black
                    pq.add(new PQElement(i, edgeMatrix[current.element][i]));
                }
            }

        }
        boolean allElementsBlack = true;
        for (int i = 0; i < colour.length; i ++) {
            if (colour[i] == 0) {
                allElementsBlack = false;
            }
        }
        if (allElementsBlack) {
            return length;
        } else {
            return -1;
        }
    }

    /**
     * getShortestPaths uses Dijkstra's algorithm to determine the distance from a source node, to each
     * other node on the graph. If a node is unreachable, the distance is set to -1.
     * @param graph that we want to get the shortest paths from.
     * @param source the node we want to calculate the distance of all nodes from.
     * @return an int array containing the distances of each node from the source node. The distance
     * is set to -1 where the node is unreachable from the source node.
     */
    public int[] getShortestPaths (Graph graph, int source) {
        int numVertices = graph.getNumberOfVertices();

        int[][] edgeMatrix = graph.getEdgeMatrix();
        int[] colour = new int[numVertices];
        int[] distance = new int[numVertices];
        for (int i = 0; i < numVertices; i ++) {
            colour[i] = 0;
            distance[i] = -1;
        }

        PriorityQueue<PQElement> pq = new PriorityQueue<PQElement>();
        pq.add(new PQElement (source, 0));
        while (pq.size() != 0) {
            PQElement current = pq.remove();
            if (colour[current.element] == 1) { //If you return a black, already visited node, skip.
                continue;
            }
            distance[current.element] = current.priority;
            colour[current.element] = 1;

            int[] adjacentNodes = edgeMatrix[current.element];
            for (int i = 0; i < numVertices; i ++) {
                if (adjacentNodes[i] != 0 && colour[i] != 1) { //It is an adjacent node and isnt black
                    pq.add(new PQElement(i, distance[current.element] + edgeMatrix[current.element][i]));
                }
            }

        }
        return distance;
    }


    /**
     * Returns the subtree
     * @param graph
     * @param source
     * @return
     */
    public int[] getSubTree (Graph graph, int source) {
        int numVertices = graph.getNumberOfVertices();

        int[][] edgeMatrix = graph.getEdgeMatrix();
        int[] colour = new int[numVertices];
        int[] key = new int[numVertices]; //this is really the weight to the tree
        int[] parent = new int[numVertices];
        for (int i = 0; i < numVertices; i ++) {
            colour[i] = 0;
            key[i] = Integer.MAX_VALUE;
        }

        PriorityQueue<PQElement> pq = new PriorityQueue<PQElement>();
        parent[source] = -1;
        key[source] = 0;
        pq.add(new PQElement (source, 0));

        while (pq.size() != 0) {
            PQElement current = pq.remove();
            if (colour[current.element] == 2) { //If you return a black, already visited node, skip.
                continue;
            }
            colour[current.element] = 2;

            int[] adjacentNodes = edgeMatrix[current.element];
            for (int i = 0; i < numVertices; i ++) {
                int priority = adjacentNodes[i];
                if (adjacentNodes[i] != 0 && colour[i] != 2) { //It is an adjacent node and isnt black

                    if (priority < key[i]) {
                        parent[i] = current.element;
                        key[i] = edgeMatrix[current.element][i];
                        pq.add(new PQElement(i, edgeMatrix[current.element][i]));
                    }
                }
            }
        }
        return parent;
    }

    /**
     * An inner class that we use to place vertexes in a priority queue with a weight
     */
    public class PQElement implements Comparable<PQElement> {
        //instance variables are public only to avoid potential error with marking machine
        public Integer element;
        public int priority;
        /**
         * A constructor for a priority queue node/link.
         * @param element the value stored in the node.
         * @param priority the priority stored in the node.
         */
        public PQElement(Integer element, int priority) {
            this.element = element;
            this.priority = priority;
        }

        /**
         * compareTo defines the mathematical order between objects of type PQElement
         * @param obj1 the object that our current or 'this' object is being compared with.
         * @return a negative integer if obj1 is greater than the current object, 0 if obj1 is
         * equal to the current object, a positive integer if obj1 is smaller than the current object.
         */
        public int compareTo (PQElement obj1) {
            return this.priority - obj1.priority;
        }
    }
}
