import java.util.Scanner;
import java.util.*;

public class BattleShipGame{
   private BattleShipBoard b;
   private ArrayList<Ship> ships = new ArrayList<Ship>();
   private static int MAX_NUM_OF_GUESS = 50;
   
   public BattleShipGame(){
      b = new BattleShipBoard();
      Ship carrier = new Ship(Ship.CARRIER);
      Ship destroyer = new Ship(Ship.DESTROYER);
      Ship battleship = new Ship(Ship.BATTLESHIP);
      Ship sub = new Ship(Ship.SUBMARINE);
      Ship patrol = new Ship(Ship.PATROLBOAT);
      b.placeShip(carrier);
      b.placeShip(destroyer);
      b.placeShip(battleship);
      b.placeShip(sub);
      b.placeShip(patrol);
      ships.add(carrier);
      ships.add(destroyer);
      ships.add(battleship);
      ships.add(sub);
      ships.add(patrol);
   }
   
   public void play(){
      System.out.println("\n5 Ships Have Been Deployed.  Let's Start Battle!\n");
      boolean done = false;
      int consecutiveMiss = 0;
      int totalMiss = 0;
      String mode = "guess";
      Scanner in = new Scanner(System.in);
      while (! done) {
         if (ships.size() == 0){
            System.out.println("Congrats!  You have sunk all the ships!  You won!!");
            break;
         }
         if (totalMiss == MAX_NUM_OF_GUESS){
            System.out.println("Sorry, you have run out of chances.  You lost!");
            break;
         }
         if (consecutiveMiss == 3){
            System.out.print("Do you need help?  Enter h to see the ships, or c to continue to guess: "); 
            char ch = in.next().charAt(0);
            consecutiveMiss = 0;
            if (ch == 'h'){
               b.displayShips();
               mode = "prompt";
               continue;
            } else if (ch == 'c'){
               mode = "guess";
               continue;
            } else {
               System.out.println("Invaliad input.  We will continue to guess.\n");
               mode = "guess";
               continue;
            }
         }
         if (mode.equals("guess")){
            b.displayBoard();
            System.out.print("Please guess the row number and column number of a ship.\nRow(1-10): ");
            int r = in.nextInt();
            if (r<1 || r>10){
               System.out.println("Invalid input.  Please try again.\n");
               mode = "guess";
               continue;
            }
            System.out.print("Column(1-10): ");
            int c = in.nextInt();
            if (c<1 || c>10){
               System.out.println("Invalid input.  Please try again.\n");
               mode = "guess";
               continue;
            }
            if (! b.isAHit(r-1, c-1)){
               totalMiss++;
               consecutiveMiss++;
               System.out.println("Miss!\n");
               mode = "prompt";
            } else {
               System.out.println("HIT!\n");
               updateShips(r-1, c-1);
               consecutiveMiss = 0;
               mode = "prompt";
            }
            continue;
         }
         if (mode.equals("prompt")){
            System.out.print("Enter c to continue to guess, or q to quit the game: ");
            char ch = in.next().charAt(0);
            if (ch == 'c'){
               mode = "guess";
               continue;
            } else if (ch == 'q'){
               break;
            } else {
               System.out.println("Invalid input.  Please try again.\n");
               mode = "prompt";
               continue;
            }
         }
         
      }
   }
   
   private void updateShips(int r, int c){
      for (int i=0; i<ships.size(); i++){
         int[][] tmp = ships.get(i).getCords();
         for (int j=0; j<tmp.length; j++){
            if (tmp[j][0]==r && tmp[j][1]==c){
               ships.get(i).setHP(ships.get(i).getHP()-1);
               if (ships.get(i).getHP() == 0){
                  if (ships.get(i).getType() == Ship.CARRIER){
                     System.out.println("You have sunk my aircraft carrier!\n");
                  } else if (ships.get(i).getType() == Ship.DESTROYER){
                     System.out.println("You have sunk my destroyer!\n");
                  } else if (ships.get(i).getType() == Ship.BATTLESHIP){
                     System.out.println("You have sunk my battleship!\n");
                  } else if (ships.get(i).getType() == Ship.SUBMARINE){
                     System.out.println("You have sunk my submarine!\n");
                  } else if (ships.get(i).getType() == Ship.PATROLBOAT){
                     System.out.println("You have sunk my patrol boat!\n");
                  } 
                  ships.remove(i);
               }
               return;
            }
         }
      }
   }
   
   public static void main (String[] args){
      BattleShipGame game = new BattleShipGame();
      game.play();
   }
}