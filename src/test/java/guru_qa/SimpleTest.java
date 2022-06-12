package guru_qa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class SimpleTest {

    @DisplayName("Check that 3> 2")
@Disabled("TICKET-234")
    @Test
    void simpleTest(){
        Assertions.assertTrue(3>2);
    }
    @Test
    void simpleTest1(){
        Assertions.assertTrue(3>2);
    }

    @Test
    void simpleTest2(){
        throw new RuntimeException("secondary exception");
    }


}
