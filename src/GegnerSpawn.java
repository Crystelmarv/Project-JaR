import java.awt.Color;
import java.awt.Graphics2D;

public class GegnerSpawn extends Entity
{
  GameStates gameStates;
  Item aktuellerGegner;

  public GegnerSpawn(GameStates gameStates)
  {
    this.gameStates = gameStates;
  }

  public void paint(Graphics2D g)
  {
    int blockArt = Level.blockArt(x / 64, y / 64);
    walkable = true;
    dev = true;
    
    if(blockArt == 73)
    {
      g.drawImage(Assets.wasser, x, y, null);
    }
    else
    {
      g.setColor(Color.WHITE);
      g.fillRect(x, y, 64, 64);
    }
    

  }

  public void update()
  {

  }

  public void init()
  {
    int blockArt = Level.blockArt(x / 64, y / 64);
    switch (blockArt)
    {

    case 71:
      aktuellerGegner = new GegnerMarienKaefer(gameStates, x, y);
      break;
      
    case 72:
      aktuellerGegner = new GegnerBiene(gameStates, x, y);
      break;
    case 73:
      
      aktuellerGegner = new GegnerFisch(gameStates, x, y);
      break;

    }
    gameStates.items.add(aktuellerGegner);
    aktuellerGegner.setItem(aktuellerGegner);

  }

}
