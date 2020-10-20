package thehappyminds;

public class Main {

    public static void main(String[] args) {
        Player play1 = new Player("Paul");
        Player play2 = new Player("Hien");
        Board gameBoard = new Board(play1, play2);
        gameBoard.play();
    }
}
