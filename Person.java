import java.util.*;

public class Person {
  private Scanner sc = new Scanner(System.in);
  private String name;
  private int age;
  private int happinessIndex = 100;
  private boolean isHappy = true;
  private final String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
  private ArrayList<String> events;
  private ArrayList<String> aResponses;
  private ArrayList<String> bResponses;
  private ArrayList<String> cResponses;
  private String[][] choices;
  //mood changes per person for a
  private final int[] adult_A_changes = {-5, -1, -5, -1, -10, 5, -5, 0, 1, -5, 0};
  private final int[] teen_A_changes = {0, 10, 0, 10, 10, 0, 0, 5, 10, 5, 0, 0};
  private final int[] child_A_changes = {5, 5, 0, 0, 10, 10, 0, 0, -10, 0, 5, 0};
  //mood changes per person for b
  private final int[] adult_B_changes = {5, 5, 10, 10, 5, 0, 5, 10, 10, 5, 1, 3};
  private final int[] teen_B_changes = {0, 5, 0, 10, 10, 0, 10, 0, 1, -1, 0, 1};
  private final int[] child_B_changes = {5, 10, 0, 0, 10, 0, -10, 0, 0, 10, 10, 1};
  //mood changes per person for c
  private final int[] adult_C_changes = {10, 0, -5, -10, 10, 10, 10, -10, 0, -5, -5, 0};
  private final int[] teen_C_changes = {-5, 5, 0, -10, 0, 10, 0, -5, 0, 10, 0, 10};
  private final int[] child_C_changes = {-10, 5, 0, 0, 0, -5, 5, -5, 0, -5, -10, 0};


  public Person(String name, int age) {
    this.name = name;
    this.age = age;
  }
  public String getName() {return name;}
  public int getAge() {return age;}
  public int getHappinessIndex() {return happinessIndex;}
  public boolean isHappy() {
    if (happinessIndex < 70) {isHappy = false; return isHappy;}
    else {isHappy = true; return isHappy;}
  }
  public void changeHappinessIndex(int change) {happinessIndex += change;}
  public String getMonth(int day) {return "The month is " + months[day];}
  public String happyString(String name, boolean happy) {
    if (happy) {return name + " happy! That's good!";}
    else {return name + " not happy.  :(";}
  }

