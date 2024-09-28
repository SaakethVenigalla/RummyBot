import java.util.*;
import java.io.*;
public class RummyBot{
   static ArrayList<ArrayList<Card>> hand = new ArrayList<ArrayList<Card>>();
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
}