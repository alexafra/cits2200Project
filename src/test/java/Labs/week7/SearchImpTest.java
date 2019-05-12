package Labs.week7;

import Labs.CITS2200.Graph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * These are the unit tests I have written to test my code.
 *
 */
public class SearchImpTest {
    SearchImp search;
    Graph graph1;
    Graph graph2;
    Graph graph3;
    Graph graph4;
    Graph graph5;
    Graph graph6;

    @BeforeEach
    public void init () throws Exception {
        search = new SearchImp();
        String fileBase = "/Users/alexanderfrazis/Desktop/UWAUnits/2019Sem1/CITS2200/LabFiles/GraphInput";
        String postFix = ".txt";
        graph1 = Graph.readFile(fileBase + 1 +  postFix, true, true);
        graph2 = Graph.readFile(fileBase + 2 + postFix, true, true);
        graph3 = Graph.readFile(fileBase + 3 + postFix, true, true);
        graph4 = Graph.readFile(fileBase + 4 +  postFix, true, true);
        graph5 = Graph.readFile(fileBase + 5 +  postFix, true, true);
        graph6 = Graph.readFile(fileBase + 6 +  postFix, true, true);
    }

    @Test
    @DisplayName("Test Get Connected Tree")
    public void testGetConnectedTree () throws Exception {

        //No Nodes
        int[] parentArray1 = search.getConnectedTree(graph1, 0);
        int[] expectedArray1 = new int[0];
        System.out.println("////////////////////");
        System.out.println("Graph: ");
        System.out.println(graph1.toString());
        System.out.println("Graph Parent Array: ");
        System.out.println(Arrays.toString(expectedArray1));
        System.out.println("Expected Parent Array: ");
        System.out.println(Arrays.toString(expectedArray1));
        System.out.println();
        assertThat(parentArray1).isEqualTo(expectedArray1);

        //Single Node
        int[] parentArray2 = search.getConnectedTree(graph2, 0);
        int[] expectedArray2 = { -1 };
        System.out.println("////////////////////");
        System.out.println("Graph: ");
        System.out.println(graph2.toString());
        System.out.println("Graph Parent Array: ");
        System.out.println(Arrays.toString(parentArray2));
        System.out.println("Expected Parent Array: ");
        System.out.println(Arrays.toString(expectedArray2));
        System.out.println();
        assertThat(parentArray2).isEqualTo(expectedArray2);

        //Normal Small Graph
        int[] parentArray31 = search.getConnectedTree(graph3, 0);
        int[] expectedArray31 = {-1, 2, 0};
        System.out.println("////////////////////");
        System.out.println("Graph: ");
        System.out.println(graph3.toString());
        System.out.println("Graph Parent Array: ");
        System.out.println(Arrays.toString(parentArray31));
        System.out.println("Expected Parent Array: ");
        System.out.println(Arrays.toString(expectedArray31));
        System.out.println();
        assertThat(parentArray31).isEqualTo(expectedArray31);
        int[] parentArray32 = search.getDistances(graph3, 1);
        int[] expectedArray32 = {1, 0, 2};
        System.out.println("Graph Distance Array: ");
        System.out.println(Arrays.toString(parentArray32));
        System.out.println("Expected Distance Array: ");
        System.out.println(Arrays.toString(expectedArray32));
        System.out.println();
        assertThat(parentArray32).isEqualTo(expectedArray32);

        //Slightly complex example
        int[] parentArray4 = search.getConnectedTree(graph4, 0);
        int[] expectedArray4 = {-1, 0, 1, 1, 2};
        System.out.println("////////////////////");
        System.out.println("Graph: ");
        System.out.println(graph4.toString());
        System.out.println("Graph Parent Array: ");
        System.out.println(Arrays.toString(parentArray4));
        System.out.println("Expected Parent Array: ");
        System.out.println(Arrays.toString(expectedArray4));
        System.out.println();
        assertThat(parentArray4).isEqualTo(expectedArray4);

        //More complex example
        int[] parentArray5 = search.getConnectedTree(graph5, 0);
        int[] expectedArray5 = {-1, 0, 1, 4, 1, 2, 4};
        System.out.println("////////////////////");
        System.out.println("Graph: ");
        System.out.println(graph5.toString());
        System.out.println("Graph Parent Array: ");
        System.out.println(Arrays.toString(parentArray5));
        System.out.println("Expected Parent Array: ");
        System.out.println(Arrays.toString(expectedArray5));
        System.out.println();
        assertThat(parentArray5).isEqualTo(expectedArray5);

        //Most complex example
        int[] parentArray6 = search.getConnectedTree(graph6, 0);
        int[] expectedArray6 = {-1, 0, 1, 4, 1, 2, 4, 5};
        System.out.println("////////////////////");
        System.out.println("Graph: ");
        System.out.println(graph6.toString());
        System.out.println("Graph Parent Array: ");
        System.out.println(Arrays.toString(parentArray6));
        System.out.println("Expected Parent Array: ");
        System.out.println(Arrays.toString(expectedArray6));
        System.out.println();
        assertThat(parentArray6).isEqualTo(expectedArray6);
    }

