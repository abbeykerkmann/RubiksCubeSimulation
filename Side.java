class Side {
  
  private char[][] side;
  private boolean solved;
  
  public Side(char c) {
    side = new char[3][3];
    for(int i = 0; i < 3; i++) {
      for(int j = 0; j < 3; j++)
        side[i][j] = c;
    }
  }

  public Side copy() {
    Side s = new Side('C');
    for(int i = 0; i < 3; i++) {
      s.setRow(this.getRow(i), i);
    }
    return s;
  }
  
  public static char[] reverseRow(char[] row) {
    char[] temp = new char[3];
    for(int i = 0; i < 3; i++)
      temp[i] = row[2-i];
    for(int i = 0; i < 3; i++)
      row[i] = temp[i];
    return row;
  }
  public static char[] reverseColumn(char[] col) {
    char[] temp = new char[3];
    for(int i = 0; i < 3; i++)
      temp[i] = col[2-i];
    for(int i = 0; i < 3; i++)
      col[i] = temp[i];
    return col;
  }

  public boolean isSolved() {
    for(int i = 0; i < 2; i++) {
      for(int j = 0; j < 2; j++) {
        if(!(side[i][j] == side[i+1][j+1])) {
          solved = false;
          return false;
        }
      }
    }
    solved = true;
    return true;
  }
  
  public String toString() {
    String result = "[" + side[0][0] + ", " + side[0][1] + ", " + side[0][2] + "]" 
    + "\n[" + side[1][0] + ", " + side[1][1] + ", " + side[1][2] + "]" 
    + "\n[" + side[2][0] + ", " + side[2][1] + ", " + side[2][2] + "]";
    return result;
  }
  
  public String toString (int line) {
    String result = "[" + side[line][0] + ", " + side[line][1] + ", " + side[line][2] + "]";
    return result;
  }
  public char[] getRow(int row) {
    return side[row];
  }
  public char[] getColumn(int col) {
    char[] result = new char[3];
    for(int i = 0; i < 3; i++) {
      result[i] = side[i][col];
    }
    return result;
  }
  public void setRow(char[] row, int r) {
    side[r] = row;
  }
  public void setColumn(char[] col, int c) {
    for(int i = 0; i < 3; i++)
      side[i][c] = col[i];
  }
  public void rotate(boolean dir) {
    if(dir) {
      char[] temp = new char[3];
      for(int i = 0; i < 3; i++)
        temp[i] = side[0][i];
      for(int i = 0; i < 3; i++)
        side[0][i] = side[2-i][0];
      for(int i = 0; i < 3; i++)
        side[i][0] = side[2][i];
      for(int i = 0; i < 3; i++)
        side[2][i] = side[2-i][2];
      for(int i = 0; i < 3; i++)
        side[i][2] = temp[i];
    }
    else {
      char[] temp = new char[3];
      for(int i = 0; i < 3; i++)
        temp[i] = side[0][i];
      for(int i = 0; i < 3; i++)
        side[0][i] = side[i][2];
      for(int i = 1; i < 3; i++)
        side[i][2] = side[2][2-i];
      for(int i = 1; i < 3; i++)
        side[2][i] = side[i][0];
      for(int i = 0; i< 3; i++)
        side[i][0] = temp[2-i];
    }
  }
  public boolean contains(char c) {
    for(int i = 0; i < 3; i++) {
      for(int j = 0; j < 3; j++)
        if(side[i][j] == c)
          return true;
    }
    return false;
  }
  public char get(int x, int y) {
    return side[x][y];
  }
}