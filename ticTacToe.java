
import java.util.*;


public class ticTacToe{

        public static final int ROWS=3;
        public static final int COLS=3;
        public static String[][] board=new String[ROWS][COLS];
        public static Scanner in = new Scanner(System.in);
        Static String winner=null;
        


        public static void main(String args[])
        {

            initialiseBoard();




            printBoard();


        }

        public static void initialiseBoard(){

            for(int row=0;row<board.length;row++)
                for(int col=0;col<board[row].length;col++){
                    board[row][col]="";
                }

        }

        public static void printBoard(){

            for(int row=0;row<board.length;row++) {
                for (int col=0;col<board[row].length;col++) {
                    System.out.print(board[row][col] + " , ");
                }
                System.out.print("\n");
            }

        }




}
