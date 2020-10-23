import java.awt.Color;
import java.awt.Rectangle;


public class Piece {
  //Block Size
  public static int bs = 40;
  public static boolean[][] blockField = new boolean[25][12];
  private int rotation;
  private char type;
  private Color color;
  private int[] location;
  private int[] block1;
  private int[] block2;
  private int[] block3;
  private int[] block4;




  public Piece(char t, int r, int x, int y){
    type = t;
    rotation = r;
    location = new int[] {x,y};
    buildType();
  }


  public static void initBlockField() {
    for(int i = 0; i < 25; i++){
      for(int j = 0; j < 12; j++){
        if(i==0||j==0||j==11||i==23){
          blockField[i][j] = true;
        }else
        {
          blockField[i][j] = false;
        }
      }
    }
  }




  public static boolean hasReachedTop(){
    for(int j = 1; j < 10; j++){
      if(blockField[1][j]==true)return true;
    }
    return false;
  }

  public static void addToBlockField(Piece p)
  {
    blockField[(int) p.getFirst().getY() / bs][(int) p.getFirst().getX() / bs] = true;
    blockField[(int) p.getSecond().getY() / bs][(int) p.getSecond().getX() / bs] = true;
    blockField[(int) p.getThird().getY() / bs][(int) p.getThird().getX() / bs] = true;
    blockField[(int) p.getFourth().getY() / bs][(int) p.getFourth().getX() / bs] = true;
  }

  private static boolean hasBlockCollided(int[] b){
    int x = (b[0])/bs;
    int y = (b[1])/bs;
    return blockField[y][x];
  }

  public static boolean hasCollided(Piece p){

    int[] b1 = new int[] {(int)p.getFirst().getX(),(int)p.getFirst().getY()};
    int[] b2 = new int[] {(int)p.getSecond().getX(),(int)p.getSecond().getY()};
    int[] b3 = new int[] {(int)p.getThird().getX(),(int)p.getThird().getY()};
    int[] b4 = new int[] {(int)p.getFourth().getX(),(int)p.getFourth().getY()};

    return (hasBlockCollided(b1)||hasBlockCollided(b2)||hasBlockCollided(b3)||hasBlockCollided(b4));
  }

  public void rotatePiece(){
    if(rotation==3){
      rotation = 0;
    }else {
      rotation++;
    }
    buildType();
  }

  public void leftPiece(){
    location[0]-=bs;
    buildType();
  }

  public void rightPiece(){
    location[0]+=bs;
    buildType();
  }

  public void lowerPiece(){
    location[1] += bs;
    buildType();
  }

  public int getRotation(){return rotation;}

  public int[] getLocation(){
    return location;
  }

  public Color getColor(){
    return color;
  }

  public char getType(){
    return type;
  }

  public Rectangle getFirst(){
    Rectangle rect = new Rectangle(block1[0], block1[1], bs, bs);
    return rect;
  }

  public Rectangle getSecond(){
    Rectangle rect = new Rectangle(block2[0], block2[1], bs, bs);
    return rect;
  }

  public Rectangle getThird(){
    Rectangle rect = new Rectangle(block3[0], block3[1], bs, bs);
    return rect;
  }

  public Rectangle getFourth(){
    Rectangle rect = new Rectangle(block4[0], block4[1], bs, bs);
    return rect;
  }

