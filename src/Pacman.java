/**
* Responsbile for the movement of the pacman model
* @author Isaiah Aganon
* @version 1.9
*/
public class Pacman extends Components {
  /**
  * Sets position variables for pacman 
  * @param x position variable
  * @param y position variable
  */
  public Pacman(int x, int y) { 
    this.x = x;
    this.y = y;
  }
  /**
  * Responsible for determining where the pacman model is able to move on the map
    Stops pacman model from merging into the walls
  * @param direction controls pacman model's movement
  */
  public void move(char direction) {
    switch (direction) {
      case 'L':
        if (isValid(x - speed, y)) // Stops pacman model from merging into the walls
          x -= speed;
        else if (y > 178 && y < 182 && x == 20) // Allows pacman character model to go through the tunnel at the left end of the map
          x = 380;
        index = 0;
        break;
      case 'R':
        if (isValid(x + cellSize, y)) // Stops pacman model from merging into the walls
          x += speed;
        else if (y > 178 && y < 182 && x == 380) // Allows pacman character model to go through the tunnel at the right end of the map
          x = 20;
        index = 1;
        break;
      case 'U':
        if (isValid(x, y - speed)) // Stops pacman model from merging into the walls
          y -= speed;
        index = 2;
        break;
      case 'D':
        if (isValid(x, y + cellSize)) // Stops pacman model from merging into the walls
          y += speed;
        index = 3;
        break;
    }
  }
}