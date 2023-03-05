package sk.stuba.fei.uim.oop.cards.brown;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.player.Player;

public class Beer extends Card {

    public Beer() {
        super("Beer");
    }

    @Override
    public void play(Player player, Player[] players) {
        player.addLife();
        System.out.println(player.getName() + " using beer! Current lives: " + player.getLives());
        player.removeCardFromHand(this);
    }
}
