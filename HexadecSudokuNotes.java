package edu.ics211.h08;

import java.util.ArrayList;

/**
 * Class for recursively finding a solution to a Hexadecimal Sudoku problem.
 *
 * @author Biagioni, Edoardo, Cam Moore
 *     date August 5, 2016
 *     missing solveSudoku, to be implemented by the students in ICS 211
 */
public class HexadecSudokuNotes {
  public static int numOfRecursionCalls = 0;
  public static int numOfIterations = 0;


  /**
   * Find an assignment of values to sudoku cells that makes the sudoku valid.
   *
   * @param sudoku the sudoku to be solved.
   * @return whether a solution was found if a solution was found, the sudoku is
   *         filled in with the solution if no solution was found, restores the
   *         sudoku to its original value.
   */
  public static boolean solveSudoku(int[][] sudoku) {
    recursiveSolver(sudoku, legalValues(sudoku, 0, 0), 0, 0);
    if (checkSudoku(sudoku, true) && noEmpty(sudoku)) {
      return true;
    }
    //if returns false must leave the sudoku unchanged.  DON'T MAKE COPY OF SUDOKU.  
    //NEED TO RECURSIVELY BACKTRACK TO ORIGINAL FORMAT.
    return false;
  }
  
  
  /**
   * Recursively fills the sudoku.
   * 
   * @param sudoku The sudoku puzzle to be filled
   * @param legalVals The legal values.
   * @param row The row the cell being tested belongs to.
   * @param column The column the cell being tested belongs to.
   * @return the sudoku (filled or unfilled)
   */
  public static int[][] recursion(int[][] sudoku, ArrayList<Integer> legalVals, int row, 
      int column) {
    return sudoku;
  }
  
  
  /**
   * Recursively fills the sudoku.  Takes only 20 minutes to pass Cam's test.
   * 
   * @param sudoku The sudoku puzzle to be filled
   * @param legalVals The legal values.
   * @param row The row the cell being tested belongs to.
   * @param column The column the cell being tested belongs to.
   * @return the sudoku (filled or unfilled)
   */
  public static int[][] recursiveSolver(int[][] sudoku, ArrayList<Integer> legalVals, 
      int row, int column) {
    numOfRecursionCalls++;
    if (legalVals == null) {
      if (column == 15) {
        if (row == 15) {
          return sudoku;
        } else {
          return recursiveSolver(sudoku, legalValues(sudoku, row + 1, 0), row + 1, 0);
        }
      } else {
        return recursiveSolver(sudoku, legalValues(sudoku, row, column + 1), row, column + 1);
      }
    } else {
      for (int i = 0; i < legalVals.size(); i++) {
        numOfIterations++;
        sudoku[row][column] = legalVals.get(i);
        if (column == 15) {
          if (row == 15) {
            return sudoku;
          }
          recursiveSolver(sudoku, legalValues(sudoku, row + 1, 0), row + 1, 0);
        } else {
          //System.out.println("sudoku[" + row + "][" + column + "]: " + sudoku[row][column]);
          recursiveSolver(sudoku, legalValues(sudoku, row, column + 1), row, column + 1);
        }
        if (checkSudoku(sudoku, true) && noEmpty(sudoku)) {
          return sudoku;
        }
      }
      // Assuming the sudoku wasn't solved after using all of the legal values
      sudoku[row][column] = -1;   //reset cell to empty
      return sudoku;

      //if (column == 0) {    
      //  return recursion(sudoku, legalValues(sudoku, row - 1, 15), row - 1, 15);
      //}
      //return sudoku;
      //INCORRECT way to redo last cell
      //return recursion(sudoku, legalValues(sudoku, row, column - 1), row, column - 1);
    }
  }
  
  
  /**
   * Checks if there are any empty cells in the sudoku puzzle.
   * 
   * @param sudoku The sdoku puzzle.
   * @return true if sudoku is filled.
   */
  public static boolean noEmpty(int[][] sudoku) {
    for (int i = 0; i < sudoku.length; i++) {
      for (int j = 0; j < sudoku[i].length; j++) {
        if (sudoku[i][j] == -1) {
          return false;
        }
      }
    }
    return true;
  }
  

