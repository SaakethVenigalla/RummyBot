import java.util.*;
import java.io.*;
public class Deck {
   int numberOfDecks;
   ArrayList<Card> deckArray = new ArrayList<Card>();
   public Deck(int i) {
      numberOfDecks = i;
      initDeck();
   }
   
   //adds a full deck of playing cards to the deck for each numberOfDecks
   public void initDeck() {
      for (int i = 0; i <= numberOfDecks; i++) {
         for (int j = 1; j <= 13; j++) {
            deckArray.add(new Card(j, 'c'));
            deckArray.add(new Card(j, 'h'));
            deckArray.add(new Card(j, 's'));
            deckArray.add(new Card(j, 'd'));
         }
      }
   }
   
   public Card draw() {
      Random r = new Random();
      int x = r.nextInt(deckArray.size());
      Card c = deckArray.get(x);
      deckArray.remove(deckArray.get(x));
      return c;
   }
   
   public void addTo(Card discarded) {
      deckArray.add(discarded);
   } 
   }
     
             
