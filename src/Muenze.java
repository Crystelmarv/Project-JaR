import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Muenze
{
  int muenzen = 0;
 
  float x;
  float y;
  GameStates game;
  boolean tot = false;
  boolean timerSetzen = false;
  

  boolean test = false;

  public Muenze(GameStates game)
  {
    this.game = game;

  }

  public void paint(Graphics2D g)
  {
    int i;

    Graphics2D g2d = (Graphics2D) g;

    
      g.setColor(Color.YELLOW);
      g.fillOval((int) x, (int) y, 64, 64);
      
      Font test = new Font("Arial", Font.BOLD, 30);
      g.setFont(test);
      g.setColor(Color.black);
      g.drawString(Integer.toString(muenzen), x+80, y+45);
    
  }

  public void update(Racquet player)
  {
    // x--;

    x = player.getX() + 600 + Game.WIDTH / 2;
    y = player.getY() - 500 + Game.WIDTH / 2;

  }
  
  public void muenzePlus()
  {
    muenzen++;
  }
}
