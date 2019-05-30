package project;


import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class getHamiltonianTest {

    private MyCITS2200Project graph100;
    private MyCITS2200Project graph101;
    private MyCITS2200Project graph102;
    private MyCITS2200Project graph103;
    private MyCITS2200Project graph104;
//    private MyCITS2200Project graph105;

    private String pathBase;
    private String pathSuffix;

    @BeforeEach
    public void init() {
        graph100 = new MyCITS2200Project();
        graph101 = new MyCITS2200Project();
        graph102 = new MyCITS2200Project();
        graph103 = new MyCITS2200Project();
        graph104 = new MyCITS2200Project();
//        graph105 = new MyCITS2200Project();

        pathBase = "src/test/projectTestData/edge";
        pathSuffix = ".txt";

        CITS2200ProjectTester.loadEdges(graph100, pathBase + "100" + pathSuffix);
        CITS2200ProjectTester.loadEdges(graph101, pathBase + 101 + pathSuffix);
        CITS2200ProjectTester.loadEdges(graph102, pathBase + 102 + pathSuffix);
        CITS2200ProjectTester.loadEdges(graph103, pathBase + 103 + pathSuffix);
        CITS2200ProjectTester.loadEdges(graph104, pathBase + 104 + pathSuffix);
//        CITS2200ProjectTester.loadEdges(graph105, pathBase + 105 + pathSuffix);
    }

    @Test
    @DisplayName("Test Graph With Hamiltonian")
    public void testHasHamiltonian() {

        //Possible Hamiltonian path for this one...IF IT DOESNT PASS, JUST CHECK THAT IT CONTAINS digits 0-6.
        //Any combination will do
        String[] string1 = new String[] {"0", "1", "2", "3", "4", "5", "6"};

        assertThat(graph100.getHamiltonianPath()).isEqualTo(string1);

        //Trivial Hamiltonian, where there is only 2 vertices
        String[] string2 = new String[] {"a", "b"};
        assertThat(graph101.getHamiltonianPath()).isEqualTo(string2);
    }

    @Test
    @DisplayName("Test Graph NO Hamiltonian")
    public void testNoHamiltonian() {

        String[] string3 = new String[] {};

        //Both of these should return an empty string... ie, no hamiltonian path
        assertThat(graph102.getHamiltonianPath()).isEqualTo(string3);
        assertThat(graph103.getHamiltonianPath()).isEqualTo(string3);
        //This next graph consists of sets of disconnected vertices
        assertThat(graph104.getHamiltonianPath()).isEqualTo(string3);
    }




}