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
    boolean edgeMatrixUpToDate; //safeguarding

    HashMap<Integer, String> intToStrMap;
    HashMap<String, Integer> strToIntMap;

    public MyCITS2200Project ()  {
        adjacencyList = new ArrayList<>();
        tAdjacencyList = new ArrayList<>();
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
    //O(1) method
    public void addEdge(String urlFrom, String urlTo) { //cant test multiple, can test singles
        if (urlFrom == null || urlTo == null || urlFrom.equals("") || urlTo.equals("")) { return; }

        edgeMatrixUpToDate = false; //unless it is a duplicate edge TODO
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
     * Finds all the centers of the page graph. The order of pages
     * in the output does not matter. Any order is correct as long as
     * all the centers are in the array, and no pages that aren't centers
     * are in the array.
     *
     * @return an array containing all the URLs that correspond to pages that are centers.
     */
    public String[] getCenters() {

        //CURRENTLY DOES NOT IGNORE ANY VERTICES THAT HAVE NO EDGES COMING OUT OF THEM
        //NOT CURRENTLY SURE WHAT TO DO WITH THESE, AND WHICH CASES SHOULD BE IGNORED

        int graphSize = adjacencyList.size();
        int[] longestPathArray = new int[graphSize];
        int currentCenterLength = Integer.MAX_VALUE;
        ArrayList<Integer> centerList = new ArrayList<>();

        //Loop through all the vertices
        for(int j = 0; j < graphSize; j++) {

            //Get corresponding url string
            String jUrl = intToStrMap.get(j);

            //For a single vertex in the List, find the longest ShortestPath to any other vertex
            for (int i = 0; i < graphSize; i++) {

                //Get corresponding url string
                String iUrl = intToStrMap.get(i);

                //Find the shortest path between i and j strings
                int iShortestPath = getShortestPath(jUrl, iUrl);
                if(iShortestPath == -1) {
                    //If the vertex cannot be reached, then it cannot be a center
                    longestPathArray[j] = -1;
                    break;
                } else if (iShortestPath > longestPathArray[j]) {
                    longestPathArray[j] = iShortestPath;
                }
            }

            //See if this longest path is the smallest, ie. it is a possible center
            if(longestPathArray[j] <= currentCenterLength && longestPathArray[j] != -1) currentCenterLength = longestPathArray[j];

        }

        //Add every vertex which is a center to centerList
        for(int i = 0; i < graphSize; i++) {
            if(longestPathArray[i] == currentCenterLength) {
                centerList.add(i);
            }
        }

        //Now we transfer our centers ArrayList into a String[] array

        //Find the size of the arrayList
        int numberOfCenters = centerList.size();
        //Create a new String[] array
        String[] centers = new String[numberOfCenters];

        for(int i = 0; i < numberOfCenters; i++) {
            //Find the corresponding urlString
            String centerUrl = intToStrMap.get(centerList.get(i));
            //Add it to the String[] array
            centers[i] = centerUrl;
        }

        return centers;
    } //Test by building out

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

        //Run a DFS on the original adjacency list
        //Push vertexes onto the stack in the order that you traverse
        //Transpose our adjacency matrix...(or use it if we've already created it in addEdge()
        //Run a DFS on the transpose list

        List<List<Integer>> stringAList = new ArrayList<>();
        int count = 0;
        int vertex = 0;
        Stack stack = new Stack();
        //Find the number of vertices in our graph
        int size = adjacencyList.size();
        //Visited will track whether or not we have visited the vertex in the past
        boolean[] visited = new boolean[size];

        //NO NEED TO DO THIS AS JAVA BOOLEANS DEFAULT INIT TO FALSE
        //Make all vertices unvisited
//        for(int i = 0; i < size; i++) {
//            visited[i] = false;
//        }

        //Go through the graph and find the order of vertices that our DFS produces
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
        int numVertices = adjacencyList.size();
        int numSets = 1<<numVertices;

        boolean[][] adjacencyMatrix = new boolean[10][10];

        boolean dp[][] = new boolean[numVertices][numSets];

        //Set everything to false
        for (int vertex = 0; vertex < numVertices; ++vertex) {
            for (int set = 0; set < numSets; ++set ) {
                dp[vertex][set] = false;
            }
        }

        //initialise trivial cases to true (sets of size 1)
        for (int vertex = 0; vertex < numVertices; ++vertex) {
            dp[vertex][1<<vertex] = true;
        }


        for (int set = 0; set < numSets; ++set) {
            for (int vertex = 0; vertex < numVertices; ++vertex) {
                if (((1 << vertex) & set) != 0) {
                    int previousSet = set - (1 << vertex); // S - {c}
                    //D(S, c) == D(s- c, 1)&a[1][c] || D(s - c, 2)&a[2][c] ... || D(s - c, x)&a[2][x]
                    //we want dp[vertex][set]
                    for (int x = 0; x < numVertices; ++x) { // D(S-{c}, x)
                        if (dp[vertex][set]) {
                            break;
                        }
                        dp[vertex][set] = dp[x][previousSet] & adjacencyMatrix[x][vertex]; //should be matrix
                    }
                }
            }
        }

        int lastNodeInHamiltonian = -1;
        for (int vertex = 0; vertex < numVertices; ++vertex) {
            if (dp[vertex][1<<numVertices - 1]) {
                lastNodeInHamiltonian = vertex;
                break;
            }
        }

        String[] hamiltonianPath = new String[numVertices];
        if (lastNodeInHamiltonian == -1) {
            return hamiltonianPath;
        } else {
            int set = numSets - 1;
            for (int i = 0; i < numVertices; ++i) {
                for (int j = 0; j < numVertices; ++j) {
                    if (dp[j][set] == true) {
                        hamiltonianPath[numVertices - 1 - i] = intToStrMap.get(j);
                        set = set - (1<<j);
                        break;
                    }

                }
            }
        }
        return hamiltonianPath;
    }

    //test by looping back through the parent array

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
