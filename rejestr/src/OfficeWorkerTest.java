import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class OfficeWorkerTest {

  @Test
  public void getValue() {
    OfficeWorker employee =
            new OfficeWorker(
                    "Adam",
                    "Malysz",
                    LocalDate.of(1999, 5, 22),
                    LocalDate.of(2015, 12, 27),
                    new Address("Garnizonowa", "22", "12", "Ostróda"),100);
    Assert.assertEquals(employee.getValue(),400,0);
  }
}