    @Test
    @DisplayName("Test get Distance method")
    public void testGetDistances() {
        //No Nodes
        int[] distances1 = search.getDistances(graph1, 0);
        int[] expectedArray1 = new int[0];
        System.out.println("////////////////////");
        System.out.println("Graph: ");
        System.out.println(graph1.toString());
        System.out.println("Graph Distances Array: ");
        System.out.println(Arrays.toString(expectedArray1));
        System.out.println("Expected Distances Array: ");
        System.out.println(Arrays.toString(expectedArray1));
        System.out.println();
        assertThat(distances1).isEqualTo(expectedArray1);

        //Single Node
        int[] distances2 = search.getDistances(graph2, 0);
        int[] expectedArray2 = { 0 };
        System.out.println("////////////////////");
        System.out.println("Graph: ");
        System.out.println(graph2.toString());
        System.out.println("Graph Distances Array: ");
        System.out.println(Arrays.toString(distances2));
        System.out.println("Expected Distances Array: ");
        System.out.println(Arrays.toString(expectedArray2));
        System.out.println();
        assertThat(distances2).isEqualTo(expectedArray2);

        //Normal Small Graph
        int[] distances31 = search.getDistances(graph3, 0);
        int[] expectedArray31 = {0, 2, 1};
        System.out.println("////////////////////");
        System.out.println("Graph: ");
        System.out.println(graph3.toString());
        System.out.println("Graph Distance Array: ");
        System.out.println(Arrays.toString(distances31));
        System.out.println("Expected Distance Array: ");
        System.out.println(Arrays.toString(expectedArray31));
        System.out.println();
        assertThat(distances31).isEqualTo(expectedArray31);
        int[] distances32 = search.getDistances(graph3, 1);
        int[] expectedArray32 = {1, 0, 2};
        System.out.println("Graph Distance Array: ");
        System.out.println(Arrays.toString(distances32));
        System.out.println("Expected Distance Array: ");
        System.out.println(Arrays.toString(expectedArray32));
        System.out.println();
        assertThat(distances32).isEqualTo(expectedArray32);

        //Slightly complex example
        int[] distances4 = search.getDistances(graph4, 0);
        int[] expectedArray4 = {0, 1, 2, 2, 3};
        System.out.println("////////////////////");
        System.out.println("Graph: ");
        System.out.println(graph4.toString());
        System.out.println("Graph Distance Array: ");
        System.out.println(Arrays.toString(distances4));
        System.out.println("Expected Distances Array: ");
        System.out.println(Arrays.toString(expectedArray4));
        System.out.println();
        assertThat(distances4).isEqualTo(expectedArray4);

        //More complex example
        int[] parentArray5 = search.getDistances(graph5, 0);
        int[] expectedArray5 = {0, 1, 2, 3, 2, 3, 3};
        System.out.println("////////////////////");
        System.out.println("Graph: ");
        System.out.println(graph5.toString());
        System.out.println("Graph Distance Array: ");
        System.out.println(Arrays.toString(parentArray5));
        System.out.println("Expected Distance Array: ");
        System.out.println(Arrays.toString(expectedArray5));
        System.out.println();
        assertThat(parentArray5).isEqualTo(expectedArray5);

        //Most complex example
        int[] parentArray6 = search.getDistances(graph6, 0);
        int[] expectedArray6 = {0, 1, 2, 3, 2, 3, 3, 4};
        System.out.println("////////////////////");
        System.out.println("Graph: ");
        System.out.println(graph6.toString());
        System.out.println("Graph Distance Array: ");
        System.out.println(Arrays.toString(parentArray6));
        System.out.println("Expected Distance Array: ");
        System.out.println(Arrays.toString(expectedArray6));
        System.out.println();
        assertThat(parentArray6).isEqualTo(expectedArray6);
    }

