import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class General implements Serializable, Observable {
  private String name;
  private double gold;
  private List<Soldier> army;
  private Observer observer;

  public General(String name, double gold, List<Soldier> army, Secretary secretary) {
    this.name = name;
    this.gold = gold;
    this.army = army;
    attach(secretary);
  }


  public void trainArmy() {
    int cost = 0;
    for (Soldier soldier : army) {
      cost += soldier.getRank().getValue();
    }
    if (cost > gold) throw new IllegalArgumentException("Insufficient gold to train army.");
    this.promoteArmy();
  }

  private void rankUp(Soldier soldier) {
    soldier.promote();
    notifyObservers(Note.note(Event.PROMOTE, soldier), this);
  }

  private void promoteSoldier(Soldier soldier) {
    soldier.train();
    if (soldier.getExperience() == soldier.getRank().getValue() * 5) {
      rankUp(soldier);
    }
    notifyObservers(Note.note(Event.TRAIN, soldier), this);
  }

  public void trainSoldiers(Soldier... soldiers) {
    int cost = 0;
    for (Soldier soldier : soldiers) {
      cost += soldier.getRank().getValue();
    }
    if (cost > gold) throw new IllegalArgumentException("Insufficient gold to train soldiers.");
    promoteSoldiers(soldiers);
  }

  public void buySoldier(Soldier.Rank rank) {
    int cost = rank.getValue() * 10;
    if (cost > gold) throw new IllegalArgumentException("Insufficient gold to buy a soldier.");
    gold -= cost;
    Soldier soldier = new Soldier(rank);
    this.army.add(soldier);
    notifyObservers(Note.note(Event.BUY, soldier), this);
  }

  public void buySoldiers(Soldier.Rank... ranks) {
    int cost = 0;
    for (Soldier.Rank rank : ranks) {
      cost += rank.getValue() * 10;
    }
    if (cost > gold) throw new IllegalArgumentException("Insufficient gold to buy soldiers.");
    for (Soldier.Rank rank : ranks) {
      buySoldier(rank);
    }
  }

  public int getStrength() {
    int strength = 0;
    for (Soldier soldier : army) {
      strength += soldier.getStrength();
    }
    return strength;
  }

  private void endWar(General loser, General winner) {
    double spoilsOfWar = loser.getGold() * 0.1;
    notifyObservers("\t" + this.getName() + " looted " + spoilsOfWar + " gold\n", winner);
    notifyObservers("\t" + this.getName() + " lost " + spoilsOfWar + " gold\n", loser);
    winner.setGold(gold + spoilsOfWar);
    winner.promoteArmy();
    winner.armyAfterWar();

    loser.setGold(loser.getGold() - spoilsOfWar);
    loser.degradeArmy();
    loser.armyAfterWar();
  }

  private void promoteArmy() {
    for (Soldier soldier : army) {
      promoteSoldier(soldier);
    }
  }

  private void promoteSoldiers(Soldier... soldiers) {
    for (Soldier soldier : soldiers) {
      if (army.contains(soldier)) {
        promoteSoldier(soldier);
      } else {
        throw new IllegalArgumentException("Soldier not in army.");
      }
    }
  }

  private void degradeSoldier(Soldier soldier) {
    soldier.degrade();
    notifyObservers(Note.note(Event.DEMOTE, soldier), this);
  }

  private void degradeArmy() {
    for (Soldier soldier : army) {
      degradeSoldier(soldier);
    }
  }

  private void draw(General attackedGeneral) {
    Random rand = new Random();
    Soldier randomSoldierFromFirstArmy = this.getArmy().get(rand.nextInt(getArmy().size()));
    Soldier randomSoldierFromSecondArmy =
        attackedGeneral.getArmy().get(rand.nextInt(attackedGeneral.getArmy().size()));
    killSoldier(randomSoldierFromFirstArmy);
    attackedGeneral.killSoldier(randomSoldierFromSecondArmy);
  }

  private void killSoldier(Soldier soldier) {
    army.remove(soldier);
    notifyObservers(Note.note(Event.KILL, soldier), this);
  }

  private void armyAfterWar() {
    for (Soldier soldier : army) {
      if (soldier.getExperience() == 0) {
        killSoldier(soldier);
      }
      if (soldier.getExperience() == soldier.getRank().getValue() * 5) {
        rankUp(soldier);
      }
    }
  }

  public void attackGeneral(General attackedGeneral) {
    if (attackedGeneral != this && !this.army.isEmpty() && !attackedGeneral.getArmy().isEmpty()) {
      int strengthOfAttacker = this.getStrength();
      int strengthOfDefender = attackedGeneral.getStrength();
      notifyObservers(Note.note(Event.ATTACK, attackedGeneral), this);
      notifyObservers(Note.note(Event.ATTACKED, this), attackedGeneral);

      if (strengthOfAttacker > strengthOfDefender) {
        endWar(attackedGeneral, this);
      } else if (strengthOfDefender > strengthOfAttacker) {
        endWar(this, attackedGeneral);
      } else {
        draw(attackedGeneral);
        this.armyAfterWar();
        attackedGeneral.armyAfterWar();
      }
      notifyObservers("PEACE\n\n\n\n", this, attackedGeneral);

    } else {
      throw new IllegalArgumentException();
    }
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getGold() {
    return gold;
  }

  private void setGold(double gold) {
    this.gold = gold;
  }

  public List<Soldier> getArmy() {
    return army;
  }

  public void setArmy(List<Soldier> army) {
    this.army = army;
  }

  @Override
  public String toString() {
    return "General{" + "name='" + name + '\'' + ", gold=" + gold + ", army=" + army + '}';
  }

  @Override
  public void attach(Observer observer) {
    this.observer = observer;
  }

  @Override
  public void notifyObservers(String note, Object... o) {

    observer.update(note, o);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    General general = (General) o;
    return Double.compare(general.getGold(), getGold()) == 0 &&
            Objects.equals(getName(), general.getName()) &&
            Objects.equals(getArmy(), general.getArmy());
  }


  enum Event {
    TRAIN("TRAINING:\n"),
    BUY("BUY:\n"),
    ATTACK("WAR:\n"),
    KILL("KILL:\n"),
    DEMOTE("DEMOTE:\n"),
    PROMOTE("PROMOTE:\n"),
    ATTACKED("WAR:\n");

    final String heading;

    Event(String heading) {
      this.heading = heading;
    }
  }

  static class Note {

    static Event event;

    static String heading(Event e) {
      StringBuilder actions = new StringBuilder();
      if (event == null) {
        event = e;
        actions.append(e.heading);
        return actions.toString();
      }
      if (!e.equals(event)) {
        actions.append(e.heading);
        event = e;
      }
      return actions.toString();
    }

    static String note(Event event, Object o) {
      StringBuilder action = new StringBuilder();
      action.append(heading(event));
      // action.append(event.getHeading());
      switch (event) {
        case BUY:
          action.append("\tBought soldier   ").append(o).append("\n");
          break;
        case TRAIN:
          action.append("\tTrained soldier, experience gained   ").append(o).append("\n");
          break;
        case ATTACK:
          action.append("\tAttacked general:").append(((General) o).getName()).append("\n");
          break;
        case ATTACKED:
          action.append("\tAttacked by general:").append(((General) o).getName()).append("\n");
          break;
        case KILL:
          action.append("\t").append(o).append(" died\n");
          break;
        case DEMOTE:
          action.append("\tDemoted soldier, experience diminished   ").append(o).append("\n");
          break;
        case PROMOTE:
          action.append("\tPromoted soldier, rank up   ").append(o).append("\n");
      }
      return action.toString();
    }
  }
}
