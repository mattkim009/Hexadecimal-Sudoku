package edu.ics211.h08;

/**
 * Test a HexadecimalSudoku solver.
 *
 * @author Biagioni, Edoardo, Cam Moore and Matthew Kim
 *     date August 5, 2016
 *     bugs none
 */
public class HexadecimalSudokuOwnTest {

  /**
   * Checks the sudoku returning true if all cells are filled. Does not check
   * validity.
   *
   * @return true if all cells are filled, false otherwise.
   */
  private static boolean isFilled(int[][] sudoku) {
    for (int i = 0; i < 16; i++) {
      for (int j = 0; j < 16; j++) {
        if (sudoku[i][j] == -1) {
          return false;
        }
      }
    }
    return true;
  }


  /**
   * Test whether two sudoku are equal. If not, return a new sudoku that is
   * blank where the two sudoku differ.
   *
   * @param sudoku the sudoku to be checked.
   * @param solution the solution checked.
   * @return null if the two match, and otherwise a sudoku with 0 in every cell
   *         that differs.
   */
  private static int[][] sameSudoku(int[][] sudoku, int[][] solution) {
    int[][] result = new int[16][16];
    for (int i = 0; i < 16; i++) {        //Make a copy of the sudoku problem
      for (int j = 0; j < 16; j++) {
        result[i][j] = sudoku[i][j];
      }
    }
    //if different values at the same cell then the sudokus are not the same.
    boolean same = true;
    for (int i = 0; i < 16; i++) {
      for (int j = 0; j < 16; j++) {
        if (result[i][j] != solution[i][j]) {
          same = false;
          result[i][j] = -1;
        }
      }
    }
    //if the sudokus are the same return null.
    if (same) {
      return null;
    }
    return result;
  }


