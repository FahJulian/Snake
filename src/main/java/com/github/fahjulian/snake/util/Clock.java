package com.github.fahjulian.snake.util;

public class Clock {
  private long timer;

  public Clock()
  {
    timer = System.nanoTime();
  }

  public int peekElapsed()
  {
    return (int) ((System.nanoTime() - timer) / 1.0e6);
  }

  public int getElapsed()
  {
    int elapsed = peekElapsed();
    reset();
    return elapsed;
  }

  public void reset()
  {
    timer = System.nanoTime();
  }
}