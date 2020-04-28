package com.github.fahjulian.snake.ui;

import javax.swing.JFrame;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

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
    setLayout(new GridBagLayout());
    requestFocus();
  }

  /**
   * Create a {@code GridBagConstraints} object for the component and add it to the JFrame
   * @param comp The component to add
   * @param row The row in the grid
   * @param col The col in the grid
   * @param width The width in the grid
   * @param height The height in the grid
   */
  public void add(Component comp, int row, int col, int width, int height)
  {
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.BOTH;
    c.gridx = col;
    c.gridy = row;
    c.weightx = 1.0;
    c.weighty = 1.0;
    c.gridwidth = width;
    c.gridheight = height;

    super.add(comp, c);
  }

  @Override
  public void paint(Graphics g)
  {
    Graphics2D g2d = (Graphics2D) g;
    g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
    super.paint(g2d);
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