import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class LevelSpawn extends Entity
{
  GameStates gameStats;
  
  public LevelSpawn(GameStates game)
  {
    this.gameStats = game;
    walkable = true;

  }
  
  public void paint(Graphics2D g)
  {

  

    
    
    g.drawImage(Assets.gras, x, y, null);
    

  }
  
}
