import javax.swing.*;
import java.awt.*;

public class RectangleComponent extends JComponent {
  private Rectangle rect;
  private Color col;

  public RectangleComponent(Rectangle a, Color color){
    rect = a;
    col = color;
  }

  public void paintComponent(Graphics g){
    Graphics2D g2D = (Graphics2D) g;

    g2D.setColor(col);
    g2D.draw(rect);
  }

}