  /**
   * Find the legal values for the given sudoku and cell.
   *
   * @param sudoku the sudoku being solved.
   * @param row the row of the cell to get values for.
   * @param column the column of the cell.
   * @return an ArrayList of the valid values.
   */
  public static ArrayList<Integer> legalValues(int[][] sudoku, int row, int column) {
    int cell = sudoku [row][column];    
    int[] possibleVals = new int[16];

    ArrayList<Integer> validValues = new ArrayList<Integer>();
    for (int a = 0; a < 16; a++) {
      possibleVals[a] = a;
    }
    if (cell != -1) {
      return null;
    }
    for (int i = 0; i < sudoku.length; i++) {
      if (i != column && sudoku[row][i] > -1 && sudoku[row][i] < 16) {
        int temp = sudoku[row][i];
        possibleVals[temp] = -1;
//        for (int j = 0; j < validValues.size(); j++) {
//          if (sudoku[row][i] == validValues.get(j)) {
//            validValues.remove(j);
//            j = 16;
//          }
//        }
      }
    }
    for (int k = 0; k < sudoku.length; k++) {
      if (k != row && sudoku[k][column] > -1 && sudoku[k][column] < 16) {
        int temp = sudoku[k][column];
        possibleVals[temp] = -1;
        //for (int m = 0; m < validValues.size(); m++) {
        //  if (sudoku[k][column] == validValues.get(m)) {
        //    validValues.remove(m);
        //    m = 16;
        //  }
        //}
      }
    }
    for (int n = 0; n < 4; n++) {
      for (int o = 0; o < 4; o++) {
        int boxRow = (row / 4 * 4) + n;
        int boxColumn = (column / 4 * 4) + o;
        if (boxRow != row && boxColumn != column && sudoku[boxRow][boxColumn] > -1
            && sudoku[boxRow][boxColumn] < 16) {
          int temp = sudoku[boxRow][boxColumn];
          possibleVals[temp] = -1;

//          for (int p = 0; p < validValues.size(); p++) {
//            if (sudoku[boxRow][boxColumn] == validValues.get(p)) {
//              validValues.remove(p);
//              p = 16;
//            }
//          }
        }
      }
    }
    for (int q = 0; q < possibleVals.length; q++) {
      if (possibleVals[q] != -1) {
        validValues.add(possibleVals[q]);
      }
    }
    if (validValues.size() == 16) {       // Cam's test didn't test for an empty list situation where there are no possibilities.
      return new ArrayList<Integer>();    //if no valid values arraylist should be empty. 
    }
    return validValues;
                     //return null if cell is filled.
  }


  /**
   * checks that the sudoku rules hold in this sudoku puzzle. cells that contain
   * 0 are not checked.
   * NOTE: checkSudoku doesn’t verify that all cells are filled – it just verifies that none of the Sudoku rules are broken.
   * 
   * @param sudoku the sudoku to be checked.
   * @param printErrors whether to print the error found, if any.
   * @return true if this sudoku obeys all of the sudoku rules, otherwise false.
   */
  public static boolean checkSudoku(int[][] sudoku, boolean printErrors) {
    if (sudoku.length != 16) {
      if (printErrors) {
        System.out.println("sudoku has " + sudoku.length + " rows, should have 16");
      }
      return false;
    }
    for (int i = 0; i < sudoku.length; i++) {
      if (sudoku[i].length != 16) {
        if (printErrors) {
          System.out.println("sudoku row " + i + " has "
              + sudoku[i].length + " cells, should have 16");
        }
        return false;
      }
    }
    /* check each cell for conflicts */
    for (int i = 0; i < sudoku.length; i++) {
      for (int j = 0; j < sudoku.length; j++) {
        int cell = sudoku[i][j];
        if (cell == -1) {
          continue; /* blanks are always OK */      //continue jumps to the next interation in the loop. Break leaves the loop.
        }
        if ((cell < 0) || (cell > 16)) {    //Shouldn't it be cell > 15?????????????????????????????????/
          if (printErrors) {
            System.out.println("sudoku row " + i + " column " + j
                + " has illegal value " + String.format("%02X", cell));
          }
          return false;
        }
        /* does it match any other value in the same row? */
        for (int m = 0; m < sudoku.length; m++) {
          if ((j != m) && (cell == sudoku[i][m])) {
            if (printErrors) {
              System.out.println("sudoku row " + i + " has " + String.format("%X", cell)
                  + " at both positions " + j + " and " + m);
            }
            return false;
          }
        }
        /* does it match any other value it in the same column? */
        for (int k = 0; k < sudoku.length; k++) {
          if ((i != k) && (cell == sudoku[k][j])) {
            if (printErrors) {
              System.out.println("sudoku column " + j + " has " + String.format("%X", cell)
                  + " at both positions " + i + " and " + k);
            }
            return false;
          }
        }
        /* does it match any other value in the 4x4? */
        for (int k = 0; k < 4; k++) {
          for (int m = 0; m < 4; m++) {
            int testRow = (i / 4 * 4) + k; /* test this row */
            int testCol = (j / 4 * 4) + m; /* test this col */
            if ((i != testRow) && (j != testCol) && (cell == sudoku[testRow][testCol])) {
              if (printErrors) {
                System.out.println("sudoku character " + String.format("%X", cell) + " at row "
                    + i + ", column " + j + " matches character at row " + testRow + ", column "
                    + testCol);
              }
              return false;
            }
          }
        }
      }
    }
    return true;
  }


  /**
   * Converts the sudoku to a printable string.
   *
   * @param sudoku the sudoku to be converted.
   * @param debug whether to check for errors.
   * @return the printable version of the sudoku.
   */
  public static String toString(int[][] sudoku, boolean debug) {
    if ((!debug) || (checkSudoku(sudoku, true))) {
      String result = "";
      for (int i = 0; i < sudoku.length; i++) {
        if (i % 4 == 0) {
          result = result + "+---------+---------+---------+---------+\n";
        }
        for (int j = 0; j < sudoku.length; j++) {
          if (j % 4 == 0) {
            result = result + "| ";
          }
          if (sudoku[i][j] == -1) {
            result = result + "  ";
          } else {
            result = result + String.format("%X", sudoku[i][j]) + " ";
          }
        }
        result = result + "|\n";
      }
      result = result + "+---------+---------+---------+---------+\n";
      return result;
    }
    return "illegal sudoku";
  }
}
