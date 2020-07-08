import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;


public class DealerTest {

  @Test
  public void getValue() {
    Employee employee =
        new Dealer(
            "Adam",
            "Malysz",
            LocalDate.of(1999, 5, 22),
            LocalDate.of(2015, 12, 27),
            new Address("Garnizonowa", "22", "12", "Ostróda"),
            Dealer.Effectiveness.LOW,
            5);
    Assert.assertEquals(employee.getValue(), 240, 0);
  }
}
