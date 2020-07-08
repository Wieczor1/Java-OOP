import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddressTest {

  @Test
  public void getStreetName() {
    Address address = new Address("Pomorska", "22", "5", "Olsztyn");
    Assert.assertEquals(address.getStreetName(), "Pomorska");

  }

  @Test
  public void setStreetName() {
    Address address = new Address("Pomorska", "22", "5", "Olsztyn");
    address.setStreetName("Garnizonowa");
    Assert.assertEquals(address.getStreetName() ,"Garnizonowa");
  }
}