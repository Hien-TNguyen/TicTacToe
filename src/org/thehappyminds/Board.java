package thehappyminds;
import java.security.InvalidParameterException;


public class Board
{
    char[][] board_ = new char[3][3];
    Player player1_;
    Player player2_;


    // enum is group of named constant
    public enum State {
        PLAYER1_WIN, PLAYER2_WIN, TIE, NOT_YET_FINISH;
    }

    public Board(Player player1, Player player2) {
        player1_ = player1;
        player2_ = player2;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                board_[i][j] = ' ';
            }
        }
    }

    public String printBoard() {
        StringBuilder strBoard = new StringBuilder();
        strBoard.append(" " + board_[0][0] + " | " + board_[0][1] + " | " + board_[0][2] + "\n");
        strBoard.append("- - - - - - \n");
        strBoard.append(" " + board_[1][0] + " | " + board_[1][1] + " | " + board_[1][2]+ "\n");
        strBoard.append("- - - - - - \n");
        strBoard.append(" " + board_[2][0] + " | " + board_[2][1] + " | " + board_[2][2]+ "\n");

        return strBoard.toString();
    }

    // Evaluate the board
    // return one of 4 results: player1 win, player2 win, tie or the game is not finished.
    public State evaluateBoard() {
        // if there is a 3 consecutive 'x' in any direction player1 win
        // if there is a 3 consecutive 'o' in any direction play2 win
        for (int i = 0; i < 3; i++) {
            if ((board_[i][0] ==  'x' && board_[i][1] == 'x' && board_[i][2] == 'x') ||
                    (board_[0][i] == 'x' && board_[1][i] == 'x' && board_[2][i] == 'x') ||
                    (board_[0][0] == 'x' && board_[1][1] == 'x' && board_[2][2] == 'x') ||
                    (board_[0][2] == 'x' && board_[1][1] == 'x' &&board_[2][0] == 'x'))
                return State.PLAYER1_WIN;
            if ((board_[i][0] == 'o' && board_[i][1] == 'o' && board_[i][2] == 'o')||
                    (board_[0][i] == 'o' && board_[1][i] == 'o' && board_[2][i] == 'o') ||
                    (board_[0][0] == 'o' && board_[1][1] == 'o' && board_[2][2] == 'o') ||
                    (board_[0][2] == 'o' && board_[1][1] == 'o' && board_[2][0] == 'o'))
                return State.PLAYER2_WIN;
                // the game is not yet finished
                // check if all the square is filled or not, if it is filled and no winner => tie
                // else not yet finished
            else {
                for (int j = 1; j < 3; j++){
                    if (board_[i][j] == ' ')
                        return State.NOT_YET_FINISH;
                }
            }
        }
        return State.TIE;
    }

    public void play() {
        while(true) {
            if (evaluateBoard() != State.NOT_YET_FINISH
                    || evaluateBoard() == State.PLAYER1_WIN
                    || evaluateBoard() == State.PLAYER2_WIN) {
                break;
            }
            player1_.itIsYourTurn(this);
            if (evaluateBoard() != State.NOT_YET_FINISH
                    || evaluateBoard() == State.PLAYER1_WIN
                    || evaluateBoard() == State.PLAYER2_WIN) {
                break;
            }
            player2_.itIsYourTurn(this);
        }
        State result = evaluateBoard();
        player1_.gameOver(result);
        player2_.gameOver(result);
    }

    public void selectSquare(String name, int row, int col) {
        char mark = ' ';
        if (name == player1_.getName()) {
            mark = 'x';
        }
        if (name == player2_.getName())
            mark = 'o';
        if ((row < 0 || row >= 3) || (col < 0 || col >=3)) {
            throw new InvalidParameterException("select square");
        }
        board_[row][col] = mark;
    }
}
