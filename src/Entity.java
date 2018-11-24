
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Entity
{
  int x = 0;
  int y = 0;
  Rectangle rec;
  int blockBreite = 64;
  int blockHöhe = 64;

  

  Graphics2D gx;
  Block block;
  boolean walkable;
  boolean dev;
  

  public void setBlock(int xp, int yp)
  {

    x = xp;
    y = yp;

  }

  public Rectangle getBounds()
  {

    return rec = new Rectangle(x, y, blockBreite, blockHöhe);

  }

  public void update()
  {

   // block.update();
  }

  public void paint(Graphics2D g)
  {
    
  }

  public void setIndex(int indexSchild)
  {
    // TODO Auto-generated method stub
    
  }

  public void init()
  {
    // TODO Auto-generated method stub
    
  }

}
