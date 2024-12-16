import java.awt.*;

public class Cell {
	private Player content; // Current content of the cell
	private final int row, col; // Position of the cell on the board

	// Initializes the cell's position and sets content to EMPTY
	public Cell(int row, int col) {
		this.row = row;
		this.col = col;
		clear();
	}

	// Clears the cell's content to EMPTY
	public void clear() {
		content = Player.EMPTY;
	}

	// Gets the current content of the cell
	public Player getContent() {
		return content;
	}

	// Updates the content of the cell
	public void setContent(Player content) {
		this.content = content;
	}

	// Draws the cell's content (CROSS or NOUGHT) on the board
	public void paint(Graphics g, int cellSize, int padding, int strokeWidth) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(strokeWidth));

		int x = col * cellSize + padding; // X-coordinate
		int y = row * cellSize + padding; // Y-coordinate
		int size = cellSize - 2 * padding; // Size of the symbol

		if (content == Player.CROSS) {
			g2d.setColor(Color.RED); // Red for CROSS
			g2d.drawLine(x, y, x + size, y + size);
			g2d.drawLine(x + size, y, x, y + size);
		} else if (content == Player.NOUGHT) {
			g2d.setColor(Color.BLUE); // Blue for NOUGHT
			g2d.drawOval(x, y, size, size);
		}
	}
}
