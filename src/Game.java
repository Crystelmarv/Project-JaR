import java.awt.Button;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.RenderingHints.Key;
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
        System.out.println("522222");
        
      }
    });
    
    
    setFocusable(true);
   
    

  }

  private void move() throws InterruptedException
  {

  }

  private void update()

  {
    //keyLIs.KeyLisInit();
    switch (modus)
    {
    case "levelSelect":

      break;
    case "level1":
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
      
    if (init4 == true)
    {
      gameState.menuePaint(g2d);
      
    }
    
      
      

      break;

    case "level1":
      if (init1 == true)
      {
        gameState.levelPaint(g2d);
      }
      break;
    }
  }

  public static void main(String[] args) throws InterruptedException, IOException
  {

    frame = new JFrame("Mini Tennis");
    Game game = new Game();
  

    frame.add(game);
  
    frame.setSize(wBreite, wHoehe);
    frame.setTitle("Mal wieder ein Test");
    frame.setResizable(true);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  
    

    while (modus == "levelSelect")
    {
      game.modus();
      
      game.repaint();
      // System.out.println(Racquet.x);

      Thread.sleep(10);
    }

    while (modus == "level1")
    {
      game.requestFocus();
      game.modus();
      game.move();
      game.update();
      game.repaint();
      // System.out.println(Racquet.x);

      Thread.sleep(10);
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

    case "level1":
      if (modusSet == false)
      {
        gameState.levelErstellen();
        modusSet = true;
      }

      break;
    }
  }
}