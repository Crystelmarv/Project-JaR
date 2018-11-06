
public class Kamera
{
  
  float x, y;
  private Game game;

  public Kamera(float x, float y, Game game)
  {
    this.game = game;
    this.x = x;
    this.y = y;
  }

  
  public float getX()
  {
    return x;
    
  }
  
  public float getY()
  {
    return y;
  }


  public void update(Racquet player)
  {
    //x--;
    
    x = -player.getX()+600 + Game.WIDTH/2;
    y = -player.getY()+600 + Game.WIDTH/2;
    
  }
}
