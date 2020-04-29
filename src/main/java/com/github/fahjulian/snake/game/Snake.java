package com.github.fahjulian.snake.game;

import com.github.fahjulian.snake.enums.Direction;
import com.github.fahjulian.snake.util.Clock;

import java.util.LinkedList;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;

public class Snake {
  private final int size;
  private Rectangle head;
  private LinkedList<Rectangle> tail;
  private Direction movingDirection;
  private Clock movingClock;
  
  public Snake(int x, int y, int size)
  {
    this.size = size;
    head = new Rectangle(x, y, size, size);
    tail = new LinkedList<Rectangle>();
    tail.add(new Rectangle(x, y + size, size, size));
    tail.add(new Rectangle(x + size, y + size, size, size));
    movingDirection = Direction.UP;
    movingClock = new Clock();
  }

  public void update()
  { 
    if (movingClock.peekElapsed() > 1000)
    {
      move();
      movingClock.reset();
    }
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

  public void setDirection(Direction dir)
  {
    this.movingDirection = dir;
  }

  private void move()
  {
    tail.add(0, (Rectangle) head.clone());
    tail.remove(tail.size() - 1);
    
    switch (movingDirection)
    {
      case UP:
        head.translate(0, -size);
        break;
      case DOWN:
        head.translate(0, size);
        break;
      case LEFT:
        head.translate(-size, 0);
        break;
      case RIGHT:
        head.translate(size, 0);
        break;
    }
  }
}