  /**
   * Try to solve a sudoku. If a solution is provided, also check against the
   * solution. Print the results.
   *
   * @param name the name of this sudoku.
   * @param sudoku the sudoku to be solved.
   * @param solution the given solution, or null.
   */
  private static void testSudoku(String name, int[][] sudoku, int[][] solution) {
    System.out.println("solving " + name + "\n" + HexadecimalSudoku.toString(sudoku, true));
    if (HexadecimalSudoku.solveSudoku(sudoku)) {
      // Checks if sudoku is correct
      if (isFilled(sudoku) && HexadecimalSudoku.checkSudoku(sudoku, true)) {
        System.out.println("success!\n" + HexadecimalSudoku.toString(sudoku, true));
        // If example solution is given (for examples 1 & 2 in Cam's test) 
        if (solution != null) {
          int[][] diff = sameSudoku(sudoku, solution);
          if (diff != null) {
            System.out.println("given solution:\n" + HexadecimalSudoku.toString(solution, true));
            System.out.println("difference between solutions:\n"
                + HexadecimalSudoku.toString(diff, true));
          }
        }
      } else { /* the supposed solution is not a complete or valid sudoku */
        if (!isFilled(sudoku)) {
          System.out.println("sudoku was not completely filled:\n"
              + HexadecimalSudoku.toString(sudoku, false));
        }
        if (!HexadecimalSudoku.checkSudoku(sudoku, false)) {
          System.out.println("sudoku is not a valid solution:\n"
              + HexadecimalSudoku.toString(sudoku, false));
        }
      }
    } else {
      System.out.println("unable to complete sudoku " + name
          + "\n" + HexadecimalSudoku.toString(sudoku, true));
    }
  }
  
  
  /**
   * Tests four Sudoku proglems.
   * @param arg command line arguments, ignored.
   */
  public static void main(String[] arg) {
    
    int[][] filledSudoku = { 
        { 11, 2, 5, 7, 4, 10, 9, 15, 6, 14, 0, 1, 12, 3, 8, 13 },
        { 14, 4, 0, 9, 11, 8, 2, 12, 13, 5, 3, 10, 15, 1, 7, 6 },
        { 1, 10, 15, 12, 6, 13, 7, 3, 4, 9, 8, 2, 11, 5, 14, 0 },
        { 13, 8, 6, 3, 5, 14, 1, 0, 11, 7, 15, 12, 10, 9, 4, 2 },
        { 0, 7, 14, 2, 8, 11, 4, 9, 1, 6, 10, 5, 13, 12, 3, 15 },
        { 3, 5, 9, 1, 10, 12, 14, 7, 2, 4, 13, 15, 8, 0, 6, 11 },
        { 12, 15, 10, 13, 2, 1, 3, 6, 8, 11, 14, 0, 7, 4, 5, 9 },
        { 6, 11, 8, 4, 0, 15, 5, 13, 7, 12, 9, 3, 14, 2, 10, 1 },
        { 2, 14, 1, 15, 3, 7, 11, 4, 5, 10, 6, 9, 0, 13, 12, 8 },
        { 7, 6, 12, 8, 9, 2, 10, 5, 0, 13, 1, 11, 4, 14, 15, 3 },
        { 4, 9, 3, 5, 14, 0, 13, 8, 15, 2, 12, 7, 6, 11, 1, 10 },
        { 10, 13, 11, 0, 15, 6, 12, 1, 3, 8, 4, 14, 9, 7, 2, 5 },
        { 9, 1, 2, 14, 7, 4, 0, 11, 10, 15, 5, 6, 3, 8, 13, 12 },
        { 8, 3, 7, 10, 13, 9, 6, 2, 12, 1, 11, 4, 5, 15, 0, 14 },
        { 15, 0, 4, 11, 12, 5, 8, 10, 14, 3, 2, 13, 1, 6, 9, 7 },
        { 5, 12, 13, 6, 1, 3, 15, 14, 9, 0, 7, 8, 2, 10, 11, 4 } };
    
    int[][] oneEmptyCell = { 
        { 11, 2, -1, 7, 4, 10, 9, 15, 6, 14, 0, 1, 12, 3, 8, 13 },
        { 14, 4, 0, 9, 11, 8, 2, 12, 13, 5, 3, 10, 15, 1, 7, 6 },
        { 1, 10, 15, 12, 6, 13, 7, 3, 4, 9, 8, 2, 11, 5, 14, 0 },
        { 13, 8, 6, 3, 5, 14, 1, 0, 11, 7, 15, 12, 10, 9, 4, 2 },
        { 0, 7, 14, 2, 8, 11, 4, 9, 1, 6, 10, 5, 13, 12, 3, 15 },
        { 3, 5, 9, 1, 10, 12, 14, 7, 2, 4, 13, 15, 8, 0, 6, 11 },
        { 12, 15, 10, 13, 2, 1, 3, 6, 8, 11, 14, 0, 7, 4, 5, 9 },
        { 6, 11, 8, 4, 0, 15, 5, 13, 7, 12, 9, 3, 14, 2, 10, 1 },
        { 2, 14, 1, 15, 3, 7, 11, 4, 5, 10, 6, 9, 0, 13, 12, 8 },
        { 7, 6, 12, 8, 9, 2, 10, 5, 0, 13, 1, 11, 4, 14, 15, 3 },
        { 4, 9, 3, 5, 14, 0, 13, 8, 15, 2, 12, 7, 6, 11, 1, 10 },
        { 10, 13, 11, 0, 15, 6, 12, 1, 3, 8, 4, 14, 9, 7, 2, 5 },
        { 9, 1, 2, 14, 7, 4, 0, 11, 10, 15, 5, 6, 3, 8, 13, 12 },
        { 8, 3, 7, 10, 13, 9, 6, 2, 12, 1, 11, 4, 5, 15, 0, 14 },
        { 15, 0, 4, 11, 12, 5, 8, 10, 14, 3, 2, 13, 1, 6, 9, 7 },
        { 5, 12, 13, 6, 1, 3, 15, 14, 9, 0, 7, 8, 2, 10, 11, 4 } };
    
    int[][] oneEmptyCellSolution = { 
        { 11, 2, 5, 7, 4, 10, 9, 15, 6, 14, 0, 1, 12, 3, 8, 13 },
        { 14, 4, 0, 9, 11, 8, 2, 12, 13, 5, 3, 10, 15, 1, 7, 6 },
        { 1, 10, 15, 12, 6, 13, 7, 3, 4, 9, 8, 2, 11, 5, 14, 0 },
        { 13, 8, 6, 3, 5, 14, 1, 0, 11, 7, 15, 12, 10, 9, 4, 2 },
        { 0, 7, 14, 2, 8, 11, 4, 9, 1, 6, 10, 5, 13, 12, 3, 15 },
        { 3, 5, 9, 1, 10, 12, 14, 7, 2, 4, 13, 15, 8, 0, 6, 11 },
        { 12, 15, 10, 13, 2, 1, 3, 6, 8, 11, 14, 0, 7, 4, 5, 9 },
        { 6, 11, 8, 4, 0, 15, 5, 13, 7, 12, 9, 3, 14, 2, 10, 1 },
        { 2, 14, 1, 15, 3, 7, 11, 4, 5, 10, 6, 9, 0, 13, 12, 8 },
        { 7, 6, 12, 8, 9, 2, 10, 5, 0, 13, 1, 11, 4, 14, 15, 3 },
        { 4, 9, 3, 5, 14, 0, 13, 8, 15, 2, 12, 7, 6, 11, 1, 10 },
        { 10, 13, 11, 0, 15, 6, 12, 1, 3, 8, 4, 14, 9, 7, 2, 5 },
        { 9, 1, 2, 14, 7, 4, 0, 11, 10, 15, 5, 6, 3, 8, 13, 12 },
        { 8, 3, 7, 10, 13, 9, 6, 2, 12, 1, 11, 4, 5, 15, 0, 14 },
        { 15, 0, 4, 11, 12, 5, 8, 10, 14, 3, 2, 13, 1, 6, 9, 7 },
        { 5, 12, 13, 6, 1, 3, 15, 14, 9, 0, 7, 8, 2, 10, 11, 4 } };

    int[][] fourEmptyCells = { 
        { 11, 2, 5, 7, 4, 10, 9, 15, 6, 14, 0, 1, 12, 3, 8, 13 },
        { 14, 4, 0, 9, 11, 8, 2, 12, 13, 5, 3, 10, 15, 1, 7, 6 },
        { 1, 10, 15, 12, 6, 13, 7, 3, 4, 9, 8, 2, 11, 5, 14, 0 },
        { 13, 8, 6, 3, 5, 14, 1, 0, 11, 7, 15, 12, 10, 9, 4, 2 },
        { 0, 7, 14, 2, 8, 11, 4, 9, 1, 6, 10, 5, 13, 12, 3, 15 },
        { 3, 5, 9, 1, 10, 12, 14, 7, 2, 4, 13, 15, 8, 0, 6, 11 },
        { -1, -1, 10, 13, 2, 1, 3, 6, 8, 11, 14, 0, 7, 4, 5, 9 },
        { -1, -1, 8, 4, 0, 15, 5, 13, 7, 12, 9, 3, 14, 2, 10, 1 },
        { 2, 14, 1, 15, 3, 7, 11, 4, 5, 10, 6, 9, 0, 13, 12, 8 },
        { 7, 6, 12, 8, 9, 2, 10, 5, 0, 13, 1, 11, 4, 14, 15, 3 },
        { 4, 9, 3, 5, 14, 0, 13, 8, 15, 2, 12, 7, 6, 11, 1, 10 },
        { 10, 13, 11, 0, 15, 6, 12, 1, 3, 8, 4, 14, 9, 7, 2, 5 },
        { 9, 1, 2, 14, 7, 4, 0, 11, 10, 15, 5, 6, 3, 8, 13, 12 },
        { 8, 3, 7, 10, 13, 9, 6, 2, 12, 1, 11, 4, 5, 15, 0, 14 },
        { 15, 0, 4, 11, 12, 5, 8, 10, 14, 3, 2, 13, 1, 6, 9, 7 },
        { 5, 12, 13, 6, 1, 3, 15, 14, 9, 0, 7, 8, 2, 10, 11, 4 } };
    
    int[][] fourEmptyCellsSolution = { 
        { 11, 2, 5, 7, 4, 10, 9, 15, 6, 14, 0, 1, 12, 3, 8, 13 },
        { 14, 4, 0, 9, 11, 8, 2, 12, 13, 5, 3, 10, 15, 1, 7, 6 },
        { 1, 10, 15, 12, 6, 13, 7, 3, 4, 9, 8, 2, 11, 5, 14, 0 },
        { 13, 8, 6, 3, 5, 14, 1, 0, 11, 7, 15, 12, 10, 9, 4, 2 },
        { 0, 7, 14, 2, 8, 11, 4, 9, 1, 6, 10, 5, 13, 12, 3, 15 },
        { 3, 5, 9, 1, 10, 12, 14, 7, 2, 4, 13, 15, 8, 0, 6, 11 },
        { 12, 15, 10, 13, 2, 1, 3, 6, 8, 11, 14, 0, 7, 4, 5, 9 },
        { 6, 11, 8, 4, 0, 15, 5, 13, 7, 12, 9, 3, 14, 2, 10, 1 },
        { 2, 14, 1, 15, 3, 7, 11, 4, 5, 10, 6, 9, 0, 13, 12, 8 },
        { 7, 6, 12, 8, 9, 2, 10, 5, 0, 13, 1, 11, 4, 14, 15, 3 },
        { 4, 9, 3, 5, 14, 0, 13, 8, 15, 2, 12, 7, 6, 11, 1, 10 },
        { 10, 13, 11, 0, 15, 6, 12, 1, 3, 8, 4, 14, 9, 7, 2, 5 },
        { 9, 1, 2, 14, 7, 4, 0, 11, 10, 15, 5, 6, 3, 8, 13, 12 },
        { 8, 3, 7, 10, 13, 9, 6, 2, 12, 1, 11, 4, 5, 15, 0, 14 },
        { 15, 0, 4, 11, 12, 5, 8, 10, 14, 3, 2, 13, 1, 6, 9, 7 },
        { 5, 12, 13, 6, 1, 3, 15, 14, 9, 0, 7, 8, 2, 10, 11, 4 } };
    
    int[][] invalidRow = { 
        { 11, 2, 5, 7, 4, 1, 9, 15, 6, 14, 0, 1, 12, 3, 8, 13 },
        { 14, 4, 0, 9, 11, 8, 2, 12, 13, 5, 3, 10, 15, 1, 7, 6 },
        { 1, 10, 15, 12, 6, 13, 7, 3, 4, 9, 8, 2, 11, 5, 14, 0 },
        { 13, 8, 6, 3, 5, 14, 1, 0, 11, 7, 15, 12, 10, 9, 4, 2 },
        { 0, 7, 14, 2, 8, 11, 4, 9, 1, 6, 10, 5, 13, 12, 3, 15 },
        { 3, 5, 9, 1, 10, 12, 14, 7, 2, 4, 13, 15, 8, 0, 6, 11 },
        { 12, 15, 10, 13, 2, 1, 3, 6, 8, 11, 14, 0, 7, 4, 5, 9 },
        { 6, 11, 8, 4, 0, 15, 5, 13, 7, 12, 9, 3, 14, 2, 10, 1 },
        { 2, 14, 1, 15, 3, 7, 11, 4, 5, 10, 6, 9, 0, 13, 12, 8 },
        { 7, 6, 12, 8, 9, 2, 10, 5, 0, 13, 1, 11, 4, 14, 15, 3 },
        { 4, 9, 3, 5, 14, 0, 13, 8, 15, 2, 12, 7, 6, 11, 1, 10 },
        { 10, 13, 11, 0, 15, 6, 12, 1, 3, 8, 4, 14, 9, 7, 2, 5 },
        { 9, 1, 2, 14, 7, 4, 0, 11, 10, 15, 5, 6, 3, 8, 13, 12 },
        { 8, 3, 7, 10, 13, 9, 6, 2, 12, 1, 11, 4, 5, 15, 0, 14 },
        { 15, 0, 4, 11, 12, 5, 8, 10, 14, 3, 2, 13, 1, 6, 9, 7 },
        { 5, 12, 13, 6, 1, 3, 15, 14, 9, 0, 7, 8, 2, 10, 11, 4 } };

    int[][] invalidColumn = { 
        { 11, 2, 5, 7, 4, 10, 9, 15, 6, 14, 0, 1, 12, 3, 8, 13 },
        { 14, 4, 0, 9, 11, 8, 2, 12, 13, 5, 3, 10, 15, 1, 7, 6 },
        { 1, 10, 15, 12, 6, 13, 7, 3, 4, 9, 8, 2, 11, 5, 14, 0 },
        { 13, 8, 6, 3, 5, 14, 1, 0, 11, 7, 15, 12, 10, 9, 4, 2 },
        { 0, 7, 14, 2, 8, 11, 4, 9, 1, 6, 10, 5, 13, 12, 3, 15 },
        { 3, 5, 9, 1, 10, 12, 14, 7, 2, 4, 13, 15, 8, 0, 6, 11 },
        { 1, 15, 10, 13, 2, 1, 3, 6, 8, 11, 14, 0, 7, 4, 5, 9 },
        { 6, 11, 8, 4, 0, 15, 5, 13, 7, 12, 9, 3, 14, 2, 10, 1 },
        { 2, 14, 1, 15, 3, 7, 11, 4, 5, 10, 6, 9, 0, 13, 12, 8 },
        { 7, 6, 12, 8, 9, 2, 10, 5, 0, 13, 1, 11, 4, 14, 15, 3 },
        { 4, 9, 3, 5, 14, 0, 13, 8, 15, 2, 12, 7, 6, 11, 1, 10 },
        { 10, 13, 11, 0, 15, 6, 12, 1, 3, 8, 4, 14, 9, 7, 2, 5 },
        { 9, 1, 2, 14, 7, 4, 0, 11, 10, 15, 5, 6, 3, 8, 13, 12 },
        { 8, 3, 7, 10, 13, 9, 6, 2, 12, 1, 11, 4, 5, 15, 0, 14 },
        { 15, 0, 4, 11, 12, 5, 8, 10, 14, 3, 2, 13, 1, 6, 9, 7 },
        { 5, 12, 13, 6, 1, 3, 15, 14, 9, 0, 7, 8, 2, 10, 11, 4 } };
    
    int[][] example1 = {
        { -1, 15, -1, 1, -1, -1, 14, 8, 13, 3, -1, -1, -1, 12, -1, 0 },
        { 2, 13, -1, -1, -1, -1, -1, 5, 0, 11, -1, 10, -1, -1, -1, 1 },
        { -1, 4, -1, -1, -1, 13, 1, 0, 15, -1, -1, 7, -1, -1, -1, -1 },
        { -1, 6, 12, -1, 15, -1, -1, 9, -1, -1, 14, -1, 8, -1, -1, -1 },
        { 8, -1, 1, 15, -1, -1, -1, 10, 3, -1, 9, -1, 14, -1, -1, -1 },
        { 11, -1, 4, -1, -1, -1, 8, -1, 6, 1, -1, -1, -1, -1, 12, -1 },
        { -1, -1, 3, 13, -1, -1, 4, -1, -1, 15, -1, -1, 2, -1, 8, -1 },
        { 10, -1, -1, -1, 2, -1, -1, -1, -1, 0, -1, -1, -1, 7, -1, -1 },
        { -1, -1, 15, -1, -1, -1, 5, -1, -1, -1, -1, 2, -1, -1, -1, 4 },
        { -1, 5, -1, 4, -1, -1, 7, -1, -1, 9, -1, -1, 15, 10, -1, -1 },
        { -1, 10, -1, -1, -1, -1, 6, 3, -1, 12, -1, -1, -1, 13, -1, 5 },
        { -1, -1, -1, 6, -1, 1, -1, 4, 7, -1, -1, -1, 3, 9, -1, 2 },
        { -1, -1, -1, 12, -1, 8, -1, -1, 2, -1, -1, 3, -1, 4, 5, -1 },
        { -1, -1, -1, -1, 7, -1, -1, 12, 9, 6, 4, -1, -1, -1, 0, -1 },
        { 5, -1, -1, -1, 13, -1, 3, 6, 11, -1, -1, -1, -1, -1, 15, 14 },
        { 4, -1, 6, -1, -1, -1, 2, 15, 12, 5, -1, -1, 9, -1, 13, -1 }, };
    
    int[][] emptySudoku = {
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 }, };
    
    
    testSudoku("filledSudoku", filledSudoku, filledSudoku);
    System.out.println("Number of Recursion calls: " + HexadecimalSudoku.numOfRecursionCalls);
    System.out.println();

