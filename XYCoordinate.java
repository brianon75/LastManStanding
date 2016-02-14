public class XYCoordinate {


  private int x;
  private int y;

  public XYCoordinate () {}

  public XYCoordinate (int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return y;
  }

  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  }

  public static int xyCoordToList(int x, int y, int rows) {
    // given an x,y coordinate & rows. Calculates the position in a sorted (on x,y) list
    return (x*rows) + y;
  }

  public static  XYCoordinate listToXYCoord(int listPos, int rows, int cols) {
    // given a list positio. Convert to an x,y
    return new XYCoordinate(listPos / rows, listPos % rows);
  }

  public static boolean xyIsValid(int x, int y, int rows, int cols) {
    // given an x,y coordinate. Calculate is it's a valid positiont
    return true;
  }
}