    @Test
    @DisplayName("Test get times")
    public void testGetTimes() {
        //No Nodes
        int[][] times1 = search.getTimes(graph1, 0);
        int[][] expectedArray1 = new int[0][];
        System.out.println("////////////////////");
        System.out.println("Graph: ");
        System.out.println(graph1.toString());
        System.out.println("Graph Times Array: ");
        System.out.println(Arrays.deepToString(expectedArray1));
        System.out.println("Expected Times Array: ");
        System.out.println(Arrays.deepToString(expectedArray1));
        System.out.println();
        assertThat(times1).isEqualTo(expectedArray1);

        //Single Node
        int[][] times2 = search.getTimes(graph2, 0);
        int[][] expectedArray2 = { { 0, 1 } };
        System.out.println("////////////////////");
        System.out.println("Graph: ");
        System.out.println(graph2.toString());
        System.out.println("Graph Times Array: ");
        System.out.println(Arrays.deepToString(times2));
        System.out.println("Expected Times Array: ");
        System.out.println(Arrays.deepToString(expectedArray2));
        System.out.println();
        assertThat(times2).isEqualTo(expectedArray2);

        //Normal Small Graph
        int[][] times31 = search.getTimes(graph3, 0);
        int[][] expectedArray31 = { {0, 5}, {2, 3}, { 1, 4} };
        System.out.println("////////////////////");
        System.out.println("Graph: ");
        System.out.println(graph3.toString());
        System.out.println("Graph Times Array: ");
        System.out.println(Arrays.deepToString(times31));
        System.out.println("Expected Times Array: ");
        System.out.println(Arrays.deepToString(expectedArray31));
        System.out.println();
        assertThat(times31).isEqualTo(expectedArray31);
        int[][] times32 = search.getTimes(graph3, 1);
        int[][] expectedArray32 = { {1, 4}, {0, 5}, { 2, 3} };
        System.out.println("Graph Times Array: ");
        System.out.println(Arrays.deepToString(times32));
        System.out.println("Expected Times Array: ");
        System.out.println(Arrays.deepToString(expectedArray32));
        System.out.println();
        assertThat(times32).isEqualTo(expectedArray32);

        //Slightly complex example
        int[][] times4 = search.getTimes(graph4, 0);
        int[][] expectedArray4 = { {0, 9}, {1, 8}, {6, 7}, {2, 5}, {3, 4} };
        System.out.println("////////////////////");
        System.out.println("Graph: ");
        System.out.println(graph4.toString());
        System.out.println("Graph Times Array: ");
        System.out.println(Arrays.deepToString(times4));
        System.out.println("Expected Times Array: ");
        System.out.println(Arrays.deepToString(expectedArray4));
        System.out.println();
        assertThat(times4).isEqualTo(expectedArray4);

        //More complex example
        int[][] times5 = search.getTimes(graph5, 0);
        int[][] expectedArray5 = {{0, 13}, {1, 12}, {5, 6}, {9, 10}, {2, 11}, {4, 7}, {3, 8}};
        System.out.println("////////////////////");
        System.out.println("Graph: ");
        System.out.println(graph5.toString());
        System.out.println("Graph Times Array: ");
        System.out.println(Arrays.toString(times5));
        System.out.println("Expected Times Array: ");
        System.out.println(Arrays.toString(expectedArray5));
        System.out.println();
        assertThat(times5).isEqualTo(expectedArray5);

        //Most complex example
        int[][] times6 = search.getTimes(graph6, 0);
        int[][] expectedArray6 = {{0, 15}, {1, 14}, {7, 8}, {11, 12}, {2, 13}, {4, 9}, {3, 10}, {5, 6}};
        System.out.println("////////////////////");
        System.out.println("Graph: ");
        System.out.println(graph6.toString());
        System.out.println("Graph Times Array: ");
        System.out.println(Arrays.deepToString(times6));
        System.out.println("Expected Times Array: ");
        System.out.println(Arrays.deepToString(expectedArray6));
        System.out.println();
        assertThat(times6).isEqualTo(expectedArray6);
    }

