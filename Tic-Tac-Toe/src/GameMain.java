import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameMain extends JPanel implements MouseListener {
	// Constants for the board size and layout
	public static final int ROWS = 3;
	public static final int COLS = 3;
	public static final int CELL_SIZE = 200;
	public static final int CANVAS_WIDTH = CELL_SIZE * COLS;
	public static final int CANVAS_HEIGHT = CELL_SIZE * ROWS;
	public static final int SYMBOL_STROKE_WIDTH = 8;
	public static final int CELL_PADDING = CELL_SIZE / 6;
	public static final String TITLE = "Tic Tac Toe";

	// Game logic and status bar for messages
	private final GameLogic gameLogic;
	private final JLabel statusBar;

	// Constructor to set up the game panel and status bar
	public GameMain() {
		gameLogic = new GameLogic(ROWS, COLS);

		statusBar = new JLabel("         ");
		statusBar.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 14));
		statusBar.setBorder(BorderFactory.createEmptyBorder(2, 5, 4, 5));
		statusBar.setOpaque(true);
		statusBar.setBackground(Color.LIGHT_GRAY);

		setLayout(new BorderLayout());
		add(statusBar, BorderLayout.SOUTH);
		setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT + 30));
		addMouseListener(this);
	}

	// Main loop method to start the game, this sets up the frame of window
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame(TITLE);
			frame.add(new GameMain());
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.pack();
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			frame.setResizable(false);
		});
	}

	// This method paints the canvas with static elements
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(Color.WHITE);
		gameLogic.getBoard().paint(g, CELL_SIZE, CELL_PADDING, SYMBOL_STROKE_WIDTH);
		updateStatusBar();
	}

	// Updates the status bar based on the game's state
	private void updateStatusBar() {
		GameState state = gameLogic.getCurrentState();
		Player player = gameLogic.getCurrentPlayer();

		if (state == GameState.PLAYING) {
			statusBar.setForeground((player == Player.CROSS) ? Color.RED : Color.BLUE);
			statusBar.setText((player == Player.CROSS) ? "X's Turn" : "O's Turn");
		} else if (state == GameState.DRAW) {
			statusBar.setForeground(Color.RED);
			statusBar.setText("It's a Draw. Click to play again.");
		} else {
			statusBar.setForeground((state == GameState.CROSS_WON) ? Color.RED : Color.BLUE);
			statusBar.setText((state == GameState.CROSS_WON) ? "X Won! Click to play again." : "O Won! Click to play again.");
		}
	}

	// Handles mouse clicks to play or restart the game
	@Override
	public void mouseClicked(MouseEvent e) {
		int row = e.getY() / CELL_SIZE;
		int col = e.getX() / CELL_SIZE;

		if (gameLogic.getCurrentState() == GameState.PLAYING) {
			if (row >= 0 && row < ROWS && col >= 0 && col < COLS && gameLogic.makeMove(row, col)) {
				repaint();
			}
		} else {
			gameLogic.initGame();
			repaint();
		}
	}

	// Overide meethods that are used to make the mouse function as expected
	@Override public void mousePressed(MouseEvent e) {}
	@Override public void mouseReleased(MouseEvent e) {}
	@Override public void mouseEntered(MouseEvent e) {}
	@Override public void mouseExited(MouseEvent e) {}
}
