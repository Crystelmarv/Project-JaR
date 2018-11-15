import java.awt.Graphics2D;

public class LevelCheckpoint extends Entity
{
  GameStates gameStats;
  boolean checkpointSet = false;

  public LevelCheckpoint(GameStates game)
  {
    this.gameStats = game;
    walkable = true;
  }

  public void paint(Graphics2D g)
  {
    g.drawImage(Assets.orangerBlockOhneKreuz, x, y, null);
  }

  public void update()
  {

    if (checkpoint() == true)
    {
      if (checkpointSet == false)
      {
        checkpointSet = true;
        gameStats.racquet.checkpointX = x;
        gameStats.racquet.checkpointY = y;
      }

    }
  }

  public boolean checkpoint()
  {
    if (gameStats.racquet.x > x - 59 && gameStats.racquet.x < x + 59 && gameStats.racquet.y - 4 == y)
    {

      return true;

    }
    return false;

  }
}
