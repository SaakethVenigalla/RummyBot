import java.util.ArrayList;
import java.util.Random;

Public static class Deck {
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
      return deckArray.get(Random.randint(0, deckArray.size() - 1));
   }
   
   public void addTo(Card discarded) {
      deckArray.add(discarded);
   } 
                              