
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.PrintWriter;

/**
* Responsible for drawing the board, screens, character models and updating the state of the game
* @author Isaiah Aganon
* @version 1.9
*/
public class Board extends JPanel {

  Scanner scan = new Scanner(System.in); // For text file
  
  Image titleScreen = Toolkit.getDefaultToolkit().getImage("Images/titleScreen.png"); // For the title screen
  Image endScreen = Toolkit.getDefaultToolkit().getImage("Images/endScreen.png"); // For game over screen
  Image victoryScreen = Toolkit.getDefaultToolkit().getImage("Images/victoryScreen.png"); // For victory screen
  Image menuScreen = Toolkit.getDefaultToolkit().getImage("Images/menuScreen.png"); // For Menu screen

  // For the ghosts character model
  Image redG1 = Toolkit.getDefaultToolkit().getImage("Images/gRed1.jpg");
  Image redG2 = Toolkit.getDefaultToolkit().getImage("Images/gRed2.jpg");
  Image blueG1 = Toolkit.getDefaultToolkit().getImage("Images/gBlue1.jpg");
  Image blueG2 = Toolkit.getDefaultToolkit().getImage("Images/gBlue2.jpg");
  Image orangeG1 = Toolkit.getDefaultToolkit().getImage("Images/gOrange1.jpg");
  Image orangeG2 = Toolkit.getDefaultToolkit().getImage("Images/gOrange2.jpg");
  Image pinkG1 = Toolkit.getDefaultToolkit().getImage("Images/gPink1.jpg");
  Image pinkG2 = Toolkit.getDefaultToolkit().getImage("Images/gPink2.jpg");

  // For pacman character model
  Image pac = Toolkit.getDefaultToolkit().getImage("Images/pacman.jpg");
  Image pacD = Toolkit.getDefaultToolkit().getImage("Images/pacmanDown.jpg");
  Image pacU = Toolkit.getDefaultToolkit().getImage("Images/pacmanUp.jpg");
  Image pacL = Toolkit.getDefaultToolkit().getImage("Images/pacmanLeft.jpg");
  Image pacR = Toolkit.getDefaultToolkit().getImage("Images/pacmanRight.jpg");

  Image[] pacmanImages = {pacL, pacR, pacU, pacD}; // Image array corresponds with Pacman class and allows model to face different directions

  Pacman pacman = new Pacman(10 * Components.cellSize, 15 * Components.cellSize); // Pacman

  // Ghosts
  Ghosts ghost1 = new Ghosts(10 * Components.cellSize, 8 * Components.cellSize);
  Ghosts ghost2 = new Ghosts(10 * Components.cellSize, 9 * Components.cellSize);
  Ghosts ghost3 = new Ghosts(11 * Components.cellSize, 9 * Components.cellSize);
  Ghosts ghost4 = new Ghosts(9 * Components.cellSize, 9 * Components.cellSize);
  Ghosts ghost5 = new Ghosts(10 * Components.cellSize, 8 * Components.cellSize);
  Ghosts ghost6 = new Ghosts(10 * Components.cellSize, 9 * Components.cellSize);
  Ghosts ghost7 = new Ghosts(11 * Components.cellSize, 9 * Components.cellSize);
  Ghosts ghost8 = new Ghosts(9 * Components.cellSize, 9 * Components.cellSize);

