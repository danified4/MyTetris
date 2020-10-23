import javax.swing.*;
import java.awt.Rectangle;
import java.awt.Color;

public class TetrisScreen {
  public static JFrame window = new JFrame();
  public static char movement = '0';
  private static Piece currentPiece;
  private static int score = 0;
  private static boolean go = true;

  public static void main(String[] args){
    while(go)
    {
      playTetris();
      break;
    }
  }
  public static void playTetris(){
    window.setSize(Piece.bs*13,Piece.bs*25);
    window.add(makeEdges());
    window.addKeyListener(new MyKeyListener());
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setVisible(true);
    window.setTitle("Tetris Poo.0");

    JLabel scoreDisplay;

    scoreDisplay = new JLabel("Score: "+score);
    scoreDisplay.setFont(scoreDisplay.getFont().deriveFont(20.0f));
    scoreDisplay.setHorizontalAlignment( SwingConstants.CENTER );
    scoreDisplay.setVerticalAlignment( SwingConstants.TOP );
    window.add(scoreDisplay);
    window.validate();

    createRandomPiece();
    boolean run = true;
    Piece.initBlockField();
    DrawingComponent dc;
    while(run){
      timer(400-2*score);
      if(movement == 'p'){
        break;
      }

      if(!hasNextCollided(movement))implementMovement();
      dc = makePiece();
      window.add(dc);
      window.validate();

      if(hasNextCollided('s')){
        score+=4;
        addNextToBlockField(currentPiece);
        createRandomPiece();
      }else{
        currentPiece.lowerPiece();
        window.remove(dc);
      }

      movement = '0';

      scoreDisplay.setText("Score: "+score);

      //ClearLine.checkClear();
      if(Piece.hasReachedTop()==true){
        run = false;
      }

    }
    scoreDisplay.setText("You reached a score of:   "+score+"      Thanks for playing!");
    window.validate();

  }


  public static void addNextToBlockField(Piece p){
    Piece next = new Piece(p.getType(), p.getRotation(), p.getLocation()[0], p.getLocation()[1]);;
    Piece.addToBlockField(next);
  }

  public static boolean hasNextCollided(char move){
    Piece next = new Piece(currentPiece.getType(), currentPiece.getRotation(), currentPiece.getLocation()[0], currentPiece.getLocation()[1]);

    if(move=='w') {
      next.rotatePiece();
      return Piece.hasCollided(next);
    }else if(move=='a'){
      next.leftPiece();
      return Piece.hasCollided(next);
    }else if(move=='s'){
      next.lowerPiece();
      return Piece.hasCollided(next);
    }else if(move=='d'){
      next.rightPiece();
      return Piece.hasCollided(next);
    }

    return false;
  }


  public static void implementMovement(){
    if(movement=='0'){
      return;
    }
    if(movement=='w'){
      currentPiece.rotatePiece();
    }else if(movement=='a'){
      currentPiece.leftPiece();
    }else if(movement=='s'){
      currentPiece.lowerPiece();
    }else if(movement=='d'){
      currentPiece.rightPiece();
    }
  }

  private static void timer(int t){
    try
    {
      Thread.sleep(t);
    } catch (Exception e)
    {
    }
  }

  private static void createRandomPiece(){
    char[] types = new char[] {'O','L','J','S','Z','T','I'};
    currentPiece = new Piece(types[(int)(Math.random()*7)], 0, Piece.bs*4, Piece.bs);
  }

  private static DrawingComponent makePiece(){
    DrawingComponent DC = new DrawingComponent(currentPiece.getFirst(), currentPiece.getSecond(), currentPiece.getThird(), currentPiece.getFourth(), currentPiece.getColor());
    return DC;

  }

  private static RectangleComponent makeEdges(){
    Rectangle edges2 = new Rectangle(Piece.bs, Piece.bs, 10 * Piece.bs + 2, 22 * Piece.bs + 2);
    RectangleComponent RC = new RectangleComponent(edges2, Color.BLACK);
    return RC;
  }
}

