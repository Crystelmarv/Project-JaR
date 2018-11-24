import java.awt.Button;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LevelSelect extends JPanel implements ActionListener
{
  Game game;
  int anzahlLevel = 2;
 JPanel levelSelect;
 JFrame frameBlocke;

  JButton[] bts = new JButton[anzahlLevel];
  JLabel version = new JLabel("BETA 1.0");

  public LevelSelect(Game game)
  {
    frameBlocke = new JFrame("");
   
   

   // frameBlocke.add(game);

  /*  frameBlocke.setSize(310, 800);
    frameBlocke.setTitle("JaR Level Editor");
    frameBlocke.setResizable(false);
  //  frameBlocke.setLocationRelativeTo(game);
    frameBlocke.setLayout(null);
    frameBlocke.setVisible(true);
    
    */
    this.game = game;
    levelSelect = new JPanel();
    
    levelSelect.setSize(900,1450);
   levelSelect.setLayout(null);
    levelSelect.setVisible(true);
    
    
  // game.frame.add(levelSelect);
    int i;
    for (i = 0; i < anzahlLevel; i++)
    {
      bts[i] = new JButton();
    }

    levelMenue();

  }

  public void levelMenue()
  {
    int i;
    for (i = 0; i < anzahlLevel; i++)
    {
      game.add(bts[i]);
      bts[i].addActionListener(this);
    }
   
    game.add(version);

  }

  public void paint(Graphics2D g2d)
  {
    int i;
    int x = 50;
    int y = 50;
    
    for (i = 0; i < anzahlLevel; i++)
    {
      bts[i].setBounds(x, y, 90, 90);
      bts[i].setText("Level " + (i+1));
      x = x + 90 + 25;
      System.out.println("BUTTONS");
    }
    
    version.setBounds(100, 700, 500, 150);
    
   
    // bt1.setBounds(50, 50, 500, 500);
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {

    int i;

    for (i = 0; i < anzahlLevel; i++)
    {
      if (e.getSource() == bts[i])
      {
        LevelFileReader.level = i+1;
        Game.modus = "ladeScreen";
        Game.modusSet = false;
        levelSelctDelete();
       // levelSelect.setVisible(false);
        System.out.println("DE§EEEEEE");

      }
    }

  }

  public void levelSelctDelete()
  {
    int i;

    for (i = 0; i < anzahlLevel; i++)
    {
      game.remove(bts[i]);
    }
    game.remove(version);
  }

}
