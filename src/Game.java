import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends JPanel
{
  static JFrame frame;
  GameStates gameState = new GameStates(this);
  // static KeyLis keyLis = new KeyLis(this, frame);

  static boolean init1 = false;
  boolean init2 = true;
  boolean init3 = true;
  static boolean init4 = false;
  static String modus = "levelSelect";

  static int wHoehe = 900;
  static int wBreite = 1450;

  static boolean modusSet = false;
  KeyListener key;

  public Game()
  {

    repaint();

    addKeyListener(key = new KeyListener()
    {

      @Override
      public void keyTyped(KeyEvent e)
      {
        // TODO Auto-generated method stub

      }

      @Override
      public void keyReleased(KeyEvent e)
      {
        gameState.racquet.keyReleased(e);

      }

      @Override
      public void keyPressed(KeyEvent e)
      {
        gameState.racquet.keyPressed(e);

      }
    });

    setFocusable(true);

  }

  private void move() throws InterruptedException
  {

  }

  private void update()

  {
    // keyLIs.KeyLisInit();
    switch (modus)
    {
    case "levelSelect":

      break;
    case "level":
      gameState.levelUpdate();
      break;
    }

  }

  @Override
  public void paint(Graphics g)
  {
    super.paint(g);
    Graphics2D g2d = (Graphics2D) g;

    switch (modus)
    {
    case "levelSelect":
      System.out.println(init4);
      if (init4 == true)
      {
        gameState.menuePaint(g2d);
        System.out.println("PAINT");

      }

      break;

    case "level":
      if (init1 == true)
      {
        gameState.levelPaint(g2d);
      }
      break;
    }
  }

  public static void main(String[] args) throws InterruptedException, IOException
  {
    double fps = 100.0;
    double timePerTick = 1000000000.0 / fps;
    double delta = 0.0;
    long now;
    long lastTime = System.nanoTime();
    int ticks = 0;
    long timer = 0;

    frame = new JFrame("");
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
      now = System.nanoTime();
      delta += (now - lastTime) / timePerTick;
      timer += now - lastTime;
      lastTime = now;

      if (delta >= 1.0)
      {
        switch (modus)
        {
        case "levelSelect":
          System.out.println("while modus");
          game.requestFocus();
          game.modus();

          game.repaint();
          // System.out.println(Racquet.x);

          break;

        case "level":
          game.requestFocus();
          game.modus();
          game.move();
          game.update();
          game.repaint();
          // System.out.println(Racquet.x);

          break;
        }
        ticks++;
        delta--;
      }
      Thread.sleep(1);
      if (timer >= 1000000000)
      {
        System.out.println("FPS: " + ticks);
        ticks = 0;
        timer = 0;
      }

      game.modus();
    }

  }

  public void modus() throws IOException
  {

    switch (modus)
    {
    case "levelSelect":
      if (modusSet == false)
      {
        gameState.menueInit();
        modusSet = true;
        init4 = true;
      }

      break;

    case "level":
      if (modusSet == false)
      {
        gameState.levelErstellen();
        modusSet = true;
      }

      break;
    }
  }

}