  private void buildType(){
    //   0 O
    //   O O
    //
    //
    if(type=='O'){
      color = Color.YELLOW;
      //
      // ALL ROTATIONS
      //
      block1 = location;
      block2 = new int[]{location[0] + bs, location[1]};
      block3 = new int[]{location[0], location[1] + bs};
      block4 = new int[]{location[0] + bs, location[1] + bs};
    }

    //   0
    //   O
    //   O O
    //
    if(type == 'L'){
      color = Color.ORANGE;
      if(rotation==0) {
        block1 = location;
        block2 = new int[]{location[0], location[1] + bs};
        block3 = new int[]{location[0], location[1] + 2 * bs};
        block4 = new int[]{location[0] + bs, location[1] + 2 * bs};
      }
      else if(rotation==1) {
        block1 = new int[]{location[0]+bs, location[1]+bs};
        block2 = new int[]{location[0], location[1]+bs};
        block3 = new int[]{location[0]-bs, location[1]+bs};
        block4 = new int[]{location[0]-bs, location[1]+2*bs};
      }
      else if(rotation==2) {
        block1 = new int[]{location[0], location[1]+2*bs};
        block2 = new int[]{location[0], location[1]+bs};
        block3 = new int[]{location[0], location[1]};
        block4 = new int[]{location[0]-bs, location[1]};
      }
      else {
        block1 = new int[]{location[0]-bs, location[1]+bs};
        block2 = new int[]{location[0], location[1]+bs};
        block3 = new int[]{location[0]+bs, location[1]+bs};
        block4 = new int[]{location[0]+bs, location[1]};
      }
    }

    //     0
    //     O
    //   O O
    //
    if(type == 'J'){
      color = Color.BLUE;
      if(rotation==0) {
        block1 = new int[]{location[0] + bs, location[1]};
        block2 = new int[]{location[0] + bs, location[1] + bs};
        block3 = new int[]{location[0] + bs, location[1] + 2 * bs};
        block4 = new int[]{location[0], location[1] + 2 * bs};
      }
      else if(rotation==1) {
        block1 = new int[]{location[0]+bs, location[1]+bs};
        block2 = new int[]{location[0], location[1]+bs};
        block3 = new int[]{location[0]-bs, location[1]+bs};
        block4 = new int[]{location[0]-bs, location[1]};
      }
      else if(rotation==2) {
        block1 = new int[]{location[0], location[1]+2*bs};
        block2 = new int[]{location[0], location[1]+bs};
        block3 = new int[]{location[0], location[1]};
        block4 = new int[]{location[0]+bs, location[1]};
      }
      else {
        block1 = new int[]{location[0]-bs, location[1]+bs};
        block2 = new int[]{location[0], location[1]+bs};
        block3 = new int[]{location[0]+bs, location[1]+bs};
        block4 = new int[]{location[0]+bs, location[1]+2*bs};
      }
    }

    //      0 O
    //    O O
    //
    //
    if(type == 'S'){
      color = Color.GREEN;
      if(rotation==0||rotation==2) {
        block1 = location;
        block2 = new int[]{location[0] + bs, location[1]};
        block3 = new int[]{location[0] - bs, location[1] + bs};
        block4 = new int[]{location[0], location[1] + bs};
      }
      else if(rotation==1||rotation==3) {
        block1 = new int[]{location[0]+bs, location[1]+bs};
        block2 = new int[]{location[0]+bs, location[1]+2*bs};
        block3 = new int[]{location[0], location[1]+bs};
        block4 = new int[]{location[0], location[1]};
      }
    }

    //   0 O
    //     O O
    //
    //
    if(type == 'Z'){
      color = Color.RED;
      if(rotation==0||rotation==2) {
        block1 = location;
        block2 = new int[]{location[0] + bs, location[1]};
        block3 = new int[]{location[0] + bs, location[1] + bs};
        block4 = new int[]{location[0] + 2 * bs, location[1] + bs};
      }
      else if(rotation==1||rotation==3) {
        block1 = new int[]{location[0]+2*bs, location[1]};
        block2 = new int[]{location[0]+2*bs, location[1]+bs};
        block3 = new int[]{location[0]+bs, location[1]+bs};
        block4 = new int[]{location[0]+bs, location[1]+2*bs};
      }


    }

    //   0 O O
    //     O
    //
    //
    if(type == 'T'){
      color = new Color(102, 0, 153);
      if(rotation==2) {
        block1 = new int[]{location[0], location[1]};
        block2 = new int[]{location[0] + bs, location[1]};
        block3 = new int[]{location[0] + 2 * bs, location[1]};
        block4 = new int[]{location[0] + bs, location[1] + bs};
      }
      else if(rotation==3) {
        block1 = new int[]{location[0]+bs, location[1]-bs};
        block2 = new int[]{location[0]+bs, location[1]};
        block3 = new int[]{location[0]+bs, location[1]+bs};
        block4 = new int[]{location[0], location[1]};
      }
      else if(rotation==1) {
        block1 = new int[]{location[0] + bs, location[1]-bs};
        block2 = new int[]{location[0] + bs, location[1]};
        block3 = new int[]{location[0] + bs, location[1]+bs};
        block4 = new int[]{location[0]+2*bs, location[1]};
      }
      else if(rotation==0) {
        block1 = new int[]{location[0]+2*bs, location[1]};
        block2 = new int[]{location[0]+bs, location[1]};
        block3 = new int[]{location[0], location[1]};
        block4 = new int[]{location[0]+bs, location[1]-bs};
      }
      else{
        block1 = new int[]{location[0]+bs, location[1]+bs};
        block2 = new int[]{location[0]+bs, location[1]};
        block3 = new int[]{location[0]+bs, location[1]-bs};
        block4 = new int[]{location[0]+bs, location[1]};
      }
    }

    //   0
    //   O
    //   O
    //   O
    if(type == 'I'){
      color = new Color(51, 204, 255);
      if(rotation==1) {
        block1 = location;
        block2 = new int[]{location[0], location[1] + bs};
        block3 = new int[]{location[0], location[1] + 2 * bs};
        block4 = new int[]{location[0], location[1] + 3 * bs};
      }
      else if(rotation==0) {
        block1 = new int[]{location[0]-2*bs, location[1]+bs};
        block2 = new int[]{location[0]-bs, location[1]+bs};
        block3 = new int[]{location[0], location[1]+bs};
        block4 = new int[]{location[0]+bs, location[1]+bs};
      }
      else if(rotation==2) {
        block1 = new int[]{location[0]-2*bs, location[1]+2*bs};
        block2 = new int[]{location[0]-bs, location[1]+2*bs};
        block3 = new int[]{location[0], location[1]+2*bs};
        block4 = new int[]{location[0]+bs, location[1]+2*bs};
      }
      else {
        block1 = new int[]{location[0]-bs, location[1]};
        block2 = new int[]{location[0]-bs, location[1]+bs};
        block3 = new int[]{location[0]-bs, location[1]+2*bs};
        block4 = new int[]{location[0]-bs, location[1]+3*bs};
      }
    }
  }
}

