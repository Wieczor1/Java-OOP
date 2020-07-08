import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class LabourerTest {

  @Test
  public void getValue() {
    Employee employee =
        new Labourer(
            "Adam",
            "Malysz",
            LocalDate.of(1999, 5, 22),
            LocalDate.of(2017, 12, 27),
            new Address("Garnizonowa", "22", "12", "Ostróda"),
            100);
    Assert.assertEquals(employee.getValue(), 10, 0);
  }
}
