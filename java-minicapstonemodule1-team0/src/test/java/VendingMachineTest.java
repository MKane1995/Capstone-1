import com.techelevator.Money;
import com.techelevator.VendingMachineCLI;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.math.BigDecimal;
import java.util.Scanner;

public class VendingMachineTest {
    private File srcFile;

    @Before
    public void setUp() throws Exception {

    }
    @Test
    public void printMainDisplay_returns_happy_path(){
        //Arrange
        VendingMachineCLI vending = new VendingMachineCLI();
        Money money = new Money();
        Scanner scan = new Scanner(System.in);

        //Act
        int actual = vending.printMainDisplay();
        int expected = 3;

        //Assert
        Assert.assertEquals(expected, actual);
    }

}
