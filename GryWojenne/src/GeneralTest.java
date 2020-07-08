import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GeneralTest {

  General general;
  General general1;
  Secretary secretary;

  @BeforeEach
  void setUp() {
    secretary = new Secretary();
    general = new General("Manstein",30, new CopyOnWriteArrayList<>(), secretary);
    general1 = new General("Rommel",300, new CopyOnWriteArrayList<>(), secretary);
    secretary.addGenerals(general1, general);


  }


  @Test
  void buySoldier() {
    general.buySoldier(Soldier.Rank.PRIVATE);
    assertEquals(general.getArmy().size(), 1);
  }

  @Test
  void buySoldiers() {
    general.buySoldiers(Soldier.Rank.PRIVATE, Soldier.Rank.PRIVATE);
    assertEquals(general.getArmy().size(), 2);
  }

  @Test
  void buySoldiersWithNoCash() {
    assertThrows(IllegalArgumentException.class, () -> general.buySoldiers(Soldier.Rank.PRIVATE, Soldier.Rank.CAPTAIN));
  }

  @Test
  void trainArmy() {
    general.buySoldier(Soldier.Rank.PRIVATE);
    general.buySoldier(Soldier.Rank.PRIVATE);
    general.trainArmy();
    assertEquals(general.getArmy().get(0).getExperience(), 2);
    assertEquals(general.getArmy().get(1).getExperience(), 2);

  }

  @Test
  void trainSoldiers() {
    general.buySoldier(Soldier.Rank.PRIVATE);
    general.buySoldier(Soldier.Rank.PRIVATE);
    general.trainSoldiers(general.getArmy().get(0), general.getArmy().get(1));
    assertEquals(general.getArmy().get(0).getExperience(), 2);
    assertEquals(general.getArmy().get(1).getExperience(), 2);

  }

  @Test
  void getStrength() {
    general1.buySoldiers(Soldier.Rank.PRIVATE, Soldier.Rank.MAJOR);
    general1.trainArmy();
    general1.trainArmy();
    general1.trainArmy();
    assertEquals(general1.getStrength(), 20);

  }

  @Test
  void attackGeneralWithNoArmy() {
    assertThrows(IllegalArgumentException.class, () -> general1.attackGeneral(general));

  }

  @Test
  void attackGeneral() {
    general1.buySoldiers(Soldier.Rank.PRIVATE, Soldier.Rank.MAJOR);
    general.buySoldier(Soldier.Rank.CORPORAL);
    general.trainArmy();
    general1.attackGeneral(general);
    assertEquals(general.getGold(), 9);
    assertEquals(general1.getGold(), 251);
    assertEquals(general.getArmy().get(0).getExperience(), 1);
    assertEquals(general1.getArmy().get(0).getExperience(), 2);
  }

  @Test
  void draw() {
    general1.buySoldiers(Soldier.Rank.PRIVATE, Soldier.Rank.CORPORAL);
    general.buySoldiers(Soldier.Rank.PRIVATE, Soldier.Rank.CORPORAL);
    general.attackGeneral(general1);
    assertEquals(general.getArmy().size(), 1);
    assertEquals(general1.getArmy().size(), 1);
  }

  @Test
  void promote() {
    general1.buySoldier(Soldier.Rank.PRIVATE);
    general1.trainArmy();
    general1.trainArmy();
    general1.trainArmy();
    general1.trainArmy();
    assertEquals(general1.getArmy().get(0).getExperience(), 1);
    assertEquals(general1.getArmy().get(0).getRank(), Soldier.Rank.CORPORAL);

  }

}