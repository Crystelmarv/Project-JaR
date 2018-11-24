import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class ItemMuenze extends Item
{
  GameStates gameStates;
  int hoehe = 50;
  int breite = 50;
  boolean aufgesammelt = false;

  public ItemMuenze(GameStates gameStates, int xp, int yp)
  {

    this.gameStates = gameStates;
    x = xp;
    y = yp-64;
    walkable = true;
    dev = true;
  }
  
  public void update()
  {
    collision();
  }
  
  public void paint(Graphics2D g)
  {
    g.drawImage(Assets.apfel, x, y, null);

  }
  
  public Rectangle getBounds()
  {
    return rec = new Rectangle(x, y, breite, hoehe);
  }

  private boolean collision()
  {

    if (gameStates.racquet.getBounds().intersects(getBounds()))
    {

      if(aufgesammelt == false)
      {
       
        gameStates.muenze.muenzePlus();
        
        gameStates.items.remove(aktuellesItem);
        System.out.println("COIIIIN");
        aufgesammelt = true;
      }
     
     
    }
    return true;
  }
}
