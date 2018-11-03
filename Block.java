import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Block {
	
	private Game game;
	int blockY;
	int blockX;
	int blockHöhe = 64;
	int blockBreite = 64;
	Rectangle rec;

	public Block(Game game) {
		this.game= game;
	}

	public void setBlock(int x, int y)
	{
	  blockY = y;
	  blockX = x;
	}
	
	/*public void paint(Graphics2D g) {
	  g.setColor(Color.GREEN);
		g.fillRect(blockX, blockY, blockBreite, blockHöhe);
	}
	*/
	public Rectangle getBounds() {
		
		return rec = new Rectangle(blockX, blockY, blockBreite, blockHöhe);
		
	}

}
