package project;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Collections;
import java.lang.Boolean;

public class MyCITS2200Project implements CITS2200Project {

    List<List<Integer>> adjacencyList; //Single source of truth
    List<List<Integer>> tAdjacencyList; //The transpose adjacency list needed for getStronglyConnected()
    int[][] edgeMatrix;

    HashMap<Integer, String> intToStrMap;
    HashMap<String, Integer> strToIntMap;

    public MyCITS2200Project ()  {
        adjacencyList = new ArrayList<>();
        tAdjacencyList = new ArrayList<>();
        intToStrMap = new HashMap<>();
        strToIntMap = new HashMap<>();
        edgeMatrix = new int[0][0];
    }


    /**
     * Adds an edge to the Wikipedia page graph. If the pages do not
     * already exist in the graph, they will be added to the graph.
     *
     * @param urlFrom the URL which has a link to urlTo.
     * @param urlTo the URL which urlFrom has a link to.
     */

    //Strings are keys or ints are keys. I think strings.
    //O(1) method
    public void addEdge(String urlFrom, String urlTo) { //cant test multiple, can test singles
        if (urlFrom == null || urlTo == null || urlFrom.equals("") || urlTo.equals("")) { return; }

        Integer intFrom = strToIntMap.get(urlFrom);                         //O(1)
        if (intFrom == null) {
            intFrom = intToStrMap.size(); //we can use size because we are never deleting edges
            intToStrMap.put(intFrom, urlFrom); //therefore we can assume the number mappings are 0 -> size() - 1
            strToIntMap.put(urlFrom, intFrom); //it will also correspond to i in the edgeMatrix and i in
            adjacencyList.add(new ArrayList<>(5));// the primary adjacencyList so we can use the key as the index
            tAdjacencyList.add(new ArrayList<>(5));
        }
        Integer intTo = strToIntMap.get(urlTo);                             //O(1) Integer intTo = strToIntMap.get(urlTo);                             //O(1)
        if (intTo == null) {
            intTo = strToIntMap.size();                                     //O(1)
            intToStrMap.put(intTo, urlTo);                                  //O(1)
            strToIntMap.put(urlTo, intTo);                                  //O(1)
            adjacencyList.add(new ArrayList<>(5)); //O(1)??
            tAdjacencyList.add(new ArrayList<>(5));
        }

        adjacencyList.get(intFrom).add(intTo);                              //O(1) + O(1)
        tAdjacencyList.get(intTo).add(intFrom);     //add the opposite of the original adjacencyList
        //ALEX IF YOU THINK THIS IS INEFFICIENT, TO ADD EVERYTHING TO THE transposeAdjacencyList, THEN SEE IF YOU CAN
        //THINK OF A WAY TO COMPUTE A TRANSPOSE FROM THE ORIGINAL ADJACENCY LIST
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
                int adjacent = adjacentVertices.get(i);                                     //O(1)
                if (colour[adjacent] == 0) {                                               //O(1)
                    colour[adjacent] = 1;                                                  //O(1)
                    distances[adjacent] = distances[current] + 1;                          //O(1)
                    queue.add(adjacent);                                                   //O(log(1)),
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
     * Perform a BFS on the graph
     *
     */
    public int BFS(int vertex) {

        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[adjacencyList.size()];
        //Contains distance from
        int[] distance = new int[adjacencyList.size()];
        //Set all vertices as unreachable until reached
        for(int i = 0; i < adjacencyList.size(); i++) {
            distance[i] = -1;
        }
        distance[vertex] = 0;
        visited[vertex] = true;

        q.add(vertex);

        while(!q.isEmpty()) {
            int nextVertex = q.remove();
            for(int i = 0; i < adjacencyList.get(nextVertex).size(); i++) {
                int neighbourVertex = adjacencyList.get(nextVertex).get(i);
                if(!visited[neighbourVertex]) {
                    visited[neighbourVertex] = true;
                    //This is the distance from the original vertex
                    distance[neighbourVertex] = distance[nextVertex] + 1;
                    q.add(neighbourVertex);
                }
            }
        }
        int maxLength = -1;
        //Run through our graph, and make maxLength into the largest path length
        for(int i = 0; i < adjacencyList.size(); i++) {
            if(distance[i] > maxLength) maxLength = distance[i];
            //If there is no path, tell getCenters() that it is not eligible to be a center
            if(distance[i] == -1) return -1;
        }

        return maxLength;
    }

    /**
     * Finds all the centers of the page graph. The order of pages
     * in the output does not matter. Any order is correct as long as
     * all the centers are in the array, and no pages that aren't centers
     * are in the array.
     *
     * @return an array containing all the URLs that correspond to pages that are centers.
     * When no centers can be found, an empty String[] is @returned
     */
    public String[] getCenters() {

        //An array which will hold the longest "shortest path" between the vertices a vertex can reach
        int[] centerLengths = new int[adjacencyList.size()];
        int minCenterLength = Integer.MAX_VALUE;
        //Run a BFS on every vertex to find the longest "shortest path"
        for(int i = 0; i < adjacencyList.size(); i++) {
            centerLengths[i] = BFS(i);
            //If our BFS tells us a vertex cannot reach another vertex, it cannot be a center
            if(centerLengths[i] < minCenterLength && centerLengths[i] != -1) {
                minCenterLength = centerLengths[i];
            }
        }
        //Add our centers to an ArrayList
        ArrayList<Integer> centerList = new ArrayList<>();
        for(int i = 0; i < adjacencyList.size(); i++) {
            //If there longest "shortest path" is the minimum possible in this graph, add to arrayList
            if(centerLengths[i] == minCenterLength) {
                centerList.add(i);
            }
        }
        //Find the String equivalent and add to a String[]
        String[] centers = new String[centerList.size()];
        for(int i = 0; i < centerList.size(); i++) {
            String centerString = intToStrMap.get(centerList.get(i));
            centers[i] = centerString;
        }
        return centers;
    }

    /**
     * Runs a DFS on the transposed adjacencyList
     * It should update the String[][] with the vertices that are strongly connected
     */
    public void transposeDFS(boolean[] visited, int vertex,  List<List<Integer>> stringAList, int count) {

        //Set the vertex to visited
        visited[vertex] = true;

        for(int i = 0; i < tAdjacencyList.get(vertex).size(); i++) {
            //Check if any neighbours of our vertex can be reached and havent been visited
            //They are part of SCC
            if(!visited[tAdjacencyList.get(vertex).get(i)]) {
                transposeDFS(visited, tAdjacencyList.get(vertex).get(i), stringAList, count);
            }
        }

        //Add the vertex to the ArrayList of SCC
        stringAList.get(count).add(vertex);

    }

    /**
     * Runs a recursive DFS on the non-transposed graph
     * Will add elements to the stack in the correct order and update
     * the visited boolean array
     */
    public void fillOrder(boolean[] visited, int vertex, Stack stack) {

        visited[vertex] = true;
        int size = adjacencyList.get(vertex).size();

        for(int i = 0; i < size; i++) {
            if(visited[adjacencyList.get(vertex).get(i)] == false) {
                fillOrder(visited, adjacencyList.get(vertex).get(i), stack);
            }
        }
        stack.push(vertex);
    }

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

        List<List<Integer>> stringAList = new ArrayList<>();
        int count = 0;
        int vertex;
        Stack stack = new Stack();
        //Find the number of vertices in our graph
        int size = adjacencyList.size();
        //Visited will track whether or not we have visited the vertex in the past
        boolean[] visited = new boolean[size];

        //Go through the graph and find the order of vertices that our DFS produces
        //Will push vertices on a stack
        for(int i = 0; i < size; i++) {
            if(visited[i] == false) {
                fillOrder(visited, i, stack);
            }
        }

        //Make everything unvisited again
        for(int i = 0; i < size; i++) {
            visited[i] = false;
        }

        //Go through our transpose graph, and see what vertices are unvisited and connected
        //These will be part of our SCC
        while(stack.empty() == false) {
            vertex = (int)stack.pop();
            if(visited[vertex] == false) {
                //Create a new ArrayList to hold the current set of SCCs
                stringAList.add(new ArrayList(5));
                //This should only exit once it has found all connected+unvisited neighbours, ie SCCs
                transposeDFS(visited, vertex, stringAList, count);
                //Increase our count, so we add the next elements to the next ArrayList
                count++;
            }
        }

        //Find the size of our ArrayList, which is the number of sets of SCC
        int array1Dsize = stringAList.size();
        String[][] sccStrings = new String[array1Dsize][];

        //Copy the ArrayList into a 2D String Array
        for(int i = 0; i < array1Dsize; i++) {
            sccStrings[i] = new String[stringAList.get(i).size()];
            for(int j = 0; j < stringAList.get(i).size(); j++) {
                String tempString = intToStrMap.get(stringAList.get(i).get(j));
                sccStrings[i][j] = tempString;
            }
        }

        return sccStrings;

    }


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
        int numVertices = adjacencyList.size();
        int numSets = 1<<numVertices;

        boolean[][] adjacencyMatrix = generateAdjacencyMatrix();

        /*
         * There are 2^n * n states in my memoization table. Each state is either true or false.
         * A unique state is defined by a set of vertices S and an extra vertex v.
         * A state is set to true if:
         * 1) There is a path found that visits each node in the subset S once
         * 2) The final node of the path is the extra vertex v.
         * 3) Only the nodes in the subset can be used
         * 4) To satisfy (3) v must be a member of S.
         *
         * In short each state represents if the program has calculated if there is a hamiltonian path
         * in set S that ends at vertex v where v is a member of S.
         */
        boolean dp[][] = new boolean[numSets][numVertices];

        /*
         * Initialise trivial cases to true.
         * A trivial case is when the set of vertices S contains only vertex v it finishes at.
         * Thus the set S necessarily has a hamiltonian path ending at v.
         * Note that the null set (when set = 0) is always false, because v is never a member of the null set.
         */
        for (int vertex = 0; vertex < numVertices; ++vertex) {
            dp[1<<vertex][vertex] = true;
        }

        /*
         * Our strategy for traversing the memoization table is to traverse each set completely, one at a time from
         * 0 ... (2^N - 1). This is because any sets with dependencies are dependent on sets smaller than them.
         * Thus larger subproblems can be built from smaller subproblems by traversing from set 0 to set (2^N - 1).
         * Larger subproblems D(S, c) are built from smaller subproblems as follows:
         * D(S, c) = ( (D(S-{c}, 0) & A[0][c]) || (D(S-{c}, 1) & A[1][c]) || ... ||  (D(S-{c}, N - 1) & A[N - 1][c]) )
         */
        for (int set = 0; set < numSets; ++set) {
            for (int vertex = 0; vertex < numVertices; ++vertex) {
                // states with sets S without vertex v are left as false
                // states with sets S that only contain the vertex v are left as true
                if (((1 << vertex) & set) == 0 || (set & ~(1<<vertex)) == 0) { continue; }
                int previousSet = set & ~(1 << vertex); //(S-{c})
                for (int x = 0; x < numVertices; ++x) {
                    //The critical recursive step.
                    dp[set][vertex] = dp[previousSet][x] & adjacencyMatrix[x][vertex];
                    if (dp[set][vertex]) break;
                }
            }
        }
        boolean hamiltonianExists = false;
        for (int vertex = 0; vertex < numVertices; ++vertex) {
            if (dp[numSets - 1][vertex]) {
                hamiltonianExists = true;
                break;
            }
        }

        //Back tracing hamiltonian path
        String[] hamiltonianPath;
        if (hamiltonianExists) {
            hamiltonianPath = new String[numVertices];
            int set = numSets - 1;
            for (int i = 0; i < numVertices; ++i) {
                for (int j = 0; j < numVertices; ++j) {
                    if (dp[set][j]) {
                        hamiltonianPath[numVertices - 1 - i] = intToStrMap.get(j);
                        set = set & ~(1<<j);
                        break;
                    }
                }
            }
        } else {
            hamiltonianPath = new String[0];
        }
        return hamiltonianPath;
    }


    //test by looping back through the parent array
    public boolean[][] generateAdjacencyMatrix() {
        int numVertices = adjacencyList.size();
        boolean[][] adjacencyMatrix = new boolean[numVertices][numVertices];


        for (int i = 0; i < numVertices; i ++) {
            for (int j = 0; j < numVertices; j ++) {
                adjacencyMatrix[i][j] = false;
            }

        }
        for (int i = 0; i < numVertices; i ++) {
            List<Integer> adjacentNodes = adjacencyList.get(i);
            for (int j = 0; j < adjacentNodes.size(); j ++) {
                adjacencyMatrix[i][adjacentNodes.get(j)] = true;
            }
        }

        return adjacencyMatrix;
    }

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
