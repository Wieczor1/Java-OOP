import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public abstract class Employee {
  private static int id;
  private int employeeId;
  private String name;
  private String surname;
  private int experience;
  private int age;
  private LocalDate birthDate;
  private LocalDate hireDate;
  private Address address;

  static {
    id = 0;
  }

  abstract int getValue();

  public Employee(
      String name, String surname, LocalDate birthDate, LocalDate hireDate, Address address) {
    id += 1;
    this.employeeId = id;
    this.name = name;
    this.surname = surname;
    this.birthDate = birthDate;
    this.hireDate = hireDate;
    this.address = address;
    this.experience = getExperience();
    this.age = getAge();
  }

  public int getEmployeeId() {
    return employeeId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public int getExperience() {
    int exp = (int) ChronoUnit.YEARS.between(getHireDate(), LocalDate.now());
    if (exp >= 0) {
      return exp;
    } else {
      throw new IllegalArgumentException("Date from future.");
    }
  }

  public int getAge() {
    int age = (int) ChronoUnit.YEARS.between(getBirthDate(), LocalDate.now());
    if (age >= 18) {
      return age;
    } else {
      throw new IllegalArgumentException("Too young.");
    }
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  public LocalDate getHireDate() {
    return hireDate;
  }

  public void setHireDate(LocalDate hireDate) {
    this.hireDate = hireDate;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  @Override
  public String toString() {
    return " employeeId="
        + employeeId
        + ", name='"
        + name
        + '\''
        + ", surname='"
        + surname
        + '\''
        + ", experience="
        + experience
        + ", age="
        + age
        + ", birthDate="
        + birthDate
        + ", hireDate="
        + hireDate
        + ", address="
        + address
        + '}';
  }
}
