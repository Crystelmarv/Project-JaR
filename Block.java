import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Block extends Entity {
	
	private Game game;

	int blockHöhe = 64;
	int blockBreite = 64;
	Rectangle rec;
	Level level;
	

	public Block(Game game) {
		this.game= game;
	}

	

	public void paint(Graphics2D g) {
	  
	 
	  switch (blockArt(x, y))
    {
    // Weiß
    case 0:
      g.setColor(Color.WHITE);
      walkable = true;
   
      break;
    // helles Blau
    case 1:
      g.setColor(new Color(0, 178, 238));
     
      break;
    // helles Grün
    case 2:
      g.setColor(new Color(0, 238, 0));
    
      break;
    // Gelb
    case 3:
      g.setColor(new Color(255, 255, 0));
  
      break;
    // Schwarz
    case 4:
      g.setColor(Color.BLACK);
      break;
    // Braun
    case 5:
      g.setColor(new Color(139, 69, 19));
      break;
    case 6:
      g.setColor(Color.WHITE);
      break;
      
    
      

    }
		g.fillRect(x, y, blockBreite, blockHöhe);
	}
	
	public int blockArt(int x, int y)
  {
    int blockArt;

    blockArt = Level.map[y/64][x/64];
    return blockArt;

  }
	
 public void update()
 {
 
 }
}
