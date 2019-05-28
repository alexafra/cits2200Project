package project;
import java.util.*;
import java.util.function.ToDoubleBiFunction;

public class MyCITS2200Project implements CITS2200Project {

    List<List<Integer>> adjacencyList; //Single source of truth
    int[][] edgeMatrix;
    boolean edgeMatrixUpToDate; //safeguarding

    HashMap<Integer, String> intToStrMap;
    HashMap<String, Integer> strToIntMap;

    public MyCITS2200Project ()  {
        adjacencyList = new ArrayList<>();
        intToStrMap = new HashMap<>();
        strToIntMap = new HashMap<>();
        edgeMatrix = new int[0][0];
        edgeMatrixUpToDate = true;
    }


    /**
     * Adds an edge to the Wikipedia page graph. If the pages do not
     * already exist in the graph, they will be added to the graph.
     *
     * @param urlFrom the URL which has a link to urlTo.
     * @param urlTo the URL which urlFrom has a link to.
     */

    //Strings are keys or ints are keys. I think strings.
    //TODO duplicate edge and empty string input, not "wiki/"
    //O(1) method
    public void addEdge(String urlFrom, String urlTo) { //cant test multiple, can test singles
        if (urlFrom == null || urlTo == null || urlFrom.equals("") || urlTo.equals("") || urlFrom.equals(urlTo)) { return; }

        edgeMatrixUpToDate = false; //unless it is a duplicate edge TODO
        Integer intFrom = strToIntMap.get(urlFrom);                         //O(1)
        Integer intTo = strToIntMap.get(urlTo);                             //O(1)

        if (intFrom == null) {
            intFrom = intToStrMap.size(); //we can use size because we are never deleting edges
            intToStrMap.put(intFrom, urlFrom); //therefore we can assume the number mappings are 0 -> size() - 1
            strToIntMap.put(urlFrom, intFrom); //it will also correspond to i in the edgeMatrix and i in
            adjacencyList.add(new ArrayList<>(5));// the primary adjacencyList so we can use the key as the index
        }
        if (intTo == null) {
            intTo = strToIntMap.size();                                     //O(1)
            intToStrMap.put(intTo, urlTo);                                  //O(1)
            strToIntMap.put(urlTo, intTo);                                  //O(1)
            adjacencyList.add(new ArrayList<>(5));              //O(1)??
        }

        adjacencyList.get(intFrom).add(intTo);                              //O(1) + O(1)
    }

    /**
     * Finds the shorest path in number of links between two pages.
     * If there is no path, returns -1.
     *
     * @param urlFrom the URL where the path should start.
     * @param urlTo the URL where the path should end.
     * @return the legnth of the shorest path in number of links followed.
     */
    public int getShortestPath(String urlFrom, String urlTo) {              //how to test this? maybe use other code.
        Integer source = strToIntMap.get(urlFrom);                          //O(1)
        Integer destination = strToIntMap.get(urlTo);                       //O(1)

        //If the urls are not in the graph
        if (source == null || destination == null) {                        //O(1)
            return -1;                                                      //O(1)
        }

        int numVertices = this.adjacencyList.size();                        //O(1) Pretty sure

        int[] distances = new int[numVertices];                             //O(V)
        int[] parent = new int[numVertices];                                //O(V)

        int[] colour = new int[numVertices];                                //O(V)

        for (int i = 0; i < parent.length; i ++) {                          //O(2 * V) = O(v)
            distances[i] = -1;                                              //O(1)
            colour[i] = 0;
        }

        //this queue is the primary data structure used in this method.
        //O(1) operations when accessing the ends and no priority
        Queue<Integer> queue = new LinkedList<Integer>();                   // O(1)

        queue.add(source);                                                  //O(1) because queue is empty
        colour[source] = 1;                                                 //O(1)
        distances[source] = 0;                                              //O(1)

        while (!queue.isEmpty()) {                                          //O(V) * {}
            int current = queue.remove();                                       //O(log (1))
            if (current == destination) {                                       //O(1)
                return distances[current];                                      //O(1)
            }
            List<Integer> adjacentVertices = adjacencyList.get(current);        //O(1)
            int numEdges = adjacentVertices.size();
            for (int i = 0; i < numEdges; i++) {                               //O(|E|) * {}
                if (colour[i] == 0) {                                               //O(1)
                    colour[i] = 1;                                                  //O(1)
                    distances[i] = distances[current] + 1;                          //O(1)
                    queue.add(i);                                                   //O(log(1)),
                    //{} =  O((4*O(1)) = O(log(1))
                }                                                               //=> O(E)
            }
            colour[current] = 2;                                                //O(1)
        }                                                                   //{} = O(E)
                                                                            //O(V*E) = O(V^3)
        return -1;
    }                                                                       //O(1)
                                                                            //=>O(V*E = O(V^3)
    //Loosest bound loosest analysis, a closer look will show that each edge is is observed once in O(1) time O(E)
    //Furthermore each vertex is added to the queue once, taking O(log 1) time and removed once taking O(log(1)) time
    //Suggesting time taken is O(E + V) => O(2E))



