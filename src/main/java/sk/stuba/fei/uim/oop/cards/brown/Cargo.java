package sk.stuba.fei.uim.oop.cards.brown;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;

public class Cargo extends Card {
    private ArrayList<Card> cardsPackage;

    public Cargo(ArrayList<Card> cardsPackage) {
        super("Cargo");
        this.cardsPackage = cardsPackage;
    }

    @Override
    public void play(Player player, Player[] players) {
        if (this.cardsPackage.size() >= 2) {
            player.drawCards(this.cardsPackage, 2);
            player.removeCardFromHand(this);
        } else {
            System.out.println("Cant use this card, because there isnt enough cards in package");
        }
    }
}
