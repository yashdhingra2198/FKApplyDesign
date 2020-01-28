import java.util.*;
import java.lang.Math;
interface gameplay{
    void play();
}
interface managegame{
    void checkwin(int row,int col);
    void initialiseBoard();
    void printBoard();
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
            managerObj.board[0][x - 1][y - 1] = "X";
        } else {
            managerObj.board[0][x - 1][y - 1] = "O";
        }
    }
}

class computer implements gameplay{

    public void play() {
        int flag=0;
        manager managerObj = new manager();
        for (int i = 0; i < ticTacToe.sizeofmatrix; i++) {
            for (int j = 0; j < ticTacToe.sizeofmatrix; j++) {
                if ((managerObj.board[0][i][j]).equals(" ")) {
                    managerObj.board[0][i][j] = "X";
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

class manager implements managegame{

    static String[][][] board=new String[ticTacToe.sizeofmatrix][ticTacToe.sizeofmatrix][ticTacToe.sizeofmatrix ];
    String winner=null;
    static String turn;
    static int x,y;
    static int flag=0;
    static int n=0;
         public void checkwin(int row,int col){
             int startrow=row;
             int startcol=col;
             int var=3;
             if(ticTacToe.sizeofmatrix==4)
                 var=4;
             while(startrow%var!=0)
             {
                 startrow--;
             }
             while(startcol%var!=0)
             {
                 startcol--;
             }
            boolean val=true;

             for(int j=startcol;j<startcol+var;j++)
             {
                 val = val && turn.equals(board[n][row][j]);
             }
             if(val==false)
             {
                 int i;
                 val=true;
                 for( i=startrow;i<startrow+var;i++) {
                     val = val && turn.equals(board[n][i][col]);
                 }

                 if(val==false&&row==col)
                 {
                     val=true;
                     for( i=startrow;i<startrow+var;i++) {
                         val = val && turn.equals(board[n][i][i]);
                     }
                 }
                 else if(val==false&&row+col==((var*var)-1))
                 {
                     val=true;
                     int jj=startcol+var-1;
                     for(i=startrow;i<ticTacToe.sizeofmatrix;i++) {
                         val = val && turn.equals(board[n][i][jj]);
                         jj--;
                     }
                 }
             }
             if(val==true&&ticTacToe.sizeofmatrix==4)
             {
                 winner=turn;
                 return ;
             }
             if(val==true)
             {
                if(board[n+1][startrow/3][startcol/3].equals(" "))
                 board[n+1][startrow/3][startcol/3]=board[n][row][col];
                 n++;
                 checkwin(startrow/3,startcol/3);
                 n--;
             }
             if(val==true&&ticTacToe.sizeofmatrix==(int)Math.pow(3,n+1))
                 winner=turn;
        }

         public void initialiseBoard(){
             for(int n=0;n<board.length;n++)
            for(int row=0;row<board[n].length;row++)
                for(int col=0;col<board[n][row].length;col++){
                    board[n][row][col]=" ";
                }
        }
         public void printBoard(){

            for(int row=0;row<board[0].length;row++) {
                 for (int col=0;col<board[0][row].length;col++) {
                     System.out.print(" "+board[0][row][col] + " |");
                 }
                 System.out.print("\n\n");
             }

            /* for(int row=0;row<board[1].length;row++) {
                 for (int col=0;col<board[1][row].length;col++) {
                     System.out.print("|_"+board[1][row][col] + "_|");
                 }
                 System.out.print("\n");
             }*/
        }
}

class supermanager implements managegame{

    static int rowsize=2*ticTacToe.edgesizeofhexagon-1;
    static int colsize=4*ticTacToe.edgesizeofhexagon-3;
    static String[][] board=new String[rowsize][colsize];
    String winner=null;
    static String turn;
    static int x,y;
    static int flag=0;
    static int n=0;
    public void checkwin(int row,int col){
        boolean val=true;
  //rowCheck
        if(col+2<colsize&&board[row][col+2].equals(turn))
        {
            if(col+4<colsize&&board[row][col+4].equals(turn))
            {
                if(col+6<colsize&&board[row][col+6].equals(turn))
                {
                    winner=turn;
                    return ;
                }
                else if(col-2>=0&&board[row][col-2].equals(turn))
                {
                    winner=turn;
                    return;
                }
            }
            else if(col-2>=0&&board[row][col-2].equals(turn))
            {
                if(col-4>=0&&board[row][col-4].equals(turn))
                {
                    winner=turn;
                    return;
                }
            }
        }
        else if(col-2>=0&&board[row][col-2].equals(turn))
        {
            if(col-4>=0&&board[row][col-4].equals(turn))
            {
                if(col-6>=0&&board[row][col-6].equals(turn))
                {
                    winner=turn;
                    return;
                }
            }
        }
    //diagonal1Check
        if(col+1<colsize&&row+1<rowsize&&board[row+1][col+1].equals(turn))
        {
            if(col+2<colsize&&row+2<rowsize&&board[row+2][col+2].equals(turn))
            {
                if(col+3<colsize&&row+3<rowsize&&board[row+3][col+3].equals(turn))
                {
                    winner=turn;
                    return ;
                }
                else if(col-1>=0&&row-1>=0&&board[row-1][col-1].equals(turn))
                {
                    winner=turn;
                    return;
                }
            }
            else if(col-1>=0&&row-1>=0&&board[row-1][col-1].equals(turn))
            {
                if(col-2>=0&&row-2>=0&&board[row-2][col-2].equals(turn))
                {
                    winner=turn;
                    return;
                }
            }
        }
        else if(col-1>=0&&row-1>=0&&board[row-1][col-1].equals(turn))
        {
            if(col-2>=0&&row-2>=0&&board[row-2][col-2].equals(turn))
            {
                if(col-3>=0&&row-3>=0&&board[row-3][col-3].equals(turn))
                {
                    winner=turn;
                    return;
                }
            }
        }

        //diagonal2Check

        if(col-1>=0&&row+1<rowsize&&board[row+1][col-1].equals(turn))
        {
            if(col-2>=0&&row+2<rowsize&&board[row+2][col-2].equals(turn))
            {
                if(col-3>=0&&row+3<rowsize&&board[row+3][col-3].equals(turn))
                {
                    winner=turn;
                    return ;
                }
                else if(col+1<colsize&&row-1>=0&&board[row-1][col+1].equals(turn))
                {
                    winner=turn;
                    return;
                }
            }
            else if(col+1<colsize&&row-1>=0&&board[row-1][col+1].equals(turn))
            {
                if(col+2<colsize&&row-2>=0&&board[row-2][col+2].equals(turn))
                {
                    winner=turn;
                    return;
                }
            }
        }
        else if(col+1<colsize&&row-1>=0&&board[row-1][col+1].equals(turn))
        {
            if(col+2<colsize&&row-2>=0&&board[row-2][col+2].equals(turn))
            {
                if(col+3<colsize&&row-3>=0&&board[row-3][col+3].equals(turn))
                {
                    winner=turn;
                    return;
                }
            }
        }




    }

    public void initialiseBoard(){


            for(int row=0;row<ticTacToe.edgesizeofhexagon-1;row++)
            {
                for(int col=0;col<ticTacToe.edgesizeofhexagon-1-row;col++)
                {
                    board[row][col]="*";
                }
            }
            for(int row=ticTacToe.edgesizeofhexagon;row<rowsize;row++)
            {
                for(int col=0;col<row-ticTacToe.edgesizeofhexagon+1;col++)
                {
                    board[row][col]="*";
                }
            }
            for(int row=0;row<ticTacToe.edgesizeofhexagon-1;row++)
            {
                for(int col=colsize-ticTacToe.edgesizeofhexagon+1+row;col<colsize;col++)
                {
                    board[row][col]="*";
                }
            }
        int x=1;
            for(int row=rowsize-ticTacToe.edgesizeofhexagon+1;row<rowsize;row++)
            {

                for(int col=colsize-x;col<colsize;col++)
                {
                    board[row][col]="*";
                    x++;
                }
            }


        int s=ticTacToe.edgesizeofhexagon-1;
        int e=s+2*ticTacToe.edgesizeofhexagon-1;

            for(int row=0;row<ticTacToe.edgesizeofhexagon;row++) {



                for (int col = s; col < e; col+=2) {
                    board[row][col]=" ";
                    if(col<e-1)
                    board[row][col+1]="*";

                }
                s--;
                e++;
            }
        int start=1;
        int end=colsize-1;
        for(int row=ticTacToe.edgesizeofhexagon;row<rowsize;row++) {

            for (int col = start; col < end; col+=2) {
                board[row][col]=" ";
                if(col<e-1)
                board[row][col+1]="*";
            }
            start++;
            end--;
        }
    }
    public void printBoard(){

        for(int row=0;row<board.length;row++) {
            for (int col=0;col<board[row].length;col++) {
                System.out.print(" "+board[row][col] + " |");
            }
            System.out.print("\n\n");
        }

            /* for(int row=0;row<board[1].length;row++) {
                 for (int col=0;col<board[1][row].length;col++) {
                     System.out.print("|_"+board[1][row][col] + "_|");
                 }
                 System.out.print("\n");
             }*/
    }


}

class superhuman implements gameplay{

    Scanner in = new Scanner(System.in);
    public void play() {
        int x, y;
        supermanager supmanobj = new supermanager();
        while(true) {
            System.out.println("User " + supmanobj.turn + ",please enter the (x,y) coordinates(starting from 1) where you want to mark");
            x = in.nextInt();
            y = in.nextInt();
            supmanobj.x = x;
            supmanobj.y = y;
            if (supmanobj.board[x - 1][y - 1] == "*") {
                System.out.println("invalid coordinates");
                continue;
            }
            else
                break;
        }

        if (supmanobj.turn == "X") {
            supmanobj.board[x - 1][y - 1] = "X";
        } else {
            supmanobj.board[x - 1][y - 1] = "O";
        }
    }

}


class supercomputer implements gameplay{

    public void play() {
        int flag=0;
        supermanager supmanagerObj = new supermanager();
        System.out.println("computer chance of marking ");
        for (int i = 0; i < supmanagerObj.rowsize; i++) {
            for (int j = 0; j <supmanagerObj.colsize; j++) {
                if ((supmanagerObj.board[i][j]).equals(" ")) {
                    supmanagerObj.board[i][j] = "X";
                    supmanagerObj.x=i+1;
                    supmanagerObj.y=j+1;
                    flag=1;
                    break;
                }
            }
            if(flag==1)
                break;
        }
    }
}


class ticTacToe {
    static int sizeofmatrix;
    static int edgesizeofhexagon;
    static int countP1,countP2;
        public static void main(String args[]) {
            Scanner in = new Scanner(System.in);
            System.out.println("enter 1 to play normal tictactoe or 2 to play hexagonal tictactoe");
            int key=in.nextInt();
            if(key==1)
            tictactoegame();
            else if(key==2)
                supertictactoe();

        }


     public static void supertictactoe()
     {
         Scanner in = new Scanner(System.in);
         System.out.println("Enter the size of edge of hexagonal matrix");
         edgesizeofhexagon=in.nextInt();
         int numberOfTurns;
         int key;
         while (true) {
             supermanager supobj=new supermanager();
             while (true) {
                 System.out.println("Press 1 for human vs human game or 2 to play with computer and 3 for exit");
                 key = in.nextInt();
                 if (key == 3)
                     System.exit(0);
                 if (key == 1 || key == 2) {
                     supobj.turn="X";
                     numberOfTurns=0;
                     break;
                 }
             }

             supobj.initialiseBoard();
             while (Objects.isNull(supobj.winner)) {
                 if (numberOfTurns == 0)
                     supobj.printBoard();
                 numberOfTurns++;
                 if (numberOfTurns == edgesizeofhexagon*6 + 1) {
                     supobj.printBoard();
                     System.out.println("The match is a draw");
                     break;
                 }
                 if (numberOfTurns == 1)
                     supobj.turn = "X";
                 if (key == 1 || numberOfTurns % 2 == 0) {
                     superhuman h = new superhuman();
                     h.play();
                 }
                 if (key == 2 && numberOfTurns % 2 == 1) {
                     supercomputer m = new supercomputer();
                     m.play();
                 }
                 supobj.checkwin((supobj.x) - 1, (supobj.y) - 1);
                 supobj.printBoard();

                 if (supobj.winner == "X") {
                     countP1++;
                     System.out.println("X won the match");
                     if (supobj.winner == "X" || supobj.winner == "O")
                         System.out.println("leaderboard: player X score" + countP1 + " player 2 score " + countP2);
                     break;
                 } else if (supobj.winner == "O") {
                     countP2++;
                     System.out.println("O won the match");
                     if (supobj.winner == "X" || supobj.winner == "O")
                         System.out.println("leaderboard: player X score " + countP1 + " player O score " + countP2);
                     break;
                 }

                 if (supobj.turn == "X")
                     supobj.turn = "O";
                 else
                     supobj.turn = "X";

             }




         }
     }


    public static void tictactoegame()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the size of matrix");
        sizeofmatrix = in.nextInt();
        int numberOfTurns = 0;
        //manager obj=new manager();
        int key;
        while (true) {
            while (true) {
                System.out.println("Press 1 for human vs human game or 2 to play with computer and 3 for exit");
                key = in.nextInt();
                if (key == 3)
                    System.exit(0);
                if (key == 1 || key == 2)
                    break;
            }
            manager obj = new manager();
            obj.initialiseBoard();
            while (Objects.isNull(obj.winner)) {
                if (numberOfTurns == 0)
                    obj.printBoard();
                numberOfTurns++;
                if (numberOfTurns == sizeofmatrix * sizeofmatrix + 1) {
                    obj.printBoard();
                    System.out.println("The match is a draw");
                    break;
                }
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
                obj.printBoard();

                if (obj.winner == "X") {
                    countP1++;
                    System.out.println("X won the match");
                    if (obj.winner == "X" || obj.winner == "O")
                        System.out.println("leaderboard: player X score" + countP1 + " player 2 score " + countP2);
                    break;
                } else if (obj.winner == "O") {
                    countP2++;
                    System.out.println("O won the match");
                    if (obj.winner == "X" || obj.winner == "O")
                        System.out.println("leaderboard: player X score " + countP1 + " player O score " + countP2);
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
