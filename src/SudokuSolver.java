/**
 * Created by Jacob Gold on 11/21/17.
 * Runner/Tester for the PuzzleSolver class
 * Takes two command-line args: input filename and output filename
 * Test puzzles are from:
 * http://elmo.sbs.arizona.edu/sandiway/sudoku/examples.html
 *
 * BEFORE OPTIMIZATION:
 * Test puzzle 1: 348
 * Test puzzle 2: 226
 * Test puzzle 3: 1533
 * Test puzzle 4: 176696
 * Test puzzle 5: 592
 * Test puzzle 6: 10320
 *
 * AFTER MRV "OPTIMIZATION":
 * Test puzzle 1: 789
 * Test puzzle 2: 343
 * Test puzzle 3: 6209
 * Test puzzle 4: 19018
 * Test puzzle 5: 796
 * Test puzzle 6: 16453
 *
 * I was gonna do LCV as well, but I'm pretty sure that it would just make the runtime even worse.
 */
public class SudokuSolver
{
    /**
     * Runs PuzzleSolver on a file
     * @param args input and output filenames
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            test();
            return;
        }
        PuzzleSolver puzzleSolver = new PuzzleSolver(args[0], args[1]);
        puzzleSolver.run();
    }

    /**
     * Speed tests for PuzzleSolver
     */
    public static void test() {
        System.out.println("Test run (microseconds, averaged over 10 runs):");
        for (int i = 1; i <= 6; i++) {
            System.out.print("Test puzzle " + i + ": ");
            long total = 0;
            for (int j = 0; j < 10; j ++) {
                PuzzleSolver puzzleSolver = new PuzzleSolver("./test/test" + i + ".txt", "./test/out" + i + ".txt");
                total += puzzleSolver.run()/1000;
            }
            long avg = total/10;
            System.out.println(avg);
        }
    }
}
