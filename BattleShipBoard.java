import java.util.*;

public class BattleShipBoard extends Board{
   private char[][] displayBoard = new char[10][10];
   
   public BattleShipBoard(){
      for (int r=0; r<displayBoard.length; r++){
         for (int c=0; c<displayBoard[0].length; c++) {
            displayBoard[r][c] = '0';
         }
      }
   }
   
   public void displayBoard(){
      String s = "";
      for(int r = 0; r<displayBoard.length; r++){
         for (int c=0; c<displayBoard[0].length; c++){
            s += displayBoard[r][c] + " ";
         }
         s+="\n";
      }
      System.out.println(s);
   }

   public String toString(){
      return super.toString();
   }
   
   private boolean causeOverlap(int[][] newCords){
      boolean overlap = false;
      for (int i=0; i<newCords.length; i++){
         if (super.getValue(newCords[i][0], newCords[i][1]) != 0){
            overlap = true;
            break;
         }
      }
      return overlap;
   }
   public void placeShip(Ship s){
      boolean deployed = false;
      while (! deployed) {
         int r = (int)(Math.random()*9)+1;
         //System.out.print(r + " ");
         int c = (int)(Math.random()*9)+1;
         //System.out.println(c);
         int orient = (int)(Math.random()*4)+1;
         //System.out.println(orient);
         if (orient == Ship.NORTH){
            //System.out.println("north");
            //System.out.println(r-s.getHP());
            if (r-s.getHP()<-1){
               orient = Ship.SOUTH;
            }
         } else if (orient == Ship.SOUTH){
            //System.out.println("south");
            //System.out.println(r+s.getHP());
            if (r+s.getHP()>10){
               orient = Ship.NORTH;
            }
         } else if (orient == Ship.EAST){
            //System.out.println("east");
            //System.out.println(c+s.getHP());
            if (c+s.getHP()>10){
               orient = Ship.WEST;
            }
         } else if (orient == Ship.WEST){
            //System.out.println("west");
            //System.out.println(c-s.getHP());
            if (c-s.getHP()<-1){
               orient = Ship.EAST;
            }
         }
         //System.out.println(orient);
         s.setCords(r, c, orient);
         //s.printCords();
         if (! causeOverlap(s.getCords())) {
            int[][] temp = s.getCords();
            for (int i=0; i<temp.length; i++){
               super.setValue(temp[i][0], temp[i][1], s.getType());
            }
            deployed = true;
         }
         //else {
         //   System.out.println("found overlap...redeploying...");
         //}
      }
   }
   
   public boolean isAHit(int r, int c){
      //System.out.println(getValue(r,c));
      if (getValue(r, c) > 0){
         displayBoard[r][c] = 'H';
      } else {
         displayBoard[r][c] = 'M';
      }
      return getValue(r, c) > 0;
   }
   
   public void displayShips(){
      System.out.println(toString());
   }
}