
import java.util.*;


public class ticTacToe{

        public static final int ROWS=3;
        public static final int COLS=3;
        public static String[][] board=new String[ROWS][COLS];
        public static Scanner in = new Scanner(System.in);
        static String winner=null;
        static String turn;
        static int count=0;
        


        public static void main(String args[])
        {

            initialiseBoard();
            int x,y;
            turn="X";
            while(winner==null)
            {
                count++;
                if(count==10)
                {
                    printBoard();
                    System.out.println("The match is a draw");

                    break;
                }
                printBoard();
                System.out.println("User "+ turn+",please enter the coordinates(starting from 1) where you want to mark");
                 x=in.nextInt();
                 y=in.nextInt();
                 if(turn=="X"){
                     board[x-1][y-1]="X";
                 }
                 else
                 {
                     board[x-1][y-1]="O";
                 }

                 checkwin(x-1,y-1);

                 if(winner=="X"){
                     System.out.println("X won the match");
                     break;
                 }
                 else if(winner=="O") {
                     System.out.println("O won the match");
                     break;
                 }
                if(turn=="X")
                turn="O";
                else
                turn="X";


            }

        }


        public static void checkwin(int row,int col){

            if((turn.equals(board[row][0]) && turn.equals(board[row][1]) && turn.equals(board[row][2]))
                    ||(turn.equals(board[0][col]) && turn.equals(board[1][col]) && turn.equals(board[2][col]))
                    ||(row==col && turn.equals(board[0][0]) && turn.equals(board[1][1]) && turn.equals(board[2][2]))
                    ||((row+col)==2 && turn.equals(board[0][2]) && turn.equals(board[1][1]) && turn.equals(board[2][0])))
                winner=turn;

        }


        public static void initialiseBoard(){

            for(int row=0;row<board.length;row++)
                for(int col=0;col<board[row].length;col++){
                    board[row][col]=" ";
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
