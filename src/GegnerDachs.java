import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class GegnerDachs extends Item
{

  int y = 0;
  private static final int WIDTH = 50;
  private static final int HEIGHT = 50;
  int blockSize = 64;
  int x;
  int xa = 0;
  private GameStates game;
  boolean jump = false;
  int i;
  boolean right = false;
  boolean left = true;
  boolean falling = false;
  int xTest;
  boolean ausgabe = false;
  float speed = 3.75F;
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

  public GegnerDachs(GameStates gameStates, int xp, int yp)
  {

    this.game = gameStates;
    x = xp;
    y = yp+14;
    xSpawn = x;
    ySpawn = y;

  }

  

  public void update()
  { 

    // System.out.println("move");
    // System.out.println(y);

    xTemp = x;
    collision();

    y = y;
    // System.out.println("vorher");
    // System.out.println(y);

    // System.out.println("nacher");
    // System.out.println(y);
    // collision();
    // System.out.println("coli");
    // System.out.println(x);
    if (glitch == false)
    {
      
      calculateMovement();

      xTemp = x;

      calculateCollision();

      // System.out.println(yTemp - y);
      ai();
      move();
      // collision();

    }
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
    return rec = new Rectangle(x, y, WIDTH, HEIGHT);
  }

  private boolean collision()
  {

    if (game.racquet.getBounds().intersects(getBounds()))
    {

      game.leben.lebenAbziehen();
      right = !right;
      left = !left;
    }
    return true;
  }

  private void calculateCollision()
  {
    float toX = x + dx;
    float toY = y + dy;

    // Collision Top and Bottom
    calculateCorners(x, toY);

    //Hin und Her, Fällt nicht runter oder ähnliches
   
    if(bottomRight == false)
    {
      right = false;
      left = true;
    }
    if(bottomLeft == false)
    {
      right = true;
      left = false;
    }
    

    // Collision Left and Right
    calculateCorners(toX, y - 1);
    if (dx < 0)// links
    {
      if (topLeft == true || midLeft == true || bottomLeft == true)
      {

        dx = 0;
      }

    }

    if (dx > 0)
    {
      if (topRight == true || midRight == true || bottomRight == true)
      {
        dx = 0;
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

    if (game.level.blocke[topTile][leftTile].walkable == true)
    {
      topLeft = false;
    } else
    {
      topLeft = true;
    }

    if (game.level.blocke[topTile][rightTile].walkable == true)
    {
      topRight = false;
    } else
    {
      topRight = true;
    }

    if (game.level.blocke[midTile][leftTile].walkable == true)
    {
      midLeft = false;
    } else
    {
      midLeft = true;
    }

    if (game.level.blocke[midTile][rightTile].walkable == true)
    {
      midRight = false;
    } else
    {
      midRight = true;
    }

    if (game.level.blocke[bottomTile][leftTile].walkable == true)
    {
      bottomLeft = false;
    
    } else
    {
      bottomLeft = true;
    }

    if (game.level.blocke[bottomTile][rightTile].walkable == true)
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
    // System.out.println(y);

    dx = 0;

  }

  public void ai()
  {

    if (midRight == true)
    {
      right = false;
      left = true;

    }

    if (midLeft == true )
    {
      left = false;
      right = true;
    }
 
   
  }

  public void paint(Graphics2D g)
  {
    walkable = true;

    g.setColor(Color.RED);
    g.fillRect(x, y, WIDTH, HEIGHT);

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
