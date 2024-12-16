import java.awt.*;

public class Board {
	private final Cell[][] cells; // 2D array representing the board's cells
	private final int rows;
	private final int cols;

	// Constructor to initialize the board and its cells
	public Board(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		cells = new Cell[rows][cols];
		for (int row = 0; row < rows; ++row) {
			for (int col = 0; col < cols; ++col) {
				cells[row][col] = new Cell(row, col); // creats each cell
			}
		}
	}

	// Returns a specific cell based on its row and column
	public Cell getCell(int row, int col) {
		return cells[row][col];
	}

	// Clears all cells on the board
	public void clear() {
		for (int row = 0; row < rows; ++row) {
			for (int col = 0; col < cols; ++col) {
				cells[row][col].clear(); // Resets the cell's content
			}
		}
	}

	// Checks if the board is in a draw state (checks for no empty cells)
	public boolean isDraw() {
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				if (cells[row][col].getContent() == Player.EMPTY) {
					return false; // Found an empty cell, not a draw
				}
			}
		}
		return true; // All cells are filled
	}

	// Checks if a player has won the game
	public boolean hasWon(Player thePlayer, int playerRow, int playerCol) {
		return checkRow(thePlayer, playerRow) ||
				checkColumn(thePlayer, playerCol) ||
				checkMainDiagonal(thePlayer) ||
				checkOppositeDiagonal(thePlayer);
	}

	// Checks if a player has filled an entire row
	private boolean checkRow(Player player, int row) {
		for (int col = 0; col < cols; ++col) {
			if (cells[row][col].getContent() != player) {
				return false; // Row not fully filled by the player
			}
		}
		return true;
	}

	// Checks if a player has filled an entire column
	private boolean checkColumn(Player player, int col) {
		for (int row = 0; row < rows; ++row) {
			if (cells[row][col].getContent() != player) {
				return false; // Column not fully filled by the player
			}
		}
		return true;
	}

	// Checks if a player has filled the main diagonal
	private boolean checkMainDiagonal(Player player) {
		for (int i = 0; i < rows; ++i) {
			if (cells[i][i].getContent() != player) {
				return false; // Main diagonal not fully filled by the player
			}
		}
		return true;
	}

	// Checks if a player has filled the oppositte diagonal
	private boolean checkOppositeDiagonal(Player player) {
		for (int i = 0; i < rows; ++i) {
			if (cells[i][cols - 1 - i].getContent() != player) {
				return false; // Opposite diagonal not fully filled by the player
			}
		}
		return true;
	}

	// Paints the board and its cells on the screen
	public void paint(Graphics g, int cellSize, int padding, int strokeWidth) {
		// Draw grid lines
		g.setColor(Color.GRAY);
		for (int row = 1; row < rows; ++row) {
			g.fillRect(0, cellSize * row - strokeWidth / 2, cols * cellSize, strokeWidth);
		}
		for (int col = 1; col < cols; ++col) {
			g.fillRect(cellSize * col - strokeWidth / 2, 0, strokeWidth, rows * cellSize);
		}

		// Paint each cell
		for (int row = 0; row < rows; ++row) {
			for (int col = 0; col < cols; ++col) {
				cells[row][col].paint(g, cellSize, padding, strokeWidth);
			}
		}
	}
}
