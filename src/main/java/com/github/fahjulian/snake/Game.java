package com.github.fahjulian.snake;

import com.github.fahjulian.snake.ui.Window;

public class Game implements Runnable
{
  private static final String WINDOW_TITLE;
  private static final int WIDTH;
  private static final int HEIGHT;

  private Window window;

  static
  {
    WINDOW_TITLE = "Snake Game";
    WIDTH = 500;
    HEIGHT = 500;
  }

  @Override
  public void run()
  {
    window = new Window(WINDOW_TITLE, WIDTH, HEIGHT);
    window.setVisible(true);
  }
}