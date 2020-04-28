package com.github.fahjulian.snake.game;

import java.util.LinkedList;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;

public class Snake {
  private Rectangle head;
  private LinkedList<Rectangle> tail;
  
  public Snake(int x, int y, int size)
  {
    head = new Rectangle(x, y, size, size);
    tail = new LinkedList<Rectangle>();
    tail.add(new Rectangle(x, y + size, size, size));
    tail.add(new Rectangle(x + size, y + size, size, size));
  }

  public void render(Graphics g)
  {
    g.setColor(Color.GREEN.darker());
    g.fillRect(head.x + 1, head.y + 1, head.width - 2, head.height - 2);

    for (Rectangle tailElement: tail)
    {
      g.setColor(Color.GREEN.brighter());
      g.fillRect(tailElement.x + 1, tailElement.y + 1, tailElement.width - 2, tailElement.height - 2);
    }
  }
}