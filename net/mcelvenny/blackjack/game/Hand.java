package net.mcelvenny.blackjack.game;

import java.util.ArrayList;
/**
 * Created by john on 12/27/16.
 */

/*
 * A hand implementation. Used to group cards
 * Also used to easily add/recalculate the value of a hand
 */

public class Hand {
    private ArrayList<Card> cards;

    public Hand() {
        cards = new ArrayList<Card>();
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void add(Card c) {
        cards.add(c);
    }

    public Card getTop() {
        if (cards.size()==0) return null;
        return cards.get(0);
    }

    //Little bit of tricky code with how aces work
    public int getValue() {
        //first, count the aces
        int aces = 0;
        int val = 0;
        //Count the value of all the regular cards first.
        //Keep track of the number of aces
        for (Card c : cards) {
            if (c.getValue() == Card.Value.Ace) {
                aces++;
            } else {
                val += c.cardValue();
            }
        }
        //Each ace is worth no less than 1, so add that to the count.
        val += aces;
        for (int i = 0; i < aces; i++) {
            //If the ace can be an 11, then we can make it one.
            //We check if the extra 10 doesn't push it over.
            if (val + 10 <= 21) {
                //if we could turn the 1 in to a 10, then do that.
                val +=10;
            }
        }
        return val;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Hand:\n");
        for (Card c : cards) {
            sb.append("\t" + c + "\n");
        }
        return sb.toString();
    }
}
