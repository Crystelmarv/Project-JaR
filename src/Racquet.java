import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.stream.Collectors;

public class Racquet
{
  int y = 900;
  private static int WIDTH = 60;
  private static int HEIGHT = 60;
  int blockSize = 64;
  int x = 600;
  private GameStates game;
  boolean jump = false;
  int i;
  boolean right = false;
  boolean left = false;
  boolean falling = false;
  boolean ausgabe = false;
  float speed = 6.75F;
  int h = 0;
  boolean hTaste = false;

  boolean tot = false;

  boolean glitch = false;
  boolean glitchInit = false;

  boolean angreifbar = true;
  boolean timerGesetzt = false;

  boolean timerGesetztItem = false;
  double anfangsTimeItem;

  double anfangsTime;

  // Collision
  boolean topLeft;
  boolean topRight;
  boolean midLeft;
  boolean midRight;
  boolean bottomLeft;
  boolean bottomRight;
  boolean interaction;
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
  float jumpStart = -8.7F;

  int lastY;
  Rectangle rec;

  // Checkpoint
  int checkpointX;
  int checkpointY;

  // Items
  String aktivesItem = "null";
  Item akItem;

  public Racquet(GameStates gameStates)
  {
    this.game = gameStates;

    x = gameStates.level.getPlayerSpawnX();
    y = gameStates.level.getPlayerSpawnY();

    checkpointX = x;
    checkpointY = y;
  }

  public void update()
  {
    if (tot == false)
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

      nichtAngreifbar();
      sterbenFall();
      item();

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
     
    } else
    {

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
      if (game.level.blocke[getBlockKordinateY(y)][getBlockKordinateX(xTemp)].walkable == false)
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
      if (game.level.blocke[getBlockKordinateY(y)][getBlockKordinateX(xTemp) + 1].walkable == false)
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
    if (jump == false)
      if (glitchInit == false)
      {
        h = yTemp - y;
        if (h > 10)
        {
          glitchInit = true;
          y = yTemp;

        }

      }

    if (glitchInit == true)
    {
      if ((h) > 3)

      {

        glitch = true;

        xTemp = x;

        if (Level.map[getBlockKordinateY(y) + 1][getBlockKordinateX(xTemp) + 1] != 0)
        {

          while (game.level.blocke[getBlockKordinateY(y)][getBlockKordinateX(xTemp) + 1].getBounds()
              .intersects(getBounds()))
          {
            x = x - 1;

          }
        }
        h = h - 3;
        y = y - 3;

        if (h <= 3)
        {
          glitch = false;
          glitchInit = false;
          jump = true;

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
   // System.out.println(game.level.blocke[bottomTile][rightTile].walkable);

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
    if (angreifbar == true)
    {
      g.setColor(Color.BLACK);
    }

    if (angreifbar == false)
    {
      g.setColor(Color.ORANGE);
    }
    g.fillRect(x, y, WIDTH, HEIGHT);

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
      hTaste = false;
      break;
    case KeyEvent.VK_SPACE:
      interaction = false;
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
     hTaste = true;
      break;
    case KeyEvent.VK_SPACE:
      interaction = true;
      break;

    }
  }

  public float getX()
  {
    return x;
  }

  public float getY()
  {
    return y;
  }

  public void setX(int px)
  {
    x = px;
  }

  public void setY(int py)
  {
    y = py;
  }

  public void bodenFall()
  {
    if (game.level.blocke[blockKorY + 1][blockKorX].walkable == true
        && game.level.blocke[blockKorY + 1][blockKorX + 1].walkable == true && jump == false)
    {

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

  
  public void respawnAtCheckpoint()
  {
    x = checkpointX;
    y = checkpointY;
  }
  public boolean sterben()
  {
   
    tot = true;

    return tot;

  }

  public void sterbenFall()
  {
    int maxY;

    maxY = game.level.getMaxY();

    if (y > maxY)
    {
      x = checkpointX;
      y = checkpointY;

      game.leben.lebenAbziehen();
    }
  }

  public void nichtAngreifbar()
  {
    double timeNow = System.nanoTime() / 1000000000;

    if (timerGesetzt == false && game.leben.timerSetzen == true)
    {
      anfangsTime = System.nanoTime() / 1000000000;
      timerGesetzt = true;
      angreifbar = false;

    } else
    {

      // System.out.println(timeNow - anfangsTime);
      if (timeNow - anfangsTime > 5)
      {
        angreifbar = true;
        timerGesetzt = false;
        game.leben.timerSetzen = false;
      }
    }

  }

  public void item()
  {
    double timeNow = System.nanoTime() / 1000000;
    if (interaction == true)
    {
      if (timerGesetztItem == false)
      {
        anfangsTimeItem = System.nanoTime() / 1000000;
        timerGesetztItem = true;

        switch (aktivesItem)
        {
        case "none":

          break;

        case "feuer":

          akItem = new ItemFeuerKugel(game, x, y);

          game.items.add(akItem);
          akItem.setItem(akItem);
          akItem.update();

          break;
        }

      } else
      {

        if (timeNow - anfangsTimeItem > 500)
        {
          timerGesetztItem = false;

        }
      }
    }

  }

}