import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class EmployeeTest {

  @Test
  public void getExperience() {
    Employee employee =
        new Labourer(
            "Adam",
            "Malysz",
            LocalDate.of(1999, 5, 22),
            LocalDate.now().minusYears(2),
            new Address("Garnizonowa", "22", "12", "Ostróda"),
            50);
    Assert.assertEquals(employee.getExperience(), 2, 0);
  }

  @Test
  public void getExperienceSameYear() {
    Employee employee =
        new Labourer(
            "Adam",
            "Malysz",
            LocalDate.of(1999, 5, 22),
            LocalDate.now(),
            new Address("Garnizonowa", "22", "12", "Ostróda"),
            50);
    Assert.assertEquals(employee.getExperience(), 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void getExperiencefromFuture() {
    Employee employee =
        new Labourer(
            "Adam",
            "Malysz",
            LocalDate.of(1999, 5, 22),
            LocalDate.now().plusYears(2),
            new Address("Garnizonowa", "22", "12", "Ostróda"),
            50);
  }

  @Test
  public void getAge() {
    Employee employee =
            new Labourer(
                    "Adam",
                    "Malysz",
                    LocalDate.now().minusYears(20),
                    LocalDate.now(),
                    new Address("Garnizonowa", "22", "12", "Ostróda"),
                    50);
    Assert.assertEquals(employee.getAge(), 20, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void getAgefromFuture() {
    Employee employee =
            new Labourer(
                    "Adam",
                    "Malysz",
                    LocalDate.now().plusYears(2),
                    LocalDate.now(),
                    new Address("Garnizonowa", "22", "12", "Ostróda"),
                    50);
    Assert.assertEquals(employee.getAge(), 20, 0);
  }
}
