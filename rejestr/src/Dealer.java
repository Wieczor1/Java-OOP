import java.time.LocalDate;

public class Dealer extends Employee {
  @Override
  public String toString() {
    return "Dealer{"
        + "effectiveness="
        + effectiveness
        + ", commission="
        + commission
        + ","
        + super.toString()
        + "\n";
  }

  public enum Effectiveness {
    LOW(60),
    MEDIUM(90),
    HIGH(120);

    private int value;

    Effectiveness(int value) {
      this.value = value;
    }
  }

  private Effectiveness effectiveness;
  private double commission;

  public Dealer(
      String name,
      String surname,
      LocalDate birthDate,
      LocalDate hireDate,
      Address address,
      Effectiveness effectiveness,
      double commission) {
    super(name, surname, birthDate, hireDate, address);
    this.effectiveness = effectiveness;
    if (commission < 0 || commission > 100) throw new IllegalArgumentException();
    this.commission = commission;
  }

  @Override
  int getValue() {
    return getExperience() * effectiveness.value;
  }
}
