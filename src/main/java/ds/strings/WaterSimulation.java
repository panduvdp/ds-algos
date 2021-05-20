package ds.strings;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
//Square
This is a 2D "waterfall" simulation problem (really more like simulating sand).

Given a grid that looks something like:

| . . . . |
| ##  #   |
|   # ##  |
|         |
-----------

|.     .  |
| ##. # . |
|   # ##  |
|         |
-----------
Where '.' represents a particle of water, ' ' represents empty space, and any other character is an obstacle, iteratively simulate the particles falling.

Water should move downward if there is a space below it, and randomly either left or right otherwise (assuming there is nothing in the way).
 */

class WaterSimulation {
  static final String[] input = {
    "| . . . . |", "| ##  #   |", "|   # ##  |", "|         |", "-----------"
  };

  public static void simulateWaterFall(final String[] input) {
    if (input == null || input.length == 0) {
      return;
    }

    final char[][] grid = new char[input.length][input[0].length()];
    for (int i = 0; i < input.length; i++) {
      grid[i] = input[i].toCharArray();
    }
    simulateFall(grid);
  }

  private static void simulateFall(final char[][] grid) {
    final int maxIterations = 5;

    final List<Integer[]> particlePositions = new ArrayList<>();

    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == '.') {
          final List<Integer> position = new ArrayList<>();
          position.add(i);
          position.add(j);
          particlePositions.add(new Integer[] {i, j});
        }
      }
    }

    if (particlePositions.size() == 0) {
      return;
    }
    System.out.println(particlePositions.size());

    final Random random = new Random();

    for (int i = 0; i < maxIterations; i++) {
      for (int k = 0; k < particlePositions.size(); k++) {
        final Integer[] position = particlePositions.get(k);
        final int row = position[0];
        final int col = position[1];

        if (row + 1 < grid.length && grid[row + 1][col] == ' ') {
          grid[row][col] = ' ';
          grid[row + 1][col] = '.';
          position[0] = row + 1;

        } else {
          if (canMoveRight(grid, row, col) && canMoveLeft(grid, row, col)) {
            final int randInt = random.nextInt(2);
            if (randInt == 1) {
              moveLeft(grid, row, col);
              position[1] = col - 1;
            } else {
              moveRight(grid, row, col);
              position[1] = col + 1;
            }
          } else if (canMoveLeft(grid, row, col)) {
            moveLeft(grid, row, col);
            position[1] = col - 1;
          } else if (canMoveRight(grid, row, col)) {
            moveRight(grid, row, col);
            position[1] = col + 1;
          }
        }
        particlePositions.set(k, position);
      }
      printGrid(grid);
    }
  }

  private static boolean canMoveLeft(final char[][] grid, final int r, final int c) {
    return c - 1 > -1 && grid[r][c - 1] == ' ';
  }

  private static boolean canMoveRight(final char[][] grid, final int row, final int col) {
    return col + 1 < grid[0].length && grid[row][col + 1] == ' ';
  }

  private static boolean moveLeft(final char[][] grid, final int r, final int c) {
    if (canMoveLeft(grid, r, c)) {
      grid[r][c] = ' ';
      grid[r][c - 1] = '.';
      return true;
    }
    return false;
  }

  private static boolean moveRight(final char[][] grid, final int row, final int col) {
    if (canMoveRight(grid, row, col)) {
      grid[row][col] = ' ';
      grid[row][col + 1] = '.';
      return true;
    }
    return false;
  }

  private static void printGrid(final char[][] grid) {
    for (int i = 0; i < grid.length; i++) {
      System.out.println(grid[i]);
    }
    System.out.println("\n\n\n");

    /// Hi Garett, I lost your connection, could you please call me back>
  }

  public static void main(final String[] args) {
    final ArrayList<String> strings = new ArrayList<>();
    strings.add("Hello, World!");
    strings.add("Welcome to CoderPad.");
    simulateWaterFall(input);
  }
}
