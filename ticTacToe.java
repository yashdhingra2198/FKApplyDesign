
import java.util.*;

interface gameplay{
    void play();
}

class human implements gameplay{

    Scanner in = new Scanner(System.in);
    public void play() {
        int x, y;

        manager managerObj = new manager();

        System.out.println("User " + managerObj.turn + ",please enter the (x,y) coordinates(starting from 1) where you want to mark");
        x = in.nextInt();
        y = in.nextInt();
        managerObj.x=x;
        managerObj.y=y;
        if (managerObj.turn == "X") {
            managerObj.board[x - 1][y - 1] = "X";
        } else {
            managerObj.board[x - 1][y - 1] = "O";
        }

    }

}

class computer implements gameplay{


    public void play() {
        int flag=0;
        manager managerObj = new manager();
        for (int i = 0; i < ticTacToe.sizeofmatrix; i++) {
            for (int j = 0; j < ticTacToe.sizeofmatrix; j++) {
                if ((managerObj.board[i][j]).equals(" ")) {
                    managerObj.board[i][j] = "X";
                    managerObj.x=i+1;
                    managerObj.y=j+1;
                    flag=1;
                    break;

                }


            }
            if(flag==1)
                break;
        }
    }


}

class manager{

    static String[][] board=new String[ticTacToe.sizeofmatrix][ticTacToe.sizeofmatrix ];

        String winner=null;
    static String turn;
    static int x,y;


         void checkwin(int row,int col){

             boolean val=true;
             for(int j=0;j<ticTacToe.sizeofmatrix;j++)
             {
                 val = val && turn.equals(board[row][j]);
             }
             if(val==false)
             {
                 int i;
                 val=true;
                 for( i=0;i<ticTacToe.sizeofmatrix;i++) {
                     val = val && turn.equals(board[i][col]);
                 }
                 if(val==false&&row==col)
                 {
                     val=true;
                     for( i=0;i<ticTacToe.sizeofmatrix;i++) {
                         val = val && turn.equals(board[i][i]);
                     }
                 }
                 else if(val==false&&row+col==2)
                 {
                     val=true;
                     int jj=ticTacToe.sizeofmatrix-1;
                     for(i=0;i<ticTacToe.sizeofmatrix;i++) {

                         val = val && turn.equals(board[i][jj]);
                         jj--;
                     }
                 }
             }
             if(val==true)
                 winner=turn;




            /*if((turn.equals(board[row][0]) && turn.equals(board[row][1]) && turn.equals(board[row][2]))
                    ||(turn.equals(board[0][col]) && turn.equals(board[1][col]) && turn.equals(board[2][col]))
                    ||(row==col && turn.equals(board[0][0]) && turn.equals(board[1][1]) && turn.equals(board[2][2]))
                    ||((row+col)==2 && turn.equals(board[0][2]) && turn.equals(board[1][1]) && turn.equals(board[2][0])))
                winner=turn;*/

        }


         void initialiseBoard(){

            for(int row=0;row<board.length;row++)
                for(int col=0;col<board[row].length;col++){
                    board[row][col]=" ";
                }

        }

         void printBoard(){
            for(int row=0;row<board.length;row++) {
                for (int col=0;col<board[row].length;col++) {
                    System.out.print(board[row][col] + " , ");
                }
                System.out.print("\n");
            }

        }
}

class ticTacToe {

    static int sizeofmatrix;

        public static void main(String args[])
        {

            Scanner in = new Scanner(System.in);

            System.out.println("Enter the size of matrix");
            sizeofmatrix=in.nextInt();

            int numberOfTurns=0;
            //manager obj=new manager();
            int key;
            while(true) {
                while(true) {
                    System.out.println("Press 1 for human vs human game or 2 to play with computer and 3 for exit");
                    key = in.nextInt();
                    if (key == 3)
                        System.exit(0);
                    if(key==1||key==2)
                        break;
                }
                manager obj=new manager();
                obj.initialiseBoard();
                while (Objects.isNull(obj.winner)) {
                    numberOfTurns++;
                    if (numberOfTurns == sizeofmatrix * sizeofmatrix + 1) {
                        obj.printBoard();
                        System.out.println("The match is a draw");

                        break;
                    }
                    obj.printBoard();
                    if (numberOfTurns == 1)
                        obj.turn = "X";
                    if (key == 1 || numberOfTurns % 2 == 0) {

                        human h = new human();
                        h.play();
                    }
                    if (key == 2 && numberOfTurns % 2 == 1) {
                        computer m = new computer();
                        m.play();
                    }

                    obj.checkwin((obj.x) - 1, (obj.y) - 1);

                    if (obj.winner == "X") {
                        System.out.println("X won the match");
                        break;
                    } else if (obj.winner == "O") {
                        System.out.println("O won the match");
                        break;
                    }
                    if (obj.turn == "X")
                        obj.turn = "O";
                    else
                        obj.turn = "X";
                }
            }

        }

}
