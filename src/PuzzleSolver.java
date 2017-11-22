import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Jacob Gold on 11/21/17.
 * Sudoku solver that reads from and writes to files
 * Reads white-space separated characters row by row
 * Anything besides a valid choice for a square is regarded as an empty square
 */
public class PuzzleSolver
{
    private String inPuzzle;
    private String outPuzzle;
    private Puzzle puzzle;

    /**
     * Create a new PuzzleSolver
     * @param inPuzzle path of input file
     * @param outPuzzle desired path of output file (creates/overwrites)
     */
    public PuzzleSolver(String inPuzzle, String outPuzzle) {
        this.inPuzzle = inPuzzle;
        this.outPuzzle = outPuzzle;
        this.puzzle = new Puzzle();
    }

    /**
     * Runs the solver
     * @return duration of solve call in milliseconds
     */
    public long run() {
        readPuzzle();
        long start = System.nanoTime();
        solve();
        long end = System.nanoTime();
        writePuzzle();
        return end - start;
    }

    private void readPuzzle() {
        try(Scanner sc = new Scanner(new File(inPuzzle))) {
            for (int i = 0; i < Puzzle.SIZE; i++) {
                for (int j = 0; j < Puzzle.SIZE; j++) {
                    setElement(sc.next(), i, j);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setElement(String token, int row, int col) {
        int v = Integer.parseInt(token);
        if (v > 0 && v <= Puzzle.SIZE) {
            puzzle.set(row, col, v);
        }
    }

    private boolean solve() {
        if (puzzle.isDone()) return true;
        int[] square = chooseSquare();
        int row = square[0];
        int col = square[1];
        for(int v : puzzle.getPossibleValues(row, col)) {
            puzzle.set(row, col, v);
            if (solve()) {
                return true;
            }
            puzzle.clear(row, col);
        }
        return false;
    }

    private int[] chooseSquare() {
        int[] out = new int[2];
        int poss = 10;
        int newPoss;

        for (int i = 0; i < Puzzle.SIZE; i++) {
            for (int j = 0; j < Puzzle.SIZE; j++) {
                //MRV
                if (puzzle.get(i, j) == 0 && (newPoss = puzzle.getPossibleValues(i, j).size()) < poss) {
                    poss = newPoss;
                    out[0] = i;
                    out[1] = j;
                }
            }
        }

        return out;
    }

    private void writePuzzle()
    {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(outPuzzle))) {
            for (int i = 0; i < Puzzle.SIZE; i++) {
                for (int j = 0; j < Puzzle.SIZE; j++) {
                    bw.write("" + puzzle.get(i, j) + " ");
                }
                bw.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
