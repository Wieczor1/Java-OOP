import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class EmployeeComparatorTest {

  @Test
  public void compareByExperience() {
    ArrayList<Employee> list = new ArrayList();
    Labourer lab =
        new Labourer(
            "Adam",
            "Malysz",
            LocalDate.of(1999, 5, 22),
            LocalDate.of(2017, 12, 27),
            new Address("Garnizonowa", "22", "12", "Ostróda"),
            100);
    Dealer deal =
        new Dealer(
            "Janusz",
            "Andrzej",
            LocalDate.of(1999, 5, 22),
            LocalDate.of(2009, 12, 27),
            new Address("Garnizonowa", "22", "12", "Ostróda"),
            Dealer.Effectiveness.LOW,
            5);
    list.add(lab);
    list.add(deal);
    list.sort(new EmployeeComparator());
    Assert.assertEquals(list.get(0), deal);
  }

  @Test
  public void compareByAge() {
    ArrayList<Employee> list = new ArrayList();
    Labourer lab =
        new Labourer(
            "Adam",
            "Malysz",
            LocalDate.of(1999, 5, 22),
            LocalDate.of(2017, 12, 27),
            new Address("Garnizonowa", "22", "12", "Ostróda"),
            100);
    Dealer deal =
        new Dealer(
            "Janusz",
            "Andrzej",
            LocalDate.of(1990, 5, 22),
            LocalDate.of(2017, 12, 27),
            new Address("Garnizonowa", "22", "12", "Ostróda"),
            Dealer.Effectiveness.LOW,
            5);
    list.add(lab);
    list.add(deal);
    list.sort(new EmployeeComparator());
    Assert.assertEquals(list.get(0), lab);
  }

  @Test
  public void compareBySurname() {
    ArrayList<Employee> list = new ArrayList();
    Labourer lab =
        new Labourer(
            "Adam",
            "Abc",
            LocalDate.of(1999, 5, 22),
            LocalDate.of(2017, 12, 27),
            new Address("Garnizonowa", "22", "12", "Ostróda"),
            100);
    Dealer deal =
        new Dealer(
            "Adam",
            "Aba",
            LocalDate.of(1999, 5, 22),
            LocalDate.of(2017, 12, 27),
            new Address("Garnizonowa", "22", "12", "Ostróda"),
            Dealer.Effectiveness.LOW,
            5);
    list.add(lab);
    list.add(deal);
    list.sort(new EmployeeComparator());
    Assert.assertEquals(list.get(0), deal);
  }
}
