import java.util.ArrayList;
import java.util.*;

public class LastManStanding {

  public static void main(String[] args) {
    //ArrayList<Object> LastManStanding = new ArrayList<Object>();
    ArrayList<Item> items = new ArrayList<Item>();
    ArrayList<Creature> creatures = new ArrayList<Creature>();
    ArrayList<Cell> cells = new ArrayList<Cell>();
    ArrayList<Cell> creatureCells = new ArrayList<Cell>();
    ArrayList<String> deathLog = new ArrayList<String>();

    int turnNumber = 1;
    int numRows = 10;
    int numCols = 10;
    int numCreatures = 5;

    int testRuns = 0;


          //bad command line argument checking
          if (args.length > 0) {
                  numRows = Integer.parseInt(args[0]);
                  numCols = Integer.parseInt(args[1]);
                  numCreatures = Integer.parseInt(args[2]);
          }


          // we need to create our grid (dungeon) which we took
          // as command line args or use a default
          for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
              // add a blank cell that will be displayed as " "
              cells.add(new Cell(r, c, " "));
            }
          }
          //System.out.println("Elements of ArrayList of Cell Type: " + cells);

          /* test --- add some creatures */
          cells.set(XYCoordinate.xyCoordToList(0,0, numRows), new Cell(0,0, new Goblin("Goblin1", 100, 90, 20, 10, true, "G")));
          cells.set(XYCoordinate.xyCoordToList(0,1, numRows), new Cell(0,1, new Goblin("Goblin2", 100, 30, 70, 10, true, "G")));
          cells.set(XYCoordinate.xyCoordToList(3,8, numRows), new Cell(3,8, new Goblin("Goblin3", 100, 60, 89, 10, true, "G")));
          cells.set(XYCoordinate.xyCoordToList(1,4, numRows), new Cell(1,4, new Human("Human1", 100, 60, 35, 10, true, "H")));
          cells.set(XYCoordinate.xyCoordToList(2,2, numRows), new Cell(2,2, new Zombie("Zombie1", 100, 60, 56, 10, true, "Z")));
          cells.set(XYCoordinate.xyCoordToList(5,2, numRows), new Cell(5,2, new Zombie("Zombie2", 100, 60, 11, 10, true, "Z")));


          /**************/
          /* USER INPUT */
          /**************/
          int selection = -1;
          int level = 1;
          int skillPoints = 100;
          int strength;
          int agility;
          int luck;
          boolean bot = false;
          Scanner scanner;

          // title
          System.out.println("ooooo        ooo        ooooo  .oooooo..o ");
          System.out.println("`888'        `88.       .888' d8P'    `Y8 ");
          System.out.println(" 888          888b     d'888  Y88bo.      ");
          System.out.println(" 888          8 Y88. .P  888   `\"Y8888o.  ");
          System.out.println(" 888          8  `888'   888       `\"Y88b ");
          System.out.println(" 888       o  8    Y     888  oo     .d8P ");
          System.out.println("o888ooooood8 o8o        o888o 8\"\"88888P'  ");

          // create user character
          System.out.printf("What is your Characters name ? ");
          scanner = new Scanner(System.in);
          String name = scanner.nextLine();

          System.out.printf("\n(Assign %d Skill points to ...", skillPoints);
          System.out.printf("\n\tAgility (chance to strike first and dodge attack)");
          System.out.printf("\n\tStrength (greater damage on strike)");
          System.out.printf("\n\tAgility (chance to do extra Damage/Agility)\n");

          System.out.printf("\n(%s Skill Points Remaining) How many skill points to Agility ? ", skillPoints);
          scanner = new Scanner(System.in);
          agility = scanner.nextInt();
          skillPoints -= agility;

          if (skillPoints > 0) {
            System.out.printf("\n(%s Skill Points Remaining) How many skill points to Strength ? ", skillPoints);
            scanner = new Scanner(System.in);
            strength = scanner.nextInt();
            skillPoints -= strength;
          } else {
            strength = skillPoints; // 0
          }

          // remainder to last skill
          luck = skillPoints;
          System.out.printf("\nName: %s, Agility: %d, Strength: %d, Luck: %s", name, agility, strength, luck);

          System.out.printf("\nWould you like to control this character ? ");
          scanner = new Scanner(System.in);
          String answer = scanner.nextLine();
          if (answer.toUpperCase().equals("Y")) {
            bot = false;
            System.out.printf("\nDie Well.");
          } else {
            bot = true;
          }

          System.out.printf("\nPress Enter to start.");
          scanner = new Scanner(System.in);
          scanner.nextLine();

          //cells.set(XYCoordinate.xyCoordToList(6,9, numRows), new Cell(6,9, new Human(name, 100, strength, agility, luck, false, "H")));
          cells.set(XYCoordinate.xyCoordToList(6,9, numRows), new Cell(6,9, new Human(name, 100, strength, agility, luck, bot, "@")));


          /*******************/
          /* CORE LOGIC LOOP */
          /*******************/
          XYCoordinate xy = new XYCoordinate();
          int listIndex;
          char c;

          do {
            //System.out.println("CORE START");
            displayGrid(numRows, numCols, cells);

            // make sure we dump old entries
            creatureCells.clear();

            // copy out our Creature Cells ONLY and then we will sort them by AGILITY and use this to determine which Cells move first
            // create an ArrayList of ONLY those cells with creatures
            for (Cell cellElement : cells) {
              if (!cellElement.getIsEmpty()) {
                if (cellElement.getCreature().getIsAlive()) {
                    //System.out.println("Adding ALIVE cell for main loop " + cellElement.toString());
                  creatureCells.add(cellElement);
                }
              }
            }

            // now have ArrayList of ONLY Creatures who are still ALIVE. No empty Cells

            // now sort these cells by AGILITY
            Collections.sort(creatureCells, new CellChainedComparator(new CellAgilityComparator(), new CellXYComparator()));

            //System.out.println(creatureCells.toString());

            /* now we have things in the right order for MOVEMENT Phase (agility first) */
            /* move through the List of creatures and make moves and attacks if required */
            for (Cell cellElement : creatureCells) {

              if (cellElement.getCreature().getIsAlive()) {
                // This Creatue is Alive - Is it a PLayer Controlled Creature ?
                if (!cellElement.getCreature().getIsBot()) {
                  // ask user for a Move.
                  System.out.printf("\n%s (Agility: %d, Strength: %d, Luck: %s). Your Health is %d",
                    cellElement.getCreature().getName(), cellElement.getCreature().getAgility(), cellElement.getCreature().getStrength(),
                    cellElement.getCreature().getLuck(), cellElement.getCreature().getHealth());

                  do { // discover a valid move for this cell
                    System.out.printf("\nPlease make your move ['U' 'D' 'L' 'R' 'H'] : ");
                    scanner = new Scanner(System.in);
                    c = Character.toUpperCase(scanner.next(".").charAt(0));

                    //System.out.printf("YOU selected movement of %s\n", c);

                    xy = cellElement.attemptManualMove(charToCharacterMove(c));

                    // make sure it is valid / in bounds
                  } while (xy.getX() < 0 || xy.getY() < 0 || xy.getX() >= numRows || xy.getY() >= numCols); // what about walls !?

                } else {

                  do { // discover a valid move for this cell
                    xy = cellElement.attemptMove();
                    //System.out.println("Cell: " + cellElement.getXPos() + "|" + cellElement.getYPos() + " looking to move to " + xy.getX() + "|" + xy.getY());

                    // make sure it is valid / in bounds
                  } while (xy.getX() < 0 || xy.getY() < 0 || xy.getX() >= numRows || xy.getY() >= numCols); // what about walls !?

                }
                //System.out.println("MOVED\n");

                // get the cell/index in the ArrayList where this Element wishes to move.
                listIndex = XYCoordinate.xyCoordToList(xy.getX(),xy.getY(), numRows);

                // MUST RESOLVE CONFLICTS

                /* see what is in the new Cell. */
                // Is it an EMPTY Cell ?
                if (cells.get(listIndex).getIsEmpty()){
                  //System.out.printf("Cell @ (%d,%d) moving to is Empty\n", xy.getX(),xy.getY());

                  // it's just an empty cell so we can swap it
                  cells.get(listIndex).setXPos(cellElement.getXPos());
                  cells.get(listIndex).setYPos(cellElement.getYPos());

                  // update the current Cells XY coordinates
                  cellElement.setXPos(xy.getX());
                  cellElement.setYPos(xy.getY());

                } else if (cells.get(listIndex).getCreature().getIsAlive()) {
                    if ( cells.get(listIndex).getXPos() == cellElement.getXPos() && cells.get(listIndex).getYPos() == cellElement.getYPos() ) {
                        //System.out.printf("%d|%d vs %d|%d\n", cells.get(listIndex).getXPos(), cells.get(listIndex).getYPos(), cellElement.getXPos(),  cellElement.getYPos());
                        //System.out.printf("Looks like this Cell did not move\n");
                    } else {
                      /************************
                      / RESOLVE CONFLICT/
                      /************************/
                      while (cellElement.getCreature().getIsAlive() && cells.get(listIndex).getCreature().getIsAlive()) {
                        cellElement.getCreature().attack(cells.get(listIndex).getCreature());
                      }
                      // add any deaths to our DeathLog array List
                      if (!cellElement.getCreature().getIsAlive()) {
                        deathLog.add(String.format("%s Died on turn %d at the hands of %s",
                          cellElement.getCreature().getName(), turnNumber, cells.get(listIndex).getCreature().getName()));
                      }
                      if (!cells.get(listIndex).getCreature().getIsAlive() ) {
                        deathLog.add(String.format("%s Died on turn %d at the hands of %s",
                          cells.get(listIndex).getCreature().getName(), turnNumber, cellElement.getCreature().getName()));
                      }
                    }
                } else {
                  // IF WE ARE HERE IT MEANS WE ARE FIGHTING WITH SOMEONE LATER IN THE ARRAYLIST WHO DIED EARLIER IN LOOP
                  // SO JUST IGNORE
                }
              }

            }

              // sort our arrrayList back by XY so we can easily display it
              Collections.sort(cells, new CellChainedComparator(new CellXYComparator()));

              turnNumber++;

              // clear the console
              //??

            } while (creatureCells.size() > 1); ////****************************************???/

            // final display @ End Game
            displayGrid(numRows, numCols, cells);

            for (String element : deathLog) {
              System.out.println(element);
            }

            System.out.println("\noooooo   oooooo     oooo ooooo ooooo      ooo ooooo      ooo oooooooooooo ooooooooo.   ");
            System.out.println(" `888.    `888.     .8'  `888' `888b.     `8' `888b.     `8' `888'     `8 `888   `Y88. ");
            System.out.println("  `888.   .8888.   .8'    888   8 `88b.    8   8 `88b.    8   888          888   .d88' ");
            System.out.println("   `888  .8'`888. .8'     888   8   `88b.  8   8   `88b.  8   888oooo8     888ooo88P'  ");
            System.out.println("    `888.8'  `888.8'      888   8     `88b.8   8     `88b.8   888    \"     888`88b.    ");
            System.out.println("     `888'    `888'       888   8       `888   8       `888   888       o  888  `88b.  ");
            System.out.println("      `8'      `8'       o888o o8o        `8  o8o        `8  o888ooooood8 o888o  o888o ");

            System.out.printf("\n...in %d Turns.", turnNumber);

            // print off the winner if there was one.
            if (creatureCells.size() > 0) {
              System.out.println(creatureCells.get(0).getCreature().toString());
            } else {
              System.out.println("Everybody died.");
            }
  }

  /* just a method to run through an arraylist we assume maps out a grid */
  public static void displayGrid(int numRows, int numCols, ArrayList<Cell> cells) {

    for (int r = 0; r < numRows; r++) {
      System.out.printf("\n");
      for (int c = 0; c < numCols; c++) {
        if (!cells.get(XYCoordinate.xyCoordToList(r,c, numRows)).getIsEmpty()) {
          if (cells.get(XYCoordinate.xyCoordToList(r,c, numRows)).getCreature().getIsAlive()) {
            System.out.printf("|%s|", cells.get(XYCoordinate.xyCoordToList(r,c, numRows)).getDisplay());
          } else {
              System.out.printf("| |"); // DEAD
          }

        } else {
          System.out.printf("|%s|", cells.get(XYCoordinate.xyCoordToList(r,c, numRows)).getDisplay());
        }
      }
    }
    System.out.printf("\n");

  }

  public static Creature.Movement charToCharacterMove (char c) {
    Creature.Movement cm = Creature.Movement.UP;
    switch(c) {
      case 'U':
        cm = Creature.Movement.UP;
        break;

      case 'D':
        cm = Creature.Movement.DOWN;
        System.out.println("DOWN");
        break;

      case 'L':
        cm = Creature.Movement.LEFT;
      break;

      case 'R':
        cm = Creature.Movement.RIGHT;
      break;

      case 'H':
        cm = Creature.Movement.HOLD;
      break;
    }
    return cm;
  }

}