  public void day(int index, Adult adult, Teen teen, Child child) {
    //The beginning is where I use all the set methods
    setEvents();
    setChoiceAnswers();
    setA_Responses();
    setB_Responses();
    setC_Responses();
    int moneyLeftAfterExpenses = adult.getIncome() - adult.getMonthlyExpenses();
    System.out.println();
    //This is the beginning of the action!!
    slowTypesetter(getMonth(index));
    waiting(3);
    System.out.println();
    System.out.println("What would you like to do?");
    System.out.println("(A) Have a conversation with your children | (B) Pay off debts | (C) Get stats | (D) Cry | (E) Exit");
    String response = sc.nextLine();
    while (!response.equalsIgnoreCase("e")) {
      if (response.equalsIgnoreCase("a")) {
        System.out.println();
        System.out.println("Which child do you wanna interact with?");
        String re = sc.nextLine();
        while (re.equalsIgnoreCase("naomi") == false && re.equalsIgnoreCase("tyler") == false) {
          System.out.println("You have no such child! Type in an actual choice!!");
          re = sc.nextLine();
        }
        //Remember to actually do this part lol
        if (re.equalsIgnoreCase("Naomi")) {
          System.out.println("(a) Scold her || (b) Chat with her || (c) Hang out with her");
          String gg = sc.nextLine();
          if (gg.equalsIgnoreCase("a")) {
            System.out.println();
            System.out.println("You scold Naomi. She rolls her eyes.");
            teen.changeHappinessIndex(-5);
          }
          else if (gg.equalsIgnoreCase("b")) {
            System.out.println();
            System.out.println("You have a chat with Naomi. She keeps yawning.");
            teen.changeHappinessIndex(5);
            adult.changeHappinessIndex(5);
          }
          else if (gg.equalsIgnoreCase("c")) {
            System.out.println();
            System.out.println("You hang out with Naomi.");
            teen.changeHappinessIndex(10);
            adult.changeHappinessIndex(10);
          }
          else {System.out.println("That's not an option.");}
        }
        if (re.equalsIgnoreCase("Tyler")) {
          System.out.println("(a) Scold him || (b) Chat with him || (c) Hang out with him");
          String g = sc.nextLine();
          if (g.equalsIgnoreCase("a")) {
            System.out.println();
            System.out.println("You scold Tyler. He gives you the stink eye.");
            child.changeHappinessIndex(-10);
          }
          else if (g.equalsIgnoreCase("b")) {
            System.out.println();
            System.out.println("You have a chat with Tyler. He rants about greek mythology.");
            child.changeHappinessIndex(10);
            adult.changeHappinessIndex(1);
          }
          else if (g.equalsIgnoreCase("c")) {
            System.out.println();
            System.out.println("You hang out with Tyler.");
            child.changeHappinessIndex(10);
            adult.changeHappinessIndex(5);
          }
          else {System.out.println("That's not an option.");}
        }
      }
      if (response.equalsIgnoreCase("b")) {
        System.out.println();
        System.out.println("You have $" + moneyLeftAfterExpenses + " left after expenses.\nHow much debt would you like to pay off");
        int r = Integer.parseInt(sc.nextLine());
        while (r > moneyLeftAfterExpenses) {
          System.out.println("You do not have enough money. Try again.");
          r = Integer.parseInt(sc.nextLine());
        }
        if (adult.getCreditCardDebt() > 0) {adult.payCreditCardDebt(r);}
        else if (adult.getStudentLoans() > 0) {
          System.out.println("You've paid off all your credit card loans!!");
          adult.payStudentLoans(r);
        }
        else {System.out.println("You've paid off all your loans somehow!!");}
      }
      if (response.equalsIgnoreCase("c")) {
        System.out.println();
        System.out.println("Name: " + adult.getName() + "\nAge: " + adult.getAge() + "\nDebts: " + (adult.getCreditCardDebt() + adult.getStudentLoans()) + "\nMoney Left After Expenses: " + moneyLeftAfterExpenses + "\n" + adult.happyString("You are", adult.isHappy()));
        waiting(1);
        System.out.println();
        System.out.println("Name: " + teen.getName() + "\nAge: " + teen.getAge() + "\n" + teen.happyString("Naomi is", teen.isHappy()));
        waiting(1);
        System.out.println();
        System.out.println("Name: " + child.getName() + "\nAge: " + child.getAge() + "\n" + child.happyString("Tyler is", child.isHappy()));
        waiting(1);
      }
      if (response.equalsIgnoreCase("d")) {adult.cry();}
      System.out.println("What would you like to do?");
      System.out.println("(A) Interact with your children | (B) Pay off debts | (C) Get stats | (D) Cry | (E) Exit");
      response = sc.nextLine();
    }
    waiting(3);
    System.out.println();

    /*This is where events happen*/
    slowTypesetter(events.get(index));
    String resp = choices(choices[index][0], choices[index][1], choices[index][2]);
    if (resp.equals("a")) {
      if (index == 1) {adult.increaseMonthlyExpenses(80);}
      if (index == 2) {adult.increaseIncome(1680);}
      slowTypesetter(aResponses.get(index));
      adult.changeHappinessIndex(adult_A_changes[index]);
      teen.changeHappinessIndex(teen_A_changes[index]);
      child.changeHappinessIndex(child_A_changes[index]);
    }
    if (resp.equals("b")) {
      if (index == 1) {adult.increaseMonthlyExpenses(80);}
      if (index == 2) {adult.increaseIncome(1800);}
      if (index == 8) {adult.increaseMonthlyExpenses(-900);}
      slowTypesetter(bResponses.get(index));
      adult.changeHappinessIndex(adult_B_changes[index]);
      teen.changeHappinessIndex(teen_B_changes[index]);
      child.changeHappinessIndex(child_B_changes[index]);
    }
    if (resp.equals("c")) {
      if (index == 1) {adult.increaseMonthlyExpenses(80);}
      if (index == 2) {adult.increaseIncome(3360);}
      slowTypesetter(cResponses.get(index));
      adult.changeHappinessIndex(adult_C_changes[index]);
      teen.changeHappinessIndex(teen_C_changes[index]);
      child.changeHappinessIndex(child_C_changes[index]);
    }
  }

