<<<<<<< HEAD
import java.util.*;
import java.io.*;
public class RummyBot{
   static ArrayList<ArrayList<Card>> hand = new ArrayList<ArrayList<Card>>();
   static numOfRounds = 3;
   public static void main(String[] args){
      for(int i = 0; i<5; i++){
         hand.add(new ArrayList<Card>());
      }
      hand.get(0).add(new Card(4, 'c'));
      hand.get(1).add(new Card(11, 'h'));
      hand.get(2).add(new Card(2, 'd'));
      hand.get(3).add(new Card(3, 's'));
      hand.get(4).add(new Card(6, 'c'));
      System.out.println(decide(new Card(3, 'h')));
      System.out.println(hand);
   }
   public static boolean playOrNo(int numDeck){
      int win = 0;
      for(int i = 1; i<hand.size(); i++){
         if(hand.get(i).size()<3){
            win++;
         }
      }
      win = win*2;
      
   }
   public static void add(Card s){
      int set=-1;
      int sizeSet = 0;
      boolean isSet = true;
      for(int i = 0; i<hand.size(); i++){//sets of same number
         for(int j = 0; j<hand.get(i).size(); j++){
            if(!(s.number==hand.get(i).get(j).number)){
               isSet = false;
            }
         }
         if(isSet&&hand.get(i).size()>sizeSet){
            set = i;
         }
         isSet = true;
      }
      if(set==-1){
         hand.add(new ArrayList<Card>());
         hand.get(hand.size()-1).add(s);
      }else{
         hand.get(set).add(s);
      }
   }
   public static Card decide(Card s){
      add(s);
      boolean bad = false;
      for(int i = 0; i<hand.size(); i++){
         if(hand.get(i).size()==1){
            if(hand.get(i).get(0).number == s.number && hand.get(i).get(0).suit == s.suit){
               hand.get(i).remove(0);
               hand.remove(0);
               bad = true;
            }
         }
      }
      System.out.println(hand);
      //this part is step two, if it doesn't fit in any set replace it with your highest card
      if(bad){
         ArrayList<Integer> ones = new ArrayList<Integer>();
         for(int i = 0; i<hand.size(); i++){
            if(hand.get(i).size()==1){
               ones.add(i);
            }
         }
         System.out.println(ones);
         if(!ones.isEmpty()){
            int max;
            max = ones.get(0);
            for(int i = 1; i<ones.size(); i++){
               if(hand.get(ones.get(i)).get(0).number>hand.get(max).get(0).number){
                  max = ones.get(i);
               }
            }
            if(hand.get(max).get(0).number>s.number){
               return replace(max, 0, s);
            }else{
               return s;
            }
            
         }else{
            return s;
         }
      }else{
         return discard();
      }
   }
   public static Card discard(){
      int smallestSet = hand.get(0).size();
      int index = 0;
      for(int i = 1; i<hand.size(); i++){
         if(hand.get(i).size()<smallestSet){
            smallestSet = hand.get(i).size();
            index = i;
         }
      }
      Card c = new Card(hand.get(index).get(hand.get(index).size()-1).number, hand.get(index).get(hand.get(index).size()-1).suit);
      hand.get(index).remove(hand.get(index).size()-1);
      if(hand.get(index).size()==0){
         hand.remove(index);
      }
      return c;
   }
   public static Card replace(int i, int itwo, Card s){
      Card c = new Card(hand.get(i).get(itwo).number, hand.get(i).get(itwo).suit);
      hand.get(i).remove(itwo);
      hand.get(i).add(itwo, s);
      return c;
   }  
=======
import java.util.*;
import java.io.*;
public class RummyBot{
   //static ArrayList<ArrayList<Card>> hand = new ArrayList<ArrayList<Card>>();
   int points;
   int aggression;
   