    @Test
    @DisplayName("Test get times Recursive")
    public void testGetTimesRecursive() {
        //No Nodes
        int[][] times1 = search.getTimes2(graph1, 0);
        int[][] expectedArray1 = new int[0][];
        System.out.println("////////////////////");
        System.out.println("Graph: ");
        System.out.println(graph1.toString());
        System.out.println("Graph Times Array: ");
        System.out.println(Arrays.deepToString(expectedArray1));
        System.out.println("Expected Times Array: ");
        System.out.println(Arrays.deepToString(expectedArray1));
        System.out.println();
        assertThat(times1).isEqualTo(expectedArray1);

        //Single Node
        int[][] times2 = search.getTimes2(graph2, 0);
        int[][] expectedArray2 = { { 0, 1 } };
        System.out.println("////////////////////");
        System.out.println("Graph: ");
        System.out.println(graph2.toString());
        System.out.println("Graph Times Array: ");
        System.out.println(Arrays.deepToString(times2));
        System.out.println("Expected Times Array: ");
        System.out.println(Arrays.deepToString(expectedArray2));
        System.out.println();
        assertThat(times2).isEqualTo(expectedArray2);

        //Normal Small Graph
        int[][] times31 = search.getTimes2(graph3, 0);
        int[][] expectedArray31 = { {0, 5}, {2, 3}, { 1, 4} };
        System.out.println("////////////////////");
        System.out.println("Graph: ");
        System.out.println(graph3.toString());
        System.out.println("Graph Times Array: ");
        System.out.println(Arrays.deepToString(times31));
        System.out.println("Expected Times Array: ");
        System.out.println(Arrays.deepToString(expectedArray31));
        System.out.println();
        assertThat(times31).isEqualTo(expectedArray31);
        int[][] times32 = search.getTimes2(graph3, 1);
        int[][] expectedArray32 = { {1, 4}, {0, 5}, { 2, 3} };
        System.out.println("Graph Times Array: ");
        System.out.println(Arrays.deepToString(times32));
        System.out.println("Expected Times Array: ");
        System.out.println(Arrays.deepToString(expectedArray32));
        System.out.println();
        assertThat(times32).isEqualTo(expectedArray32);

        //Slightly complex example
        int[][] times4 = search.getTimes2(graph4, 0);
        int[][] expectedArray4 = { {0, 9}, {1, 8}, {2, 7}, {3, 6}, {4, 5} };
        System.out.println("////////////////////");
        System.out.println("Graph: ");
        System.out.println(graph4.toString());
        System.out.println("Graph Times Array: ");
        System.out.println(Arrays.deepToString(times4));
        System.out.println("Expected Times Array: ");
        System.out.println(Arrays.deepToString(expectedArray4));
        System.out.println();
        assertThat(times4).isEqualTo(expectedArray4);

        //More complex example
        int[][] times5 = search.getTimes2(graph5, 0);
        int[][] expectedArray5 = {{0, 13}, {1, 12}, {2, 11}, {4, 5}, {3, 10}, {6, 9}, {7, 8}};
        System.out.println("////////////////////");
        System.out.println("Graph: ");
        System.out.println(graph5.toString());
        System.out.println("Graph Times Array: ");
        System.out.println(Arrays.deepToString(times5));
        System.out.println("Expected Times Array: ");
        System.out.println(Arrays.deepToString(expectedArray5));
        System.out.println();
        assertThat(times5).isEqualTo(expectedArray5);

        //Most complex example
        int[][] times6 = search.getTimes2(graph6, 0);
        int[][] expectedArray6 = {{0, 15}, {1, 14}, {2, 13}, {4, 5}, {3, 12}, {7, 10}, {6, 11}, {8, 9}};
        System.out.println("////////////////////");
        System.out.println("Graph: ");
        System.out.println(graph6.toString());
        System.out.println("Graph Times Array: ");
        System.out.println(Arrays.deepToString(times6));
        System.out.println("Expected Times Array: ");
        System.out.println(Arrays.deepToString(expectedArray6));
        System.out.println();
        assertThat(times6).isEqualTo(expectedArray6);
    }
}
