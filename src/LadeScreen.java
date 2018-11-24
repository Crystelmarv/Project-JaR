import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.IOException;

public class LadeScreen
{
  private String levelName = "#ERROR levelName";
  double timeStart;
  boolean geladen = false;
  GameStates gameStates;

  public LadeScreen(GameStates gameStates)
  {
    timeStart = System.nanoTime() / 1000;
    this.gameStates = gameStates;
    levelName = LevelFileReader.getLevelName();

  }

  public void update() throws IOException
  {
    double timeNow = System.nanoTime() / 1000;
    System.out.println(timeNow - timeStart);

    if (timeNow - timeStart > 3000000)
    {
      Game.modus = "level";
      Game.modusSet = false;
    }
  }

  public void paint(Graphics2D g)
  {
    String levelNummer = "";

    g.setColor(Color.black);
    g.fillRect(0, 0, Game.wBreite, Game.wHoehe);

    Font test = new Font("Arial", Font.BOLD, 30);
    g.setFont(test);
    g.setColor(Color.WHITE);

    levelNummer = Integer.toString(LevelFileReader.level);
    levelNummer = levelNummer + ". Level";

    // g.fillRect(Game.wBreite/2,0 ,1, Game.wHoehe);
    g.drawString(levelNummer, Game.wBreite / 2 - levelNummer.length() * 8, Game.wHoehe / 3);

    g.drawString(levelName, Game.wBreite / 2 - levelName.length() * 7, Game.wHoehe / 3 + 100);
  }

  public void setLevelName(String levelN)
  {
    levelName = levelN;
  }

}
