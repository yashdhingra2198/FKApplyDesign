import java.util.*;
import java.lang.Math;

interface gameplay
{
    void play();
}
interface managegame
{
    void checkwin(int row,int col);
    void initialiseBoard();
    void printBoard();
}

class human implements gameplay
{
    Scanner in = new Scanner(System.in);
    public void play() {
        int x, y;

        while(true)
        {
            System.out.println("User " + manager.turn + ",please enter the (x,y) coordinates(starting from 1) where you want to mark.");
            x = in.nextInt();
            y = in.nextInt();
            manager.x = x;
            manager.y = y;
            if (manager.board[0][x - 1][y - 1].equals("X") ||manager.board[0][x - 1][y - 1].equals("O")) {
                System.out.println("Invalid coordinates. Please try again.");
                continue;
            } else
                break;
        }
        if (manager.turn == "X") {
            manager.board[0][x - 1][y - 1] = "X";
        } else {
            manager.board[0][x - 1][y - 1] = "O";
        }
    }
}

class computer implements gameplay
{
    public void play()
    {
        int flag=0;


        for (int i = 0; i < ticTacToe.sizeofmatrix; i++)
        {
            for (int j = 0; j < ticTacToe.sizeofmatrix; j++)
            {
                if ((manager.board[0][i][j]).equals(" "))
                {
                    manager.board[0][i][j] = "X";
                    manager.x=i+1;
                    manager.y=j+1;
                    flag=1;
                    break;
                }
            }
            if(flag==1)
                break;
        }
        System.out.println("Computer has marked on ("+manager.x+","+manager.y+") coordinates.");
    }
}

