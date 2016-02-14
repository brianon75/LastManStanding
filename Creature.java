public class Creature {

  //public enum CreatureType {GOBLIN, ORC, HUMAN};
  public enum Movement {UP, DOWN, LEFT, RIGHT, HOLD};

  //private CreatureType type;
  private String name;
  private int health;
  private int strength;
  private int agility; // Or agility 75 v agility 90. random # 0-75 v # 0-90  goes first.
  private int luck;

  // TODO
  private int kills;
  private int damageDone;
  private int encounters;

  //private int movement;

  private boolean isAlive;
  private boolean isBot;

  private String display;

  public Creature () {
    //this.agility = 0;

  }

  public Creature (String name, int health, int strength, int agility, int luck, boolean isBot, String display) {

    //this.type = ct;
    this.name = name;
    this.health = health;
    this.strength = strength;
    this.agility = agility;
    this.luck = luck;

    this.isBot = isBot; // allow any creature and multiple creatures to be user controlled.
    this.isAlive = true;

    this.display = display;


  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public void setDisplay(String display) {
    this.display = display;
  }

  public String getDisplay() {
    return this.display;
  }

  public int getHealth() {
    return this.health;
  }
  public void setHealth(int health) {
    this.health = health;
  }

  public int getStrength() {
    return this.strength;
  }
  public void setStrength(int strength) {
    this.strength = strength;
  }

  public int getAgility() {
    return this.agility;
  }
  public void setAgility(int agility) {
    this.agility = agility;
  }

  public int getLuck() {
    return this.luck;
  }
  public void setLuck(int luck) {
    this.luck = luck;
  }

  public boolean getIsBot() {
    return this.isBot;
  }
  public void setIsBot(boolean isBot) {
    this.isBot = isBot;
  }

  public boolean getIsAlive() {
    return this.isAlive;
  }
  public void setIsAlive(boolean isAlive) {
    this.isAlive = isAlive;
  }


  public String toString() {
          //return "Creature(Name:" + this.name + "|" + type + " Health:" + this.health + ")";
          return "Creature(Name:" + this.name + " Health:" + this.health + ")";
  }

  // returns the damage it took and updates it's own health and dies if required
  public int attack(Creature attacker) {

    // PASS 1 - Strength 1 v Strength 2
    /*this.health -= attacker.strength;
    attacker.health -= this.strength;*/

    /*System.out.println("BEFORE ATTACK-----");
    System.out.println(attacker.toString());
    System.out.println(this.toString());*/

    /* PASS 2 - Strength/Agility */
    /*int attackerAgilityScore = randomWithRange(0, attacker.agility);
    int defenderAgilityScore = randomWithRange(0, this.agility);
    int attackerStrengthScore = randomWithRange(0, defender.strength)
    int defenderStrengthScore = randomWithRange(0, this.strength)*/

    /* PASS 2A - Strength/Agility/Luck */
    /* luck added to allow for better scores */
    int attackerAgilityScore = randomWithRange(attacker.luck/2, attacker.agility);
    int attackerStrengthScore = randomWithRange(attacker.luck/2, attacker.strength);

    int defenderAgilityScore = randomWithRange(this.luck/2, this.agility);
    int defenderStrengthScore = randomWithRange(this.luck/2, this.strength);

    System.out.printf("Attacker (%s) Agility score for this round (%d) VS Defender (%s) Agility score for this round (%d)\n",
                      attacker.getName(), attackerAgilityScore, this.name, defenderAgilityScore);

    // determine who goes first
    if (defenderAgilityScore > attackerAgilityScore) {
      System.out.printf("Defender (%s) goes first.\n", this.name);

      attacker.takeDamage(defenderStrengthScore, defenderAgilityScore);

      // make sure he wasn't killed
      if (attacker.getIsAlive()) {
        this.takeDamage(attackerStrengthScore, attackerAgilityScore);
      } else {
        System.out.printf("Defender (%s) survived with (%d) health remaining.\n", this.name, this.health);
      }

    } else { // if it's a tie then the attacker gets the advantage

      System.out.printf("Attacker (%s) goes first.\n", attacker.name);

      this.takeDamage(attackerStrengthScore, attackerAgilityScore);

      // make sure he wasn't killed
      if (this.getIsAlive()) {
        attacker.takeDamage(defenderStrengthScore, defenderAgilityScore);
      } else {
        System.out.printf("Attacker (%s) survived with (%d) health remaining.\n", attacker.getName(), attacker.getHealth());
      }
    }



/*
    // see if either one died
    if (this.health <= 0) {
      this.isAlive = false;
      System.out.printf("%s(%s) was killed by %s(%s)\n", this.name, this.display, attacker.name, attacker.display);
    }

    if (attacker.health <=0) {
      attacker.isAlive = false;
      System.out.printf("%s(%s) was killed by %s(%s)\n", attacker.name, attacker.display, this.name, this.display);
    }*/

    /*System.out.println("AFTER ATTACK-----");
    System.out.println(attacker.toString());
    System.out.println(this.toString());*/

    // logic to determine the attack result
    return -1;
  }

  public Movement movement() {
    // VERSION 1 : Pick a random direction
    return Movement.values()[(int) (Math.random() * Movement.values().length)];
  }

  public int takeDamage(int strength, int agility) {
    int damageTaken = 0;

    // see how much damage was taken based on an attackers strength and their agility for this hit.
    damageTaken = randomWithRange(this.luck/2, strength);
    this.health -= damageTaken;

    if (this.health <= 0) {
      System.out.printf("(%s) took (%d) damage and died.\n", this.name, damageTaken);
      this.isAlive = false;
    } else {
      System.out.printf("(%s) took (%d) damage.\n", this.name, damageTaken);
    }

    return damageTaken;
  }

  /* some creatures can hold an item if found */
/*  public void addItem(Item item) {

}*/

  public int randomWithRange(int min, int max)  {
     int range = (max - min) + 1;
     return (int)(Math.random() * range) + min;
  }

  // can be used to get this creature to move in a certain way
  public void moveBias(Movement m) {

  }

}
