
import java.awt.Rectangle;

/**
* Components class provides ghost and pacman classes with variables and conditions
* @author Isaiah Aganon
* @version 1.9
*/
public class Components implements ComponentsTool {

  int index = 0; // For Pacman class
  boolean states[][]; // Game state
  static int cellSize = 20; // Model size
  static int max = 400; // Height and width, 20 x 20
  static int speed = 4; // Sets the speed for the ghosts
  char direction; // Model's direction
  int x, y; // Position variables

  public Components() {
    this.states = new boolean[20][20];
    for (int i = 0; i < 20; i++) {
      for (int j = 0; j < 20; j++) {
        this.states[i][j] = false;
      }
    }
  }
  /**
  * Responsible for updating game
  * @param UpdateStates determines if game state is updated
  */
  public void updateState(boolean[][] UpdateStates) {
    for (int i = 0; i < 20; i++) {
      for (int j = 0; j < 20; j++) {
        this.states[i][j] = UpdateStates[i][j];
      }
    }
  }
  /**
  * Determines character model's pathfinding throughout the maze
  * @param x the character model's x coordinate position
  * @param y the character model's y coordinate position
  * @return returns true if player is within bounds
  */
  public boolean isValid(int x, int y) {
    if (x >= cellSize && x < max && y >= cellSize && y < max && states[x / 20 - 1][y / 20 - 1]) {
      return true;
    } else {
      return false;
    }
  }
  /**
  * Used to determine when ghost and pacman model intersect causing the game to either end or deduct one of the player's lives
  * @return The model's x and y position along with its width and height
  * @return returns rectangle for ghost and pacman interaction
  */
  public Rectangle getShape() {
    return new Rectangle(x, y, 20, 20);
  }
}