  public void setEvents() {
    events = new ArrayList<String>();
    events.add("You've had a terrible day.\nYou woke up late, stepped in a pile of dog poop while running to the subway, just to realize your train wasn't running today.\nAfter calling and taking an Uber, you were ripped into by your supervisor for being late to work.\nYou spent the entire day miserable, with the distinct stench of feces around you.\nWhen you get home, all you want to do is pass out on on your bed.\nBefore you can make it to your room, Tyler runs up to you.\nHe asks for you to help him with homework. What will you do?");
    //either dont adopt, adopt and care for kitten urself, or have ur kids care for it
    events.add("While chatting with the local store clerk, you notice three little kittens in a basket.\nThe store clerk says their cat Martha gave birth to the kittens 12 weeks ago.\nThe clerk asks if you would be interested in adopting one of them.\nTyler and Naomi have been begging you for a pet cat for years.\nWhat do you do?");
    //Three options with different salaries and hours
    events.add("You're thinking of getting a second job to help pay for rent.\nYou're scrolling through the internet and find three positions you are qualified for.\nWill you:");
    //Three options: go out and cram project, stay home, go out and ask coworker to do ur
    //work for payment
    events.add("It's Naomi's birthday!\nShe was planning on spending the day with her friends but, as evidenced by her presence on the couch, her plans fell through.\nYou have an assignment due tomorrow and, like the scholar you are, haven't started it yet.\nYou planned on working on it tonight, but feel bad seeing Naomi so sad.\nWill you:");
    //choices: Central Park, Prospect Park, Brooklyn Bridge Park
    //you find $100 on the street for prospect (and share it among ur children), $50 for Brooklyn Bridge 
    //(and keep it), and dog poop for central
    events.add("It's the weekend, and you decide to go to the park with your family.\nWhich park will you go to?");
    //chocies: do project on solar system, do project on dirt, or
    //ignore promise and make Naomi help him. This will make her roll her eyes.
    events.add("Tyler has a science project due tomorrow.\nBecause he takes after you, he hasn't started it yet.\nSadly you promised to help him, and now you are scrambling for last minute ideas.\nWhat will you do?");
    //choices: stay home, have naomi look after Tyler, ask ur friend to look after them.
    events.add("Your coworker Jaiden invited you to a barbeque this weekend.\nYou're thinking of going, but need to find someone willing to look after Naomi and Tyler.\nwhat will you do?");
    //choices: go to ur room, break up the fight, or tell them to shut up.
    events.add("When you open the door to your apartment, you are greeted by your children yelling at each other.\nYou don't know why they're arguing and, frankly, you don't care. You're tired, hungry, irritable, and did I mention tired?\nSo what will you do?");
    //choices: have her walk home with tyler, convince her to get a part time job
    // or push her to do more chores around the house.
    events.add("Back to school! You're thinking about giving Naomi new responsibilities as the new school year begins.\nWhich responsibility will you give Naomi?");
    //choices: let naomi pick, let tyler pick, go as a middle aged parent
    events.add("It's almost Halloween! Tyler decided to dress up as a werewolf, while Naomi choose a simple vampire (read: all black).\nYou haven't given much thought about your costume, but now you remember there's a costume party your coworkers have planned.\nAnd you need a costume obviously! What will you do?");
    //choices: at a movie theater, at skyzone, at the park
    //skyzone will cause Tyler to sprain his ankle
    events.add("It's Tyler's birthday! He wants to spend the day with his friends!\nBut you don't want them to wreck your apartment.\nSo where will you all spend the day?");
    //choices: Will be added. Either way, naomi is skeptical that u can complete them.
    events.add("The year is finally over!\nToday is New Years Eve, and you're thinking about resolutions for the new year.\nWhat resolution will you choose?");
  }

