package sk.stuba.fei.uim.oop.cards.blue;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Prison extends BlueCard {

    public Prison() {
        super("Prison");
    }

    @Override
    public void play(Player player, Player[] players) {
        Player defender = this.choosePlayer(players);
        if (isPlayable(defender.getCardsOnTable())) {
            defender.addCardOnTable(this);
            player.getCards().remove(this);
        } else {
            System.out.println("You cant play this card on " + defender.getName() + " right now!");
        }
    }

    @Override
    public boolean controlEffect(Player activePlayer, List<Player> players) {
        boolean stillPlay = true;
        if (random.nextInt(4) == 0) {
            System.out.println(activePlayer.getName() + " gets out of the prison.");
        } else {
            System.out.println(activePlayer.getName() + " will be in prison this round.");
            stillPlay = false;
        }
        activePlayer.removeCardFromTable(this);
        return stillPlay;
    }

    @Override
    public boolean isPlayable(ArrayList<BlueCard> cardsOnTable) {
        for (Card card : cardsOnTable) {
            if (card instanceof Prison) {
                return false;
            }
        }
        return true;
    }
}
