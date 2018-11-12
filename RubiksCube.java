import java.lang.Math;
import java.util.Scanner;

class RubiksCube {
  
  private Side[] cube;
  private boolean isSolved;
  private boolean dimensions;
  private final char[] COLOURS = {'W', 'R', 'B', 'O', 'G', 'Y'};
  private Side top;
  private Side bottom;
  private Side left;
  private Side right;
  private Side front;
  private Side back;
  private int counter;
  
  public RubiksCube() {
    cube = new Side[6];
    for(int i = 0; i < cube.length; i++)
      cube[i] = new Side(COLOURS[i]);
    top = cube[0];
    front = cube[1];
    right = cube[2];
    back = cube[3];
    left = cube[4];
    bottom = cube[5];
    counter = 0;
  }

  public void rotateCube(int degrees) {
    while (degrees > 0) {
      Side temp = front.copy();
      front = left.copy();
      left = back.copy();
      back = right.copy();
      right = temp.copy();
      bottom.rotate(true);
      top.rotate(false);
      degrees -= 90;
    }
  }

  public void flipCube(int degrees) {
    while (degrees > 0) {
      Side temp = front.copy();
      front = top.copy();
      for(int i = 0; i < 3; i++)
        back.setColumn(Side.reverseColumn(back.getColumn(i)), i);
     for(int i = 0; i < 3; i++)
        back.setRow(Side.reverseRow(back.getRow(i)), i);
      top = back.copy();
      for(int i = 0; i < 3; i++)
        bottom.setColumn(Side.reverseColumn(bottom.getColumn(i)), i);
      for(int i = 0; i < 3; i++)
        bottom.setRow(Side.reverseRow(bottom.getRow(i)), i);
      back = bottom.copy();
      bottom = temp.copy();
      right.rotate(false);
      left.rotate(true);
      degrees -= 90;
    }
  }

  public void display() {
    for(int i = 0; i < 3; i++)
      System.out.println("         "+top.toString(i));
    for(int i = 0; i < 3; i++)
      System.out.println(left.toString(i) + front.toString(i) + right.toString(i) + back.toString(i));
    for(int i = 0; i < 3; i++)
      System.out.println("         "+bottom.toString(i));
  }  
  public void scramble() {
    System.out.println("Scrambling the cube...");
    int times = (int)(Math.random() * 100);
    for(int i = 0; i < times; i++) {
      int op = (int)(Math.random() * 12);
      switch(op) {
        case 0:
          this.u(true);
          break;
        case 1:
          this.d(true);
          break;
        case 2:
          this.l(true);
          break;
        case 3:
          this.r(true);
          break;
        case 4:
          this.f(true);
          break;
        case 5:
          this.b(true);
          break;
        case 6:
          this.u(false);
          break;
        case 7:
          this.d(false);
          break;
        case 8:
          this.l(false);
          break;
        case 9:
          this.r(false);
          break;
        case 10:
          this.f(false);
          break;
        case 11:
          this.b(false);
          break;
      }
    }
  }

  public void alignCorners() {
    r(false);
    d(false);
    r(true);
    d(true);
  }

  public void middleRight() {
    u(false);
    l(false);
    u(true);
    l(true);
    u(true);
    f(true);
    u(false);
    f(false);
  }

  public void middleLeft() {
    u(true);
    r(true);
    u(false);
    r(false);
    u(false);
    f(false);
    u(true);
    f(true);
  }

  public void secondCross() {
    f(true);
    r(true);
    u(true);
    r(false);
    u(false);
    f(false);
  }

  public void alignTopCorners() {
    r(true);
    u(true);
    r(false);
    u(true);
    r(true);
    u(true);
    u(true);
    r(false);
  }

  public void centreMiddle(boolean dir) {
    if(dir) {
      f(true);
      f(true);
      u(true);
      l(true);
      r(false);
      f(true);
      f(true);
      l(false);
      r(true);
      u(true);
      f(true);
      f(true);
    }
    else {
      f(true);
      f(true);
      u(false);
      l(true);
      r(false);
      f(true);
      f(true);
      l(false);
      r(true);
      u(false);
      f(true);
      f(true);
    }
  }

  public void positionTopCorners() {
    r(false);
    f(true);
    r(false);
    b(true);
    b(true);
    r(true);
    f(false);
    r(false);
    b(true);
    b(true);
    r(true);
    r(true);
    u(false);
  }