  //displays the three different choices.
  public String choices(String a, String b, String c) {
    System.out.println();
    slowTypesetter("(a) " + a + "\n(b) " + b + "\n(c) " + c);
    System.out.println();
    String response = sc.nextLine();
    while (response.equals("a") == false && response.equals("b") == false && response.equals("c") == false) {
      System.out.println("Try again");
      response = sc.nextLine();
    }
    return response;
  }

  //puts values in the three choice arrays, which cannot be changed
  public void setChoiceAnswers() {
    choices = new String[12][3];
    //a will decrease ur happiness, but make ur kids happy
    //b will keep ur happiness the same, but make ur kids happy
    //c will make one of your kids sad (and make u happy)
    String[] a = {"Help him with his homework", "Adopt the kitten, and decide to be her main caretaker", "Take up a job with 6 hours, $10 per hour, 7 days a week", "Go out and cram your project later.", "Central Park", "Do the project on the Solar System", "Stay home", "Break up the fight", "Have her walk with Tyler to and from school", "Go as a middle aged parent", "At the movie theater", "To be more relaxed and flexible"/*both children will laught at you*/};

    String[] b = {"Direct him to the internet, where you look up all the answers to his homework.", "Adopt the kitten, with the expectation that your children will be her main caretakers.", "Take up a job with 6 hours, $15 per hour, only on weekdays", "Go out and beg your coworker to do it for you", "Prospect Park", "Do the project on Soil", "Have Naomi look after Tyler", "Go to your room", "Convince her to get a part-time job", "Let Tyler pick", "At the park", "To spend more time with your children" /*Naomi will be skeptical, but they'll both be happy*/};

    String[] c = {"Tell Naomi to help him.", "Don't adopt the kitten", "Take up a job with 8 hours, $15 per hour, 7 days a week", "Stay home", "Brooklyn Bridge Park", "Ignore your promise and get Naomi to help him", "Ask your friend to look after them", "Tell them to shut up", "Push her to do more chores around the house", "Let Naomi pick", "At skyzone", "Find a new hobby" /*Again, Naomi will be skeptical and laugh at you*/};

    for (int row = 0; row < choices.length; row++) {
      choices[row][0] = a[row];
      choices[row][1] = b[row];
      choices[row][2] = c[row];
    }
  }

  public void setA_Responses() {
    /*"Help him with his homework", "Adopt the kitten, and decide to be her main caretaker", "Take up a job with 6 hours, $10 per hour, 7 days a week", "Go out and cram your project later.", "Central Park", "Do the project on the Solar System", "Stay home", "Break up the fight", "Have her walk with Tyler to and from school", "Go as a middle aged parent", "At the movie theater", "To be more relaxed and flexible" both children will laugh at you*/
    aResponses = new ArrayList<String>();
    aResponses.add("Tyler smiles and thanks you for your help.\nYou finish in two hours, and pass out on the couch promptly afterwards.\nThe next day, he is able to do his homework by himself.");
    aResponses.add("You don't trust Tyler or Naomi enough to take care of the kitten alone.\nWhen you bring the kitten home, they both fawn over her.\nThey decide to name her Hazel.\nYour monthly expenses have increased by $80.");
    aResponses.add("Your monthly income increases by $1,680.");
    aResponses.add("You spend the entire day with Naomi, who seems happier than normal.\nYou get home at midnight, and spend the next 8 hours working on your assignment.\nYou get no sleep, and look like the undead the next day.\nAfter you hand in your assignment, your supervisor takes one look at your face and snickers.");
    aResponses.add("While walking in Central Park, you don't look where you're going.\nYou step on something mushy, and look down to see your foot in a pile of dog poop.\nNaomi and Tyler cackle like little witches at your misfortune.");
    aResponses.add("You spend the rest of the day making a diagram of the solar system, and doing research on each of the planets.\nYou also add a small section on Tyler's favorite planet, Pluto.\nHe gets compliments from his science teacher and classmates.");
    aResponses.add("You decide to stay home, as you don't want to bother Andrew on such short notice.\n(and you don't trust Naomi alone with Tyler for an entire day).\nThe next day at work, everyone is talking about the barbeque.\nAnd you feel even sadder for not going.");
    aResponses.add("Like a good parent, you break up the fight.\nYou send Naomi to her room, and Tyler to yours.\nAfter an hour of cooldown tine, they come back to the living room and apologize to each other.\nYou still don't know why they were fighting.");
    aResponses.add("Naomi sends Tyler a wicked smirk.\nTyler spends the rest of the day begging you to change your mind.\nYou probably should, but you think Naomi won't kill him.");
    aResponses.add("You decide to go as yourself, a perpetually tired middle aged parent.\nTyler giggles, and Naomi makes a jab at your grey hairs.\nOn the day of the party, you dress up in clothes that Naomi says makes you look like an old miser.\nShe says exactly that before you leave the house.\nWhen you arrive at the party, everyone keeps asking what your costume in.\nYou answer, and they always give you a weird look before moving to another topic.");
    aResponses.add("You end up going to the movie theater.\nYou realize, belatedly, how stupid it was to bring 5 hyperactive 8 years olds to a movie theater, with no other adults to help.\nNaomi laughs at your pain.");
    aResponses.add("After you say your resolution out loud, Naomi and Tyler cackle.\nNaomi says you won't survive past January.\nTyler nods in agreement.");
  }

