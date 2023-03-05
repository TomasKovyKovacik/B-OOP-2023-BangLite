package sk.stuba.fei.uim.oop.cards.blue;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dynamite extends BlueCard {

    public Dynamite() {
        super("Dynamite!");
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
        if (random.nextInt(8) == 0) {
            System.out.println(activePlayer.getName() + " is blown by Dynamite, losing 3 lives!");
            activePlayer.lostLife();
            activePlayer.lostLife();
            activePlayer.lostLife();
            if (!activePlayer.isActive()) {
                System.out.println(activePlayer.getName() + " died on Dynamite!");
                activePlayer.returnCardsAfterDeath();
                return false;
            }
        } else {
            System.out.println(activePlayer.getName() + " not blown on Dynamite, passing to another player");
            for (int i = 0; i < players.size(); i++) {
                if (activePlayer.equals(players.get(i))) {
                    if (i > 0) {
                        players.get(i - 1).addCardOnTable(this);
                    } else {
                        players.get(players.size() - 1).addCardOnTable(this);
                    }
                    activePlayer.removeCardFromTable(this);
                }
            }
        }
        return true;
    }

    @Override
    public boolean isPlayable(ArrayList<BlueCard> cardsOnTable) {
        for (Card card : cardsOnTable) {
            if (card instanceof Dynamite) {
                return false;
            }
        }
        return true;
    }
}
