import javax.swing.JFrame;
import java.awt.*;
import javax.swing.Timer;

import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.Scanner;
import java.io.File;
/**
* Responsible for running the game
* @author Isaiah Aganon
* @version 1.9
*/
public class Main implements KeyListener {
  Board board = new Board(); // Creates instance of board object
  Timer timer; // For timer
  char direction; // For pacman character model direction
  static int n; // For game mode selection
  int speed = 40; // Sets speed of ghost and pacman
  /**
  * Creates gui and updates the game
  */
  public Main() { 
    JFrame frame = new JFrame();
    frame.setSize(420, 500);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setResizable(false);
    frame.add(board, BorderLayout.CENTER); // Creates black screen
    frame.setTitle("PACMAN BY ISAIAH AGANON");
    frame.setVisible(true);
    frame.addKeyListener(this); // Allows the player to control the pacman model

    timer = new Timer(speed, new ActionListener() { // Sets the ghosts speed. Lower number equates to more speed  
      /**
      * Runs the game and updates the program based on player and ai interactions.
        Introduces an easy game mode which spawns two ghosts and gives the player 5 lives, a medium game mode which spawns four ghosts and gives the player 3 lives and a hard game mode which spawns two ghosts and gives the player 1 life
      */
      @Override
      public void actionPerformed(ActionEvent e) {
        if (!board.title && !board.titleMode && board.lives > 0 && n == 1) { // Starts game if program is not in title screen mode and player lives are over 0
          board.ghost1.move();
          if (board.ghost1.getShape().intersects(board.pacman.getShape())) { // Removes one of pacman's lives if player intersects with ghost
            board.reset();
          }
          board.ghost2.move();
          if (board.ghost2.getShape().intersects(board.pacman.getShape())) { // Removes one of pacman's lives if player intersects with ghost
            board.reset();
          }
          board.ghost1.updateState(board.states);
          board.ghost2.updateState(board.states);
          board.pacman.move(direction);
          if (board.pellets[board.pacman.x / 20][board.pacman.y / 20]) {
            board.pellets[board.pacman.x / 20][board.pacman.y / 20] = false; // Deletes a pellet on the board whenever the pacman model passes over it.
            board.score++; // Increases the players score whenever the pacman model consumes a pellet.
            if(board.score == 198) // Calls the score saver once the player wins the game
              board.scoreSaver();         
          }
          board.pacman.updateState(board.states);
      } else if (!board.title && !board.titleMode && board.lives > 0 && n == 2) { // Starts game if program is not in title screen mode and player lives are over 0
          board.ghost1.move();
          if (board.ghost1.getShape().intersects(board.pacman.getShape())) { // Removes one of pacman's lives if player intersects with ghost
            board.reset();
          }
          board.ghost2.move();
          if (board.ghost2.getShape().intersects(board.pacman.getShape())) { // Removes one of pacman's lives if player intersects with ghost
            board.reset();
          }
          board.ghost3.move();
          if (board.ghost3.getShape().intersects(board.pacman.getShape())) { // Removes one of pacman's lives if player intersects with ghost
            board.reset();
          }
          board.ghost4.move();
          if (board.ghost4.getShape().intersects(board.pacman.getShape())) { // Removes one of pacman's lives if player intersects with ghost
            board.reset();
          }
          board.ghost1.updateState(board.states);
          board.ghost2.updateState(board.states);
          board.ghost3.updateState(board.states);
          board.ghost4.updateState(board.states);
          board.pacman.move(direction);
          if (board.pellets[board.pacman.x / 20][board.pacman.y / 20]) {
            board.pellets[board.pacman.x / 20][board.pacman.y / 20] = false; // Deletes a pellet on the board whenever the pacman model passes over it.
            board.score++; // Increases the players score whenever the pacman model consumes a pellet.
             if(board.score == 198) // Calls the score saver once the player wins the game
              board.scoreSaver();  
          }
          board.pacman.updateState(board.states);
      } else if (!board.title && !board.titleMode && board.lives > 0 && n == 3) { // Starts game if program is not in title screen mode and player lives are over 0
          board.ghost1.move();
          if (board.ghost1.getShape().intersects(board.pacman.getShape())) { // Removes one of pacman's lives if player intersects with ghost
            board.reset();
          }
          board.ghost2.move();
          if (board.ghost2.getShape().intersects(board.pacman.getShape())) { // Removes one of pacman's lives if player intersects with ghost
            board.reset();
          }
          board.ghost3.move();
          if (board.ghost3.getShape().intersects(board.pacman.getShape())) { // Removes one of pacman's lives if player intersects with ghost
            board.reset();
          }
          board.ghost4.move();
          if (board.ghost4.getShape().intersects(board.pacman.getShape())) { // Removes one of pacman's lives if player intersects with ghost
            board.reset();
          }
          board.ghost5.move();
          if (board.ghost5.getShape().intersects(board.pacman.getShape())) { // Removes one of pacman's lives if player intersects with ghost
            board.reset();
          }
          board.ghost6.move();
          if (board.ghost6.getShape().intersects(board.pacman.getShape())) { // Removes one of pacman's lives if player  intersects with ghost
            board.reset();
          }
          board.ghost7.move();
          if (board.ghost7.getShape().intersects(board.pacman.getShape())) { // Removes one of pacman's lives if player intersects with ghost
            board.reset(); 
          }
          board.ghost8.move();
          if (board.ghost8.getShape().intersects(board.pacman.getShape())) { // Removes one of pacman's lives if player  intersects with ghost
            board.reset();
          }
          board.ghost1.updateState(board.states);
          board.ghost2.updateState(board.states);
          board.ghost3.updateState(board.states);
          board.ghost4.updateState(board.states);
          board.ghost5.updateState(board.states);
          board.ghost6.updateState(board.states);
          board.ghost7.updateState(board.states);
          board.ghost8.updateState(board.states);
          board.pacman.move(direction);
          if (board.pellets[board.pacman.x / 20][board.pacman.y / 20]) {
            board.pellets[board.pacman.x / 20][board.pacman.y / 20] = false; // Deletes a pellet on the board whenever  the pacman model passes over it.
            board.score++; // Increases the players score whenever the pacman model consumes a pellet.   
             if(board.score == 198) // Calls the score saver once the player wins the game
               board.scoreSaver();  
          }
          board.pacman.updateState(board.states);
        }
      }
    });
    timer.start();   
  }
  /**
  * Pacman Game
  * Game consists of three modes
  * Game mode 1 is easy mode, two ghosts spawn in and the player has five lives
  * Game mode 2 is medium mode, four ghosts spawn in and the player has three lives
  * Game mode 3 is hard mode, eight ghosts spawn in and the player has one life
  * The game ends once the player loses all of their lives or consumes all of the pellets on the board
  * Player scores are saved to text file, scores increase based on pellets consumed and amount of player lives 
  * Controls 
    To move left press A or ⟵
    To move right press D or →
    To move up press W or ↑
    To move down press S or ↓
  */
  public static void main(String[] args) {
    new Main();
    System.out.print("\033[H\033[2J"); // Clears console
    System.out.flush();     
  }   
  @Override
  public void keyTyped(KeyEvent e) {} // To run keylistener
  @Override
  public void keyReleased(KeyEvent e) {}// To run keylistener
  @Override
  public void keyPressed(KeyEvent e) { // Button controls
    // Controls for pacman model
    if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
      direction = 'L';
    } else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
      direction = 'R';
    } else if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
      direction = 'U';
    } else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
      direction = 'D';
    }
    // Controls for title visibility
    else if (e.getKeyCode() == KeyEvent.VK_ENTER) { // If user presses enter the pacman game begins
      board.title = false;
    } else if (e.getKeyCode() == KeyEvent.VK_1) {
      board.titleMode = false;
      n = 1; // Sets game to easy mode
      board.lives = 5; // Sets player lives to 5
    } else if (e.getKeyCode() == KeyEvent.VK_2) {
      board.titleMode = false;
      n = 2; // Set game to medium mode
      board.lives = 3; // Sets player lives to 3
    } else if (e.getKeyCode() == KeyEvent.VK_3) {
      board.titleMode = false;
      n = 3; // Sets game to hard mode
      board.lives = 1; // Sets player lives to 1 
    } else if (e.getKeyCode() == KeyEvent.VK_4) { // Displays scores to console window
      System.out.print("\033[H\033[2J"); // Clears console
      System.out.flush();
      try {
        File file = new File("Scores/SCORES.txt");
        Scanner scan = new Scanner(file);
        while (scan.hasNextLine()) { // Loop reads and prints out everything in text file
          String line = scan.nextLine();
          System.out.println(line);
        }
        scan.close();
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    } else if (e.getKeyCode() == KeyEvent.VK_5){ // Displays controls to console window
      System.out.print("\033[H\033[2J"); // Clears console
      System.out.flush();
      System.out.println("\n\t\t\t\t  PACMAN CONTROLS" + "\n " + "\n\t\t\t To Move left press A or ⟵"
          + "\n\t\t\tTo Move right press D or →" + "\n\t\t\t  To Move up press W or ↑"
          + "\n\t\t\t To Move down press S or ↓");
    }
  }
}