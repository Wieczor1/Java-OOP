import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class Secretary implements Observer, Serializable {

  private Map<General, StringBuilder> reports;

  public Secretary() {
    this.reports = new HashMap<>();
  }

  @Override
  public void update(String note, Object... os) {
    for (Object o : os) {
      StringBuilder report = reports.get(o);
      report.append(note);
    }
  }


  public void addGenerals(General...generals) {
    for (General general : generals) {
      reports.put(general, new StringBuilder());
      StringBuilder report = reports.get(general);
      report.append("Report of general ").append(general.getName()).append(" \n\n");
    }
  }


  public String getGeneralReport(General general) {

    return reports.get(general).toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Secretary secretary = (Secretary) o;
    return Objects.equals(reports, secretary.reports);
  }

  @Override
  public int hashCode() {
    return Objects.hash(reports);
  }
}
