import com.techelevator.Money;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class MoneyTest {

    @Test
    public void subtractBalance_happy_path_number_too_big(){
        //Arrange
        Money money = new Money();
        BigDecimal big = new BigDecimal(100);

        //Act
        boolean actual = money.subtractBalance(new BigDecimal(10));
        boolean expected = false;

        //Assert
        Assert.assertEquals(expected, actual);
    }


}
