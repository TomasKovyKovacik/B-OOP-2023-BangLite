package sk.stuba.fei.uim.oop.cards.brown;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.cards.blue.Barrel;
import sk.stuba.fei.uim.oop.cards.blue.BlueCard;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.Arrays;

public class BangCard extends Card {

    public BangCard() {
        super("BANG!");
    }

    @Override
    public void play(Player attacker, Player[] players) {
        Player defender = this.choosePlayer(players);
        boolean dodge = false;
        for (BlueCard blueCard : defender.getCardsOnTable()) {
            if (blueCard instanceof Barrel) {
                System.out.println("Controlling Barrel of " + defender.getName() + " ...");
                if (blueCard.controlEffect(attacker, Arrays.asList(players))) {
                    System.out.println(defender.getName() +" have a LUCK, you dodge the BANG");
                    dodge = true;
                } else {
                    System.out.println(defender.getName() + " cant hide behind the BARREL");
                }
                break;
            }
        }
        if (!dodge) {
            for (Card card : defender.getCards()) {
                if (card instanceof Dodge) {
                    defender.removeCardFromHand(card);
                    System.out.println(defender.getName() + " using a DODGE card");
                    dodge = true;
                    break;
                }
            }
        }
        if (!dodge) {
            System.out.println(defender.getName() + " cant dodge the BANG of " + attacker.getName() + ",so " + defender.getName() + " losing live.");
            defender.lostLife();
            if (!defender.isActive()) {
                System.out.println(attacker.getName() + " kills " + defender.getName());
                defender.returnCardsAfterDeath();
            }
        }
        attacker.removeCardFromHand(this);
    }
}
