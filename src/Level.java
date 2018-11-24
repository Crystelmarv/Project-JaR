import java.awt.Color;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.IOException;

public class Level
{
  private GameStates game;
  boolean start = false;

  int bx = 0;
  int by = 0;
  int maxY;
  boolean gegnerInLevel = false;
  boolean schilTextLaden = false;
  static int map[][];
  int playerX;
  int playerY;
  int moeglicheAepfel = 0;
  
  

  Entity[][] blocke = new Entity[map.length][map[0].length];
  GegnerMarienKaefer[] gegner;

  // map.length --> y Achse
  // map[0].lenth --> x Achse
  // !!! In dem 2D Array steht nicht [x-Achse][y-Achse] ---> sondern
  // [y-Achse][x-Achse] !!!

  /*
   * public static void main(String[] argv) { level(); }
   */
  public Level(GameStates game) throws IOException
  {
    this.game = game;

    int ix;
    int iy;
    int y = 0;
    int x = 0;
    int blockArt;
    int k = 0;
    int indexSchild = 0;

    // System.out.println(map[5][1]);
    // System.out.println(blocke[3][5]);
    // System.out.println(map[0].length);
    // System.out.println(blocke[0].length);
    for (iy = 0; iy < map.length; iy++)

    {
      // System.out.println("y" +iy);
      for (ix = 0; ix < map[0].length; ix++)
      {

        // System.out.println(ix);
        blockArt = blockArt(ix, iy);
        switch (blockArt)
        {
        // Block
        case 20:
        case 53:
        case 30:
        case 31:
        case 33:
        case 34:
        case 22:

          blocke[by][bx] = new Block(game);
          blocke[by][bx].setBlock(x, y);
          
          if(blockArt == 53)
          {
            moeglicheAepfel++;
          }

          break;
        // helles Blau
        case 21:

          blocke[by][bx] = new Block(game);
          blocke[by][bx].setBlock(x, y);

          break;
     
        case 32:
          blocke[by][bx] = new BlockZerstoerbar(game);
          blocke[by][bx].setBlock(x, y);
          break;

        case 19:
          //Schild
          if(schilTextLaden == false)
          {
            
            LevelFileReader.schildEinlesen();
            schilTextLaden = true;
          }
          
          blocke[by][bx] = new BlockSchild(game);
          blocke[by][bx].setBlock(x, y);
          blocke[by][bx].setIndex(indexSchild);
          indexSchild++;

          break;

        // BlockItem

        case 10:
        case 51:
        case 52:
        case 54:

          blocke[by][bx] = new BlockItem(game);
          blocke[by][bx].setBlock(x, y);
          
          if(blockArt == 52)
          {
            moeglicheAepfel++;
          }

          break;
         
          //LevelSpawn
        case 15:
          blocke[by][bx] = new LevelSpawn(game);
          blocke[by][bx].setBlock(x, y);
          playerX = x;
          playerY = y;
          break;
          
          //LevelZiel
          
        case 16:
          blocke[by][bx] = new LevelZiel(game);
          blocke[by][bx].setBlock(x, y);
          break;
          
          //LevelCheckpoint
          
        case 17:
          blocke[by][bx] = new LevelCheckpoint(game);
          blocke[by][bx].setBlock(x, y);
          break;
          
          //Level Tod
        case 18:
          blocke[by][bx] = new LevelTod(game);
          blocke[by][bx].setBlock(x, y);
          break;
          
          
          
          //Gegner Spawn
          
        case 70:
        case 71:
        case 72:
        case 73:
          blocke[by][bx] = new GegnerSpawn(game);
          blocke[by][bx].setBlock(x, y);
          blocke[by][bx].init();
         
          break;
          
          
       
          
          
        }
        x = x + 64;

        // g.fillRect(blocke[by][bx].blockX, blocke[by][bx].blockY,
        // blocke[by][bx].blockBreite, blocke[by][bx].blockHöhe);

        if (bx < blocke[0].length - 1)
        {
          bx++;
        }
        // System.out.println(blocke.length);

      }
      y = y + 64;
      x = 0;
      bx = 0;
      if (by < blocke.length - 1)
      {
        by++;

      }
    }

    maxY = y - 64 * 2;
    if (k > 0)
    {
      gegner = new GegnerMarienKaefer[k];

    }

  }

  public String getBlockId()
  {
    return "Block";

  }

  public void paint(Graphics2D g)
  {

  }

  public static int blockArt(int x, int y)
  {
    int blockArt;

    blockArt = map[y][x];

    return blockArt;

  }

  /*
  public void gegnerSpawn()
  {
    int i, j;
    int x = 0;
    int y = 0;
    int k = 0;

    for (i = 0; i < map.length; i++)
    {
      for (j = 0; j < map[0].length; j++)
      {
        if (map[i][j] == 26)
        {
          gegner[k] = new GegnerDachs(game);
          gegner[k].setBlock(x, y);
          k++;
        }
        x = x + 64;

      }
      x = 0;
      y = y + 64;
    }
    gegnerInLevel = true;

  }
  */

  public int getMaxY()
  {

    return maxY;
  }

  public static void setMap(int i, int j)
  {
    map = new int[j][i];
    System.out.println("j" +j);
    System.out.println(i);

  }

  public int getPlayerSpawnY()
  {
    return playerY;
  }
  
  public int getPlayerSpawnX()
  {
    return playerX;
  }
}
