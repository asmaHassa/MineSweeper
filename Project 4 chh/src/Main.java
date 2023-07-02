
//written by Emily Mathew, mathe798
//written by Asma Hassan, hassa749
import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        Scanner myScanner = new Scanner(System.in);
        System.out.println("Welcome to Minesweeper! Choose the level which you want to play, easy, medium, or hard!");
        String level = myScanner.nextLine();
        int rows = 0;
        int columns = 0;
        int flags = 0;

        int mines = 0;

        if(level.equals("easy")) {//sets the rows and columns for each level
            rows = 5;
            columns = 5;
            flags = 5;
            mines = 5;

        } else if (level.equals("medium")) {
            rows = 9;
            columns = 9;
            flags = 12;
            mines = 12;
        } else if (level.equals("hard")) {
            rows = 20;
            columns = 20;
            flags = 40;
            mines = 40;
        }
        else{
            System.out.println("Invalid input. Try Again");
            return;
        }
        System.out.println("Would you like to play in debug mode? Yes or No:");
        String debug = myScanner.nextLine();
        Minefield mine = new Minefield(rows, columns, flags);
        System.out.println(mine);
        System.out.println("Enter a coordinate X, press enter, then enter a coordinate Y, then press enter:");
        String xcor = myScanner.nextLine();
        String ycor = myScanner.nextLine();
        int x = Integer.parseInt(xcor);
        int y = Integer.parseInt(ycor);
        if(x < 0 || x > rows || y < 0 || y > columns){
            System.out.println("Out of bounds!");
        }
        mine.createMines(x, y, mine.flags);
        mine.evaluateField();
        mine.revealMines(x, y);
        boolean guess = false;
        while (mine.gameOver() && guess == false) {
            if (debug.equals("Yes")) {//prints debug board as well as regular board
                mine.printMinefield();

            }
            System.out.println(mine);
            System.out.println("Enter a coordinate X(row), press enter, then enter a coordinate Y(column), then press enter:");
            xcor = myScanner.nextLine();
            ycor = myScanner.nextLine();
            x = Integer.parseInt(xcor);
            y = Integer.parseInt(ycor);
            mine.currBoard[x][y].setRevealed(true);

                System.out.println(mine);
                System.out.println("Do you want to place a flag? Yes or No; Remaining :" + mine.flags);
                String value = myScanner.nextLine();
                if (value.equals("Yes")) {
                    mine.guess(x, y, true);
                    mine.flags -= 1;
                    mine.currBoard[x][y].setStatus("F");
                    System.out.println(mine);
                    if (mine.flags == 0) {
                        System.out.println("No more flags for you!");
                    }
                }
                 else {
                    guess = mine.guess(x, y, false);
                    if (guess == true) {
                        System.out.println("mine found");
                        break;
                    }
                }



        }
            if(guess){
                System.out.println("You hit a mine. You lose!");
            }
            else{
                System.out.println("You won. Game Over!");
            }
        }

    }
