import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LevelFileReader
{
  static boolean levelGelesen = false;
  static String levelPfad ="";
  static int level = 1;

  public LevelFileReader()
  {

  }

  public static void LevelRead() throws IOException
  {
    
    ArrayInit();
    
    int i, j;
    int y = 0;
    

    String currentLine;

    setLevelPfad();
    FileReader fileReader = new FileReader(levelPfad);
    BufferedReader bufferedReader = new BufferedReader(fileReader);

    while ((currentLine = bufferedReader.readLine()) != null)
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
    FileReader fileReader = new FileReader(levelPfad);
    BufferedReader bufferedReader = new BufferedReader(fileReader);
    
    
    currentLine = bufferedReader.readLine();
        
    String[] values = currentLine.trim().split(" ");
    
    

    return values.length;

  }
  
  public static int ArrayGroesse2() throws IOException
  {
    int i = 0;

    String currentLine;
    setLevelPfad();
    FileReader fileReader = new FileReader(levelPfad);
    BufferedReader bufferedReader = new BufferedReader(fileReader);
    
    while ((currentLine = bufferedReader.readLine()) != null)
    {
      if(currentLine == null)
      {        
      }
      else
      {
        i++;
      }
    }

    return i;


  }
  
  public static void ArrayInit() throws IOException
  {
    int i, j;
    
    i = ArrayGroesse1();
    j = ArrayGroesse2();
    System.out.println("j" + j);
    System.out.println("i" + i );
    
   Level.setMap(i, j);
    
  }
  
  public static void setLevelPfad()
  {
    switch(level)
    {
    case 1:
      levelPfad = "res/level/1.Level.txt";
      break;
      
    case 2:
      levelPfad = "res/level/2.Level.txt";
      break;
    }
  }

}
