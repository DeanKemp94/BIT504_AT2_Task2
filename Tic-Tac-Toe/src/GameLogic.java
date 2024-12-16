public class GameLogic {
    private final Board board; // The game board
    private GameState currentState; // Current game state
    private Player currentPlayer; // Current player's turn

    // Initializes the game board and sets up a new game
    public GameLogic(int rows, int cols) {
        board = new Board(rows, cols);
        initGame();
    }

    // getter for board
    public Board getBoard() {
        return board;
    }

    //getter for current state
    public GameState getCurrentState() {
        return currentState;
    }

    // getter for current pplayer
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    // Resets the board and starts a new game
    public void initGame() {
        board.clear();
        currentState = GameState.PLAYING;
        currentPlayer = Player.CROSS;
    }

    // Makes a move if the selected cell is empty
    public boolean makeMove(int row, int col) {
        if (board.getCell(row, col).getContent() == Player.EMPTY) {
            board.getCell(row, col).setContent(currentPlayer);
            updateGame(row, col); // Check for win or draw after the move
            switchPlayer(); // switches the player
            return true;
        }
        return false;
    }

    // Switches the turn to the other player
    private void switchPlayer() {
        currentPlayer = (currentPlayer == Player.CROSS) ? Player.NOUGHT : Player.CROSS;
    }

    // Updates the game state based on the move
    private void updateGame(int row, int col) {
        if (board.hasWon(currentPlayer, row, col)) {
            currentState = (currentPlayer == Player.CROSS) ? GameState.CROSS_WON : GameState.NOUGHT_WON;
        } else if (board.isDraw()) {
            currentState = GameState.DRAW;
        }
    }
}
