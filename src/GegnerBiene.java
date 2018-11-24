import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class GegnerBiene extends Item
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
  
  double timeStart;

  public GegnerBiene(GameStates gameStates, int xp, int yp)
  {

    this.game = gameStates;
    x = xp;
    y = yp;
    xSpawn = x;
    ySpawn = y;
    timeStart = System.nanoTime();

  }

 

  public void update()
  {

    xTemp = x;
    collision();
   
    y = y;

    if (glitch == false)
    {
      ai();
      calculateMovement();

      xTemp = x;

      calculateCollision();

      // System.out.println(yTemp - y);

      move();
      stachel();
      // collision();

    }

  }

  public Rectangle getBounds()
  {
    return rec = new Rectangle(x, y, WIDTH, HEIGHT);
  }

  
  public void stachel()
  {
   double timeNow = System.nanoTime();

    if (timeNow - timeStart > 1500000000) //4000000)
    {
      GegnerBieneStachel stachl = new GegnerBieneStachel(game, x, y, right);
      stachl.setItem(stachl);
      game.items.add(stachl);
     timeStart = timeNow;
    }
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

    if (topLeft == true || topRight == true)
    {
      dy = 0;
      falling = true;
      int playerY = getBlockKordinateY((int) toY);
      y = (playerY + 1) * blockSize;
    }
    if (bottomLeft == true && falling == true || bottomRight == true && falling == true)
    {
      falling = false;
      dy = 0;

      int playerY = getBlockKordinateY((int) toY + HEIGHT);

      y = playerY * blockSize - HEIGHT;

    }

    if (bottomLeft == false && bottomRight == false)
    {
      falling = true;
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

    if (game.level.blocke[topTile][leftTile].dev == true)
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

    if (game.level.blocke[bottomTile][leftTile].dev == true)
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

    if (midLeft == true)
    {
      left = false;
      right = true;
    }
  }

  public void paint(Graphics2D g)
  {
    walkable = true;
    dev = true;

    if(left == true)
    {
      g.drawImage(Assets.bieneLinks, x, y+3, null);
    }
    if(right == true)
    {
      g.drawImage(Assets.bieneRechts, x, y+3, null);
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
