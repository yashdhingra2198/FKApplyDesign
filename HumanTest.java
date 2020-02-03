import junit.framework.TestCase;
import org.junit.runner.Computer;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class HumanTest extends TestCase {

    public HumanTest()
    {

    }

    public void testAdd()
    {
        //testing the 3*3 normal tictactoe game
        //testing the initialiseBoard function
        //testing the printBoard function
        //testing the checkwin function

        manager.board = new String[3][3][3];
        manager managerObject = new manager();
        managerObject.initialiseBoard();
        managerObject.printBoard();

        ticTacToe.sizeofmatrix = 3;
        manager.turn="X";

        managerObject.board[0][0][0]="X";
        managerObject.board[0][0][1]="X";
        managerObject.board[0][0][2]="X";
        managerObject.board[0][1][1]="O";
        managerObject.board[0][1][2]="O";
        managerObject.printBoard();
        managerObject.checkwin(0,0);
        System.out.println(managerObject.winner);
        assert(managerObject.winner.equals("X"));

    }



}