    /**
     * Finds all the centers of the page graph. The order of pages
     * in the output does not matter. Any order is correct as long as
     * all the centers are in the array, and no pages that aren't centers
     * are in the array.
     *
     * @return an array containing all the URLs that correspond to pages that are centers.
     */
    public String[] getCenters() {
        return new String[0];
    } //Test by building out

    /**
     * Finds all the strongly connected components of the page graph.
     * Every strongly connected component can be represented as an array
     * containing the page URLs in the component. The return value is thus an array
     * of strongly connected components. The order of elements in these arrays
     * does not matter. Any output that contains all the strongly connected
     * components is considered correct.
     *
     * @return an array containing every strongly connected component.
     */
    public String[][] getStronglyConnectedComponents() {
        return new String[0][0];
    } //Test by building out


    /**
     * Finds a Hamiltonian path in the page graph. There may be many
     * possible Hamiltonian paths. Any of these paths is a correct output.
     * This method should never be called on a graph with more than 20
     * vertices. If there is no Hamiltonian path, this method will
     * return an empty array. The output array should contain the URLs of pages
     * in a Hamiltonian path. The order matters, as the elements of the
     * array represent this path in sequence. So the element [0] is the start
     * of the path, and [1] is the next page, and so on.
     *
     * @return a Hamiltonian path of the page graph.
     */
    public String[] getHamiltonianPath() {
        return new String[0];
    } //test by looping back through the parent array

    public String graphToMatrixString() {
        int numVertices = this.adjacencyList.size();
        String delimiter = " ";
        StringBuffer var1 = new StringBuffer(numVertices + "\n");

        for(int i = 0; i < numVertices; ++i) {
            List<Integer> nodeI = adjacencyList.get(i);
            Collections.sort(nodeI);
            int k = 0;
            for(int j = 0; j < numVertices; ++j) {
                if (k < nodeI.size() && j == nodeI.get(k)) {
                    var1.append(1);
                    k++;
                } else {
                    var1.append(0);
                }
                var1.append(delimiter);
            }
            var1.append("\n");
        }
        return var1.toString();
    }

    public String graphToEdgeString() {

        int numVertices = this.adjacencyList.size();
        StringBuffer edgeString = new StringBuffer(numVertices + "\n");

        for(int i = 0; i < numVertices; ++i) {
            List<Integer> edgesOfI = this.adjacencyList.get(i);
            for(int j = 0; j < edgesOfI.size(); ++j) {
                int startNode = i;
                int endNode = edgesOfI.get(j);
                edgeString.append(intToStrMap.get(startNode));
                edgeString.append("\n");
                edgeString.append(intToStrMap.get(endNode));
                edgeString.append("\n");
            }
        }
        return edgeString.toString();
    }

}
