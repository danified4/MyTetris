import java.awt.event.*;

class MyKeyListener extends KeyAdapter{
  public void keyPressed(KeyEvent e)
  {
    if (e.getKeyCode()== KeyEvent.VK_W) {
      TetrisScreen.movement = 'w';
    }
    else if (e.getKeyCode()== KeyEvent.VK_A) {
      TetrisScreen.movement = 'a';
    }
    else if (e.getKeyCode()== KeyEvent.VK_D) {
      TetrisScreen.movement = 'd';
    }
    else if (e.getKeyCode()== KeyEvent.VK_S) {
      TetrisScreen.movement = 's';
    }
    else if (e.getKeyCode()== KeyEvent.VK_P){
      TetrisScreen.movement = 'p';
    }
  }
}