import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class BlockZerstoerbar extends Entity
{
  GameStates game;
  boolean getroffen = false;
 

  public BlockZerstoerbar(GameStates gameStates)
  {
    this.game = gameStates;
  }

  public void paint(Graphics2D g)
  {
    int blockArt = Level.blockArt(x / 64, y / 64);

    switch (blockArt)
    {
    // Orange Block zerstörbar
    case 7:

      g.drawImage(Assets.gras, x, y, null);

      break;

    }

  }

  public void update()
  {
    collission();
  
  }

  public void collission()
  {
   
    //FALSCH! ICH MUSS TESTEN OB UNETR DEM BLOCK MEIN PLAAYER IS UND NICHT OB PLAYER IRGENDETWSAS HITTET
    // VIL IN PAlYER KLASSE EINE METHODE DIE VOR DEM JUMP PRÜFT OB DER HBLOCM ÜBER MIR ZERSTÖRBAR IST, WENN JA --> DIES HIER WEITER GEBEN
    if (game.racquet.topLeft == true || game.racquet.topRight == true)
    {
     
      if(getroffen == true)
      {
        zerströren();
      }
      else
      {
        game.racquet.falling = true;
        getroffen = true;
      }
     
     
      
    }
  }

  private void zerströren()
  {
    System.out.println("555555555555555");
    game.level.blocke[y/64][x/64].walkable = true;
   
    
  }

  public Rectangle getBounds()
  {
    return rec = new Rectangle(x, y, blockBreite, blockHöhe);
  }
}
