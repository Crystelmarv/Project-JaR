import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class GegnerFisch extends Item
{
  GameStates gameStates;
  int ySpawn;
  int xSpawn;
  double timeStart;
  boolean fall = false;
  boolean sprung = false;
  int yWasser;
  int anzahlWasserBlocke;

  public GegnerFisch(GameStates gameStates, int xp, int yp)
  {

    this.gameStates = gameStates;
    x = xp;
    y = yp;
    xSpawn = x;
    ySpawn = y;
    yWasser = y;
    timeStart = System.nanoTime();
    wasserBlocke();

  }

  public void wasserBlocke()
  {
    System.out.println(Level.blockArt(xSpawn/64, yWasser/64));
   while(Level.blockArt(xSpawn/64, yWasser/64) == 33 || Level.blockArt(xSpawn/64, yWasser/64) == 73)
   {
    yWasser = yWasser - 64; 
    anzahlWasserBlocke++;
   }
    
  }

  public void update()
  {
    sprung();
    getroffen();
  }

  
  public void getroffen()
  {
    if (gameStates.racquet.getBounds().intersects(getBounds()))
    {

      gameStates.leben.lebenAbziehen();
    
    }
  }
  public void paint(Graphics2D g)
  {
    int i; 
    g.fillRect(x, y, 50, 50);
    yWasser = ySpawn;
    for(i=0; i < anzahlWasserBlocke; i++ )
    {
      g.drawImage(Assets.wasser, x, yWasser, null);
      yWasser = yWasser - 64;
    }
    
   
  }

  public void sprung()
  {
    double timeNow = System.nanoTime();
    if (timeNow - timeStart > 1500000000 && fall == false)
    {
      y = y - 4;
      sprung = true;

    }

    if (ySpawn - y > 384 || fall == true)
    {
      fall = true;
      sprung = false;
      y = y + 4;
      
        if(y >= ySpawn && sprung == false) 
        { 
          timeStart = timeNow; 
          fall = false; 
        }
       
    }
  }
  public Rectangle getBounds()
  {

    return rec = new Rectangle(x, y, 50, 50);

  }


}
