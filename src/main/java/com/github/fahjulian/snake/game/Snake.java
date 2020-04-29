package com.github.fahjulian.snake.game;

import com.github.fahjulian.snake.enums.Direction;
import com.github.fahjulian.snake.util.Clock;
import com.github.fahjulian.snake.util.CollisionManager;

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
  private Grid grid;
  
  public Snake(Grid grid, int x, int y, int size)
  {
    this.grid = grid;
    this.size = size;
    this.head = new Rectangle(x, y, size, size);
    this.tail = new LinkedList<Rectangle>();
    this.tail.add(new Rectangle(x, y + size, size, size));
    this.movingDirection = Direction.UP;
    this.movingClock = new Clock();
  }

  private Snake(Snake snake)
  {
    this.grid = snake.grid;
    this.size = snake.size;
    this.head = (Rectangle) snake.head.clone();
    this.tail = new LinkedList<Rectangle>();
    for (Rectangle tailElement: snake.tail)
      this.tail.add((Rectangle) tailElement.clone());
    this.movingClock = new Clock();
    this.movingDirection = snake.movingDirection;
  }

  public void update()
  { 
    if (movingClock.peekDuration() > 500)
    {
      if (wouldCollideOnMove())
      {
        grid.game.gameover();
        return;
      }
      else
      { 
        boolean growSnake = false;
        if (CollisionManager.hasHitStaticObject(head, grid.treat))
        {
          growSnake = true;
          grid.placeTreat();
        }
        move(growSnake);
      }
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

  private void move(boolean growSnake)
  {
    tail.add(0, (Rectangle) head.clone());
    if (!growSnake)
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

  private boolean wouldCollideOnMove()
  {
    Snake testSnake = new Snake(this);
    testSnake.move(false);
    for (Rectangle tailElement: testSnake.tail)
      if (CollisionManager.hasHitStaticObject(testSnake.head, tailElement))
        return true;
    if (CollisionManager.isOutsideBounds(testSnake.head, grid.getBounds()))
      return true;

    return false;
  }
}