    testSudoku("oneEmptyCell", oneEmptyCell, oneEmptyCell);
    System.out.println("Number of Recursion calls: " + HexadecimalSudoku.numOfRecursionCalls);
    System.out.println();

    testSudoku("fourEmptyCells", fourEmptyCells, fourEmptyCellsSolution);
    System.out.println("Number of Recursion calls: " + HexadecimalSudoku.numOfRecursionCalls);
    System.out.println();
    
    testSudoku("invalidRow", invalidRow, null);
    System.out.println("Should print out: sudoku row 0 has 1 at both positions 5 and 11");
    System.out.println();
    
    testSudoku("invalidColumn", invalidColumn, null);
    System.out.println("Should print out: sudoku column 0 has 1 at both positions 2 and 6");
    System.out.println();

    testSudoku("emptySudoku", emptySudoku, emptySudoku);
    System.out.println("Number of Recursion calls: " + HexadecimalSudoku.numOfRecursionCalls);
    System.out.println("Number of Iterations: " + HexadecimalSudoku.numOfIterations);
    System.out.println();
    
//    int ex1Count = 0;
//    for (int i = 0; i < 16; i++) {
//      for (int j = 0; j < 16; j++) {
//        if (example1[i][j] == -1) {
//          ex1Count++;
//        } 
//      }
//    }
//    
//    testSudoku("example1", example1, null);
//    System.out.println("Number of Recursion calls: " + HexadecimalSudoku.numOfRecursionCalls);
//    System.out.println();
  }
}
