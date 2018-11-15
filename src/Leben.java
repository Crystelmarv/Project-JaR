import java.awt.Color;
import java.awt.Graphics2D;

public class Leben
{
  int maxLeben = 5;
  int aktuelleLeben = 3;
  float[] x;
  float y;
  GameStates game;
  boolean tot = false;
  boolean timerSetzen = false;

  boolean test = false;

  public Leben(GameStates game)
  {
    this.game = game;

    x = new float[maxLeben];
  }

  public void paint(Graphics2D g)
  {
    int i;

    Graphics2D g2d = (Graphics2D) g;

    if (tot == true)
    {
      g.setColor(Color.BLACK);
      g.fillRect(0, 0, 999999, 99999);
    }

    for (i = 0; i < aktuelleLeben; i++)
    {
      g.setColor(Color.RED);
      g.fillOval((int) x[i], (int) y, 64, 64);
    }
  }

  public void update(Racquet player)
  {
    // x--;

    x[0] = player.getX() - 500 + Game.WIDTH / 2;
    y = player.getY() - 500 + Game.WIDTH / 2;
    x[1] = x[0] + 100;
    x[2] = x[1] + 100;
    x[3] = x[2] + 100;
    x[4] = x[3] + 100;

    tot();

  }

  private void tot()
  {
    if (aktuelleLeben <= 0)
    {
      tot = game.racquet.sterben();

    }

  }

  public void lebenAbziehen()
  {
    if (game.racquet.angreifbar == true)
    {
      aktuelleLeben--;
      timerSetzen = true;
      game.racquet.nichtAngreifbar();
    }

  }

  public void lebenPlus()
  {
    if (aktuelleLeben < maxLeben)
    {
      aktuelleLeben++;

    }

  }
}
