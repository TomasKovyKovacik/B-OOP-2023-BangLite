package sk.stuba.fei.uim.oop.cards.brown;

import sk.stuba.fei.uim.oop.board.Board;
import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.player.Player;

public class Cargo extends Card {
    private Board board;

    public Cargo(Board board) {
        super("Cargo");
        this.board = board;
    }

    @Override
    public void play(Player player, Player[] players) {
        if (this.board.getCardsPackage().size() < 3) {
            this.board.shuffleCards();
            if (this.board.getCardsPackage().size() > 1) {
                this.drawCards(player);
            } else {
                System.out.println("Cant use this card, because there isnt enough cards in package");
            }
        } else {
            this.drawCards(player);
        }
    }

    private void drawCards(Player player) {
        player.drawCards(this.board.getCardsPackage(), 2);
        player.removeCardFromHand(this);
    }
}
