import java.io.*;

public class ObjectIO {
  static boolean write(String filename, Object object) {
    try {
      FileOutputStream f = new FileOutputStream(new File(filename));
      ObjectOutputStream o = new ObjectOutputStream(f);
      o.writeObject(object);
      o.close();
      f.close();
      return true;
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
  }

  static Object read(String filename) {
    try {
      FileInputStream f = new FileInputStream(new File(filename));
      ObjectInputStream o = new ObjectInputStream(f);
      Object obj = o.readObject();
      o.close();
      f.close();
      return obj;
    } catch (FileNotFoundException e) {
      System.out.println("File not found");
    } catch (ClassNotFoundException | IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
