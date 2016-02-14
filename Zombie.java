import java.lang.Math;

public class Zombie extends Creature {

  public Zombie () {
    super();
  }

  public Zombie (String name, int health, int strength, int agility, int luck, boolean isBot, String display) {

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
          //return "Zombie(Name:" + this.name + "|" + type + " Health:" + this.health + ")";
          return "Zombie(Name:" + this.getName() + " Health:" + this.getHealth() + ")";
  }

  public Movement movement() {

    // VERSION 1 : Zombie picks a random direction
    return Movement.values()[(int) (Math.random() * Movement.values().length)];

  }


  /* some creatures can hold an item if found */
/*  public void addItem(Item item) {

}*/

}
