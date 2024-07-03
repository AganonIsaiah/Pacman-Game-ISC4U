
import java.lang.Math;
import java.util.HashSet;
import java.util.*;

/**
* Responsible for the ghosts movement and pathfinding
* @author Isaiah Aganon
* @version 1.9
*/
public class Ghosts extends Components { // Use of inheritance

  /**
  * Sets position variables for the ghosts
  * @param x position variable
  * @param y position variable
  */
  public Ghosts(int x, int y) {
    this.x = x;
    this.y = y;
    direction = 'L';
  }
  /**
  * Responsbile for the ghosts' decision making in terms of traversing through the maze
  * @return returns true if ghost are within bounds
  */
  public boolean choice() {
    if (x % cellSize == 0 && y % cellSize == 0) {
      return true;
    } else {
      return false;
    }
  }
  /**
  * Responsible for determining where the ghosts travel, employs the use of a randomizer and hashset to achieve this
  * @return returns the new direction for the ghosts
  */
  public char selectDirection() {
    int random;
    int newx = x, newy = y;
    Set<Character> mySet = new HashSet<Character>();
    char backwards = 'R';
    switch (direction) {
      case 'L':
        backwards = 'R';
        break;
      case 'R':
        backwards = 'L';
        break;
      case 'U':
        backwards = 'D';
        break;
      case 'D':
        backwards = 'U';
        break;
    }
    char newDirection = backwards;
    while (newDirection == backwards || !isValid(newx, newy)) {
      if (mySet.size() == 3) {
        mySet.clear();
        newDirection = backwards;
        break;
      }
      random = (int) (Math.random() * 4) + 1;
      if (random == 1) {
        newDirection = 'L';
        newx -= speed;
      } else if (random == 2) {
        newDirection = 'R';
        newx += cellSize;
      } else if (random == 3) {
        newDirection = 'U';
        newy -= speed;
      } else if (random == 4) {
        newDirection = 'D';
        newy += cellSize;
      }
      if (newDirection != backwards) {
        mySet.add(newDirection);
      }
    }
    return newDirection;
  }
  /**
  * Sets the direction of the ghosts based on what character is sent back
    and stops the ghosts from merging into the walls
  */
  public void move() {
    if (choice()) {
      direction = selectDirection();
    }
    switch (direction) {
      case 'L':
        if (isValid(x - speed, y))
          x -= speed;
        break;
      case 'R':
        if (isValid(x + cellSize, y))
          x += speed;
        break;
      case 'U':
        if (isValid(x, y - speed))
          y -= speed;
        break;
      case 'D':
        if (isValid(x, y + cellSize))
          y += speed;
        break;
    }
  }
}