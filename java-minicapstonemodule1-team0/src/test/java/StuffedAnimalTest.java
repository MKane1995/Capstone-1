import com.techelevator.StuffedAnimal;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class StuffedAnimalTest {

    @Test
    public void makeSoundTest() {
        StuffedAnimal testAnimal = new StuffedAnimal("A1", "Special Duck", new BigDecimal(800), "Duck");

        String actual = testAnimal.makeSound();
        String expected = "Quack,Quack,Splash!\n";

        Assert.assertEquals(expected, actual);
    }
}
