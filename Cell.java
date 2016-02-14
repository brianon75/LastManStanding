import java.util.ArrayList;

public class Cell implements Comparable <Cell>{

  private int xPos;
  private int yPos;

  // TODO - replace xPos & yPos witjh XYCoordinate Class

  private Creature creature;
  private ArrayList<Item> items;

  private boolean isEntrance;
  private boolean isExit;
  private boolean isWall;

  private boolean isEmpty;

  private String display;

  public Cell () {

  }

  public Cell (int x, int y, String display) {

    // blank cell
    this.xPos = x;
    this.yPos = y;
    this.display = display;

    this.isEmpty = true;
  }


  public Cell (int x, int y, Creature creature) {
    this.xPos = x;
    this.yPos = y;

    this.creature = creature;
    this.display = creature.getDisplay();

    this.isExit = false;
    this.isEntrance = false;
    this.isWall = false;
  }

  public Cell (int x, int y, boolean isEntrance, boolean isExit, boolean isWall, String display) {

    // not a creatue
    this.xPos = x;
    this.yPos = y;

    // make sure it's not invalid
    if (isExit && isEntrance && isWall) {
      // just make it an exit
      this.isExit = true;
    }

    this.display = display;
  }

  public int getXPos () {
    return this.xPos;
  }

  public int getYPos () {
    return this.yPos;
  }

  public void setXPos(int x) {
    this.xPos = x;
  }

  public void setYPos(int x) {
    this.yPos = x;
  }

  public Creature getCreature() {
    return this.creature;
  }

  public boolean getIsEmpty() {
    return this.isEmpty;
  }

  public String getDisplay() {
    return this.display;
  }

  //@Override
  public int compareTo(Cell cell) {
    // sort by xPos and then yPos so we replacate a grid (for those we have)

          // A little more robust. ensure the supplied Cell is not null
          if (cell == null) {
                  return -1;
          }

          // Quick check to see if the tracks are equal. i.e Same object
          if (this == cell) {
                  return 0;
          }

          if (this.xPos != cell.getXPos()) {
            return this.xPos - cell.getXPos();
          } else {
            return this.yPos - cell.getYPos();
          }

  }

  public String toString() {
          //return "Creature(Name:" + this.name + "|" + type + " Health:" + this.health + ")";
          return "Cell(" + this.xPos + "," + this.yPos + " | " + this.display + ")";
  }

  // public enum Movement {UP, DOWN, LEFT, RIGHT};
  //public boolean move(int rows, int cols, int gridX, int gridY) {
  //public boolean move(Creature.Movement bias, int percentBias) {
  public XYCoordinate attemptMove() {
    Creature.Movement m = Creature.Movement.UP;

    XYCoordinate xy = new XYCoordinate(this.xPos, this.yPos);

    if (this.creature == null) {
      //System.out.println("EMPTY SPACE - NO MOVE");

    } else if (this.creature.getIsAlive()) {
      m = this.creature.movement();

      //System.out.println("Will attempt to move " + this.toString() + " " + m);
      switch (m) {
        case UP :
        xy.setX(this.xPos - 1);
        break;
        case DOWN :
        xy.setX(this.xPos + 1);
        break;
        case LEFT :
        xy.setY(this.yPos - 1);
        break;
        case RIGHT :
        xy.setY(this.yPos + 1);
        break;
        case HOLD :
        break;
      }


    }
    return xy;

  }

  public XYCoordinate attemptManualMove(Creature.Movement m) {

    XYCoordinate xy = new XYCoordinate(this.xPos, this.yPos);

    if (this.creature == null) {
      //System.out.println("EMPTY SPACE - NO MOVE");

    } else if (this.creature.getIsAlive()) {
      //m = this.creature.movement();

      //System.out.println("Will attempt to move " + this.toString() + " " + m);
      switch (m) {
        case UP :
        xy.setX(this.xPos - 1);
        break;
        case DOWN :
        xy.setX(this.xPos + 1);
        break;
        case LEFT :
        xy.setY(this.yPos - 1);
        break;
        case RIGHT :
        xy.setY(this.yPos + 1);
        break;
        case HOLD :
        break;
      }


    }
    return xy;

  }
}
