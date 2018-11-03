import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.stream.Collectors;

public class Racquet
{
  int y = 704;
  private static final int WIDTH = 60;
  private static final int HEIGHT = 60;
  int blockSize = 64;
  int x = 600;
  int xa = 0;
  private Game game;
  boolean jump = false;
  int i;
  boolean right = false;
  boolean left = false;
  boolean falling = false;
  int xTest;
  boolean ausgabe = false;
  float speed = 6.75F;
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

  int yTemp;
  int xTemp;

  float gravity = 0.22F;
  float maxFallingSpeed = 5.5F;
  float jumpStart = -7.0F;

  int lastY;
  int boundingBox;
  int boundingBoxTop;
  Rectangle rec;

  public Racquet(Game game)
  {
    this.game = game;
  }

  public void update()
  {

    // System.out.println("move");
    // System.out.println(y);
    yTemp = y;
    xTemp = x;

    // System.out.println("vorher");
    // System.out.println(y);

    jumpGlitch();
    // System.out.println("nacher");
    // System.out.println(y);
    collision();
    // System.out.println("coli");
    // System.out.println(x);
    if (glitch == false)
    {

      calculateMovement();
      yTemp = y;
      xTemp = x;

      calculateCollision();

      // System.out.println(yTemp - y);
      jumpGlitch();
      move();
      collision();

    }
    if (ausgabe)
    {
      // System.out.println(topLeft);
      // System.out.println(topRight);
      // System.out.println(bottomLeft);
      System.out.println(bottomRight);
    }

  }

  public Rectangle getBounds()
  {
    return rec = new Rectangle(x, y, WIDTH, HEIGHT);
  }

  private void collision()
  {
    int xTemp;

    if (left == true)
    {
      xTemp = x;
      // System.out.println(Level.map[getBlockKordinateY(y)][getBlockKordinateX(x)]);
      if (Level.map[getBlockKordinateY(y)][getBlockKordinateX(xTemp)] != 0)
      {

        while (game.level.blocke[getBlockKordinateY(y)][getBlockKordinateX(xTemp)].getBounds().intersects(getBounds()))
        {

          x = x + 1;

        }
      }
    }

    if (right == true)
    {
      xTemp = x;
      if (Level.map[getBlockKordinateY(y)][getBlockKordinateX(xTemp) + 1] != 0)
      {

        while (game.level.blocke[getBlockKordinateY(y)][getBlockKordinateX(xTemp) + 1].getBounds()
            .intersects(getBounds()))
        {
          x = x - 1;

        }
      }
    }

  }

  private void glitchJump()
  {

  }

  private void jumpGlitch()
  {
    int xTemp;
if(jump == false)
    if (glitchInit == false)
    {
      h = yTemp - y;
      if (h > 10)
      {
        glitchInit = true;
        y = yTemp;
        System.out.println("!HHHHHHHHHH");

      }

    }

    if (glitchInit == true)
    {
      if ((h) > 3)

      {
        System.out.println(h);
        glitch = true;

        xTemp = x;

        if (Level.map[getBlockKordinateY(y) + 1][getBlockKordinateX(xTemp) + 1] != 0)
        {

          while (game.level.blocke[getBlockKordinateY(y)][getBlockKordinateX(xTemp) + 1].getBounds()
              .intersects(getBounds()))
          {
            x = x - 1;
            System.out.println("x");

          }
        }
        h = h - 3;
        y = y - 3;
        

        if (h <= 3)
        {
          glitch = false;
          glitchInit = false;
          jump = true;
          System.out.println("GLITCH SOLVE");
        }

        /*
         * dy += gravity; if (dy > maxFallingSpeed) { dy = maxFallingSpeed; }
         * 
         */
      }
    }
  }

  private void calculateCollision()
  {
    float toX = x + dy;
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
      System.out.println("MFUFFF");
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
        System.out.println("*******************");
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

    if (Level.map[topTile][leftTile] == 0)
    {
      topLeft = false;
    } else
    {
      topLeft = true;
    }

    if (Level.map[topTile][rightTile] == 0)
    {
      topRight = false;
    } else
    {
      topRight = true;
    }

    if (Level.map[midTile][leftTile] == 0)
    {
      midLeft = false;
    } else
    {
      midLeft = true;
    }

    if (Level.map[midTile][rightTile] == 0)
    {
      midRight = false;
    } else
    {
      midRight = true;
    }

    if (Level.map[bottomTile][leftTile] == 0)
    {
      bottomLeft = false;
    } else
    {
      bottomLeft = true;
    }

    if (Level.map[bottomTile][rightTile] == 0)
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

    if (falling == true && jump == false)
    {
      dy += gravity;
      if (dy > maxFallingSpeed)
      {
        dy = maxFallingSpeed;
      }
    }

    if (jump == true && falling == false)
    {
      dy = jumpStart;
      jump = false;
      falling = true;
    }

  }

  public void move()
  {

    x += dx;
    y += dy;

    dx = 0;

  }

  public void paint(Graphics2D g)
  {
    g.fillRect(x, y, WIDTH, HEIGHT);
    g.setColor(Color.BLACK);
    Graphics2D g2d = (Graphics2D) g;

  }

  public void keyReleased(KeyEvent ee)
  {

    int key2 = ee.getKeyCode();

    switch (key2)
    {
    case KeyEvent.VK_LEFT:
      left = false;
      break;
    case KeyEvent.VK_RIGHT:
      right = false;
      break;
    case KeyEvent.VK_H:
      ausgabe = false;
      break;
    }
  }

  public void keyPressed(KeyEvent e)
  {

    int key = e.getKeyCode();

    switch (key)
    {
    case KeyEvent.VK_UP:
      if (falling == false)
      {
        jump = true;
      }
      break;
    case KeyEvent.VK_LEFT:
      left = true;
      break;
    case KeyEvent.VK_RIGHT:
      right = true;
      break;
    case KeyEvent.VK_H:
      ausgabe = true;
      break;

    }
  }

  public float getX()
  {
    return x;
  }

  public void bodenFall()
  {
    if (Level.map[blockKorY + 1][blockKorX] == 0 && Level.map[blockKorY + 1][blockKorX + 1] == 0 && jump == false)
    {
      System.out.println("0++++++++++++++++++");
      falling = true;
    }
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