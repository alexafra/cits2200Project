package project;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.HashMap;

public class MyCITS2200Project implements CITS2200Project {

    private List<List<Integer>> adjacencyList; //Single source of truth
    private int[][] edgeMatrix;
    private boolean edgeMatrixUpToDate; //safeguarding

    private HashMap<Integer, String> intToStrMap;
    private HashMap<String, Integer> strToIntMap;

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
    public void addEdge(String urlFrom, String urlTo) {
        edgeMatrixUpToDate = false; //unless it is a duplicate edge TODO
        Integer intFrom = strToIntMap.get(urlFrom);
        Integer intTo = strToIntMap.get(urlTo);

        if (intFrom == null) {
            intFrom = intToStrMap.size(); //we can use size because we are never deleting edges
            intToStrMap.put(intFrom, urlFrom); //therefore we can assume the number mappings are 0 -> size() - 1
            strToIntMap.put(urlFrom, intFrom); //it will also correspond to i in the edgeMatrix and i in
            adjacencyList.add(new ArrayList<>(5));// the primary adjacencyList so we can use the key as the index
        }
        if (intTo == null) {
            intTo = strToIntMap.size();
            intToStrMap.put(intTo, urlTo);
            strToIntMap.put(urlTo, intTo);
            adjacencyList.add(new ArrayList<>(5));
        }

        adjacencyList.get(intFrom).add(intTo);
    }

    /**
     * Finds the shorest path in number of links between two pages.
     * If there is no path, returns -1.
     *
     * @param urlFrom the URL where the path should start.
     * @param urlTo the URL where the path should end.
     * @return the legnth of the shorest path in number of links followed.
     */
    public int getShortestPath(String urlFrom, String urlTo) {
        return 0;
    }


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
        return new String[0][0];
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
        return new String[0];
    }
}