  public void u(boolean dir) {
    char[] temp = front.getRow(0);
    if(dir) {
      front.setRow(right.getRow(0), 0);
      right.setRow(back.getRow(0), 0);
      back.setRow(left.getRow(0), 0);
      left.setRow(temp, 0);
    }
    else {
      front.setRow(left.getRow(0), 0);
      left.setRow(back.getRow(0), 0);
      back.setRow(right.getRow(0), 0);
      right.setRow(temp, 0);
    }
    top.rotate(dir);
  }
  public void d(boolean dir) {
    char[] temp = front.getRow(2);
    if(dir) {
      front.setRow(right.getRow(2), 2);
      right.setRow(back.getRow(2), 2);
      back.setRow(left.getRow(2), 2);
      left.setRow(temp, 2);
    }
    else {
      front.setRow(left.getRow(2), 2);
      left.setRow(back.getRow(2), 2);
      back.setRow(right.getRow(2), 2);
      right.setRow(temp, 2);
    }
    bottom.rotate(!dir);
  }
  public void l(boolean dir) {
    char[] temp = front.getColumn(0);
    if(dir) {
      front.setColumn(top.getColumn(0), 0);
      back.setColumn(Side.reverseColumn(back.getColumn(2)), 2);
      top.setColumn(back.getColumn(2), 0);
      bottom.setColumn(Side.reverseColumn(bottom.getColumn(0)), 0);
      back.setColumn(bottom.getColumn(0), 2);
      bottom.setColumn(temp, 0);
    }
    else {
      front.setColumn(bottom.getColumn(0), 0);
      back.setColumn(Side.reverseColumn(back.getColumn(2)), 2);
      bottom.setColumn(back.getColumn(2), 0);
      top.setColumn(Side.reverseColumn(top.getColumn(0)), 0);
      back.setColumn(top.getColumn(0), 2);
      top.setColumn(temp, 0);
    }
    left.rotate(dir);
  }
  public void r(boolean dir) {
    char[] temp = front.getColumn(2);
    if(dir) {
      front.setColumn(bottom.getColumn(2), 2);
      back.setColumn(Side.reverseColumn(back.getColumn(0)), 0);
      bottom.setColumn(back.getColumn(0), 2);
      top.setColumn(Side.reverseColumn(top.getColumn(2)), 2);
      back.setColumn(top.getColumn(2), 0);
      top.setColumn(temp, 2);
    }
    else {
      front.setColumn(top.getColumn(2), 2);
      back.setColumn(Side.reverseColumn(back.getColumn(0)), 0);
      top.setColumn(back.getColumn(0), 2);
      bottom.setColumn(Side.reverseColumn(bottom.getColumn(2)), 2);
      back.setColumn(bottom.getColumn(2), 0);
      bottom.setColumn(temp, 2);
    }
    right.rotate(dir);
  }
  public void f(boolean dir) {
    char[] temp = top.getRow(2);
    if(dir) {
      left.setColumn(Side.reverseColumn(left.getColumn(2)), 2);
      top.setRow(left.getColumn(2), 2);
      left.setColumn(bottom.getRow(0), 2);
      right.setColumn(Side.reverseColumn(right.getColumn(0)), 0);
      bottom.setRow(right.getColumn(0), 0);
      right.setColumn(temp, 0);
    }
    else {
      top.setRow(right.getColumn(0), 2);
      bottom.setRow(Side.reverseColumn(bottom.getRow(0)), 0);
      right.setColumn(bottom.getRow(0), 0);
      bottom.setRow(left.getColumn(2), 0);
      left.setColumn(reverse(temp), 2);
    }
    front.rotate(dir);
  }
  public static char[] reverse(char[] c) {
    char[] temp = new char[3];
    for(int i = 0; i < 3; i++)
      temp[i] = c[2-i];
    for(int i = 0; i < 3; i++)
      c[i] = temp[i];
    return c;
  }
  public void b(boolean dir) {
    char[] temp = top.getRow(0);
    if(dir) {
      left.setColumn(Side.reverseColumn(left.getColumn(0)), 0);
      top.setRow(left.getColumn(0), 0);
      left.setColumn(bottom.getRow(2), 0);
      right.setColumn(Side.reverseColumn(right.getColumn(2)), 2);
      bottom.setRow(right.getColumn(2), 2);
      right.setColumn(temp, 2);
    }
    else {
      top.setRow(right.getColumn(2), 0);
      bottom.setRow(Side.reverseRow(bottom.getRow(2)), 2);
      right.setColumn(bottom.getRow(2), 2);
      bottom.setRow(left.getColumn(0), 2);
      left.setColumn(reverse(temp), 0);
    }
    back.rotate(!dir);
  }  
  public void checkSolved() {
    if(top.isSolved() && front.isSolved() && right.isSolved() && left.isSolved() && back.isSolved() && bottom.isSolved())
      isSolved = true;
  } 
  public void play() {
    this.display();
    this.scramble();
    counter = 0;
    this.display();
    while(!isSolved) {
      System.out.println("Please enter a command:");
      Scanner in = new Scanner(System.in);
      String command = in.next();
      switch(command) {
        case "u":
          this.u(true);
          break;
        case "u'":
          this.u(false);
          break;
        case "d":
          this.d(true);
          break;
        case "d'":
          this.d(false);
          break;
        case "l":
          this.l(true);
          break;
        case "l'":
          this.l(false);
          break;
        case "r":
          this.r(true);
          break;
        case "r'":
          this.r(false);
          break;
        case "f":
          this.f(true);
          break;
        case "f'":
          this.f(false);
          break;
        case "b":
          this.b(true);
          break;
        case "b'":
          this.b(false);
          break;
        case "rotate":
          System.out.println("Enter the degrees (90, 180 or 270):");
          int degrees = in.nextInt();
          while(degrees % 90 != 0) {
            System.out.println("Enter a valid degrees value:");
            degrees = in.nextInt();
          }
          rotateCube(degrees);
          break;
        case "flip":
          System.out.println("Enter the degrees (90, 180 or 270):");
          degrees = in.nextInt();
          while(degrees % 90 != 0) {
            System.out.println("Enter a valid degrees value:");
            degrees = in.nextInt();
          }
          flipCube(degrees);
          break;
        case "corner":
          alignCorners();
          break;
        case "middle":
          System.out.println("Left or right?");
          String side = in.next();
          while (!side.equals("left") && !side.equals("right")) {
            System.out.println("Left or right?");
            side = in.next();
          }
          if(side.equals("left"))
            middleLeft();
          else if (side.equals("right"))
            middleRight();
          break;
        case "cross":
          secondCross();
          break;
        case "topcorners":
          alignTopCorners();
          break;
        case "topedges":
          String dir = in.next();
          boolean input = false;
          if(in.equals("cw"))
            input = true;
          else
            input = false;
          centreMiddle(input);
          break;
        case "postopcorners":
          positionTopCorners();
          break;     
      }
      counter++;
      this.display();
      checkSolved();
    }
    System.out.println("Congratulations! You won in " + counter + " turns!");
  }
}