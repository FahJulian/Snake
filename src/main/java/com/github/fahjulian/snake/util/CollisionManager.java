package com.github.fahjulian.snake.util;

import java.awt.Rectangle;

public class CollisionManager {
  public static boolean isOutsideBounds(Rectangle movingObject, Rectangle bounds)
  {
    return (movingObject.getMinX() < bounds.getMinX() || movingObject.getMaxX() > bounds.getMaxX() ||
            movingObject.getMinY() < bounds.getMinY() || movingObject.getMaxY() > bounds.getMaxY());
  }

  public static boolean hasHitStaticObject(Rectangle movingObject, Rectangle staticObject)
  {
    return (movingObject.intersects(staticObject));
  }
}