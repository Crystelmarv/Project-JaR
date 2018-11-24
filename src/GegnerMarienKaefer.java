import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class GegnerMarienKaefer extends Item
{

  int y = 0;
  private static final int WIDTH = 64;
  private static final int HEIGHT = 64;
  int blockSize = 64;
  int x;
  int xa = 0;
  private GameStates game;
  boolean jump = false;
  int i;
  boolean right = true;
  boolean left = false;
  boolean falling = false;
  int xTest;
  boolean ausgabe = false;
  float speed = 1.0F;
  int h = 0;

  boolean glitch = false;
  boolean glitchInit = false;

  // Collision
  boolean topLeft;
  boolean topRight;
  boolean midLeft;
  boolean midRight;
  boolean bottomLeft;
  boolean bottomRight;
  float dx;
  float dy;

  int blockKorX;
  int blockKorY;

  int blockKorX2;
  int blockKorY2;

  int xTemp;

  int xSpawn;
  int ySpawn;
  boolean initi = true;

  int boundingBox;
  int boundingBoxTop;
  Rectangle rec;

  public GegnerMarienKaefer(GameStates gameStates, int xp, int yp)
  {

    this.game = gameStates;
    x = xp;
    y = yp;
    xSpawn = x;
    ySpawn = y;

  }

  public void update()
  {

    xTemp = x;
   

    y = y;

    getroffen();
    

      calculateMovement();

      xTemp = x;

      calculateCollision();

      // System.out.println(yTemp - y);
      
      move();
      // collision();

    
    if (ausgabe)
    {
      // System.out.println(topLeft);
      // System.out.println(topRight);
      // System.out.println(bottomLeft);
      // System.out.println(bottomRight);
    }

    // paint(gx);

  }

  public Rectangle getBounds()
  {
    return rec = new Rectangle(x, y+32, WIDTH, 32);
  }

  
  public void getroffen()
  {
   // System.out.println(y);
   
   
    if (game.racquet.x > x - 59 && game.racquet.x < x + 59 && game.racquet.y+60 < y+32 
        && game.racquet.y+60 > y +20 && game.racquet.falling == true )
    {
     
     game.items.remove(aktuellesItem);
     game.racquet.dy = game.racquet.jumpStart;
     game.racquet.jump = false;
     game.racquet.falling = true;
     
    }
    else
    {
      collision();
    }
  }
  private boolean collision()
  {

    if (game.racquet.getBounds().intersects(getBounds()))
    {

      game.leben.lebenAbziehen();
      dx = 0;
    }
    return true;
  }

  private void calculateCollision()
  {
    float toX = x + dx;
    float toY = y + dy ;

    // Collision Top and Bottom
    calculateCorners(x, toY);

    // Hin und Her, Fällt nicht runter oder ähnliches

    if (bottomRight == false)
    {
      right = false;
      left = true;
      
    //  System.out.println("22222222222");
    }
    if (bottomLeft == false)
    {
      right = true;
      left = false;
     
   //   System.out.println("3233333333333333333");
    }

    // Collision Left and Right
    calculateCorners(toX, y - 1);
    

   
    if (dx < 0)// links
    {
      if (topLeft == true || midLeft == true || bottomLeft == true)
      {
        left = false;
        right = true;
        dx =0;
      }

    }

    if (dx > 0)
    {
      if (topRight == true || midRight == true || bottomRight == true)
      {
        left = true;
        right = false;
        dx =0;
      }
    }

  }

  private void calculateCorners(float x, float y)
  {
    int leftTile = getBlockKordinateX((int) x);
    int rightTile = getBlockKordinateX((int) x + WIDTH - 1);
    int topTile = getBlockKordinateY((int) y);
    int midTile = getBlockKordinateY((int) y + HEIGHT / 2);
    int bottomTile = getBlockKordinateY((int) y + HEIGHT);

    if (game.level.blocke[topTile][leftTile].dev == true )
    {
      topLeft = false;
    } else
    {
      topLeft = true;
    }

    if (game.level.blocke[topTile][rightTile].dev == true)
    {
      topRight = false;
    } else
    {
      topRight = true;
    }

    if (game.level.blocke[midTile][leftTile].dev == true)
    {
      midLeft = false;
    } else
    {
      midLeft = true;
    }

    if (game.level.blocke[midTile][rightTile].dev == true)
    {
      midRight = false;
    } else
    {
      midRight = true;
    }

    if (game.level.blocke[bottomTile][leftTile].dev == true )
    {
      bottomLeft = false;

    } else
    {
      bottomLeft = true;
    }

    if (game.level.blocke[bottomTile][rightTile].dev == true)
    {

      bottomRight = false;
    } else
    {
      bottomRight = true;
    }
    // System.out.println(Level.map[bottomTile][leftTile]);
    // System.out.println(game.level.blocke[bottomTile][leftTile].walkable);
  }

  private void calculateMovement()
  {
   
    if (left == true)
    {
      dx = -speed;
    }
    if (right == true)
    {
      dx = speed;
    }

  }

  public void move()
  {

    x += dx;
   

    dx = 0;

  }



  public void paint(Graphics2D g)
  {
    walkable = true;
    dev = true;

    if(left == true)
    {
      g.drawImage(Assets.marienKaeferLinks, x, y+3, null);
    }
    if(right == true)
    {
      g.drawImage(Assets.marienKaeferRechts, x, y+3, null);
    }
    

  }

  public float getX()
  {
    return x;
  }

  public int getBlockKordinateY(int y)
  {
    return blockKorY = y / blockSize;

    // System.out.println(blockKorY);
    // System.out.println(blockKorX);
    // System.out.println(Level.map[blockKorY-1][blockKorX]);
    // System.out.println(x);
  }

  public int getBlockKordinateX(int x)
  {
    return blockKorX = x / blockSize;
  }

}
