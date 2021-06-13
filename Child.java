import java.util.*;

public class Child extends Person {

  public Child(String name, int age) {
    super(name, age);
  }

  public void cry() {
    if (this.getAge() > 10) {
      System.out.println(this.getName() + " cries. Boo hoo");
      this.changeHappinessIndex(5);
    }
    else {
      System.out.println(this.getName() + " cries. Wahh Wahh");
      this.changeHappinessIndex(-5);
    }
  }

}