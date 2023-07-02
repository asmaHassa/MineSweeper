//written by Emily Mathew, mathe798
//written by Asma Hassan, hassa749
import java.util.Random;

public class Minefield {
    /**
     * Global Section
     */
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE_BRIGHT = "\u001b[34;1m";
    public static final String ANSI_BLUE = "\u001b[34m";
    public static final String ANSI_RED_BRIGHT = "\u001b[31;1m";
    public static final String ANSI_RED = "\u001b[31m";
    public static final String ANSI_GREEN = "\u001b[32m";
    public static final String ANSI_GREY_BG = "\u001b[0m";
    public static final String MAGENTA = "\u001b[35m";

    public static final String CYAN = "\u001b[36m";

    public static final String LAVENDER = "\u001b[38;5;147m";

    public static final String AQUA = "\u001b[38;2;145;231;255m";

    public static final String PINK = "\u001b[38;5;201m";

    public static final String RED_YELLOW = "\u001b[31;43m";

    public static final String BRIGHT_BLUE = "\u001b[34;1m";

    public static final String BRIGHT_GREEN = "\u001b[32;1m";


    /**
     * Constructor
     *
     * @param rows       Number of rows.
     * @param columns    Number of columns.
     * @param flags      Number of flags, should be equal to mines
     */
    int rows;
    int columns;
    int flags;


    Cell[][] currBoard;

    public Minefield(int rows, int columns, int flags) {

        currBoard = new Cell[rows][columns];
        this.rows = rows;
        this.columns = columns;
        this.flags = flags;
        for (int i = 0; i < currBoard.length; i++) {
            for (int j = 0; j < currBoard.length; j++) {
                currBoard[i][j] = new Cell(false, " - ");
            }
        }


    }

    /**
     * evaluateField
     *
     * @function When a mine is found in the field, calculate the surrounding 9x9 tiles values. If a mine is found, increase the count for the square.
     */

    public void evaluateField() {
        int count = 0;
        for (int i = 0; i < currBoard.length; i++) {
            for (int j = 0; j < currBoard.length; j++) {
                if ((!currBoard[i][j].getStatus().equals("M"))) {//checks if it dosen't equal a mine
                    for (int k = i - 1; k <= i + 1; k++) {
                        for (int l = j - 1; l <= j + 1; l++) {
                            if (k < rows && k >= 0 && l < columns && l >= 0) {
                                if (currBoard[k][l].getStatus().equals("M")) {
                                    count++;
                                }
                            }


                        }
                    }
                    String mines = Integer.toString(count);//sets the surrounding 9 by 9 titles
                    currBoard[i][j].setStatus(mines);
                    count = 0;

                }

            }
        }
    }

    /**
     * createMines
     *
     * @param x     Start x, avoid placing on this square.
     * @param y     Start y, avoid placing on this square.
     * @param mines Number of mines to place.
     * @return
     */
    public void createMines(int x, int y, int mines) {//ask
        while (mines > 0) {
            Random row = new Random();//makes randomly generated rows and columns
            Random col = new Random();
            int mineRow = row.nextInt(rows) ;
            int mineCol = col.nextInt(columns);
            if (mineRow != x && mineCol != y) {
                currBoard[mineRow][mineCol].setStatus("M");
                mines--;
            }
            //new coordinates
        }
    }

    /**
     * guess
     *
     * @param x    The x value the user entered.
     * @param y    The y value the user entered.
     * @param flag A boolean value that allows the user to place a flag on the corresponding square.
     * @return boolean Return false if guess did not hit mine or if flag was placed, true if mine found.
     */
    public boolean guess(int x, int y, boolean flag) {
        if (x < 0 || x > rows || y < 0 || y > columns) {//checks if it's out of bounds
            return false;
        }
        if (flag) {
            if (flags > 0) {
                if (currBoard[x][y].getStatus() == "F") {
                   currBoard[x][y].setStatus("F");//sets coordinates status to F
                   currBoard[x][y].setRevealed(true);
                   return false;
                }

            }
        }
        if (currBoard[x][y].equals('0')) {//checks if user hit cell with 0 status
            revealZeroes(x, y);
            return false;
        }
        if (currBoard[x][y].getStatus() == "M") {//if user hits a mine
            currBoard[x][y].getRevealed();

            return true;
        }

        return false;

    }

