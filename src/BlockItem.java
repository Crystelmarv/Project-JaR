import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class BlockItem extends Entity
{
  GameStates game;
  boolean getroffen = false;
  boolean itemGespawned = false;
  ItemLebenPlus itemLebenPlus;
  Item aktuellesItem;

  public BlockItem(GameStates gameStates)
  {
    this.game = gameStates;
  }

  public void paint(Graphics2D g)
  {
    // int blockArt = Level.blockArt(x / 64, y / 64);
    // switch (blockArt)
    // {
    // Orange Block zerstörbar
    // case 10:
    if (getroffen == true)
    {

      g.drawImage(Assets.blauerBlock, x, y, null);

    } else
    {
      g.drawImage(Assets.orangerBlock, x, y, null);
    }

    /*
     * if(itemGespawned == true) { itemLebenPlus.paint(g); }
     */
    // break;
    // }
  }

  public void update()
  {
    collission();

    int blockArt = Level.blockArt(x / 64, y / 64);

    // Leben Plus

    if (itemGespawned == false && getroffen)
    {
      switch (blockArt)
      {
      case 51:
        
        aktuellesItem = new ItemLebenPlus(game, x, y);
        
        break;
        
      case 52:
        aktuellesItem = new ItemMuenze(game, x, y);
        break;
      case 54:
        aktuellesItem = new ItemFeuer(game, x, y);
        break;
        
      }
      game.items.add(aktuellesItem);
      aktuellesItem.setItem(aktuellesItem);
      aktuellesItem.update();
      
      itemGespawned = true;
    }
    
   
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

      } else
      {
        game.racquet.falling = true;
        getroffen = true;
      }

    }
  }

  public Rectangle getBounds()
  {
    return rec = new Rectangle(x, y, blockBreite, blockHöhe);
  }
}
