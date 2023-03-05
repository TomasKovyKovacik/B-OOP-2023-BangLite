package sk.stuba.fei.uim.oop.cards.blue;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class BlueCard extends Card {
    protected Random random;

    public BlueCard(String name) {
        super(name);
        this.random = new Random();
    }

    public abstract boolean controlEffect(Player activePlayer, List<Player> players);

    public abstract boolean isPlayable(ArrayList<BlueCard> cardsOnTable);
}
