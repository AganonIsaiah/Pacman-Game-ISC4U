
import java.awt.Rectangle;

/**
* Interface for Components class
* @author Isaiah Aganon
* @version 1.9
*/
public interface ComponentsTool { 
  public void updateState(boolean[][] UpdateStates); // Updates game state
  public boolean isValid(int x, int y); // Determines validity of character models path finding
  public Rectangle getShape(); // Responsible for determining if pacman and ghost models intersect
}