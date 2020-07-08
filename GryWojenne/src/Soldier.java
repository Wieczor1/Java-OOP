import java.io.Serializable;
import java.util.Objects;

public class Soldier implements Serializable {
  private static int id;
  private Rank rank;
  private int experience;
  private int soldierId;

  public Soldier(Rank rank) {
    this.soldierId = id;
    id++;
    this.rank = rank;
    this.experience = 1;
  }

  public Rank getRank() {
    return rank;
  }

  public void setRank(Rank rank) {
    this.rank = rank;
  }

  public int getExperience() {
    return experience;
  }

  public void setExperience(int experience) {
    this.experience = experience;
  }

  @Override
  public String toString() {
    return "Soldier:" + " id=" + soldierId + ", rank=" + rank + ", experience=" + experience;
  }

  public int getStrength() {
    return this.getExperience() * this.getRank().getValue();
  }

  public void train() {
    this.setExperience(getExperience() + 1);
  }

  public void degrade() {
    this.setExperience(getExperience() - 1);
  }

  public void promote() {
    int rankValue = this.getRank().getValue();
    if (rankValue < 4) {
      this.setExperience(1);
      switch (rankValue) {
        case 1:
          setRank(Rank.CORPORAL);
          break;
        case 2:
          setRank(Rank.CAPTAIN);
          break;
        case 3:
          setRank(Rank.MAJOR);
          break;
      }
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Soldier soldier = (Soldier) o;
    return getExperience() == soldier.getExperience() &&
            soldierId == soldier.soldierId &&
            getRank() == soldier.getRank();
  }

  @Override
  public int hashCode() {
    return Objects.hash(getRank(), getExperience(), soldierId);
  }

  public enum Rank {
    PRIVATE(1),
    CORPORAL(2),
    CAPTAIN(3),
    MAJOR(4);

    private int value;

    Rank(int value) {
      this.value = value;
    }

    public int getValue() {
      return value;
    }
  }
}
