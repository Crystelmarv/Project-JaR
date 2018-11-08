import java.awt.Button;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class LevelSelect implements ActionListener
{
  Game game;
  JButton bt1 = new JButton("Level 1");
  JButton bt2 = new JButton("Level 2");

  public LevelSelect(Game game)
  {
    this.game = game;
    levelMenue();

  }

  public void levelMenue()
  {

    game.add(bt1);
    game.add(bt2);

    bt1.addActionListener(this);
    bt2.addActionListener(this);

  }

  public void paint(Graphics2D g2d)
  {
    bt1.setBounds(50, 50, 500, 500);
    bt2.setBounds(600, 50, 500, 500);
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    if (e.getSource() == bt1)
    {
      LevelFileReader.level = 1;
      Game.modus = "level1";
      Game.modusSet = false;
      levelSelctDelete();

    }
    if (e.getSource() == bt2)
    {
      LevelFileReader.level = 2;
      Game.modus = "level1";
      Game.modusSet = false;
      levelSelctDelete();
    }

  }

  public void levelSelctDelete()
  {
    game.remove(bt1);
    game.remove(bt2);
  }

}
