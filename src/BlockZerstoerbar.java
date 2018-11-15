
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class BlockZerstoerbar extends Entity
{
  GameStates game;
  boolean getroffen = false;

  public BlockZerstoerbar(GameStates gameStates)
  {
    this.game = gameStates;
  }

  public void paint(Graphics2D g)
  {
    int blockArt = Level.blockArt(x / 64, y / 64);

    switch (blockArt)
    {
    // Orange Block zerst�rbar
    case 7:
      if(getroffen == true)
      {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, blockBreite, blockH�he);
      }
      else
      {
        g.drawImage(Assets.orangerBlockOhneKreuz, x, y, null);
      }
      break;
    }
  }

  public void update()
  {
    collission();

  }

  public void collission()
  {

    // System.out.println("Player" +game.racquet.y);
    // System.out.println("oben" + (y));
    // System.out.println("unten" + (y+64));
    if (game.racquet.x > x - 59 && game.racquet.x < x + 59 && game.racquet.y == y + 64)
    {

      if (getroffen == true)
      {
        zerstr�ren();
      } else
      {
        game.racquet.falling = true;
        getroffen = true;
      }

    }
  }

  private void zerstr�ren()
  {
    walkable = true;
  }

  public Rectangle getBounds()
  {
    return rec = new Rectangle(x, y, blockBreite, blockH�he);
  }
}
