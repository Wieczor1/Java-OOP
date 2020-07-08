import org.junit.jupiter.api.Test;

import java.util.concurrent.CopyOnWriteArrayList;

class SecretaryTest {

  @Test
  void printReport() {
    Secretary secretary = new Secretary();
    General general = new General("XD", 100, new CopyOnWriteArrayList<>(), secretary);
    General general2 = new General("LOL", 100, new CopyOnWriteArrayList<>(), secretary);
    //secretary.addGeneral(general);
    secretary.addGenerals(general, general2);

    general.buySoldier(Soldier.Rank.PRIVATE);
    general.buySoldier(Soldier.Rank.PRIVATE);
    general.buySoldier(Soldier.Rank.PRIVATE);

    general2.buySoldier(Soldier.Rank.PRIVATE);
    general2.buySoldier(Soldier.Rank.PRIVATE);
    general2.buySoldier(Soldier.Rank.PRIVATE);



    general.attackGeneral(general2);


    //general.trainArmy();
//    System.out.println(secretary.getReport());
    System.out.println(secretary.getGeneralReport(general2));
  }
}