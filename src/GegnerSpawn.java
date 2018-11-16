import java.awt.Color;
import java.awt.Graphics2D;

public class GegnerSpawn extends Entity
{
  GameStates gameStats;
  Item aktuellerGegner;

  public GegnerSpawn(GameStates gameStates)
  {
    this.gameStats = gameStates;
  }

  public void paint(Graphics2D g)
  {
    walkable = true;
    g.setColor(Color.CYAN);
    g.fillRect(x, y, 64, 64);

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
      aktuellerGegner = new GegnerDachs(gameStats, x, y);
      break;

    }
    gameStats.items.add(aktuellerGegner);
    aktuellerGegner.setItem(aktuellerGegner);

  }

}
