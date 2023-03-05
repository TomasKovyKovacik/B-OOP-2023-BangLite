package sk.stuba.fei.uim.oop.cards.brown;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.Random;

public class CatBalou extends Card {
    protected Random random;

    public CatBalou() {
        super("Cat Balou");
        this.random = new Random();
    }

    @Override
    public void play(Player player, Player[] players) {
        Player defender = this.choosePlayer(players);
        int cardsChoose;
        do {
            System.out.println("-------------------");
            System.out.println("1. Cards on Table");
            System.out.println("2. Cards on Hands");
            cardsChoose = ZKlavesnice.readInt("On which card pile you want to play card? (enter number)");
            if (cardsChoose != 1 &&  cardsChoose != 2) {
                System.out.println("Wrong number, please try again!");
            }
        } while (cardsChoose != 1 &&  cardsChoose != 2);
        if (cardsChoose == 1) {
            if (defender.getCardsOnTable().size() > 0) {
                defender.removeCardFromTable(defender.getCardsOnTable().get(this.random.nextInt(defender.getCardsOnTable().size())));
                player.removeCardFromHand(this);
            } else {
                System.out.println("Cant use this card on Table Cards, please use another card");
            }
        } else {
            if (defender.getCards().size() > 0) {
                defender.removeCardFromHand(defender.getCards().get(this.random.nextInt(defender.getCards().size())));
                player.removeCardFromHand(this);
            } else {
                System.out.println("Cant use this card on Cards in hand, please use another card");
            }
        }
    }
}