  public void setB_Responses() {
    /*"Direct him to the internet, where you look up all the answers to his homework.", "Adopt the kitten, with the expectation that your children will be her main caretakers.", "Take up a job with 6 hours, $15 per hour, only on weekdays", "Go out and beg your coworker to do it for you", "Prospect Park", "Do the project on Soil", "Have Naomi look after Tyler", "Go to your room", "Convince her to get a part-time job", "Let Tyler pick", "At the park", "To spend more time with your children" Naomi will be skeptical, but they'll both be happy*/
    bResponses = new ArrayList<String>();
    bResponses.add("After directing Tyler to the internet, you sleep on the couch.\nThe next day, you get a call from his teacher.\nHis teacher asks why Tyler is handing in work for 7th graders.\nYou decide to teach him better cheating methods tomorrow.");
    bResponses.add("When you bring the kitten home, they both fawn over her.\nThey decide to name her Hazel.\nWhen you tell them they are responsible for taking care of Hazel, Naomi immediately looks up how to care for kittens.\nYour monthly expenses have increased by $80.");
    bResponses.add("Your monthly income increases by $1,800");
    bResponses.add("You beg Taliyah to do your assignment for you.\nAfter you explain the situation, she agrees (and berates you for your procrastinating ways).\nThe next day you slide a $100 Dunkin' Donuts gift card into her hands.\nA week later, your supervisor compliments you on your work, and how much you've improved.\nYou feel bittersweet.");
    bResponses.add("While walking around Prospect park, you see two $50 bills on the ground.\nNo one is nearby, so you quickly pick up the bills and give one to each of your kids.\nThey both smile and whisper thanks, sliding the money into their pockets.");
    bResponses.add("You decide to do the project on soil.\nMore specifically, erosion.\nIt's easy, but not that impressive.\nTyler finds it boring.");
    bResponses.add("Against your better judgement, you let Naomi babysit Tyler.\nThe younger's face morphs into fear, as he begs you to change your mind.\nYou don't.\nThe day of the barbeque, you constantly worry about them.\nWhen you get back home, the apartment is in one piece.\nHowever, Tyler is sitting and staring into the distance.\nAnd Naomi is laughing at his expression.");
    bResponses.add("You go to your room.\nAfter 30 minutes the arguing stops.\nYou fall asleep.");
    bResponses.add("You convince Naomi to get a part time job.\nShe manages to snag a job as a cashier at Target.\nShe gets to work 5 days a week, $15 per hour, 3 hours every day.\nLike the angel she is, she says she'll use her paycheck to pay for her expenses, rather than making you do so.\nYour monthly expenses decrease by 900!!");
    bResponses.add("You let Tyler pick.\nHe chooses a cowboy costume.\nYou go to the party in this costume and get compliments from your coworkers.\nYou're shocked that Tyler choose something good.");
    bResponses.add("Tyler and his friends spend the day running around the park\nNaomi ends up pushing random children at the park on the swings.\nShe doesn't seem bothered though.\nYou spend the day watching over your charges.");
    bResponses.add("Naomi gives you a skeptical look, but smiles.\nTyler says that you made a good resolution.");
  }

