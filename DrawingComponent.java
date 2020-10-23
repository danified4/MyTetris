import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import java.awt.Rectangle;
import java.awt.Color;

public class DrawingComponent extends JComponent{
  private Rectangle block1;
  private Rectangle block2;
  private Rectangle block3;
  private Rectangle block4;
  private Color col;

  public DrawingComponent(Rectangle a, Rectangle b, Rectangle c, Rectangle d, Color color){
    block1 = a;
    block2 = b;
    block3 = c;
    block4 = d;
    col = color;
  }

  public void paintComponent(Graphics g){
    Graphics2D g2D = (Graphics2D) g;

    g2D.setColor(col);
    g2D.fill(block1);
    g2D.fill(block2);
    g2D.fill(block3);
    g2D.fill(block4);
  }
}



