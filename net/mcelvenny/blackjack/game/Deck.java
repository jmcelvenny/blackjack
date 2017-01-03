package net.mcelvenny.blackjack.game;

import java.util.ArrayList;
/**
 * Created by john on 12/27/16.
 */

/*
 * Class contains a deck implementation.
 * Graveyard is used to stop cards from double appearing
 */
public class Deck {
    private ArrayList<Card> cards;
    private ArrayList<Card> graveyard;

    public Deck() {
        init();
        shuffle();
    }

    private void init() {
        cards = new ArrayList<Card>();

        if (graveyard != null && graveyard.size() != 0) {
            //THIS block for when the graveyard has been filled with cards previously
            //in this case, when the dealer shuffles, he will only have these cards
            //since the player will have some in his hand.
            cards.addAll(graveyard);
            graveyard = new ArrayList<Card>();
            //now shuffle
            shuffle();
        } else {
            //THIS block for when the graveyard has not been used before, i.e.
            //first time usage. We need to create an empty graveyard and fill cards
            //with all the cards
            //fill the deck with all 52 cards
            for (Card.Suit s: Card.Suit.values()) {
                for (Card.Value v : Card.Value.values()) {
                    cards.add(new Card(s, v));
                }
            }
            //create an empty graveyard
            graveyard = new ArrayList<Card>();
            //and now shuffle the cards
            shuffle();
        }
    }

    private void shuffle() {
        //Fisher-Yates modern shuffle
        for (int i=0; i<cards.size()-2; i++) {
            int j =(int) (Math.random() * (cards.size() - i));
            //swap
            Card temp = cards.get(i);
            cards.set(i, cards.get(j));
            cards.set(j, temp);
        }
    }

    public int size() {

        return cards.size();
    }

    public Card draw() {
        if (cards.size()==0) {
            System.out.println("Deck size was 0. calling init()");
            init();
            shuffle();
        }
        return cards.remove(0);
    }

    public void returnHand(Hand h) {
        for (Card c : h.getCards()) {
            graveyard.add(c);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Deck cards:\n");
        for (Card c : cards) {
            sb.append("\t" + c +"\n");
        }
        sb.append("Deck graveyard:\n");
        for (Card c : graveyard){
            sb.append("\t" + c  + "\n");
        }
        return sb.toString();
    }
}