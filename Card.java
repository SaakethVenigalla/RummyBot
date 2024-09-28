public class Card{
   int number;
   char suit;
   public Card(int n, char s){
      number = n;
      suit = s;
   }
   public String toString(){
      return number+" "+suit;
   }
}