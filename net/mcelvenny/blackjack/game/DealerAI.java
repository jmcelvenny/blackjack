package net.mcelvenny.blackjack.game;
/**
 * Created by john on 12/29/16.
 */
public class DealerAI {
    /*
     * Method: getAction()
     * Parameters: Hand of the dealer (d), Hand of the player (p)
     * Return: (0) stand (1) hit
     */
    public static int getAction(Hand d) {
        //Abide by standard regulations
        if (d.getValue() > 16) {
            return 0;
        } else {
            return 1;
        }

    }



}


