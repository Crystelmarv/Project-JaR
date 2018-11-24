import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LevelFileReader
{
  static boolean levelGelesen = false;
  static boolean levelNameGelesen = false;
  static String levelPfad = "";
  static int level = 1;
  static String[] schildText;
  private static String levelName = "#ERROR levelName";

  private static String trennerName = "NAME";
  private static String trennerLevel = "LEVEL";
  private static String trennerSchild = "SCHILD";

  public LevelFileReader()
  {

  }

  public static void LevelRead() throws IOException
  {
    setLevelPfad();
    ArrayInit();

    int i, j;
    int y = 0;

    String currentLine;

    setLevelPfad();
    InputStream is = Game.class.getResourceAsStream(levelPfad);
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));

    do
    {
      currentLine = bufferedReader.readLine();
      // System.out.println("5");

    } while (!currentLine.regionMatches(0, trennerLevel, 0, trennerLevel.length()));
    while ((currentLine = bufferedReader.readLine()) != null
        && !currentLine.regionMatches(0, trennerName, 0, trennerName.length())
        && !currentLine.regionMatches(0, trennerSchild, 0, trennerSchild.length()))
    {
      String[] values = currentLine.trim().split(" ");

      for (i = 0; i < values.length; i++)
      {
        Level.map[y][i] = Integer.parseInt(values[i]);
      }
      y++;
    }
    levelGelesen = true;

  }

  public static int ArrayGroesse1() throws IOException
  {

    String currentLine;
    setLevelPfad();

    InputStream is = Game.class.getResourceAsStream(levelPfad);
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));

    // currentLine = bufferedReader.readLine();

    do
    {
      currentLine = bufferedReader.readLine();
      System.out.println(currentLine);

    } while (!currentLine.regionMatches(0, trennerLevel, 0, trennerLevel.length()));
    currentLine = bufferedReader.readLine();
    String[] values = currentLine.trim().split(" ");
    // System.out.println(values.length);
    return values.length;

  }

  public static int ArrayGroesse2() throws IOException
  {
    int i = 0;

    String currentLine;
    setLevelPfad();
    InputStream is = Game.class.getResourceAsStream(levelPfad);
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));

    do
    {
      currentLine = bufferedReader.readLine();
      // System.out.println("5");

    } while (!currentLine.regionMatches(0, trennerLevel, 0, trennerLevel.length()));
    while ((currentLine = bufferedReader.readLine()) != null
        && !currentLine.regionMatches(0, trennerName, 0, trennerName.length())
        && !currentLine.regionMatches(0, trennerSchild, 0, trennerSchild.length()))
    {
      // if (currentLine != null)
      // {
      i++;
      // }

    }
    System.out.println(i);
    return i;

  }

  public static void ArrayInit() throws IOException
  {
    int i, j;

    i = ArrayGroesse1();
    j = ArrayGroesse2();

    Level.setMap(i, j);

  }

  public static void setLevelPfad()
  {
    switch (level)
    {
    case 1:
      levelPfad = "/level/1.Level.txt";
      break;

    case 2:
      levelPfad = "/level/2.Level.txt";
      break;
    }
  }

  public static void schildEinlesen() throws IOException
  {
    schildArrayInit();

    String currentLine;
    int i = 0;
    String trenner = "123ABC";

    InputStream is = Game.class.getResourceAsStream(levelPfad);
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));

    do
    {
      currentLine = bufferedReader.readLine();
      // System.out.println("5");

    } while (!currentLine.regionMatches(0, trennerSchild, 0, trennerSchild.length()));

    while ((currentLine = bufferedReader.readLine()) != null)
    {

      if (currentLine.regionMatches(0, trenner, 0, trenner.length()))
      {
        i++;
      } else
      {
        schildText[i] = currentLine;
        System.out.println(schildText[i]);
      }

    }

  }

  public static void schildArrayInit() throws IOException
  {
    String trenner = "123ABC";
    String currentLine;
    int i = 1;

    InputStream is = Game.class.getResourceAsStream(levelPfad);
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));

    do
    {
      currentLine = bufferedReader.readLine();
      // System.out.println("5");

    } while (!currentLine.regionMatches(0, trennerSchild, 0, trennerSchild.length()));
    while ((currentLine = bufferedReader.readLine()) != null)
    {
      if (currentLine.regionMatches(0, trenner, 0, trenner.length()))
      {

        i++;

      }

    }

    schildText = new String[i];
  }

  public static String getLevelName()
  {
    return levelName;
  }

  public static void levelNameLaden() throws IOException
  {
    setLevelPfad();
    String currentLine;
    InputStream is = Game.class.getResourceAsStream(levelPfad);
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
    do
    {
      currentLine = bufferedReader.readLine();
      System.out.println(currentLine);

    } while (!currentLine.regionMatches(0, trennerName, 0, trennerName.length()));

    levelName = bufferedReader.readLine();
    levelNameGelesen = true;
  }
}
