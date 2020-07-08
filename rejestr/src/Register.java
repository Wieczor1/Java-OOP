import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class EmployeeComparator implements Comparator<Employee> {

  public int compare(Employee e1, Employee e2) {
    if (e1.getExperience() > e2.getExperience()) return -1;
    if (e1.getExperience() < e2.getExperience()) return 1;
    else {
      if (e1.getAge() < e2.getAge()) return -1;
      if (e1.getAge() > e2.getAge()) return 1;
      else {
        return e1.getSurname().compareTo(e2.getSurname());
      }
    }
  }
}

public class Register {
  private ArrayList<Employee> register;

  public Register() {
    this.register = new ArrayList<Employee>();
  }

  public void addEmployee(Employee employee) {
    for (Employee e: register) {
      if (e.getEmployeeId() == employee.getEmployeeId()) {
        throw new IllegalArgumentException("Employee already in register.");
      }
    }
    this.register.add(employee);
  }

  public void addEmployees(Employee... employees) {
    for (Employee e: employees ) {
      addEmployee(e);
    }
  }

  public void deleteEmployee(int id) {
    for (Employee e : register) {
      if (e.getEmployeeId() == id) {
        register.remove(e);
        return;
      }
    }
  }

  public void printSortedEmployees() {
    register.sort(new EmployeeComparator());
    for (Employee e : register) {
      System.out.println(e + "\n");
    }
  }

  public void printEmployeesWithValue() {
    for (Employee e : register) {
      System.out.println(e + "value=" + e.getValue() + "\n");
    }
  }

  public void printEmployeesFromCity(String city) {
    List<Employee> fromCity = new ArrayList<Employee>();
    for (Employee e : register) {
      if (e.getAddress().getCity().equals(city)) fromCity.add(e);
    }

    for (Employee e : fromCity) {
      System.out.println(e);
    }
  }

  public ArrayList<Employee> getRegister() {
    return register;
  }
}
