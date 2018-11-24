import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class LevelTod extends Entity
{
  GameStates gameStates;

  public LevelTod(GameStates game)
  {
    this.gameStates = game;
    walkable = true;
    dev = true;

  }

  public void paint(Graphics2D g)
  {

    g.setColor(Color.black);
    g.fillRect(x, y, 64, 64);

  }
  
  public void kontakt()
  {
    if (gameStates.racquet.getBounds().intersects(getBounds()))
    {
      gameStates.racquet.respawnAtCheckpoint();
      gameStates.leben.lebenAbziehen();
    }
  }
  
  public void update()
  {
    kontakt();
  }
  

}
