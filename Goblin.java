public class Goblin extends Creature {

  public Goblin () {
    super();
  }

  public Goblin (String name, int health, int strength, int agility, int luck, boolean isBot, String display) {

    this.setName(name);
    this.setHealth(health);
    this.setStrength(strength);
    this.setAgility(agility);
    this.setLuck(luck);
    this.setDisplay(display);

    this.setIsBot(isBot); // allow any creature and multiple creatures to be user controlled.

    if (health > 0) {
      this.setIsAlive(true);
    }
    else {
      this.setIsAlive(false);
    }
  }

  public String toString() {
          //return "Goblin(Name:" + this.name + "|" + type + " Health:" + this.health + ")";
          return "Goblin(Name:" + this.getName() + " Health:" + this.getHealth() + ")";
  }

  // can be used to get this creature to move in a certain way
  public void moveBias(Movement m) {
    
  }

  //public Movement movement() {
//    return Creature.Movement.UP;
  //}


  /* some creatures can hold an item if found */
/*  public void addItem(Item item) {

}*/

}
