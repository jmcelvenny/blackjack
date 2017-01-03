import jdk.nashorn.internal.scripts.JO;

import javax.swing.JOptionPane;
/**
 * Created by john on 12/29/16.
 */

/*
 * This class controls all communications between Player <---> Game
 */
public class PlayerInterface {

    private static String[] options = {"Hit", "Stand"};

    public PlayerInterface() {

    }

    public int getBetAmount(int mymoney) {
        if (mymoney == 0) {
            JOptionPane.showMessageDialog(null, "Looks like you ran out of money! Play again soon!",
                    "Blackjack: Bet", JOptionPane.INFORMATION_MESSAGE);
            return  -1;
        }
        String input;
        while(true) {
            input = JOptionPane.showInputDialog(null, "How much would you like to bet? You have $" + mymoney,
                    "Blackjack: Bet", JOptionPane.QUESTION_MESSAGE);
            try {
                if (input==null) continue;
                if (input.contains("$"))
                    input=input.replaceAll("$", "");

                int choice = Integer.parseInt(input);
                if (choice > mymoney) {
                    JOptionPane.showMessageDialog(null, "You only have $"+mymoney,
                            "Blackjack: Bet", JOptionPane.INFORMATION_MESSAGE, null);
                } else if (choice <= 0) {
                    JOptionPane.showMessageDialog(null, "You have to bet at least $1!",
                            "Blackjack: Bet", JOptionPane.INFORMATION_MESSAGE, null);
                } else {
                    return choice;
                }
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "Please enter an integer with no $ symbol.",
                        "Blackjack: Bet", JOptionPane.INFORMATION_MESSAGE, null);
                continue;
            }
        }
    }

    public int getPlayerChoice(Hand player, Card dealer) {
        StringBuilder sb = new StringBuilder();
        sb.append("Dealer's Visible Card:\n\t");
        sb.append(dealer);
        sb.append("\n\n");
        sb.append("Your ");
        sb.append(player);
        int choice = JOptionPane.showOptionDialog(null,
                sb.toString(),
                "Blackjack: Your move.", JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null,
                options, "Hit");
        return choice;
    }

    public int showOutcome(Hand player, Hand dealer) {
        if (player.getValue() > 21 || ( (dealer.getValue() > player.getValue()) && dealer.getValue() <= 21 )) {
            JOptionPane.showMessageDialog(null, "Dealer's " + dealer + "\nYour " + player + "\n\n You lose!");
            return -1;
        }
        if (player.getValue() <= 21 && ( (dealer.getValue() < player.getValue()) || dealer.getValue() > 21)) {
            JOptionPane.showMessageDialog(null, "Dealer's " + dealer + "\nYour " + player + "\n\n You win!");
            return 1;
        }
        if (player.getValue() == dealer.getValue() && player.getValue() <=21 ) {
            JOptionPane.showMessageDialog(null, "Dealer's " + dealer + "\nYour " + player + "\n\n Push!");
            return 0;
        }
        return 0;
    }
}
