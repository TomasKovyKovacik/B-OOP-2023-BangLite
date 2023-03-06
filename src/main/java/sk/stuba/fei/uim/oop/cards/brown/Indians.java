package sk.stuba.fei.uim.oop.cards.brown;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.player.Player;

public class Indians extends Card {

    public Indians() {
        super("Indians");
    }

    @Override
    public void play(Player attacker, Player[] players) {
        for (Player defender : players) {
            boolean bang = false;
            for (Card card : defender.getCards()) {
                if (card instanceof BangCard) {
                    defender.removeCardFromHand(card);
                    System.out.println(defender.getName() + " using a Bang card against Indians!");
                    bang = true;
                    break;
                }
            }
            if (!bang) {
                System.out.println(defender.getName() + " cant BANG the indians of the " + attacker.getName() + ",so " + defender.getName() + " losing live.");
                defender.lostLife();
                if (!defender.isActive()) {
                    System.out.println(attacker.getName() + " kills " + defender.getName());
                    defender.returnCardsAfterDeath();
                }
            }
        }
        attacker.removeCardFromHand(this);
    }
}
