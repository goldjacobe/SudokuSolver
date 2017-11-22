import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jacob Gold on 11/15/17.
 * Representation of a Sudoku board state
 */
public class Puzzle
{
    private static final int DEGREE = 3;
    public static final int SIZE = DEGREE * DEGREE;

    private int[][] grid;

    private boolean[][] usedByRow;
    private boolean[][] usedByCol;
    private boolean[][] usedByBox;

    private int empty;

    /**
     * Initializes a blank Sudoku puzzle
     */
    public Puzzle() {
        grid = new int[SIZE][SIZE];
        usedByRow = new boolean[SIZE][SIZE];
        usedByCol = new boolean[SIZE][SIZE];
        usedByBox = new boolean[SIZE][SIZE];
        empty = SIZE * SIZE;

    }

    private static int getBoxNumber(int row, int col) {
        return (row / DEGREE) * DEGREE + col / DEGREE;
    }

    /**
     * Finds all possible values for a given cell
     * @param row row index of cell
     * @param col col index of cell
     * @return list of possible values
     */
    public List<Integer> getPossibleValues(int row, int col) {
        ArrayList<Integer> out = new ArrayList<>(SIZE);
        for (int i = 0; i < SIZE; i++) {
            if (!usedByRow[row][i] && !usedByCol[col][i] && !usedByBox[getBoxNumber(row, col)][i]) {
                out.add(i + 1);
            }
        }
        return out;
    }

    /**
     * Sets a cell to a value
     * @param row row index of cell
     * @param col col index of cell
     * @param v value to set
     */
    public void set(int row, int col, int v) {
        grid[row][col] = v;
        usedByRow[row][v-1] = true;
        usedByCol[col][v-1] = true;
        usedByBox[getBoxNumber(row, col)][v-1] = true;
        empty--;
    }

    /**
     * Clears a cell
     * @param row row index of cell
     * @param col col index of cell
     */
    public void clear(int row, int col) {
        int v = grid[row][col];
        grid[row][col] = 0;
        usedByRow[row][v-1] = false;
        usedByCol[col][v-1] = false;
        usedByBox[getBoxNumber(row, col)][v-1] = false;
        empty++;
    }

    /**
     * Gets the value at a cell
     * @param row row index of cell
     * @param col col index of cell
     * @return value
     */
    public int get(int row, int col) {
        return grid[row][col];
    }

    public boolean isDone() {
        return empty == 0;
    }
}
