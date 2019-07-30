
/**
 * Requirements: 
1. Take as input the size of the grid.
2. Show the live cells in a color other than black.
3. Have 3 arrays with hardcoded locations for each of the following seeds:
Block, Blinker and Glider. 
4. Show a menu with the 3 seeds option and prompt the user for the selection.
5. Include a quick video for each seed.
Suggestion: Use what you learned in Minesweeper!

NOTE: Don't forget the documentation.
 *
 * @Anna Lieb
 * @Java version 1.8.0 - 2/22/19
 */
import java.util.Scanner;
public class GameOfLife_AL
{
    public static void main(String [] args)
    {
        int n = Integer.parseInt(args[0]) + 1; // to make an n x n grid
        int mid = n/2; //the middle of the board

        int [][] block = {
                {mid, mid + 1, mid, mid + 1}, // x coordinatees
                {mid, mid, mid - 1, mid -1}, }; //y coordinates
        int [][] blinker = {
                {mid, mid + 1, mid - 1}, 
                {mid, mid, mid}, };
        int [][] glider = {
                {2, 3, 3, 2, 1}, 
                {n - 2, n - 3, n - 4, n - 4, n - 4}, };

        Scanner scan = new Scanner(System.in);
        System.out.println("Which pattern do you want to play (block, blinker, or glider)? ");
        String pattern = scan.next();
        int [][] initial = block;
        if ((pattern).equals("block"))  initial = block; 
        else if ((pattern).equals("blinker")) initial = blinker;
        else if ((pattern).equals("glider")) initial = glider;

        boolean [][] origGrid = new boolean [n][n];
        for (int i = 0; i < initial[0].length; i++) { // inputs initial state onto blank board
            origGrid[initial[0][i]][initial[1][i]] = true;
        }
        for (int i = 0; i < n; i++){ 
            for (int j = 0; j < n; j++){
                if (origGrid[i][j] == true) {
                    StdDraw.filledSquare(i + 0.5, j + 0.5, 0.5); // draws a filled square
                }
            }
        }

        while (true)  { 
            StdDraw.pause(800);
            StdDraw.clear(StdDraw.WHITE); // clears the screen

            // draws the grid
            StdDraw.setXscale(0, n - 1);
            StdDraw.setYscale(0, n - 1);
            for (int i = 0; i < n; i++) {
                StdDraw.line(0, i, n, i);
            }
            for (int i = 0; i < n; i++) {
                StdDraw.line(i, 0, i, n);
            }

            //makes a copy of the origGrid array
            boolean [][] newGrid = new boolean [n][n];
            for (int i = 0; i < n; i++){ 
                for (int j = 0; j < n; j++){
                    newGrid[i][j] = origGrid[i][j];
                }
            }
            
            for (int i = 1; i < n - 1; i++){  
                for (int j = 1; j < n - 1; j++){
                    // checks for neighboring live cells and adds them up
                    int num = 0;
                    if (origGrid[i-1][j-1] == true) num++;
                    if (origGrid[i-1][j] == true) num++;
                    if (origGrid[i-1][j+1] == true) num++;
                    if (origGrid[i][j+1] == true) num++;
                    if (origGrid[i+1][j+1] == true) num++;
                    if (origGrid[i+1][j] == true) num++;
                    if (origGrid[i+1][j-1] == true) num++;
                    if (origGrid[i][j-1] == true) num++;
                    
                    // applies the rules to new grid
                    if (num < 2) newGrid[i][j] = false; // 1. Live cells with less than two live neighbors die
                    // 2. Any live cell with two or three live neighbours lives on to the next generation
                    if (num > 3) newGrid[i][j] = false;// 3. Live cells with more than three live neighbors dies
                    if (num == 3) newGrid[i][j] = true; // 4. Dead cells with exactly three live neighbors become live cells
                }
            }
            

            //draws new board
            for (int i = 0; i < n; i++){ 
                for (int j = 0; j < n; j++){
                    if (newGrid[i][j] == true) {
                        if (i < n - 3){
                        StdDraw.filledSquare(i + 0.5, j + 0.5, 0.5);
                    }// draws a filled square
                    }
                }
            }

            for (int i = 0; i < n; i++){ // resets the original grid
                for (int j = 0; j < n; j++){
                    origGrid[i][j] = newGrid[i][j];
                }
            }
        }
    }
}