  boolean title; // Determines title screens visiblity
  boolean titleMode; // Determines difficulty screens visibility
  boolean pellets[][]; // Pellets
  boolean states[][];
  int lives = 5; // Players lives
  int score; // Score represents amount of pellets the player consumes
  /**
  * Draws the amount of lives the player has on the bottom left of the screen
  * @param g for graphics
  */
  public void drawLives(Graphics g) {
    g.setColor(Color.yellow);
    for (int i = 0; i < lives; i++) {
      g.drawImage(pacR, (Components.cellSize + 5) * i + 15, Components.max + 10, null); 
    }
  }
  /**
  * Fills maze with pellets but clears space for where ghost and pacman model spawns
  */
  public void init() {
    for (int i = 0; i < Components.cellSize; i++) {
      for (int j = 0; j < Components.cellSize; j++) {
        pellets[i][j] = true;
        states[i][j] = true;
      }
    }
    // Clears space for where the ghosts model spawns
    pellets[10][8] = false;
    pellets[10][9] = false;
    pellets[11][9] = false;
    pellets[9][9] = false;
    // Clears space for where pacman model spawns
    pellets[10][15] = false;
  }
  /**
  * Responsible for displaying title screen and menu screen along with along with pellets and character states
  */
  public Board() {
    title = true; 
    titleMode = true;
    pellets = new boolean[Components.cellSize][Components.cellSize];
    states = new boolean[Components.cellSize][Components.cellSize];
    init();
  }
  /**
  * Responsible for updating game state and pellets
  * @param g for graphics
  * @param x position variable
  * @param y position variable
  * @param width for pellets, maze and determining if models can go through walls
  * @param height for pellets, maze and determining if models can go through walls
  */
  public void update(Graphics g, int x, int y, int width, int height) {
    //g.drawRect(x, y, width, height); // Draws rectangle with outline 
    g.fillRect(x, y, width, height); // Draws rectangle with outline filled in
    for (int i = x / 20; i < x / 20 + width / 20; i++) {
      for (int j = y / 20; j < y / 20 + height / 20; j++) {
        pellets[i][j] = false;
        states[i - 1][j - 1] = false; // - 1 allows the ghosts to move within the maze, simply putting states[1][j]  would allow the ghosts to move freely through the walls
      }
    }
  }
  /**
  * Responsbile for populating the pellets on the map
  * @param g for graphics
  */
  public void drawPellets(Graphics g) { 
    g.setColor(Color.yellow);
    for (int i = 1; i < Components.cellSize; i++) {
      for (int j = 1; j < Components.cellSize; j++) {
        if (pellets[i][j]) {
          g.fillOval(i * 20 + 8, j * 20 + 8, 4, 4);
        }
      }
    }
  }  
  /**
  * Saves user score to text file at end of game
  */
  public void scoreSaver() {
    System.out.print("\033[H\033[2J"); // Clears console
    System.out.flush();
    System.out.print("Enter name: ");
    String name = scan.nextLine();
    System.out.print("\033[H\033[2J"); // Clears console
    System.out.flush();
      try (FileWriter f = new FileWriter("Scores/SCORES.txt", true);
          BufferedWriter b = new BufferedWriter(f);
          PrintWriter p = new PrintWriter(b);) { // Writes user name and score to file
        p.print("\n"+name + "'s Score: " + multiplier(score) + ". Pellets consumed: " + score + ". lives: " +lives+".");
      } catch (IOException i) {
        i.printStackTrace();
      }  
  }
  /**
  * Responsible for resetting the game state once a player loses a life
  */
  public void reset() { 
    if (lives > 0) // Takes away one of pacmans lives if player intersects with ghost
      lives--;

    // Resets ghosts position
    ghost1.x = 10 * Components.cellSize;
    ghost1.y = 8 * Components.cellSize;
    ghost2.x = 10 * Components.cellSize;
    ghost2.y = 9 * Components.cellSize;
    ghost3.x = 11 * Components.cellSize;
    ghost3.y = 9 * Components.cellSize;
    ghost4.x = 9 * Components.cellSize;
    ghost4.y = 9 * Components.cellSize;
    ghost5.x = 10 * Components.cellSize;
    ghost5.y = 8 * Components.cellSize;
    ghost6.x = 10 * Components.cellSize;
    ghost6.y = 9 * Components.cellSize;
    ghost7.x = 11 * Components.cellSize;
    ghost7.y = 9 * Components.cellSize;
    ghost8.x = 9 * Components.cellSize;
    ghost8.y = 9 * Components.cellSize;

    // Resets player position
    pacman.x = 10 * Components.cellSize;
    pacman.y = 15 * Components.cellSize;

    if(lives == 0) // Saves user score
       scoreSaver();
  }
  /**
  * Responsible for creating the maze
  * @param g for graphics
  */
  public void drawBoard(Graphics g) { 
    g.setColor(Color.white);
    g.drawRect(19, 19, 382, 382); // Draws a white rectangle outline on the screen representing the border of the map

    g.setColor(Color.orange);
    // Topside of map
    update(g, 40, 40, 60, 20);
    update(g, 120, 40, 60, 20);
    update(g, 200, 20, 20, 40);
    update(g, 240, 40, 60, 20);
    update(g, 320, 40, 60, 20);
    update(g, 40, 80, 60, 20);
    update(g, 160, 80, 100, 20);
    update(g, 200, 80, 20, 60);
    update(g, 320, 80, 60, 20);

    // Middle ends and spawn area
    update(g, 20, 120, 80, 60);
    update(g, 320, 120, 80, 60);
    update(g, 20, 200, 80, 60);
    update(g, 320, 200, 80, 60);
    update(g, 160, 160, 40, 20);
    update(g, 220, 160, 40, 20);
    update(g, 160, 180, 20, 20);
    update(g, 160, 200, 100, 20);
    update(g, 240, 180, 20, 20);

    // Middle borders between end and spawn area
    update(g, 120, 120, 60, 20);
    update(g, 120, 80, 20, 100);
    update(g, 280, 80, 20, 100);
    update(g, 240, 120, 60, 20);
    update(g, 280, 200, 20, 60);
    update(g, 120, 200, 20, 60);
    update(g, 160, 240, 100, 20);
    update(g, 200, 260, 20, 40);

    // Bottom Side of map
    update(g, 120, 280, 60, 20);
    update(g, 240, 280, 60, 20);
    update(g, 40, 280, 60, 20);
    update(g, 80, 280, 20, 60);
    update(g, 320, 280, 60, 20);
    update(g, 320, 280, 20, 60);
    update(g, 20, 320, 40, 20);
    update(g, 360, 320, 40, 20);
    update(g, 160, 320, 100, 20);
    update(g, 200, 320, 20, 60);
    update(g, 40, 360, 140, 20);
    update(g, 240, 360, 140, 20);
    update(g, 280, 320, 20, 60);
    update(g, 120, 320, 20, 60);

    repaint();
  }
  /**
  * Responsible for drawing the maze, pellets, lives, score, screens and character models 
  * @param g for graphics
  */
  @Override
  public void paint(Graphics g) {
    g.setColor(Color.black); // Creates background
    g.fillRect(0, 0, 420, 500);

    drawBoard(g); // Draws maze
    drawPellets(g); // Draws pellets
    drawLives(g); // Draws amount of player lives at bottom left of screen

    g.drawString("PELLETS CONSUMED: " + score, Components.max / 2 + 50, Components.max + 20); // Draws player score at bottom right of the screen

    g.drawImage(redG1, ghost1.x, ghost1.y, null); // Draws red ghost at spawn area facing right
    g.drawImage(blueG1, ghost2.x, ghost2.y, null); // Draws blue ghost at spawn area facing right
    g.drawImage(orangeG1, ghost3.x, ghost3.y, null); // Draws orange ghost at spawn area facing right
    g.drawImage(pinkG1, ghost4.x, ghost4.y, null); // Draws pink ghost at spawn area facing right
    g.drawImage(redG2, ghost5.x, ghost5.y, null); // Draws red ghost at spawn area facing right
    g.drawImage(blueG2, ghost6.x, ghost6.y, null); // Draws blue ghost at spawn area facing right
    g.drawImage(orangeG2, ghost7.x, ghost7.y, null); // Draws orange ghost at spawn area facing right
    g.drawImage(pinkG2, ghost8.x, ghost8.y, null); // Draws pink ghost at spawn area facing right
    g.drawImage(pacmanImages[pacman.index], pacman.x, pacman.y, null); // Draws pacman at player spawn area
    
    if (title) { // Draws title card upon starting the program
      g.drawImage(titleScreen, 0, 0, null);
    }
    if (!title && titleMode) { // Draws menu card upon closing the title card
      g.drawImage(menuScreen, 0, 0, null);
    }
    if (lives == 0) { // Draws end screen once the player loses all their lives
      g.drawImage(endScreen, 0, 0, null);
    }
    if (check()) { // Draws victory screen once user collects all pellets
      g.drawImage(victoryScreen, 0, 0, null);
    }
  }
  /**
  * Responsible for determining if the player consumed all the pellets on the map without dying
  * @return returns true if player consumes all pellets, returns false if not true
  */
  public boolean check() {
    for (int i = 1; i < Components.cellSize; i++) {
      for (int j = 1; j < Components.cellSize; j++) {
        if (pellets[i][j]) {
          return false; 
        }
      }
    }
    return true; 
  }
  /**
  * Recursive function that acts as a score multiplier. Used to mimic the scoring system in the original pacman game
  * @param score takes score and multiplies it
  * @return int Returns score multiplier
  */
  public int multiplier(int score){  
    if(score == 0)
      return score;
    else
      score--;
      return ((score * 5 + multiplier(score)) - (lives * 5)); // Generates player score based on pellets consumed, less player lives equate to a higher score
    }
}