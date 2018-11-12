class Main {
  public static void main(String[] args) {
    RubiksCube cube = new RubiksCube();
    
    System.out.println("Welcome to the Rubiks Cube simulation!");
    System.out.println("Commands:");
    System.out.println("u - rotates the top of the cube clockwise.");
    System.out.println("u' - rotates the top of the cube counter-clockwise.");
    System.out.println("d - rotates the bottom of the cube clockwise.");
    System.out.println("d' - rotates the bottom of the cube counter-clockwise.");
    System.out.println("l - rotates the left side of the cube clockwise.");
    System.out.println("l' - rotates the left side of the cube counter-clockwise.");
    System.out.println("r - rotates the right side of the cube clockwise.");
    System.out.println("r' - rotates the right side of the cube counter-clockwise.");
    System.out.println("f - rotates the front side of the cube clockwise.");
    System.out.println("f' - rotates the front side of the cube counter-clockwise.");
    System.out.println("b - rotates the back side of the cube clockwise.");
    System.out.println("b' - rotates the back side of the cube counter-clockwise.");
    System.out.println("rotate - rotates the entire cube to the right by a degree amount");
    System.out.println("flip - rotates the cube down a degree amount");
    cube.play();
    
  }
}