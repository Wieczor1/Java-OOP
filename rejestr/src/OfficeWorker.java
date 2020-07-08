import java.time.LocalDate;

public class OfficeWorker extends Employee {
  public int iq;
  private static int officeId;
  private int workdeskId;

  static {
    officeId = 0;
  }

  public OfficeWorker(
      String name,
      String surname,
      LocalDate birthDate,
      LocalDate hireDate,
      Address address,
      int iq) {
    super(name, surname, birthDate, hireDate, address);
    if (iq < 70 || iq > 150) throw new IllegalArgumentException();
    this.iq = iq;
    officeId += 1;
    this.workdeskId = officeId;
  }
  // to-do - identyfikator stanowiska biurowego
  @Override
  int getValue() {
    return iq * getExperience();
  }

  @Override
  public String toString() {
    return "OfficeWorker{"
        + "iq="
        + iq
        + ", "
        + "workdeskId="
        + workdeskId
        + ","
        + super.toString()
        + "\n";
  }
}
