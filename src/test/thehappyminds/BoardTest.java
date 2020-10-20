package thehappyminds;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void evaluateBoard3xFirstRowPlayer1Wins() {
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        Board board = new Board(player1, player2);
        board.selectSquare("player1", 0, 0);
        board.selectSquare("player1", 0, 1);
        board.selectSquare("player1", 0, 2);

        Board.State result = board.evaluateBoard();

        assertEquals(Board.State.PLAYER1_WIN, result);
    }
}