   /*  We probably don't want RummyBot to have a main? Esp if there are multiple of them. Gonna run that in the GameMaster for now. -P
   public static void main(String[] args){
      for(int i = 0; i<5; i++){
         hand.add(new ArrayList<Card>());
      }
      hand.get(0).add(new Card(4, 'c'));
      hand.get(1).add(new Card(11, 'h'));
      hand.get(2).add(new Card(2, 'd'));
      hand.get(3).add(new Card(3, 's'));
      hand.get(4).add(new Card(6, 'c'));
      System.out.println(decide(new Card(3, 'h')));
      System.out.println(hand);
   } */
   
   
   public RummyBot(Deck drawFrom, int aggressionNum) {
      //Note about this: The code throws errors if you try to compile the RummyBot file on its own (because it doesn't know what hand it) but I think if you compile the GameMaster file which calls this constructor it should work.
      ArrayList<ArrayList<Card>> hand = new ArrayList<ArrayList<Card>>(13);
      for (int i = 0; i < 13; i++) { 
         hand.add(new ArrayList<Card>());
         hand.get(i).add(drawFrom.draw());
      }
      points = 0;
      aggression = aggressionNum;
   }
         
   
   public static void add(Card s){
      int set=-1;
      int sizeSet = 0;
      boolean isSet = true;
      for(int i = 0; i<hand.size(); i++){//sets of same number
         for(int j = 0; j<hand.get(i).size(); j++){
            if(!(s.number==hand.get(i).get(j).number)){
               isSet = false;
            }
         }
         if(isSet&&hand.get(i).size()>sizeSet){
            set = i;
         }
         isSet = true;
      }
      if(set==-1){
         hand.add(new ArrayList<Card>());
         hand.get(hand.size()-1).add(s);
      }else{
         hand.get(set).add(s);
      }
   }
   
   
   public static Card decide(Card s){
      add(s);
      int ch;
      int check;
      for(int i = 0; i<hand.size(); i++){
         if(hand.get(i).size()==1){
            //is this for the straight flushes? If so I dont think its gonna work as intended. -P
            if(hand.get(i).get(0).number == s.number && hand.get(i).get(0).suit == s.suit){
               hand.get(i).remove(0);
               hand.remove(0);
               return s;
            }
         }
      }
      System.out.println(hand);
      //this part is the last resort, if it doesn't fit in any set replace it with your highest card
      ArrayList<Integer> ones = new ArrayList<Integer>();
      for(int i = 0; i<hand.size(); i++){
         if(hand.get(i).size()==1){
            ones.add(i);
         }
      }
      System.out.println(ones);
      if(!ones.isEmpty()){
         int max;
         max = ones.get(0);
         for(int i = 1; i<ones.size(); i++){
            if(hand.get(ones.get(i)).get(0).number>hand.get(max).get(0).number){
               max = ones.get(i);
            }
         }
         
         if(hand.get(max).get(0).number>s.number){
            return replace(max, 0, s);
         }else{
            return s;
         }
         
      }else{
         return s;
      }
      
   }
   
   
   public static Card replace(int i, int itwo, Card s){
      Card c = new Card(hand.get(i).get(itwo).number, hand.get(i).get(itwo).suit);
      hand.get(i).remove(itwo);
      hand.get(i).add(itwo, s);
      return c;
   }
   
   //checks if a set contains a straight flush. Precondition: target.size() is 3 or more
   public static boolean straightCheck(ArrayList<Card> target) {
      for (int i = 1; i < target.size(); i++) {
         if (target.get(i).suit != target.get(i - 1).suit){
            return false;
         } else if (!(/* target.get(i - 1) is numerically adjacent to target.get(i) */(target.get(i - 1).number == target.get(i).number + 1 || target.get(i - 1).number == target.get(i).number - 1) && /* there is no other instance of target.get(i - 1) */(target.indexOf(target.get(i - 1)) == i - 1 && target.lastIndexOf(target.get(i - 1)) == i - 1))) {
            return false;
         }
      }
      return true;
   }
   
   //checks if a set contains cards of all the same number
   public static boolean kindCheck(ArrayList<Card> target) {
      for (int i = 1; i < target.size(); i++) {
         if (target.get(i).number != target.get(i - 1).number)
            return false;
      }
      return true;
      
   }
   
   
   public static boolean hasWon() {
      for (int i = 0; i < hand.size(); i++) {
         if (straightCheck(hand.get(i)) == false && kindCheck(hand.get(i)) == false) {
            return false;
         } else {
            return true;
         }
      }
   }  
   
   
>>>>>>> f3017d9c78ffebaf9ee8330dd5db56ce2190d52e
}