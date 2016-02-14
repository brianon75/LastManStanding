import java.util.Comparator;

/**
 * This comparator compares two Cells by their job titles.
 * @author www.codejava.net
 *
 */
public class CellXYComparator implements Comparator<Cell> {

    @Override
    public int compare(Cell cell1, Cell cell2) {

        if (cell1.getXPos() != cell2.getXPos()) {
          return cell1.getXPos() - cell2.getXPos();
        }

        return cell1.getYPos() - cell2.getYPos();
    }
}
