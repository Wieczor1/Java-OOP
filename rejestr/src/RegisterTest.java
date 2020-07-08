import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class RegisterTest {

  @Test
  public void addEmployee() {
    Register register = new Register();
    Labourer lab =
            new Labourer(
                    "Adam",
                    "Malysz",
                    LocalDate.of(1999, 5, 22),
                    LocalDate.of(2017, 12, 27),
                    new Address("Garnizonowa", "22", "12", "Ostróda"),
                    100);
    register.addEmployee(lab);
    Assert.assertEquals(register.getRegister().size(), 1);

  }
  @Test(expected = IllegalArgumentException.class)
  public void addEmployeeTwice() {
    Register register = new Register();
    Labourer lab =
            new Labourer(
                    "Adam",
                    "Malysz",
                    LocalDate.of(1999, 5, 22),
                    LocalDate.of(2017, 12, 27),
                    new Address("Garnizonowa", "22", "12", "Ostróda"),
                    100);
    register.addEmployee(lab);
    register.addEmployee(lab);
  }

  @Test
  public void addEmployees() {
    Register register = new Register();
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
                    LocalDate.of(2015, 12, 27),
                    new Address("Garnizonowa", "22", "12", "Ostróda"),
                    Dealer.Effectiveness.LOW,
                    5);
    register.addEmployees(lab, deal);
    Assert.assertEquals(register.getRegister().size(), 2);
  }

  @Test
  public void deleteEmployee() {
    Register register = new Register();
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
                    LocalDate.of(2015, 12, 27),
                    new Address("Garnizonowa", "22", "12", "Ostróda"),
                    Dealer.Effectiveness.LOW,
                    5);
    register.addEmployees(lab, deal);
    register.deleteEmployee(deal.getEmployeeId());
    Assert.assertEquals(register.getRegister().size(), 1);
  }

  @Test
  public void printEmployeesWithValue() {
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
                    LocalDate.of(2015, 12, 27),
                    new Address("Garnizonowa", "22", "12", "Ostróda"),
                    Dealer.Effectiveness.LOW,
                    5);
    OfficeWorker off =
            new OfficeWorker(
                    "Stefan",
                    "Popiolek",
                    LocalDate.of(1999, 5, 22),
                    LocalDate.of(2015, 12, 27),
                    new Address("Garnizonowa", "22", "12", "Ostróda"),100);

    Register register = new Register();
    register.addEmployees(lab, deal, off);
    register.printEmployeesWithValue();
  }

  @Test
  public void printEmployeesFromCity() {
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
                    LocalDate.of(2015, 12, 27),
                    new Address("Garnizonowa", "22", "12", "Gdańsk"),
                    Dealer.Effectiveness.LOW,
                    5);
    OfficeWorker off =
            new OfficeWorker(
                    "Stefan",
                    "Popiolek",
                    LocalDate.of(1999, 5, 22),
                    LocalDate.of(2015, 12, 27),
                    new Address("Garnizonowa", "22", "12", "Ostróda"),100);

    Register register = new Register();
    register.addEmployees(lab, deal, off);
    register.printEmployeesFromCity("Ostróda");
  }

  @Test
  public void printSortedEmployees() {
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
                    new Address("Garnizonowa", "22", "12", "Gdańsk"),
                    Dealer.Effectiveness.LOW,
                    5);
    Dealer deal1 =
            new Dealer(
                    "Janusz",
                    "Andrzej",
                    LocalDate.of(2001, 5, 22),
                    LocalDate.of(2016, 12, 27),
                    new Address("Garnizonowa", "22", "12", "Gdańsk"),
                    Dealer.Effectiveness.LOW,
                    5);
    OfficeWorker off =
            new OfficeWorker(
                    "Stefan",
                    "Popiolek",
                    LocalDate.of(1999, 5, 22),
                    LocalDate.of(2016, 12, 27),
                    new Address("Garnizonowa", "22", "12", "Ostróda"),100);
    OfficeWorker off1 =
            new OfficeWorker(
                    "Iwona",
                    "Popiolek",
                    LocalDate.of(1989, 5, 22),
                    LocalDate.of(2016, 12, 27),
                    new Address("Garnizonowa", "22", "12", "Ostróda"),100);
    OfficeWorker off2 =
            new OfficeWorker(
                    "Iwona",
                    "Aniolek",
                    LocalDate.of(1989, 5, 22),
                    LocalDate.of(2016, 12, 27),
                    new Address("Garnizonowa", "22", "12", "Ostróda"),100);

    Register register = new Register();
    register.addEmployees(lab, deal, off, off1, deal1, off2);
    register.printSortedEmployees();
  }
}