package com.company;

public class Main {

   public static void main(String[] args) {
       War game = new War();
   }
}

package com.company;

public class Card {
   // instance variables
   private String cardSuit;
   private int cardNumber;
   private String cardName;

   public Card() //default constructor
   {
       String cardSuit = "";
       int cardNumber = 0;
       String cardName = "";
   }

   public Card(String cardSuit, String cardName, int cardNumber){ //alternate constructr
       this.cardSuit = cardSuit;
       this.cardName = cardName;
       this.cardNumber = cardNumber;
   }

   public int getCardNumber(){return cardNumber;} //accessor method for each piece of instance data
   public String getCardSuit(){return cardSuit;}
   public String getCardName(){return cardName;}
   public String toString(){return "|" + cardName+" of " + cardSuit+ ", "+ cardNumber + "|";}

   public int compareTo(Card card2){ //compares 2 cards
       int whichCardWon = 0;
       System.out.println("Card 1 is: " + this.toString());
       System.out.println("Card 2 is: " + card2.toString());
       if(this.getCardNumber() > card2.getCardNumber()){ //retrieves both cardNumbers
           System.out.println(this.toString() + " is bigger than " + card2.toString());
           return whichCardWon = 1;
       }else if (this.getCardNumber() < card2.getCardNumber()){
           System.out.println(card2.toString() +  " is bigger than " + this.toString());
           return whichCardWon = 2;
       }else{
           System.out.println(this.toString() + " is equal to " + card2.toString());
           return whichCardWon = 0;
       }
   }

}

package com.company;

import java.util.ArrayList;

public class Player {
   //constructor
   ArrayList<Card> myHand = new ArrayList<Card>();

   public Player(ArrayList<Card> initialHand){
       myHand = initialHand;
   }
   //accept card method
   public void acceptCard(ArrayList<Card> cardList){myHand.addAll(cardList);}
   //deal card method

   public void deal(int i, ArrayList<Card> bin){
       bin.add(myHand.remove(i));
   }
   public void printHand(){ System.out.println(myHand);}
   //accessor methods
   public ArrayList<Card> getHand(){
       return myHand;
   }

}

package com.company;
import java.util.ArrayList;
import java.util.Collections;

public class Deck{
   private ArrayList<Card> myList; //creates the ArrayList.
   private String[] Suits = {"Heart", "Diamond", "Spade", "Club"}; //array of suitnames
   private String[] Names = {"2", "3", "4,", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"}; //array of card names

   public  Deck(){
       myList = new ArrayList<Card>(); //formal declaration of arrayList
       int counter = 2; //counter for
       for(int i = 0; i<Suits.length; i++){
           for(int x = 0; x<Names.length; x++){
               myList.add(new Card(Suits[i], Names[x], counter));
               if(counter ==14){counter=1;}
               counter++;
           }
       }
   }

   public void shuffleDeck(){
       System.out.println("SHUFFLING....");
       System.out.println("======");
       Collections.shuffle(myList);
   }

   public ArrayList<Card> getDeck(){return myList;}

   public void printDeck(){System.out.println(myList);}
}

package com.company;
import java.util.ArrayList;
import java.util.List;

public class War {
   Deck masterDeck = new Deck();
   int rounds = 0;
   int tieCount = 0;
   ArrayList<Card> bin = new ArrayList<>();
   Player player1;
   Player player2;

   public War() {
       masterDeck.shuffleDeck(); // Shuffle Deck.

       //Split deck into two and initialize player objects.
       List<Card> listPlayer1 = masterDeck.getDeck().subList(0, 26); //0 - 25
       ArrayList<Card> hand1 = new ArrayList<Card>(listPlayer1);
       player1 = new Player(hand1); //create playerObject with the arrayList

       List<Card> listPlayer2 = masterDeck.getDeck().subList(0, 26); //0 - 25
       ArrayList<Card> hand2 = new ArrayList<Card>(listPlayer2);
       player2 = new Player(hand2); //create playerObject with the arrayList

       while (player1.getHand().size() < 52 && player2.getHand().size() <52) { //While nobody has every single card
           roundOfWar();
       }

   }

   public void roundOfWar() {
       rounds++;
       System.out.println("ROUND " + rounds);
       System.out.println("====================");
       player1.deal(0, bin);
       player2.deal(0, bin);
       int result = bin.get(0).compareTo(bin.get(1));
       if (result == 1) {
           player1.getHand().addAll(bin);
           System.out.println("Player 1 wins. They have " + player1.getHand().size());
           bin.removeAll(bin);
       } else if (result == 2) {
           player2.getHand().addAll(bin);
           System.out.println("Player 2  wins. They have " + player2.getHand().size());
           bin.removeAll(bin);
       }else{
           handleTie(1);
       }
   }

   public void handleTie(int i){
       System.out.println("WAR IS DECLARED!!!!!!!!!!!!!!!!!!!!");
       System.out.println("==================");
       if(player1.getHand().size() >= 2 && player2.getHand().size() >= 2){
           player1.deal(0, bin);
           player1.deal(0, bin);
           player2.deal(0, bin);
           player2.deal(0, bin);
           int result2 = bin.get(2+i).compareTo(bin.get(4+i));
           if (result2 == 1) {
               player1.getHand().addAll(bin);
               System.out.println("Player 1 one wins. They have " + player1.getHand().size());
               bin.removeAll(bin);
           } else if (result2 == 2) {
               player2.getHand().addAll(bin);
               System.out.println("Player 2 wins. They have " + player2.getHand().size());
               bin.removeAll(bin);
           }else{
               i++;
               handleTie(i);
           }
       }else{
           if(player1.getHand().size() > player2.getHand().size()){
               player1.getHand().addAll(bin);
               System.out.println("Player 1 one wins. They have " + player1.getHand().size());
               bin.removeAll(bin);
           }else{
               player2.getHand().addAll(bin);
               System.out.println("Player 2 wins. They have " + player2.getHand().size());
               bin.removeAll(bin);
           }
           System.out.println("GAME OVER.");
       }

   }
}


