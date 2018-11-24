import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class BlockSchild extends Entity
{

  GameStates gameStats;
  boolean interaction = false;
  int index;

  public BlockSchild(GameStates game)
  {
    this.gameStats = game;

  }

  public void paint(Graphics2D g)
  {

    walkable = true;
    dev = true;

    if (interaction == true)
    {
      Font test = new Font("Arial", Font.BOLD, 20);
      g.setFont(test);
      g.setColor(Color.red);
      g.drawString(LevelFileReader.schildText[index], x + 10, y - 10);
      g.drawImage(Assets.schild, x, y, null);

    } else
    {
      g.drawImage(Assets.schild, x, y, null);
    }

  }

  public void setIndex(int i)
  {
   index = i;
    
  }

  public void update()
  {
    interaction();
  }

  public void interaction()
  {
    if (gameStats.racquet.x > x - 59 && gameStats.racquet.x < x + 59 && gameStats.racquet.y - 4 == y
        && gameStats.racquet.interaction == true)
    {

      interaction = true;

    }
  }

}
