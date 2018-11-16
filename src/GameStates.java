import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.IOException;
import java.util.ArrayList;

public class GameStates
{
  Game game;
  Racquet racquet;
  Level level;
  Kamera kam;
  Leben leben;
  LevelSelect levelSelect;
  Muenze muenze;
  Assets assets;
  static boolean levelInit = false;
  boolean levelGegnerInit = false;
  boolean levelGegner = false;
  ArrayList<Entity> items = new ArrayList<>();

  public GameStates(Game game)
  {
    this.game = game;

  }

  public void levelErstellen() throws IOException
  {
   assets = new Assets();
   assets.init();
    LevelFileReader.LevelRead();
    if (LevelFileReader.levelGelesen == true)
    {
      level = new Level(this);
      racquet = new Racquet(this);
      kam = new Kamera(0, 0, game);
      leben = new Leben(this);
      muenze = new Muenze(this);
      
    }
    Game.init1 = true;
  }

  public void levelUpdate()
  {
    
    if (racquet.hTaste)
    {
      Game.modus = "levelSelect";
      Game.modusSet = false;
      
      Game.modus = "level";
      Game.modusSet = false;
      
      

    }
    Entity aktuellesItem;
    int i, j, k, ii;

    for (i = 0; i < Level.map.length; i++)
    {
      for (j = 0; j < Level.map[0].length; j++)
      {
        level.blocke[i][j].update();
      }
    }

    if (level.gegnerInLevel == true)
    {
      for (k = 0; k < level.gegner.length; k++)
      {

        level.gegner[k].update();

      }
    }

    leben.update(racquet);
    muenze.update(racquet);

    kam.update(racquet);

    racquet.update();
    
    for (ii = 0; ii < items.size(); ii++)
    {
      aktuellesItem = items.get(ii);
      aktuellesItem.update();
    }

    if (levelGegner == true && levelGegnerInit == false)
    {
     // level.gegnerSpawn();
      levelGegnerInit = true;

    }
    // System.out.println(level.blocke[13][13].walkable);
  }

  public void levelPaint(Graphics2D g)
  {
    int i, j, k, ii;
    Entity aktuellesItem;

    Graphics2D g2d = (Graphics2D) g;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    //////////////////////
    g2d.translate(kam.getX(), kam.getY());

    for (i = 0; i < Level.map.length; i++)
    {
      for (j = 0; j < Level.map[0].length; j++)
      {
        level.blocke[i][j].paint(g2d);
      }
    }
    levelGegner = true;

    if (level.gegnerInLevel == true)
    {
      for (k = 0; k < level.gegner.length; k++)
      {

        level.gegner[k].paint(g2d);

      }
    }

    for (ii = 0; ii < items.size(); ii++)
    {
      aktuellesItem = items.get(ii);
      aktuellesItem.paint(g2d);
    }

    leben.paint(g2d);
    muenze.paint(g2d);
    racquet.paint(g2d);

    // block.paint(g2d);

    // g2d.translate(-kam.getX(), kam.getY());
  }

  public void menueInit()
  {
    levelSelect = new LevelSelect(game);
    
  }

  public void menuePaint(Graphics2D g2d)
  {
    levelSelect.paint(g2d);
  }
}
