import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ObjectIOTest {


    General general;
    General general1;
    Secretary secretary;

    @BeforeEach
    void setUp() {
        secretary = new Secretary();
        general = new General("Manstein",300, new CopyOnWriteArrayList<>(), secretary);
        general1 = new General("Rommel",300, new CopyOnWriteArrayList<>(), secretary);
        secretary.addGenerals(general1, general);


    }

  @Test
  void writeAndRead() {
    general.buySoldiers(Soldier.Rank.CAPTAIN, Soldier.Rank.PRIVATE, Soldier.Rank.CORPORAL);
    System.out.println(general);
    ObjectIO.write("test.txt", general);
    general1 = (General) ObjectIO.read("test.txt");
    System.out.println(general1);
    assertEquals(general,general1);
  }
}
