import java.util.*;

public class Teen extends Person {

  public Teen(String name, int age) {
    super(name, age);
  }

  public void cry() {
    System.out.println(this.getName() + " cries.");
    this.changeHappinessIndex(5);
  }

}