import java.time.LocalDate;

public class Labourer extends Employee {
  public int strenght;

  public Labourer(
      String name,
      String surname,
      LocalDate birthDate,
      LocalDate hireDate,
      Address address,
      int strenght) {
    super(name, surname, birthDate, hireDate, address);
    if (strenght < 1 || strenght > 100) throw new IllegalArgumentException();
    this.strenght = strenght;
  }

  public int getStrenght() {
    return strenght;
  }

  public void setStrenght(int strenght) {
    this.strenght = strenght;
  }

  @Override
  int getValue() {
    return getExperience() * (strenght / getAge());
  }

  @Override
  public String toString() {
    return "Labourer{" + "strenght=" + strenght + "," + super.toString() + "\n";
  }
}
