import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class GegnerBieneStachel extends Item
{
  GameStates gameStates;
  private boolean right;
  int y = 900;
  private static int WIDTH = 20;
  private static int HEIGHT = 20;
  int blockSize = 64;
  int x = 600;

  boolean jump = false;
  int i;

  boolean left = false;
  boolean falling = false;
  boolean ausgabe = false;
  float speed = 6.75F;
  int h = 0;
  boolean hTaste = false;

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

  public GegnerBieneStachel(GameStates gameStates, int xp, int yp, boolean right)
  {

    this.gameStates = gameStates;
    x = xp;
    y = yp;
    this.right = right;

  }

  public void update()
  {
    
    calculateCollision();
    calculateMovement();
    move();
    getroffen();

  }

  public void paint(Graphics2D g)
  {
    g.setColor(Color.BLACK);
    g.fillRect(x, y, 20, 20);
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
      stachelRemove();
      falling = true;
      int playerY = getBlockKordinateY((int) toY);
      y = (playerY + 1) * blockSize;
    }
    if (bottomLeft == true && falling == true || bottomRight == true && falling == true)
    {
      falling = false;
      dy = 0;
      stachelRemove();

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
        stachelRemove();
      }

    }

    if (dx > 0)
    {
      if (topRight == true || midRight == true || bottomRight == true)
      {
        dx = 0;
        stachelRemove();
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

    if (gameStates.level.blocke[topTile][leftTile].walkable == true)
    {
      topLeft = false;
    } else
    {
      topLeft = true;
    }

    if (gameStates.level.blocke[topTile][rightTile].walkable == true)
    {
      topRight = false;
    } else
    {
      topRight = true;
    }

    if (gameStates.level.blocke[midTile][leftTile].walkable == true)
    {
      midLeft = false;
    } else
    {
      midLeft = true;
    }

    if (gameStates.level.blocke[midTile][rightTile].walkable == true)
    {
      midRight = false;
    } else
    {
      midRight = true;
    }

    if (gameStates.level.blocke[bottomTile][leftTile].walkable == true)
    {
      bottomLeft = false;
    } else
    {
      bottomLeft = true;
    }

    if (gameStates.level.blocke[bottomTile][rightTile].walkable == true)
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

  public void move()
  {
    if (right == true)
    {
      y++;
      x++;
    } else
    {
      y++;
      x--; 
    }

  }

  public void stachelRemove()
  {
    gameStates.items.remove(aktuellesItem);
  }

  public void getroffen()
  {
    if (gameStates.racquet.getBounds().intersects(getBounds()))
    {
      stachelRemove();
      gameStates.leben.lebenAbziehen();
    }
  }
  
  public Rectangle getBounds()
  {

    return rec = new Rectangle(x, y, WIDTH, HEIGHT);

  }
}
