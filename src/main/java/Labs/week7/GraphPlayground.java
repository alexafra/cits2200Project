package Labs.week7;

import Labs.CITS2200.Graph;

public class GraphPlayground {
    public static void main(String args[]) throws Exception {
//        Graph graphNonWeighted = Graph.randomGraph(5, true, 0.5);
//        System.out.println(graphNonWeighted.toString());

//        try {
            Graph myGraph = Graph.readFile("/Users/alexanderfrazis/Desktop/UWAUnits/2019Sem1/Labs.CITS2200/LabFiles/GraphInput.txt", true, true);
            System.out.println(myGraph.toString());
//        } catch (Exception e) {
//            System.out.println("error: " + e.getMessage());
//        }


        Graph weightedGraph = Graph.randomWeightedGraph(5, true, 0.5, 5);
        System.out.println(weightedGraph.toString());
    }
}