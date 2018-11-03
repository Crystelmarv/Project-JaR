import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends JPanel
{

  Ball ball = new Ball(this);
  Racquet racquet = new Racquet(this);
  Level level = new Level(this);
  Kamera kam = new Kamera(0, 0, this);
  boolean tes = true;

  static int wHoehe = 900;
  static int wBreite = 1450;

  public Game()
  {
    repaint();
    addKeyListener(new KeyListener()
    {
      @Override
      public void keyTyped(KeyEvent e)
      {
      }

      @Override
      public void keyReleased(KeyEvent e)
      {
        racquet.keyReleased(e);
      }

      @Override
      public void keyPressed(KeyEvent e)
      {
        racquet.keyPressed(e);
      }
    });
    setFocusable(true);
  }

  private void move() throws InterruptedException
  {
    ball.move();

  }

  private void update()
  {
    int i, j;

    
    if (tes == false)
    {
      kam.update(racquet);
      racquet.update();
      for (i = 0; i < Level.map.length; i++)
      {
        for (j = 0; j < Level.map[0].length; j++)
        {
          level.blocke[i][j].update();
        }
      }
    }

  }

  @Override
  public void paint(Graphics g)
  {
    int i, j;

    super.paint(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    //////////////////////
    g2d.translate(kam.getX(), kam.getY());
    if (tes == true)
    {
      level.paint(g2d);
      tes = false;
    }

    for (i = 0; i < Level.map.length; i++)
    {
      for (j = 0; j < Level.map[0].length; j++)
      {
        level.blocke[i][j].paint(g2d);
      }
    }
    racquet.paint(g2d);
    ball.paint(g2d);

    // block.paint(g2d);

    g2d.translate(-kam.getX(), kam.getY());

  }

  public void gameOver()
  {
    // JOptionPane.showMessageDialog(this, "Game Over", "Game Over",
    // JOptionPane.YES_NO_OPTION);
    // System.exit(ABORT);
  }

  public static void main(String[] args) throws InterruptedException
  {
    JFrame frame = new JFrame("Mini Tennis");
    Game game = new Game();
    frame.add(game);
    frame.setSize(wBreite, wHoehe);
    frame.setTitle("Mal wieder ein Test");
    frame.setResizable(true);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    while (true)
    {
      game.move();
      game.update();
      game.repaint();
      
      // System.out.println(Racquet.x);

      Thread.sleep(10);
    }
  }
}