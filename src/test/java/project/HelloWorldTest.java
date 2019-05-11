package project;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;

public class HelloWorldTest {
    private HelloWorld hello;

    @BeforeEach
    public void init () {
        hello = new HelloWorld();
    }

    @Test
    @DisplayName("Test the Hello World Method")
    public void testHelloWorld () {
        assertThat(hello.speak()).isEqualTo("Hello World!");
    }

}
