import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Block extends Entity
{

  private Game game;

  int blockH�he = 64;
  int blockBreite = 64;
  Rectangle rec;
  Level level;

  public Block(Game game)
  {
    this.game = game;
  }

  public void paint(Graphics2D g)
  {

    switch (blockArt(x, y))
    {
    // Wei�
    case 0:
      g.setColor(Color.WHITE);
      walkable = true;
      g.fillRect(x, y, blockBreite, blockH�he);

      break;
    // helles Blau
    case 1:
      g.setColor(new Color(0, 178, 238));
      g.fillRect(x, y, blockBreite, blockH�he);

      break;
    // Gras
    case 2:
      g.drawImage(Assets.gras, x, y, null);
      

      break;
    // Erde
    case 3:
      g.drawImage(Assets.erde, x, y, null);

      break;
    // Schwarz
    case 4:
      g.setColor(Color.BLACK);
      g.fillRect(x, y, blockBreite, blockH�he);
      break;
    // Braun
    case 5:
      g.setColor(new Color(139, 69, 19));
      g.fillRect(x, y, blockBreite, blockH�he);
      break;
      
    case 6:

      g.setColor(Color.GRAY);
      walkable = true;
      g.fillRect(x, y, blockBreite, blockH�he);

      break;

    }
   
  }

  public int blockArt(int x, int y)
  {
    int blockArt;

    blockArt = Level.map[y / 64][x / 64];
    return blockArt;

  }

  public void update()
  {

  }
}