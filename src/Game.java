import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends JPanel
{

  GameStates gameState = new GameStates(this);
 
  
  static boolean init1 = false;
  boolean init2 = true;
  boolean init3 = true;

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
   
   gameState.levelUpdate();
  }

  @Override
  public void paint(Graphics g)
  {
    super.paint(g);
    Graphics2D g2d = (Graphics2D) g;
    
    if(init1 == true)
    {
      gameState.levelPaint(g2d);
    }
   
  }

  public static void main(String[] args) throws InterruptedException, IOException
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
    game.gameState.levelErstellen();
    
    
    
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