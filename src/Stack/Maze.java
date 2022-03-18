package Stack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

/**
 * The Maze class used to keep track of the maze details.
 * Contains information about the starting Point, and the row/column lengths.
 * Can read a .maze file or take in a premade char[][] maze.
 * Performs a depth first search on a maze and creates a stack of nodes.
 * containing the path to follow to solve the maze from start to finish.
 *
 * @author Randeep Singh Virk
 * @version 1.0
 * @updated 2022-03-04
 * @since 2022-02-20
 */
public class Maze {
    // An array of character values corresponding to the maze.
    private char[][] charMaze;
    private Point startingPoint;
    private Stack<Point> stack;

    //region Methods

    /**
     * Constructor that converts the input file into a character-array.
     * Input file must be in src/maze_files/
     *
     * @param fileName Text file containing maze data.
     */
    public Maze(String fileName) {
        try {
            // Open the input file.
            File f = new File(fileName);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            Scanner sc = new Scanner(br.readLine());
            int rows = sc.nextInt(); // Get the number of rows of the maze
            int columns = sc.nextInt(); // Get the number of columns of the maze
            this.charMaze = new char[rows][columns]; // Create a char array of the proper size
            sc = new Scanner(br.readLine());
            int rowStart = sc.nextInt();
            int columnStart = sc.nextInt();
            startingPoint = new Point(rowStart, columnStart);
            for (int r = 0; r < rows; r++) {
                String line1 = br.readLine();
                for (int c = 0; c < columns; c++) {
                    this.charMaze[r][c] = line1.charAt(c);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    /**
     * Constructor, assigns the starting point based on the passed Row/Column values.
     * Assigns internal charMaze variable to the passed existingMaze value.
     *
     * @param startingRow    The starting point of the row.
     * @param startingColumn The starting point of the column.
     * @param existingMaze   The existing maze.
     */
    public Maze(int startingRow, int startingColumn, char[][] existingMaze) {
        if (existingMaze[startingRow][startingColumn] != ' ')
            throw new UnsupportedOperationException();

        if (startingRow <= 0 || startingColumn <= 0 || startingRow >= existingMaze.length || startingColumn >= existingMaze[0].length) {
            throw new IndexOutOfBoundsException();
        }
        startingPoint = new Point(startingRow, startingColumn);
        this.charMaze = existingMaze;
    }

    /**
     * Gets the starting point in the maze.
     *
     * @return Returns the starting point in the maze.
     */
    public Point getStartingPoint() {
        return startingPoint;
    }

    /**
     * Gets the number of rows in the maze.
     *
     * @return The number of rows in the maze.
     */
    public int getRowLength() {
        return charMaze.length;
    }

    /**
     * Gets the number of columns in the maze.
     *
     * @return The number of columns in the maze.
     */
    public int getColumnLength() {
        return charMaze[0].length;
    }

    /**
     * Returns the character array corresponding to the maze.
     *
     * @return Rectangular array of characters that correspond to input maze-file.
     */
    public char[][] getMaze() {
        return charMaze;
    }

    /**
     * Print the maze from the charArray(i.e. charMaze) private variable.
     *
     * @return Returns the string representation of the maze.
     */
    public String printMaze() {
        StringBuilder temp = new StringBuilder();
        for (int r = 0; r < charMaze.length; r++) {
            temp.append(new String(charMaze[r]));
            if (r < charMaze.length - 1) {
                temp.append("\n");
            }
        }
        System.out.print(temp);

        return temp.toString();
    }


    /**
     * The returned value of this method will be a string indicating the path to follow
     * and a print out of the maze after exploring.
     * If charMaze[p.getRow()][p.getColumn()] is blank the mark it with
     * The path to follow will be visually indicated by changing ‘V’s to ‘.’s for all nodes in your Stack.
     *
     * @return Returns value as a string indicating the path to follow from start to exit.
     */
    public String depthFirstSearch() {
        String temp;
        // Fields for the maze
        final char VISITED = 'V';
        final char PATH = '.';
        final char EXIT = 'E';
        final char HALLWAY = ' ';
//        final char WALL = 'W';
        stack = new Stack<Point>();
        stack.push(startingPoint);

        while (!stack.isEmpty()) // Until the stack is empty, continue loop.
        {
            Point p = stack.top(); // Get the top element from the Stack and assign it to p.
            if (charMaze[p.getRow()][p.getColumn()] == EXIT) {
                break;
            }
            charMaze[p.getRow()][p.getColumn()] = VISITED;
            // 4 Directional Movements (S.E.W.N.)
            // South
            if (charMaze[p.getRow() + 1][p.getColumn()] == HALLWAY || charMaze[p.getRow() + 1][p.getColumn()] == EXIT) {
                stack.push(new Point(p.getRow() + 1, p.getColumn()));
            }
            // East
            else if (charMaze[p.getRow()][p.getColumn() + 1] == HALLWAY || charMaze[p.getRow()][p.getColumn() + 1] == EXIT) {
                stack.push(new Point(p.getRow(), p.getColumn() + 1));
            }
            // West
            else if (charMaze[p.getRow()][p.getColumn() - 1] == HALLWAY || charMaze[p.getRow()][p.getColumn() - 1] == EXIT) {
                stack.push(new Point(p.getRow(), p.getColumn() - 1));
            }
            // North
            else if (charMaze[p.getRow() - 1][p.getColumn()] == HALLWAY || charMaze[p.getRow() - 1][p.getColumn()] == EXIT) {
                stack.push(new Point(p.getRow() - 1, p.getColumn()));
            }
            // Popping because it's a deadend.
            else {
                stack.pop();
            }
        }

        // If the stack is empty(i.e. size = -1) return the printed maze else return the printed maze with path to follow.
        if (stack.isEmpty()) {
            temp = "No exit found in maze!\n\n" + printMaze();
        } else {
            String path = "";
            Point exitPoint = stack.top();
            Stack<Point> reverseStack = new Stack<Point>();
            while (!stack.isEmpty()) {
                Point p = stack.pop();
                if (charMaze[p.getRow()][p.getColumn()] == VISITED) {
                    charMaze[p.getRow()][p.getColumn()] = PATH;
                }
                path = "%s\n%s".formatted(p.toString(), path);
                reverseStack.push(p);
            }
            stack = reverseStack; //Storing reverse stack to stack
            temp = "Path to follow from Start " + startingPoint + " to Exit " + exitPoint + " - " + stack.getSize() + " steps:\n" + path + printMaze();
        }

        return temp;
    }

    /**
     * Returns a stack containing the locations in the order of starting point to exit (top to bottom).
     *
     * @return Returns a stack containing the locations in the order of starting point to exit (top to bottom).
     */
    public Stack<Point> getPathToFollow() {
        Stack<Point> stackCopy = new Stack<Point>();
        try {
            if (stack == null) {
                throw new UnsupportedOperationException();
            } else {
                stackCopy = (Stack<Point>) stack.clone(); // Cloning or copying the stack before returning it.
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return stackCopy;
    }
    //endregion
}

