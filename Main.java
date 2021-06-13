import java.util.*;

class Main {

  public static void main(String[] args) {
    //Intro Part
    System.out.println();
    Scanner c = new Scanner(System.in);
    System.out.println("What is your name?");
    String name = c.nextLine();
    Adult you = new Adult(name);
    final double total = (double) (you.getCreditCardDebt() + you.getStudentLoans()) / 4;
    System.out.println("Hello " + you.getName() + "!");
    Teen naomi = new Teen("Naomi", 15);
    Child tyler = new Child("Tyler", 8);
    System.out.print("You are the single parent of two children: ");
    System.out.println(naomi.getName() + " and " + tyler.getName() + "\nAges " + naomi.getAge() + " and " + tyler.getAge() + " respectively");
    System.out.println("Your partner went to the grocery store six years ago.");
    System.out.println("You work a 7-to-4 job on only weekdays, make an income of 110,000 dollars a year, and have outstanding loans.");
    System.out.println("Credit Card Debt: " + you.getCreditCardDebt() + "; Student Loans: " + you.getStudentLoans());
    System.out.println("By the end of this year, you want to pay at least a quarter of your debts off.\nThat's " + total + " dollars, not including interest!\nYou also need to pay rent of $2,900 a month, along with $600 other monthly expenses\nGood luck with that.");
    System.out.println();
    //End of Intro

    //for day 0 (january) to day 11 (december)
    for (int day = 0; day < 12; day++) {
      you.day(day, you, naomi, tyler);
      System.out.println();
    }
    if (you.getCreditCardDebt() + you.getStudentLoans() == 75000) {
      System.out.println("You've paid off 25% of your loans!! Good job!!");
    }
    else if (you.getCreditCardDebt() + you.getStudentLoans() < 75000) {
      System.out.println("You've paid off more than 25% of your loans!! Good job!!");
    }
    else {
      System.out.println("You haven't paid off as much loans as you hoped.\nHopefully you spent the year connecting with your children, and had a good year regardless.");
      System.out.println();
    }
    System.out.println("I hope you had fun!!");


  }

}