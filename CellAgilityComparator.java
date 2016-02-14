import java.util.Comparator;

/**
 * This comparator compares two Cells by their job titles.
 * @author www.codejava.net
 *
 */
public class CellAgilityComparator implements Comparator<Cell> {

    @Override
    public int compare(Cell cell1, Cell cell2) {

      if (cell1.getIsEmpty() && cell2.getIsEmpty()) {
        return 0;
      }
      if (cell1.getIsEmpty()) {
        return 1;
      }
      if (cell2.getIsEmpty()) {
        return -1;
      }
      //System.out.printf("CellAgilityComparator: Cell1Name|%s|%d", cell1.getCreature().getName(), cell1.getCreature().getAgility());
      //System.out.printf(" VS Cell1Name|%s|%d\n", cell2.getCreature().getName(), cell2.getCreature().getAgility());

      return cell2.getCreature().getAgility() - cell1.getCreature().getAgility();
    }
}
