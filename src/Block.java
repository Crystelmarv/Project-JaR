import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Block extends Entity
{

  private GameStates game;

  int blockHöhe = 64;
  int blockBreite = 64;
  boolean vorhaneden = false;
  Rectangle rec;
  Level level;
  Item aktuellesItem;

  public Block(GameStates game)
  {
    this.game = game;
  }

  public void paint(Graphics2D g)
  {
    // EIGENTLICH MUSS JA NICHT MMER ABGEFRAGT WWERDEN ICH KÖNNTE IENFACH EINE
    // VARIABLE IN DER DIE BEIM ERSTELLEN DES ONJEKTES DIE NUMMER GESPICHERT WIRD
    switch (Level.blockArt(x / 64, y / 64))
    {
    // Weiß
    case 20:
      g.setColor(Color.WHITE);
      walkable = true;
      g.fillRect(x, y, blockBreite, blockHöhe);

      break;
    // helles Blau
    case 21:
      g.setColor(new Color(0, 178, 238));
      g.fillRect(x, y, blockBreite, blockHöhe);

      break;
    // Gras
    case 2:
      g.drawImage(Assets.gras, x, y, null);

      break;
    // Erde
    case 3:
      g.drawImage(Assets.erde, x, y, null);

      break;
    // Schwarz
    case 4:
      g.setColor(Color.BLACK);
      g.fillRect(x, y, blockBreite, blockHöhe);
      break;
    // Braun
    case 5:
      g.setColor(new Color(139, 69, 19));
      g.fillRect(x, y, blockBreite, blockHöhe);
      break;

    case 26:

      g.setColor(Color.GRAY);
      walkable = true;
      g.fillRect(x, y, blockBreite, blockHöhe);

      break;

    case 53:
      g.setColor(Color.WHITE);
      walkable = true;
      g.fillRect(x, y, blockBreite, blockHöhe);

      if (vorhaneden == false)
      {
        aktuellesItem = new ItemMuenze(game, x, y);
        game.items.add(aktuellesItem);
        aktuellesItem.setItem(aktuellesItem);
        aktuellesItem.update();
        vorhaneden = true;
      }

      break;

    }

  }

  /*
   * public int blockArt(int x, int y) { int blockArt;
   * 
   * blockArt = Level.map[y / 64][x / 64]; return blockArt;
   * 
   * }
   */

  public void update()
  {
    if (vorhaneden == true)
    {
      aktuellesItem.update();
    }

  }
}
