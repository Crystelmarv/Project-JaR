import java.awt.Color;
import java.awt.Graphics2D;

public class LevelCheckpoint extends Entity
{
  GameStates gameStats;
  boolean checkpointSet = false;

  public LevelCheckpoint(GameStates game)
  {
    this.gameStats = game;
    walkable = true;
    dev = true;
  }

  public void paint(Graphics2D g)
  {
    g.setColor(Color.WHITE);
    g.fillRect(x, y, 64, 64);
    g.drawImage(Assets.checkPointUnten, x, y, null);
    
    if(checkpointSet == false)
    {
      g.drawImage(Assets.checkPointObenAus, x, y -64,null);
    }
    if(checkpointSet == true)
    {
      g.drawImage(Assets.checkPointObenAn, x, y -64,null);
    }
    
  }

  public void update()
  {

    if (checkpoint() == true)
    {
      if (checkpointSet == false)
      {
        checkpointSet = true;
        gameStats.racquet.checkpointX = x;
        gameStats.racquet.checkpointY = y;
      }

    }
  }

  public boolean checkpoint()
  {
    if (gameStats.racquet.x > x - 59 && gameStats.racquet.x < x + 59 && gameStats.racquet.y - 4 == y)
    {

      return true;

    }
    return false;

  }
}
