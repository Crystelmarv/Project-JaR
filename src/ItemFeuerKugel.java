import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class ItemFeuerKugel extends Item
{

  
  GameStates gameStates;
  
  boolean feuerDown = true;
  int yTemp;
  int test = 1; 
  float velocityY = (float) (Math.sin(10) * 2);
  float velocityX = (float) (Math.cos(10) * -8);
 
  public ItemFeuerKugel(GameStates gameStates, int xp, int yp)
  {

    this.gameStates = gameStates;
    x = xp + 50;
    y = yp;
    walkable = true;
    dev = true;
  }

  public void update()
  {
    move();
  }

  public void move()
  {
    int blockX, blockY;
    
    
   
    velocityY += 0.1;
   

    blockX = x / 64;
    blockY = y / 64;

    if (gameStates.level.blocke[blockY+1][blockX].getBounds().intersects(getBounds()) 
        && gameStates.level.blocke[blockY+1][blockX].walkable == false
        && feuerDown == true)
    {
      
      
      feuerDown = false;
      yTemp = y;
    }
    else
    {
      if(gameStates.level.blocke[blockY][blockX+1].getBounds().intersects(getBounds()) 
          && gameStates.level.blocke[blockY][blockX+1].walkable == false)
      {
        gameStates.items.remove(aktuellesItem);
      }
      if(feuerDown == true)
      {
        x += velocityX;
        y += velocityY;
      }
      else
      {
        if(yTemp-64 < y)
        {
          
          
         x += velocityX;
          y -= velocityY;
          test++;
        }
        else
        {
          feuerDown = true;
          velocityY = (float) (Math.sin(10) * 2);
        }
        
      }
    }
  }

  public void paint(Graphics2D g)
  {
    g.setColor(Color.blue);
    g.fillOval(x, y, 30, 30);
  }

  public Rectangle getBounds()
  {
    return rec = new Rectangle(x, y, 30, 30);
  }

}
