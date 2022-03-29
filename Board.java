public class Board{
   private int[][] board = new int[10][10];
   public int getValue(int r, int c){
      return board[r][c];
   }
   public void setValue(int r, int c, int value){
      board[r][c] = value;
   }
   public String toString(){
      String s = "";
      for(int r = 0; r< board.length; r++){
         for (int c=0; c<board[0].length; c++){
            s += board[r][c] + " ";
         }
         s+="\n";
      }
      return s;
   }
}