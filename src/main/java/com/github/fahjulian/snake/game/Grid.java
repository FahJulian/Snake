package com.github.fahjulian.snake.game;

import java.util.Random;

import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

public class Grid extends JPanel
{
  private static final long serialVersionUID = 84309685906098456L;

  private final int rows;
  private final int cols;
  private final int cellSize;
  private final Point pos;
  final Game game;
  final Snake snake;

  Rectangle treat;

  public Grid(Game game, int x, int y, int rows, int cols, int cellSize)
  {
    super.setSize(cols * cellSize + 2, rows * cellSize + 2);
    this.game = game;
    this.rows = rows;
    this.cols = cols;
    this.cellSize = cellSize;
    this.pos = new Point(x, y);
    this.snake = new Snake(this, x + rows / 2 * cellSize + 1, y + cols / 2 * cellSize + 1, cellSize);
    placeTreat();
  }

  public void update()
  {
    snake.update();
  }

  public void render()
  {
    repaint();
  }

  void placeTreat()
  {
    Random r = new Random();
    this.treat = new Rectangle(
      pos.x + ((int) Math.round(r.nextDouble() * cols)) * cellSize + 1,
      pos.y + ((int) Math.round(r.nextDouble() * rows)) * cellSize + 1,
      cellSize, cellSize);
  }

  @Override
  protected void paintComponent(Graphics g)
  {
    // Background
    g.setColor(Color.WHITE);
    g.fillRect(pos.x, pos.y, getSize().width, getSize().height);
    
    // Grid lines
    g.setColor(Color.LIGHT_GRAY);
    for (int row = 0; row < rows + 1; row++)
      g.fillRect(pos.x, pos.y + row * cellSize, getSize().width, 2);
    for (int col = 0; col < cols + 1; col++)
      g.fillRect(pos.x + col * cellSize, pos.y, 2, getSize().height);

    // Treat
    if (treat != null)
    {
      g.setColor(Color.RED);
      g.fillRect(treat.x + 1, treat.y + 1, treat.width - 2, treat.height - 2);
    }

    snake.render(g);
  }
}