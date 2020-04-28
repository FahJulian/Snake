package com.github.fahjulian.snake.ui;

import javax.swing.JFrame;

import java.awt.Dimension;

/**
 * Small JFrame wrapper to simplify code and add resizing function
 * -> JFrame content Pane is not as big as the actual JFrame
 */
public class Window extends JFrame
{
  private static final long serialVersionUID = 6334810647748211998L;
  
  private final Dimension size;

  public Window(String title, int width, int height)
  {
    super(title);
    size = new Dimension(width, height);

    setSize(size);
    setResizable(false);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
  }

  @Override
  public void setVisible(boolean visible)
  {
    super.setVisible(visible);
    if (visible) resize();
  }

  private void resize()
  {
    if (!isVisible()) return;

    int extraHeight = size.height - getContentPane().getSize().height;
    setSize(size.width, size.height + extraHeight);
  }
}