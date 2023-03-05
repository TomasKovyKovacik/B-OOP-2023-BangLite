package sk.stuba.fei.uim.oop.cards.brown;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.player.Player;

public class Dodge extends Card {

    public Dodge() {
        super("Dodge");
    }

    @Override
    public void play(Player player,Player[] players) {
        System.out.println("You cant play this card on your turn!");
    }

}
