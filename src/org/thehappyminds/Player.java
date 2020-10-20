package thehappyminds;
import java.util.Scanner;

public class Player
{
    String name_;
    Scanner input_;

    public Player(String name) {
        name_ = name;
        input_ = new Scanner(System.in);
    }

    public String getName() {
        return name_;
    }

    public void itIsYourTurn(Board board) {
        System.out.println(board.printBoard());
        System.out.println(name_ + " it's your turn: ");
        int row = input_.nextInt();
        int col = input_.nextInt();
        board.selectSquare(name_, row, col);
    }

    public void gameOver(Board.State result) {
        System.out.println("Game over");
        if (result == Board.State.TIE)
            System.out.println("It is a tie!");
        else {
            System.out.println("Somebody won!");
        }
    }
}
