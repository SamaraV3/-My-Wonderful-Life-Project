import java.util.*;

public class Adult extends Person {
  private int creditCardDebt;
  private int studentLoans;
  private int monthlyExpenses;
  private int monthlyIncome;
  private Scanner sc = new Scanner(System.in);

  public Adult(String name) {
    super(name, 39);
    creditCardDebt = 50000;
    studentLoans = 50000;
    monthlyExpenses = 3500;
    monthlyIncome = 9166;
  }

  public int getCreditCardDebt() {return creditCardDebt;}
  public void payCreditCardDebt(int payment) {creditCardDebt -= payment;}
  public int getStudentLoans() {return studentLoans;}
  public void payStudentLoans(int payment) {studentLoans -= payment;}
  public int getMonthlyExpenses() {return monthlyExpenses;}
  public void increaseMonthlyExpenses(int num) {monthlyExpenses += num;}
  public int getIncome() {return monthlyIncome;}
  public void increaseIncome(int num) {monthlyIncome += num;}

}