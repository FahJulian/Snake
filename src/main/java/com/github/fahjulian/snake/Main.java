package com.github.fahjulian.snake;

import com.github.fahjulian.snake.game.Game;

public class Main 
{
  public static void main(String[] args) {
    new Thread(new Game()).start();
  }
}
