import java.awt.Graphics2D;
import java.io.IOException;

public class LevelZiel extends Entity
{
 GameStates gameStats;
 Game game;
  
  public LevelZiel(GameStates game)
  {
    this.gameStats = game;
    walkable = true;
    dev = true;

  }
  
  public void paint(Graphics2D g)
  {

    g.drawImage(Assets.ziel, x, y, null);
    

  }
  
  public void update()
  {
    if(ziel() == true)
    {
      System.out.println("ZIIIIIIEL");
      Game.modus = "levelSelect";
     Game.modusSet = false;
     Game.init4 = false;
     listenLeerer();
      
    }
  }
  
  public boolean ziel()
  {
    if (gameStats.racquet.x > x - 59 && gameStats.racquet.x < x + 59 && gameStats.racquet.y - 4 == y )
    {

     return true;

    }
    return false;
    
  }
  
  public void listenLeerer()
  {
    gameStats.items.clear();
  }
}
