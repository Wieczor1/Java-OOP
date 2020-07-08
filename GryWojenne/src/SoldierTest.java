import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SoldierTest {

  General general;
  Soldier soldier;
  Secretary secretary;

  @BeforeEach
  void setUp() {
    secretary = new Secretary();
    general = new General("Manstein", 300, new CopyOnWriteArrayList<>(), secretary);
    secretary.addGenerals(general);
    soldier = new Soldier(Soldier.Rank.MAJOR);
    general.buySoldier(Soldier.Rank.PRIVATE);
  }

  @Test
  void getStrength() {
      general.trainArmy();
      general.trainArmy();
      general.trainArmy();
      general.trainArmy();
      general.getArmy().get(0).getStrength();
      assertEquals(2, general.getArmy().get(0).getStrength()); // 5
  }


  @Test
  void promote() {
    soldier.promote();
    soldier.promote();
    soldier.promote();
    soldier.promote();
    soldier.promote();
    soldier.promote();
    assertEquals(soldier.getRank(), Soldier.Rank.MAJOR);
  }
}
