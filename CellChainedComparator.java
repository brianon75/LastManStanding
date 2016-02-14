import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * This is a chained comparator that is used to sort a list by multiple
 * attributes by chaining a sequence of comparators of individual fields
 * together.
 * @author www.codejava.net
 *
 */
public class CellChainedComparator implements Comparator<Cell> {

    private List<Comparator<Cell>> listComparators;

    @SafeVarargs
    public CellChainedComparator(Comparator<Cell>... comparators) {
        this.listComparators = Arrays.asList(comparators);
    }

    @Override
    public int compare(Cell cell1, Cell cell2) {
        for (Comparator<Cell> comparator : listComparators) {
            int result = comparator.compare(cell1, cell2);
            if (result != 0) {
                return result;
            }
        }
        return 0;
    }
}
