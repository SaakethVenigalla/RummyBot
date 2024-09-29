import java.util.Scanner;

public class GameMaster {

   public static void main(String[] args) {
      
      Deck gameDeck = new Deck(2);
      Scanner input = new Scanner(System.in);
      RummyBot ghandi = new RummyBot(gameDeck, 100);
      RummyBot gilgamesh = new RummyBot(gameDeck, 50);
      RummyBot trajan = new RummyBot(gameDeck, 30);
      RummyBot player = new RummyBot(gameDeck, 0);
      
      startGame();
      
      
      
   }
   
   public boolean startGame() { 
      //this is where we would call whatever function the bots use to find that magic number.
      System.out.println("Behold, your hand! Please input \"play\" to play it, or \"fold\" to wait until the next round.");
      if (input.nextLine() == "play") {
         return true;
      } else {
         return false;
      }
   }
   
   
   public void playerTurn() {
      System.out.println("Your turn! Please input \"left\" to scroll to the left, \"right\" to scroll to the right, or \"select\" to discard the current card");
      
   }
      
   
   public void playRound {
      while (ghandi.hasWon == false && gilgamesh.hasWon == false && trajan.hasWon == false && player.hasWon == false_ {
         ghandi.decide(gameDeck.draw());
         if (ghandi.hasWon == false)
            gilgamesh.decide(gameDeck.draw());
         if (gilgamesh.hasWon == false)
            trajan.decide(gameDeck.draw());
         if (trajan.hasWon == false)
            playerTurn();
      }
   }
}