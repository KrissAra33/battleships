public class Ship{
   private int type;                      // type of the ship
   private int hp;                        // remaining hit point of the ship
   private int[][] cords;                 // cordinates of the ship once deployed
   
   public static int CARRIER = 1;
   public static int BATTLESHIP = 2;
   public static int DESTROYER = 3;
   public static int SUBMARINE = 4;
   public static int PATROLBOAT = 5;
   
   public static int C_HP = 5;
   public static int B_HP = 4;
   public static int D_HP = 3;
   public static int S_HP = 3;
   public static int P_HP = 2;
   
   public static int NORTH = 1;
   public static int SOUTH = 2;
   public static int EAST = 3;
   public static int WEST = 4;
   
   public Ship(int type){
      this.type = type;
      if (type == CARRIER) {
         hp = C_HP;
      } else if (type == BATTLESHIP) {
         hp = B_HP;
      } else if (type == DESTROYER) {
         hp = D_HP;
      } else if (type == SUBMARINE) {
         hp = S_HP;
      } else if (type == PATROLBOAT) {
         hp = P_HP;
      }
      cords = new int[hp][2];
   } 

   public void setCords(int r, int c, int orientation){
      if (orientation == NORTH){
         for (int i=0; i<hp; i++){
            cords[i][0] = r-i;
            cords[i][1] = c;
         }
      }
      if (orientation == SOUTH){
         for (int i=0; i<hp; i++){
            cords[i][0] = r+i;
            cords[i][1] = c;
         }
      }
      if (orientation == EAST){
         for (int i=0; i<hp; i++){
            cords[i][0] = r;
            cords[i][1] = c+i;
         }
      }
      if (orientation == WEST){
         for (int i=0; i<hp; i++){
            cords[i][0] = r;
            cords[i][1] = c-i;
         }
      }
   }
   
   public int[][] getCords(){
      return cords;
   }
   
   public void setHP(int hp){
      this.hp = hp;
   }
   
   public int getHP(){
      return hp;
   }

   public int getType(){
      return type;
   }
   
   public void printCords(){
      String s = "";
      for(int r = 0; r< cords.length; r++){
         for (int c=0; c<cords[0].length; c++){
            s += cords[r][c] + " ";
         }
         s+="\n";
      }
      System.out.println(s);
   }

}