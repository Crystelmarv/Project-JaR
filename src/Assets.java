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
  
  public static BufferedImage gras, erde, blauerBlock, orangerBlock, schild, orangerBlockOhneKreuz, apfel, 
                              marienKaeferLinks, marienKaeferRechts, checkPointUnten, checkPointObenAus, 
                              checkPointObenAn, bieneLinks, bieneRechts, wasser, seerose, devBlock, herz,
                              fischOben, fischUnten, tod, spawn, ziel, weiss, blau;;
  
  public void init()
  {
    try
    {
      sheet = new SpriteSheet(ImageIO.read(getClass().getResource("/textures/TilesSheet.png")));
    } catch (IOException e)
    {
      e.printStackTrace();
      System.exit(1);
    }
    
    gras = sheet.crop(0, 0, breite, hoehe);
    erde = sheet.crop(breite, 0, breite, hoehe);
    blauerBlock = sheet.crop(breite * 2, 0, breite, hoehe);
    orangerBlock = sheet.crop(breite * 3, 0, breite, hoehe);
    schild = sheet.crop(breite * 4, 0, breite, hoehe);
    orangerBlockOhneKreuz = sheet.crop(0, hoehe, breite, hoehe);
    apfel = sheet.crop(breite, hoehe, breite, hoehe);
    marienKaeferLinks = sheet.crop(breite*2, hoehe, breite, hoehe);
    marienKaeferRechts = sheet.crop(breite*3, hoehe, breite, hoehe);
    checkPointObenAn = sheet.crop(breite*4, hoehe, breite, hoehe);
    checkPointObenAus = sheet.crop(breite*4, hoehe*2, breite, hoehe);
    checkPointUnten = sheet.crop(breite*4, hoehe*3, breite, hoehe);
    bieneLinks = sheet.crop(breite*3, hoehe*2, breite, hoehe);
    bieneRechts = sheet.crop(breite*2, hoehe*2, breite, hoehe);
    wasser  = sheet.crop(breite, hoehe*2, breite, hoehe);
    seerose  = sheet.crop(0, hoehe*2, breite, hoehe);
    devBlock  = sheet.crop(0, hoehe*3, breite, hoehe);
    herz  = sheet.crop(breite, hoehe*3, breite, hoehe);
    fischOben = sheet.crop(breite*2, hoehe*3, breite, hoehe);
    fischUnten = sheet.crop(breite*3, hoehe*3, breite, hoehe);
    tod = sheet.crop(0, hoehe*4, breite, hoehe);
    spawn = sheet.crop(breite, hoehe*4, breite, hoehe);
    ziel = sheet.crop(breite*2, hoehe*4, breite, hoehe);
    weiss = sheet.crop(breite*3, hoehe*4, breite, hoehe);
    blau = sheet.crop(breite*4, hoehe*4, breite, hoehe);
    
  }
  
  

}
