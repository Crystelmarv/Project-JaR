import java.awt.Color;
import java.awt.Graphics2D;

public class Leben
{
  int maxLeben = 3;
  int aktuelleLeben = maxLeben;
  float[] x;
  Game game;
  boolean tot = false;
  boolean timerSetzen = false;

  
  public Leben(Game game)
  {
    this.game = game;
 
    x = new float[maxLeben];
  }
  public void paint(Graphics2D g)
  {
    int i;
    
    Graphics2D g2d = (Graphics2D) g;
    
    if(tot == true)
    {
      g.setColor(Color.BLACK);
      g.fillRect(0, 0, 999999, 99999);
    }
   
    for(i = 0; i < aktuelleLeben; i++)
    {
      g.setColor(Color.RED);
      g.fillOval((int) x[i], 80, 64, 64);
    }             
  }

  
  public void update(Racquet player)
  {
    //x--;
    
    x[0] = player.getX()-500 + Game.WIDTH/2;
    x[1] = x[0] + 100;
    x[2] = x[1] + 100;
    
    tot();
    
  }
  private void tot()
  {
    if(aktuelleLeben <= 0 )
    {
      tot = game.racquet.sterben();
      
        
      
    }
    
  }
  public void lebenAbziehen()
  {
    if(game.racquet.angreifbar == true)
    {
      aktuelleLeben--;
      timerSetzen = true;
      game.racquet.nichtAngreifbar();
      System.out.println("leben abzihen");
    }
    
    
   
    
    
  }
}