  public void setC_Responses() {
    /*"Tell Naomi to help him.", "Don't adopt the kitten", "Take up a job with 8 hours, $15 per hour, 7 days a week", "Stay home", "Brooklyn Bridge Park", "Ignore your promise and get Naomi to help him", "Ask your friend to look after them", "Tell them to shut up", "Push her to do more chores around the house", "Let Naomi pick", "At skyzone", "Find a new hobby" Again, Naomi will be skeptical and laugh at you*/
    cResponses = new ArrayList<String>();
    cResponses.add("You tell Naomi to help him.\nShe looks at you in betrayal, but doesn't argue.\nThey finish the work quickly, though you suspect she did it for him.");
    cResponses.add("You don't adopt the kitten.\nYou also don't tell Naomi and Tyler about this instance.\nHowever, on your next visit with both of them, there's still one kitten in the store.\nThey beg and beg and, because you're weak, you give in.\nThey decide to name her Hazel.\nYour monthly expenses have increased by $80.");
    cResponses.add("Your monthly income has increased by $3,360.");
    cResponses.add("You decide to stay home and finish your assignment.\nYou feel guilty the entire week, even when your supervisor compliments you on your work.\nAlso, Naomi spends less time talking to her \"friends\"");
    cResponses.add("You decide to go to Brooklyn Bridge Park. While walking around the park, you spot a $50 dollar bill on the floor.\nYou pocket it before anyoen but your children see.\nLater they says you should share it with them.\nYou say \"Finder's keepers\"");
    cResponses.add("You tell Naomi to help him.\nShe complains until you mention the first place winner of the city science fair gets $500.\nShe then puts her chemistry class to use, makign a project about different chemical reactions.\nThey win first place!\nShe keeps half of the money for herself, and gives the other half to Tyler.\nShe then brags about being the \"Best sister in the world\" for the rest of the week.");
    cResponses.add("You ask Andrew to look after them.\nHe does so without complaint, and you have fun at the barbeque.\nAfterwards, Andrew gushes about how well behaved your children were.\nYou snicker.");
    cResponses.add("You tell them to shut up.\nStupid decision, as they band together to attack you instead of each other.\nYou leave the living room feeling worse about yourself.\nAt least they stopped fighting.");
    cResponses.add("You push Naomi to do more chores around the house.\nShe rolls her eyes and says she will.\nA week later, with a pile of unwashed dishes in the sink, you remember she's a habitual liar.");
    cResponses.add("You let Naomi pick.\nYou have second thoughts as she cackles and scrolls through Party City.\nShe suggests you dress up as a Crusade Warrior.\nYou do so, because you stick to your word (and are too lazy to think of another costume.\nAt the party, you are the subject of laughter and teasing.");
    cResponses.add("You decide to go to skyzone.\n All is going well until 30 minutes before you plan to leave.\nAt that moment, Tyler sprains his ankle on the trampoline.\nHe bawls, and you regret not going to the park.");
    cResponses.add("Naomi looks skeptical, but at least they don't laugh at you.");
  }

  public void cry() {
    int max = 10;
    int min = 2;
    int hours = (int) ((Math.random() * (max - min)) + min);
    System.out.println("You cried for " + hours + " hours.");
    if (hours % 2 == 0) {System.out.println("There there");}
    else {System.out.println("Pat pat");}
    happinessIndex += 5;
  }

  public void rollEyes() {System.out.println("You roll your eyes.");}

  public static void slowTypesetter(String b) {
    for (int i = 0; i < b.length(); i++) {
      System.out.print(b.substring(i, i + 1));
      try {Thread.sleep(64);}
      catch (Exception e) {System.out.println(e);}
    }
  }

  public static void waiting(int a) {
    try {Thread.sleep(a * 1000);}
    catch (Exception e) {System.out.println(e);}
  }

}