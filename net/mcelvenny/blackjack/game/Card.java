package net.mcelvenny.blackjack.game;
/**
 * Created by john on 12/27/16.
 */

/*
 * Class contains a simple declaration of a Card
 */
public class Card {
    private Suit suit;
    private Value value;

    public Card(Suit s, Value v) {
        this.suit = s;
        this.value = v;
    }

    public Value getValue() {
        return value;
    }

    public Suit getSuit() {
        return suit;
    }

    public int cardValue() {
        switch(value) {
            case Ace:
                return 1;
            case Two:
                return 2;
            case Three:
                return 3;
            case Four:
                return 4;
            case Five:
                return 5;
            case Six:
                return 6;
            case Seven:
                return 7;
            case Eight:
                return 8;
            case Nine:
                return 9;
            case Ten:
            case Queen:
            case King:
            case Jack:
                return 10;
            default:
                return -100;
        }
    }

    public enum Suit {
        Hearts, Spades, Diamonds, Clubs
    }
    public enum Value {
        Ace, Two, Three, Four, Five, Six,
        Seven, Eight, Nine, Ten, Queen,
        King, Jack
    }

    @Override
    public String toString() {
        return value.toString() + " of " + suit.toString();
    }
}