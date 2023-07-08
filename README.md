# Minesweeper Game (Terminal)

This is a simple Minesweeper game played on the terminal. The objective is to reveal all the cells on the minefield without hitting a mine. The game provides different levels of difficulty (easy, medium, and hard) and the option to play in debug mode.

## How to Play

1. Run the program to start the game.
2. Choose the level you want to play: easy, medium, or hard.
3. The minefield will be generated based on the selected level, with the number of rows, columns, and mines determined accordingly.
4. If you want to play in debug mode (displaying the complete minefield), enter "Yes" when prompted. Otherwise, enter "No".
5. The minefield will be displayed, showing the current state of revealed and hidden cells.
6. Enter the X-coordinate (row) and press enter, then enter the Y-coordinate (column) and press enter to choose a cell to reveal.
7. If the chosen coordinates are out of bounds, an "Out of bounds!" message will be displayed.
8. The game will evaluate the chosen cell and reveal its contents.
9. If the chosen cell contains a mine, the game ends, and a "You hit a mine. You lose!" message will be displayed.
10. Otherwise, the game continues, and you can choose another cell to reveal or place a flag.
11. If you choose to place a flag, enter "Yes" when prompted. The number of remaining flags will be displayed.
12. If all mines are flagged correctly, a "You won. Game Over!" message will be displayed.
13. If you run out of flags before revealing all cells, a "No more flags for you!" message will be displayed.
14. The game can be played multiple times by restarting the program.

Enjoy playing Minesweeper on the terminal!

## Dependencies

- Java SE 8 or above.

## Contributors

- Emily Mathew (mathe798)
- Asma Hassan (hassa749)
