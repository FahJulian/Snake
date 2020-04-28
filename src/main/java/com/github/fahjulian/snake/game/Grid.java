package com.github.fahjulian.snake.game;

import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;

public class Grid extends JPanel
{
  private static final long serialVersionUID = 84309685906098456L;

  private final int rows;
  private final int cols;
  private final int cellSize;
  private final Point pos;
  private final Snake snake;

  public Grid(int x, int y, int rows, int cols, int cellSize)
  {
    super.setSize(cols * cellSize + 2, rows * cellSize + 2);
    this.rows = rows;
    this.cols = cols;
    this.cellSize = cellSize;
    this.pos = new Point(x, y);
    this.snake = new Snake(x + rows / 2 * cellSize + 1, y + cols / 2 * cellSize + 1, cellSize);
  }

  public void update()
  {

  }

  public void render()
  {
    repaint();
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

    snake.render(g);
  }
}