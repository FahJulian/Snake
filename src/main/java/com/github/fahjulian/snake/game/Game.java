package com.github.fahjulian.snake.game;

import com.github.fahjulian.snake.enums.Direction;
import com.github.fahjulian.snake.enums.GameState;
import com.github.fahjulian.snake.ui.Window;
import com.github.fahjulian.snake.util.Clock;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game implements Runnable {
  private static final String WINDOW_TITLE;
  private static final int WIDTH;
  private static final int HEIGHT;
  private static final int FPS;
  private static final int GRID_ROWS;
  private static final int GRID_COLS;
  private static final int CELL_SIZE;

  private Clock gameClock;
  private Window window;
  Grid grid;

  private GameState state;

  static {
    WINDOW_TITLE = "Snake Game";
    GRID_ROWS = 25;
    GRID_COLS = 25;
    CELL_SIZE = 25;
    WIDTH = GRID_COLS * CELL_SIZE + 2;
    HEIGHT = GRID_ROWS * CELL_SIZE + 2;
    FPS = 60;
  }

  public Game() {
    // state = GameState.NEWGAME;
    state = GameState.INGAME;
    gameClock = new Clock();
  }

  private void update() {
    grid.update();
  }

  private void render() {
    grid.render();
  }

  @Override
  public void run() {
    window = new Window(WINDOW_TITLE, WIDTH, HEIGHT);
    window.setVisible(true);

    grid = new Grid(0, 0, GRID_ROWS, GRID_COLS, CELL_SIZE);
    window.add(grid, 1, 0, grid.getSize().width, grid.getSize().height);

    window.addKeyListener(new KeyHandler(this));

    gameloop();
  }

  private void gameloop() {
    while (true) {
      if (state == GameState.INGAME)
        update();
      render();

      if (gameClock.peekElapsed() < 1000 / FPS) {
        try {
          Thread.sleep(1000 / FPS - gameClock.getElapsed());
        } catch (InterruptedException e) {
          e.printStackTrace();
          System.exit(-1);
        }
      }
      gameClock.reset();
    }
  }
}

class KeyHandler implements KeyListener 
{
  private Game game;

  public KeyHandler(Game game)
  {
    this.game = game;
  }

  @Override
  public void keyPressed(KeyEvent e) {
  }
  
  @Override
  public void keyReleased(KeyEvent e) {
    switch (e.getKeyCode())
    {
      case KeyEvent.VK_UP: game.grid.snake.setDirection(Direction.UP); break;
      case KeyEvent.VK_DOWN: game.grid.snake.setDirection(Direction.DOWN); break;
      case KeyEvent.VK_LEFT: game.grid.snake.setDirection(Direction.LEFT); break;
      case KeyEvent.VK_RIGHT: game.grid.snake.setDirection(Direction.RIGHT); break;
    }
  }
  
  @Deprecated @Override
  public void keyTyped(KeyEvent e) {}
}