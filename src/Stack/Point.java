package Stack;

/**
 * The Point class used implements an application that
 * will store and process the stack data.
 *
 * @author Randeep Singh Virk
 * @version 1.0
 * @since 2022-02-18
 */
public class Point {

    /**
     * Private variables to store row and column data.
     */
    private final int row;
    private final int column;

    //region Methods

    /**
     * Instance constructor with two parameters row and column.
     *
     * @param row    The row value.
     * @param column The column value.
     */
    public Point(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Gets the row value.
     *
     * @return Returns the row value.
     */
    public int getRow() {
        return row;
    }

    /**
     * Gets the column value.
     *
     * @return Returns the column value.
     */
    public int getColumn() {
        return column;
    }

    /**
     * Returns the string consisting row, and column value(i.e. [2, 3]).
     *
     * @return Returns the string consisting row, and column value.
     */
    @Override
    public String toString() {
        return "[" + getRow() + ", " + getColumn() + "]";
    }

    //endregion
}