    /**
     * gameOver
     *
     * @return boolean Return false if game is not over and squares have yet to be revealed, otheriwse return true.
     */
    public boolean gameOver() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (currBoard[i][j].getStatus().equals("M")) {
                    return true;//returns true if user hit a mine, saying game over
                }
            }
        }
        return false;
    }

    /**
     * revealField
     * <p>
     * This method should follow the psuedocode given.
     * Why might a stack be useful here rather than a queue?
     *
     * @param x The x value the user entered.
     * @param y The y value the user entered.
     */
    public void revealZeroes(int x, int y) {
        Stack1Gen<int[]> stack = new Stack1Gen();
        currBoard[x][y].setRevealed(true);//sets cell revealed attibute to true
        stack.push(new int[]{x, y});
        //add x and y into the stack
        while (stack.top() != null) {//loops until stack is empty
            int[] temp = stack.top();//gets top element off the stack
            if (temp[0] >= 0 && currBoard[temp[0] - 1][temp[1]].getStatus().equals("0") && currBoard[temp[0] + 1][temp[1]].getRevealed() == false && !currBoard[temp[0] + 1][temp[1]].getStatus().equals("0")) {
                stack.push(new int[]{temp[0] - 1, temp[1]});
            }//for each if statements, it pushes all valid neighbor coordinates to the stack, by searching up(+) down(-) side to side
            if (temp[0] >= 0 && currBoard[temp[0] + 1][temp[1]].getStatus().equals("0") && currBoard[temp[0] + 1][temp[1]].getRevealed() == false && !currBoard[temp[0] + 1][temp[1]].getStatus().equals("0")) {
                stack.push(new int[]{temp[0] + 1, temp[1]});
            }
            if (temp[0] >= 0 && currBoard[temp[0]][temp[1] - 1].getStatus().equals("0") && currBoard[temp[0]][temp[1] - 1].getRevealed() == false && !currBoard[temp[0]][temp[1] - 1].getStatus().equals("0")) {
                stack.push(new int[]{temp[0], temp[1] - 1});
            }
            if (temp[0] >= 0 && currBoard[temp[0]][temp[1] + 1].getStatus().equals("0") && currBoard[temp[0]][temp[1] + 1].getRevealed() == false && !currBoard[temp[0]][temp[1] + 1].getStatus().equals("0")) {
                stack.push(new int[]{temp[0], temp[1] + 1});
            }
        }
    }


    /**
     * revealMines
     * <p>
     * This method should follow the psuedocode given.
     * Why might a queue be useful for this function?
     *
     * @param x The x value the user entered.
     * @param y The y value the user entered.
     */
    public void revealMines(int x, int y) {
        Q1Gen<int[]> queue = new Q1Gen();
        queue.add(new int[]{x, y});
        while (queue.length() != 0) {//loops until queue is empty
            int[] temp = queue.remove();//dequeues front of queue
            currBoard[temp[0]][temp[1]].setRevealed(true);
            if (currBoard[temp[0]][temp[1]].getStatus().equals("M")) {//if current cell is at finish point, break the loop
                break;
            }
            if (temp[0] - 1 >= 0 && temp[0] - 1 < rows && temp[1] >= 0 && temp[1] < columns && currBoard[temp[0] - 1][temp[1]].getStatus().equals("-")) {
                queue.add(new int[]{temp[0] - 1, temp[1]});
            }//for each if statement checks all valid neighbors, and enqueues all valid neighbors
            if (temp[0] + 1 >= 0 && temp[0] + 1 < rows && temp[1] >= 0 && temp[1] < columns && currBoard[temp[0] + 1][temp[1]].getStatus().equals("-") && currBoard[temp[0] + 1][temp[1]].getRevealed() == false && !currBoard[temp[0] + 1][temp[1]].getStatus().equals("0")) {
                queue.add(new int[]{temp[0] + 1, temp[1]});
            }
            if (temp[0] >= 0 && temp[0] < rows && temp[1] - 1 >= 0 && temp[1] - 1 < columns && currBoard[temp[0]][temp[1] - 1].getStatus().equals("-") && currBoard[temp[0]][temp[1] - 1].getRevealed() == false && !currBoard[temp[0]][temp[1] - 1].getStatus().equals("0")) {
                queue.add(new int[]{temp[0], temp[1] - 1});
            }
            if (temp[0] >= 0 && temp[0] < rows && temp[1] + 1 >= 0 && temp[1] + 1 < columns && currBoard[temp[0]][temp[1] + 1].getStatus().equals("-") && currBoard[temp[0]][temp[1] + 1].getRevealed() == false && !currBoard[temp[0]][temp[1] + 1].getStatus().equals("0")) {
                queue.add(new int[]{temp[0], temp[1] + 1});
            }
        }

    }

    /**
     * revealStart
     *
     * @param x The x value the user entered.
     * @param y The y value the user entered.
     */
    public void revealStart(int x, int y) {
        currBoard[x][y].setRevealed(true);
        if (currBoard[x][y].getStatus().equals("0")) {
            revealZeroes(x, y);

        }
    }

    /**
     * printMinefield
     *
     * @fuctnion This method should print the entire minefield, regardless if the user has guessed a square.
     * *This method should print out when debug mode has been selected.
     */
    public void printMinefield() {//
        String output = "   ";
        for (int j = 0; j < columns; j++) {
            output+= j + "  ";//loops through the columns, and adds column numbers
        }
        output+="\n";
        for (int i = 0; i < rows; i++) { //loops through the rows, and adds row numbers
            output += i + "  ";
//                if (i == rows) {
//                    output += "\n";

            for (int j = 0; j < columns; j++) { //col
                if(j >= 9){//checks to see if rows reach double digits, then lessen the space
                    output+= " ";
                }
                if (currBoard[i][j].getStatus().equals("0")) {//for each statement, changes the color for each digit on board
                    output += ANSI_YELLOW + " " + currBoard[i][j].getStatus() + " " + ANSI_GREY_BG;
                }
                else if (currBoard[i][j].getStatus().equals("1")) {
                    output += ANSI_GREEN + " " + currBoard[i][j].getStatus() + " " + ANSI_GREY_BG;
                }
                else if (currBoard[i][j].getStatus().equals("2")) {
                    output += ANSI_BLUE + " " + currBoard[i][j].getStatus() + " " + ANSI_GREY_BG;
                }
                else  if (currBoard[i][j].getStatus().equals("3")) {
                    output += ANSI_RED + " " + currBoard[i][j].getStatus() + " " + ANSI_GREY_BG;
                }
                else if (currBoard[i][j].getStatus().equals("4")) {
                    output += ANSI_RED_BRIGHT + " " + currBoard[i][j].getStatus() + " " + ANSI_GREY_BG;
                }
                else if (currBoard[i][j].getStatus().equals("5")) {
                    output += CYAN + " " + currBoard[i][j].getStatus() + " " + ANSI_GREY_BG;
                }
                else if (currBoard[i][j].getStatus().equals("6")) {
                    output += RED_YELLOW + " " + currBoard[i][j].getStatus() + " " + ANSI_GREY_BG;
                }
                else if (currBoard[i][j].getStatus().equals("7")) {
                    output += AQUA + " " + currBoard[i][j].getStatus() + " " + ANSI_GREY_BG;
                }
                else if (currBoard[i][j].getStatus().equals("8")) {
                    output += MAGENTA + " " + currBoard[i][j].getStatus() + " " + ANSI_GREY_BG;
                }
                else if (currBoard[i][j].getStatus().equals("9")) {
                    output += PINK + " " + currBoard[i][j].getStatus() + " " + ANSI_GREY_BG;
                }
                else if (currBoard[i][j].getStatus().equals("M")) {
                    output += BRIGHT_GREEN + " " + currBoard[i][j].getStatus() + " " + ANSI_GREY_BG;
                }
                else if (currBoard[i][j].getStatus().equals("F")) {
                    output += BRIGHT_BLUE + " " + currBoard[i][j].getStatus() + " " + ANSI_GREY_BG;
                }



            }

            output += "\n";

        }

        System.out.println(output);

    }

    /**
     * toString
     *
     * @return String The string that is returned only has the squares that has been revealed to the user or that the user has guessed.
     */
    public String toString() {
        String output = "   ";
        for (int j = 0; j < columns; j++) {
            if(j < 10) {
                output += j + "  ";
            }
            if(j > 9){
                output+= j + "  ";
            }
        }
        output+= "\n";
        for (int i = 0; i < rows; i++) { //row
                output+= i + " ";
            for (int j = 0; j < columns; j++) { //col
                if(j >= 10){
                    output += " ";
                }
                if (currBoard[i][j].getRevealed()) {
                    if (currBoard[i][j].getStatus().equals("0")) {
                        output += ANSI_YELLOW + " " + currBoard[i][j].getStatus() + " " + ANSI_GREY_BG;
                    }
                    else if (currBoard[i][j].getStatus().equals("1")) {
                        output += ANSI_GREEN + " " + currBoard[i][j].getStatus() + " " + ANSI_GREY_BG;
                    }
                    else if (currBoard[i][j].getStatus().equals("2")) {
                        output += ANSI_BLUE + " " + currBoard[i][j].getStatus() + " " + ANSI_GREY_BG;
                    }
                    else  if (currBoard[i][j].getStatus().equals("3")) {
                        output += ANSI_RED + " " + currBoard[i][j].getStatus() + " " + ANSI_GREY_BG;
                    }
                    else if (currBoard[i][j].getStatus().equals("4")) {
                        output += ANSI_RED_BRIGHT + " " + currBoard[i][j].getStatus() + " " + ANSI_GREY_BG;
                    }
                    else if (currBoard[i][j].getStatus().equals("5")) {
                        output += CYAN + " " + currBoard[i][j].getStatus() + " " + ANSI_GREY_BG;
                    }
                    else if (currBoard[i][j].getStatus().equals("6")) {
                        output += RED_YELLOW + " " + currBoard[i][j].getStatus() + " " + ANSI_GREY_BG;
                    }
                    else if (currBoard[i][j].getStatus().equals("7")) {
                        output += AQUA + " " + currBoard[i][j].getStatus() + " " + ANSI_GREY_BG;
                    }
                    else if (currBoard[i][j].getStatus().equals("8")) {
                        output += MAGENTA + " " + currBoard[i][j].getStatus() + " " + ANSI_GREY_BG;
                    }
                    else if (currBoard[i][j].getStatus().equals("9")) {
                        output += PINK + " " + currBoard[i][j].getStatus() + " " + ANSI_GREY_BG;
                    }
                    else if (currBoard[i][j].getStatus().equals("M")) {
                        output += BRIGHT_GREEN + " " + currBoard[i][j].getStatus() + " " + ANSI_GREY_BG;
                    }
                    else if (currBoard[i][j].getStatus().equals("F")) {
                        output += BRIGHT_BLUE + " " + currBoard[i][j].getStatus() + " " + ANSI_GREY_BG;
                    }
                    else{
                        output+="-";

                    }

                }
                else{
                    output+=" - ";
                }

            }

            output += "\n";
        }
        return output;
    }

}




