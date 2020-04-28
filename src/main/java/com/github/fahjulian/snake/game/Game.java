package com.github.fahjulian.snake.game;

import com.github.fahjulian.snake.enums.GameState;
import com.github.fahjulian.snake.ui.Window;

public class Game implements Runnable
{
  private static final String WINDOW_TITLE;
  private static final int WIDTH;
  private static final int HEIGHT;
  private static final int FPS;
  private static final int GRID_ROWS;
  private static final int GRID_COLS;
  private static final int CELL_SIZE;

  private Window window;
  private Grid grid;
  private GameState state;

  static
  {
    WINDOW_TITLE = "Snake Game";
    GRID_ROWS = 25;
    GRID_COLS = 25;
    CELL_SIZE = 25;
    WIDTH = GRID_COLS * CELL_SIZE + 2;
    HEIGHT = GRID_ROWS * CELL_SIZE + 2;
    FPS = 60;
  } 

  public Game()
  {
    state = GameState.NEWGAME;
  }

  private void update()
  {
    grid.update();
  }

  private void render()
  {
    grid.render();
  }

  @Override
  public void run()
  {
    window = new Window(WINDOW_TITLE, WIDTH, HEIGHT);
    window.setVisible(true);

    grid = new Grid(0, 0, GRID_ROWS, GRID_COLS, CELL_SIZE);
    window.add(grid, 1, 0, grid.getSize().width, grid.getSize().height);

    gameloop();
  }

  private void gameloop()
  {
    while (true)
    {
      long start = System.nanoTime();

      if (state == GameState.INGAME)
        update();
      render();

      int delta = (int)((System.nanoTime() - start) / 1.0e6);
      if (delta < 1000 / FPS)
      {
        try 
        {
          Thread.sleep(1000 / FPS - delta);
        } 
        catch (InterruptedException e)
        {
          e.printStackTrace();
          System.exit(-1);
        }
      }
    }
  }
}