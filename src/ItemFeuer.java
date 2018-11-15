import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class ItemFeuer extends Item
{
  GameStates gameStates;
  int hoehe = 50;
  int breite = 50;
  boolean aufgesammelt = false;

  public ItemFeuer(GameStates gameStates, int xp, int yp)
  {

    this.gameStates = gameStates;
    x = xp;
    y = yp - hoehe;
    walkable = true;
  }

  public void update()
  {
    collision();
  }

  public void paint(Graphics2D g)
  {
    g.setColor(Color.ORANGE);
    g.fillRect(x, y, breite, hoehe);
  }

  public Rectangle getBounds()
  {
    return rec = new Rectangle(x, y, breite, hoehe);
  }

  private boolean collision()
  {

    if (gameStates.racquet.getBounds().intersects(getBounds()))
    {

      if (aufgesammelt == false)
      {

        gameStates.racquet.aktivesItem = "feuer";

        gameStates.items.remove(aktuellesItem);

        aufgesammelt = true;
      }

    }
    return true;
  }
}
