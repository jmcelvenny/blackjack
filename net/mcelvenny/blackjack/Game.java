package net.mcelvenny.blackjack;

import javax.swing.JOptionPane;
import net.mcelvenny.blackjack.game.*;
import net.mcelvenny.blackjack.ui.*;


/**
 * Created by john on 12/27/16.
 */
public class Game {
    private Deck deck; //The deck this round will play from
    private PlayerInterface pi;
    private int playerMoney;

    public static void main(String[] args) {
        Game g = new Game();
        g.deck = new Deck();
        g.pi = new PlayerInterface();
        g.playerMoney = 2500;
        while(true) {
            g.playRound();
        }
    }

    /*
     * * Core Game Logic
     * Keep the logic minimal and keep the UI-specific details in PlayerInterface
     * Unless you're changing how the game is played, don't touch this...
     */
    public void playRound() {
            //Robust getBetAmount
            int x = pi.getBetAmount(playerMoney);
            if (x == -1)
                System.exit(0);

            Hand dealer = new Hand();
            Hand player = new Hand();
            //Two cards each
            dealer.add(deck.draw()); dealer.add(deck.draw());
            player.add(deck.draw()); player.add(deck.draw());
            //Keep looping until the player stands
            while (pi.getPlayerChoice(player, dealer.getTop()) != 1) {
                    player.add(deck.draw());
                    if (player.getValue() > 21) break;
            }
            //Allow the dealer AI to decide until it decides to stand
            while(DealerAI.getAction(dealer)==1) {
                dealer.add((deck.draw()));
            }

            playerMoney += x*pi.showOutcome(player, dealer);
            deck.returnHand(dealer);
            deck.returnHand(player);
    }
}