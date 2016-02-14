import java.util.ArrayList;
import java.util.*;

public class DungeonGrid {

  public static void main(String[] args) {

          //ArrayList<Object> dungeonGrid = new ArrayList<Object>();
          ArrayList<Item> items = new ArrayList<Item>();
          ArrayList<Creature> creatures = new ArrayList<Creature>();
          ArrayList<Cell> cells = new ArrayList<Cell>();
          ArrayList<Cell> creatureCells = new ArrayList<Cell>();
          int numRows = 3;
          int numCols = 3;
          int numCreatures = 5;

          //bad command line argument checking
          if (args.length > 0) {
                  numRows = Integer.parseInt(args[0]);
                  numCols = Integer.parseInt(args[1]);
                  numCreatures = Integer.parseInt(args[2]);
          }


          // we need to create our grid (dungeon) which we took
          // as command line args or use a default
          int size = numRows * numCols;

          System.out.printf("We have %d Cells in the dungeon and %d creatures.\n", size, numCreatures);




          //dungeonGrid.add("Test");
          //dungeonGrid.add(1);
          //dungeonGrid.add("Two");
          for (int i = 0; i < numCreatures; i++) {

          }
          /*creatures.add(new Creature(Creature.CreatureType.GOBLIN, "Gobby", 100, 25, 90, false));
          creatures.add(new Creature(Creature.CreatureType.ORC, "Orcy", 100, 90, 10, false));
          creatures.add(new Creature(Creature.CreatureType.HUMAN, "Brian", 100, 60, 70, true));*/

          /*creatures.add(new Goblin("Gob1", 100, 60, 70, true, "G"));
          creatures.add(new Creature("Gob2", 100, 90, 10, false, "C"));
          creatures.add(new Goblin("Gob3", 100, 90, 10, false, "G"));

          System.out.println("Elements of ArrayList of String Type: " + creatures);

          while (creatures.get(0).getIsAlive() == true && creatures.get(1).getIsAlive() == true) {
            creatures.get(0).attack(creatures.get(1));
          }

          System.out.println("Elements of ArrayList of String Type: " + creatures);*/

          /*Cell c1 = new Cell(0,0, new Creature("Gob-00", 100, 90, 10, false), "G");
          Cell c2 = new Cell(0,2, new Goblin("Gob-01", 100, 90, 10, false), "G");
          Cell c3 = new Cell(2,0, new Goblin("Gob-20", 100, 90, 10, false), "G");
          Cell c4 = new Cell(0,1, new Goblin("Gob-00", 100, 90, 10, false), "G");
          Cell c5 = new Cell(1,0, new Goblin("Gob-10", 100, 90, 10, false), "G");

          cells.add(c1);
          cells.add(c2);
          cells.add(c3);
          cells.add(c4);
          cells.add(c5);
          System.out.println("Elements of ArrayList of Cell Type: " + cells);

          // sort our cells by 2d grid
          Collections.sort(cells);

          System.out.println("Elements of ArrayList of Cell Type: " + cells);*/


          /* create an enry point on grid */

          /* create and exit point */

          /* place our creatures @ positions in our grid. */

          /* place some items // store these in our ArrayList items */
          /*********************************************************/


          /* create a grid of basic cells */
          for (int r = 0; r < numRows; r++) {
            //cells.add(r, c, " ");
            for (int c = 0; c < numCols; c++) {
              cells.add(new Cell(r, c, " "));
            }
          }
          //System.out.println("Elements of ArrayList of Cell Type: " + cells);

          /* test --- add some creatures */
          cells.set(XYCoordinate.xyCoordToList(0,0, numRows), new Cell(0,0, new Goblin("Gob-00", 100, 90, 20, true, "5")));
          cells.set(XYCoordinate.xyCoordToList(0,1, numRows), new Cell(0,1, new Goblin("Gob-01", 100, 30, 70, true, "2")));
          cells.set(XYCoordinate.xyCoordToList(3,8, numRows), new Cell(3,8, new Goblin("Gob-03", 100, 60, 89, true, "1")));
          cells.set(XYCoordinate.xyCoordToList(1,4, numRows), new Cell(1,4, new Goblin("Gob-14", 100, 60, 35, true, "4")));
          cells.set(XYCoordinate.xyCoordToList(2,2, numRows), new Cell(2,2, new Zombie("Zombie-55", 100, 60, 56, true, "3")));
          cells.set(XYCoordinate.xyCoordToList(5,2, numRows), new Cell(5,2, new Zombie("Zombie-88", 100, 60, 11, true, "6")));


          /* CORE */



          for (int r = 0; r < numRows; r++) {
            System.out.printf("\n");
            for (int c = 0; c < numCols; c++) {
              System.out.printf("|%s|", cells.get(XYCoordinate.xyCoordToList(r,c, numRows)).getDisplay());
            }
          }
          System.out.printf("\n");

          //XYCoordinate xy = XYCoordinate.listToXYCoord(99, numRows, numRows);
          //System.out.printf("xy: %d, %d", xy.getX(), xy.getY());

          /* MOVE 1) USER 2) BOTS */
          // USER MOVE

          //TODO  - GET USER MOVE

          XYCoordinate xy = new XYCoordinate();
          int listIndex;


          // MOVE BOTS & USERS

          // copy out our Creature Cells ONLY and then we will sort them by AGILITY and use this to determine which Cells move first
          // sort for AGILITY


          // create an ArrayList of ONLY those cells with creatures
          for (Cell cellElement : cells) {
            if (!cellElement.getIsEmpty()) {
              System.out.println(cellElement.toString());
              creatureCells.add(cellElement);
            }
          }

          // now sort these cells by AGILITY
          Collections.sort(creatureCells, new CellChainedComparator(new CellAgilityComparator(), new CellXYComparator()));
          for (Cell cellElement : creatureCells) {
              System.out.println(cellElement.toString());
          }



          /***************************************/
          // NOW WE HAVE SORTED ON AGILITY OUR XYs ARE OFF !!!!!!!!!!!!
          /***************************************/

          /* now we have things in teh right order for MOVEMENT Phase */
          /* move through the List of creatures and make moves and attacks if required */
          for (Cell cellElement : creatureCells) {

            if (cellElement.getCreature().getIsAlive()){
              // discover a valid move for this cell
              do {
                xy = cellElement.attemptMove();
                System.out.println("Cell: " + cellElement.getXPos() + "|" + cellElement.getYPos() + " looking to move to " + xy.getX() + "|" + xy.getY());

              } // make sure it is valid / in bounds
              while (xy.getX() < 0 || xy.getY() < 0 || xy.getX() >= numRows || xy.getY() >= numCols); // what about walls !?
              System.out.println("This seems OK");

              // get the cell/index in the ArrayList where this Element wishes to move.
              listIndex = XYCoordinate.xyCoordToList(xy.getX(),xy.getY(), numRows);

              // MUST RESOLVE CONFLICTS

              /* see what is in the new Cell. */
              // Is it an EMPTY Cell ?
              if (cells.get(listIndex).getIsEmpty()){
                System.out.printf("Cell @ (%d,%d) moving to is Empty\n", xy.getX(),xy.getY());

                // it's just an empty cell so we can swap it
                cells.get(listIndex).setXPos(cellElement.getXPos());
                cells.get(listIndex).setYPos(cellElement.getYPos());

                // update the current Cells XY coordinates
                cellElement.setXPos(xy.getX());
                cellElement.setYPos(xy.getY());

              } else if ( cells.get(listIndex).getCreature().getIsAlive() ) {
                  if ( cells.get(listIndex).getXPos() == cellElement.getXPos() && cells.get(listIndex).getYPos() == cellElement.getYPos() ) {
                      System.out.printf("%d|%d vs %d|%d\n", cells.get(listIndex).getXPos(), cells.get(listIndex).getYPos(), cellElement.getXPos(),  cellElement.getYPos());
                      System.out.printf("Looks like this Cell did not move\n");
                  } else {
                    System.out.println("FIGHT!");
                    /* ????????????????? */
                    /************************
                    / RESOLVE CONFLICT/
                    /************************/
                    //while (creatures.get(0).getIsAlive() == true && creatures.get(1).getIsAlive() == true) {
                    // go until one or more dies
                    while (cellElement.getCreature().getIsAlive() && cells.get(listIndex).getCreature().getIsAlive()) {
                      cellElement.getCreature().attack(cells.get(listIndex).getCreature());
                    }


                  }


              } else {
                System.out.println("WTF!");

              }
            }

          }





            System.out.println("SORT-->");
            Collections.sort(cells, new CellChainedComparator(new CellXYComparator()));
            //Collections.sort(cells, new CellChainedComparator(new CellAgilityComparator(), new CellXYComparator()));
            System.out.println("<--SORT");

            for (int r = 0; r < numRows; r++) {
              System.out.printf("\n");
              for (int c = 0; c < numCols; c++) {
                if (!cells.get(XYCoordinate.xyCoordToList(r,c, numRows)).getIsEmpty()) {
                  if (cells.get(XYCoordinate.xyCoordToList(r,c, numRows)).getCreature().getIsAlive()) {
                  System.out.printf("|%s|", cells.get(XYCoordinate.xyCoordToList(r,c, numRows)).getDisplay());
                } else {
                  System.out.printf("| |"); // DEAD
                }

                }
                else {
                  System.out.printf("|%s|", cells.get(XYCoordinate.xyCoordToList(r,c, numRows)).getDisplay());
                }
              }
            }
            System.out.printf("\n");





          /* RESOLVE */
          /* DISPLAY */





  }




}
