import java.awt.Button;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class LevelSelect implements ActionListener
{
  Game game;
  int anzahlLevel = 10;

  JButton[] bts = new JButton[anzahlLevel];

  public LevelSelect(Game game)
  {
    this.game = game;

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
        Game.modus = "level";
        Game.modusSet = false;
        levelSelctDelete();
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

  }

}
