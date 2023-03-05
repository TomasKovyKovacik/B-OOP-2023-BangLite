package sk.stuba.fei.uim.oop.cards.blue;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Barrel extends BlueCard {

    public Barrel() {
        super("Barrel");
    }

    @Override
    public void play(Player player, Player[] players) {
        if (isPlayable(player.getCardsOnTable())) {
            player.addCardOnTable(this);
            player.getCards().remove(this);
        } else {
            System.out.println("You cant play this card right now!");
        }
    }

    @Override
    public boolean controlEffect(Player activePlayer, List<Player> players) {
        return random.nextInt(4) == 0;
    }

    @Override
    public boolean isPlayable(ArrayList<BlueCard> cardsOnTable) {
        for (Card card : cardsOnTable) {
            if (card instanceof Barrel) {
                return false;
            }
        }
        return true;
    }
}