class manager implements managegame
{
    static String[][][] board=new String[ticTacToe.sizeofmatrix][ticTacToe.sizeofmatrix][ticTacToe.sizeofmatrix ];
    String winner=null;
    static String turn;
    static int x,y;
    static int flag=0;
    static int n=0;
         public void checkwin(int row,int col)
         {
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
                 for( i=startrow;i<startrow+var;i++)
                 {
                     val = val && turn.equals(board[n][i][col]);
                 }
                 if(val==false&&row==col)
                 {
                     val=true;
                     for( i=startrow;i<startrow+var;i++)
                     {
                         val = val && turn.equals(board[n][i][i]);
                     }
                 }
                 else if(val==false&&row+col==((var*var)-1))
                 {
                     val=true;
                     int jj=startcol+var-1;
                     for(i=startrow;i<ticTacToe.sizeofmatrix;i++)
                     {
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
         public void initialiseBoard()
         {
             for(int n=0;n<board.length;n++)
            for(int row=0;row<board[n].length;row++)
                for(int col=0;col<board[n][row].length;col++){
                    board[n][row][col]=" ";
                }
         }
         public void printBoard()
         {
            for(int row=0;row<board[0].length;row++)
            {
                 for (int col=0;col<board[0][row].length;col++)
                 {
                     System.out.print(" "+board[0][row][col] + " |");
                 }
                 System.out.print("\n");
             }
             System.out.print("\n\n");

        }
}
class supermanager implements managegame
{
    static int rowsize=2*ticTacToe.edgesizeofhexagon-1;
    static int colsize=4*ticTacToe.edgesizeofhexagon-3;
    static String[][] board=new String[rowsize][colsize];
    String winner=null;
    static String turn;
    static int x,y;
    public void checkwin(int row,int col)
    {
        boolean val=true;
        rowcheck(row,col);
        diagonal1check(row,col);
        diagonal2check(row,col);
    }
    public void rowcheck(int row,int col)
    {
        boolean val;
        int start=col-6>=0?col-6:((col-4>=0)?col-4:(col-2>=0?col-2:col));
        int end=start+6;
        for(int index=start;index<=col;index+=2)
        {
            val=true;
            if(end<colsize)
            for(int colindex=index;colindex<=end;colindex+=2)
            {
                val=val&&(board[row][colindex].equals(turn));
            }
            end+=2;
            if(val==true&&end<colsize)
            {
                winner=turn;
                return ;
            }
        }
    }
    public void diagonal1check(int row,int col)
    {
        boolean val;
        int startcol=col-3>=0?col-3:((col-2>=0)?col-2:(col-1>=0?col-1:col));
        int startrow=row-3>=0?row-3:((row-2>=0)?row-2:(row-1>=0?row-1:row));
        int endcol=startcol+3;
        int endrow=startrow+3;

        for(int check=startrow;check<=row;check++)
        {
            val=true;
            if (endcol < colsize && endrow < rowsize)
                for (int indexr = startrow, indexc = startcol; indexr <= endrow&&indexc <= endcol;indexr++ , indexc++)
                    {
                        val = val && (board[indexr][indexc].equals(turn));
                    }
            if (val == true&&endcol < colsize && endrow < rowsize)
            {
                winner = turn;
                return;
            }
            endcol++;
            endrow++;
            startcol++;
            startrow++;
        }
    }
    public void diagonal2check(int row,int col)
    {
        boolean val;
        int startcol=row-3>=0?col+3:((row-2>=0)?col+2:(row-1>=0?col+1:col));
        int startrow=row-3>=0?row-3:((row-2>=0)?row-2:(row-1>=0?row-1:row));
        int endcol=startcol-3;
        int endrow=startrow+3;

        for(int check=startrow;check<=row;check++)
        {
            val=true;
            if (endcol >=0 && endrow < rowsize)
                for (int indexr = startrow, indexc = startcol; indexr <= endrow&&indexc >= endcol;indexr++ , indexc--)
                {
                    val = val && (board[indexr][indexc].equals(turn));
                }
            if (val == true&&endcol >=0 && endrow < rowsize)
            {
                winner = turn;
                return;
            }
            endcol--;
            endrow++;
            startcol--;
            startrow++;
        }
    }
    public void initialiseBoard()
    {
            for(int row=0;row<ticTacToe.edgesizeofhexagon-1;row++)
            {
                for(int col=0;col<ticTacToe.edgesizeofhexagon-1-row;col++)
                {
                    board[row][col]="-";
                }
            }
            for(int row=ticTacToe.edgesizeofhexagon;row<rowsize;row++)
            {
                for(int col=0;col<row-ticTacToe.edgesizeofhexagon+1;col++)
                {
                    board[row][col]="-";
                }
            }
            for(int row=0;row<ticTacToe.edgesizeofhexagon-1;row++)
            {
                for(int col=colsize-ticTacToe.edgesizeofhexagon+1+row;col<colsize;col++)
                {
                    board[row][col]="-";
                }
            }
            int x=1;
            for(int row=rowsize-ticTacToe.edgesizeofhexagon+1;row<rowsize;row++)
            {

                for(int col=colsize-x;col<colsize;col++)
                {
                    board[row][col]="-";
                    x++;
                }
            }
        int s=ticTacToe.edgesizeofhexagon-1;
        int e=s+2*ticTacToe.edgesizeofhexagon-1;
            for(int row=0;row<ticTacToe.edgesizeofhexagon;row++)
            {
                for (int col = s; col < e; col+=2) {
                    board[row][col]=" ";
                    if(col<e-1)
                    board[row][col+1]="-";
                }
                s--;
                e++;
            }
        int start=1;
        int end=colsize-1;
        for(int row=ticTacToe.edgesizeofhexagon;row<rowsize;row++)
        {
            for (int col = start; col < end; col+=2)
            {
                board[row][col]=" ";
                if(col<e-1)
                board[row][col+1]="-";
            }
            start++;
            end--;
        }
    }
    public void printBoard()
    {
        for(int row=0;row<board.length;row++)
        {
            for (int col=0;col<board[row].length;col++)
            {
                System.out.print(" "+board[row][col] + " |");
            }
            System.out.print("\n\n");
        }
    }
}
class superhuman implements gameplay
{
    Scanner in = new Scanner(System.in);
    public void play()
    {
        int x, y;

        while(true)
        {
            System.out.println("User " + supermanager.turn + ",please enter the (x,y) coordinates(starting from 1) where you want to mark. Note: Provide coordinates for empty cell only.");
            x = in.nextInt();
            y = in.nextInt();
            supermanager.x = x;
            supermanager.y = y;
            if (supermanager.board[x - 1][y - 1] == "-") {
                System.out.println("Invalid coordinates. Please try again.");
                continue;
            }
            else
                break;
        }
        if (supermanager.turn == "X")
        {
            supermanager.board[x - 1][y - 1] = "X";
        }
        else
            {
                supermanager.board[x - 1][y - 1] = "O";
            }
    }
}
class supercomputer implements gameplay
{
    public void play()
    {
        int flag=0,i,j;


        for (i = 0; i < supermanager.rowsize; i++)
        {
            for (j = 0; j <supermanager.colsize; j++)
            {
                if ((supermanager.board[i][j]).equals(" "))
                {
                    supermanager.board[i][j] = "X";
                    supermanager.x=i+1;
                    supermanager.y=j+1;
                    flag=1;
                    break;
                }
            }
            if(flag==1)
                break;
        }
        System.out.println("Computer has marked on ("+supermanager.x+","+supermanager.y+") coordinates.");
    }
}
class ticTacToe
{
    static int sizeofmatrix;
    static int edgesizeofhexagon;
    static int countP1,countP2;
    static Scanner in = new Scanner(System.in);
        public static void main(String args[])
        {

            System.out.println("Enter 1 to play normal tictactoe or 2 to play hexagonal tictactoe.");
            int key=in.nextInt();
            if(key==1)
            tictactoegame();
            else if(key==2)
                supertictactoe();
        }
     public static void supertictactoe()
     {
         superhuman h = new superhuman();
         supercomputer m = new supercomputer();

         while(true) {
             System.out.println("Enter the size of edge of hexagonal matrix(size of edge should be greater than 2).");
             edgesizeofhexagon = in.nextInt();

             if (edgesizeofhexagon < 3) {
                 System.out.println("Try again with valid size!!!");
                 continue;
             }
             else
                 break;

         }
         supermanager supobj=new supermanager();
         int numberOfTurns;
         int key;
         while (true)
         {

             while (true)
             {
                 System.out.println("Press 1 for human vs human game or 2 to play with computer and 3 for exit.");
                 key = in.nextInt();
                 if (key == 3)
                     System.exit(0);
                 if (key == 1 || key == 2)
                 {
                     supobj.turn="X";
                     numberOfTurns=0;
                     break;
                 }
             }
             supobj.initialiseBoard();
             supobj.winner=null;
             while (Objects.isNull(supobj.winner))
             {
                 if (numberOfTurns == 0)
                     supobj.printBoard();
                 numberOfTurns++;
                 if (numberOfTurns == edgesizeofhexagon*6 + 1)
                 {
                     supobj.printBoard();
                     System.out.println("The match is a draw!!!");
                     break;
                 }
                 if (numberOfTurns == 1)
                     supobj.turn = "X";
                 if (key == 1 || numberOfTurns % 2 == 0)
                 {

                     h.play();
                 }
                 if (key == 2 && numberOfTurns % 2 == 1)
                 {

                     m.play();
                 }
                 supobj.checkwin((supobj.x) - 1, (supobj.y) - 1);
                 supobj.printBoard();
                 if (supobj.winner == "X")
                 {
                     countP1++;
                     System.out.println("X won the match!!!");
                     if (supobj.winner == "X" || supobj.winner == "O")
                         System.out.println("Leaderboard: player X score" + countP1 + " player 2 score " + countP2);
                     break;
                 } else if (supobj.winner == "O")
                 {
                     countP2++;
                     System.out.println("O won the match!!!");
                     if (supobj.winner == "X" || supobj.winner == "O")
                         System.out.println("Leaderboard: player X score " + countP1 + " player 0 score " + countP2);
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

        System.out.println("Enter the size of matrix (size should either be 4 or multiples of 3.");
        sizeofmatrix = in.nextInt();
        int numberOfTurns = 0;
        //manager obj=new manager();
        manager obj = new manager();
        human h = new human();
        computer m = new computer();
        int key;
        while (true)
        {
            while (true)
            {
                System.out.println("Press 1 for human vs human game or 2 to play with computer and 3 for exit.");
                key = in.nextInt();
                if (key == 3)
                    System.exit(0);
                if (key == 1 || key == 2){
                    numberOfTurns=0;
                    break;
                }
            }

            obj.initialiseBoard();
            obj.winner=null;
            while (Objects.isNull(obj.winner))
            {
                if (numberOfTurns == 0)
                    obj.printBoard();
                numberOfTurns++;
                if (numberOfTurns == sizeofmatrix * sizeofmatrix + 1)
                {
                    obj.printBoard();
                    System.out.println("The match is a draw!!!");
                    break;
                }
                if (numberOfTurns == 1)
                    obj.turn = "X";
                if (key == 1 || numberOfTurns % 2 == 0)
                {

                    h.play();
                }
                if (key == 2 && numberOfTurns % 2 == 1)
                {

                    m.play();
                }
                obj.checkwin((obj.x) - 1, (obj.y) - 1);
                obj.printBoard();
                if (obj.winner == "X")
                {
                    countP1++;
                    System.out.println("X won the match!!!");
                    if (obj.winner == "X" || obj.winner == "O")
                        System.out.println("Leaderboard: player 1(X) score: " + countP1 + " player 2(O) score: " + countP2);
                    break;
                } else if (obj.winner == "O")
                {
                    countP2++;
                    System.out.println("O won the match!!!");
                    if (obj.winner == "X" || obj.winner == "O")
                        System.out.println("Leaderboard: player 1(X) score: " + countP1 + " player 2(O) score: " + countP2);
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
