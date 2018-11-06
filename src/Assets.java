import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Assets
{
  static SpriteSheet sheet;
  
  private static int breite = 64;
  private static int hoehe = 64;
  
  public static BufferedImage gras, erde;
  
  public static void init()
  {
    try
    {
      sheet = new SpriteSheet(ImageIO.read(new File("res/textures/TilesSheet.png")));
    } catch (IOException e)
    {
      e.printStackTrace();
      System.exit(1);
    }
    
    gras = sheet.crop(0, 0, breite, hoehe);
    erde = sheet.crop(breite, 0, breite, hoehe);
  }
  
  

}