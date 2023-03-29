package sk.stuba.fei.uim.oop.bang;

import sk.stuba.fei.uim.oop.board.Board;
import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.cards.blue.Barrel;
import sk.stuba.fei.uim.oop.cards.blue.Dynamite;
import sk.stuba.fei.uim.oop.cards.blue.Prison;
import sk.stuba.fei.uim.oop.cards.brown.*;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;
import java.util.Collections;

public class Bang {
    private final Player[] players;
    private Board board;
    private int currentPlayer;
    private int roundCounter;

    public Bang() {
        System.out.println("*** Welcome to Bang game, OOP Version ***");

        int numberPlayers = 0;
        while (numberPlayers < 2 || numberPlayers > 4) {
            numberPlayers = ZKlavesnice.readInt("*** Enter number of players (2-4): ***");
            if (numberPlayers < 2 || numberPlayers > 4) {
                System.out.println(" !!! You enter wrong number of players. Try Again! !!!");
            }
        }
        this.players = new Player[numberPlayers];
        this.board = new Board();
        for (int i = 0; i < numberPlayers; i++) {
            this.players[i] = new Player(ZKlavesnice.readString("Enter name for PLAYER " + (i + 1)
                    + " :"), this.board.getDisposalPackage());
        }

        this.startGame();
    }



    private void startGame() {
        System.out.println("*** GAME STARTED ***");
        this.startDrawCards();
        for (this.currentPlayer = 0;getActivePlayers().size() > 1;this.incrementCounter()) {
            Player activePlayer = this.roundCounter();
            if (!activePlayer.isActive()) {
                continue;
            }
            this.printPlayerInfo(activePlayer);

            boolean stillPlay = activePlayer.canPlayRound(this.getActivePlayers());
            this.turnDrawCards(activePlayer, stillPlay);

            while (stillPlay) {
                if (this.getActivePlayers().size() < 2) {
                    break;
                }
                ArrayList<Player> temp = this.getActivePlayers();
                temp.removeIf(o -> o.equals(activePlayer));
                stillPlay = activePlayer.playCards(temp.toArray(new Player[0]));
            }
            if (activePlayer.getLives() < activePlayer.getCards().size() && activePlayer.isActive()) {
                activePlayer.dropCards(activePlayer.getCards().size() - activePlayer.getLives());
            }
        }
        System.out.println("--- GAME FINISHED ---");
        System.out.println("And the WINNER is " + getActivePlayers().get(0).getName());
    }

    private void turnDrawCards(Player activePlayer, boolean stillPlay) {
        if (this.board.getCardsPackage().size() < 3 && stillPlay) {
            this.board.shuffleCards();
            if (this.board.getCardsPackage().size() > 1) {
                activePlayer.drawCards(this.board.getCardsPackage(), 2);
            } else {
                System.out.println("Cant draw 2 cards on start of the turn, because there isnt enough cards, drawing " + this.board.getCardsPackage().size() + " cards.");
                activePlayer.drawCards(this.board.getCardsPackage(), this.board.getCardsPackage().size());
            }
        } else if (stillPlay) {
            activePlayer.drawCards(this.board.getCardsPackage(), 2);
        }
    }

    private void printPlayerInfo(Player activePlayer) {
        System.out.println("--- PLAYER " + activePlayer.getName() + " STARTS TURN ---");
        System.out.println("Players lives: " + activePlayer.getLives());
    }

    private Player roundCounter() {
        if (this.currentPlayer == 0) {
            System.out.println("--- ROUND " + (this.roundCounter / this.players.length + 1) + " STARTS ---");
        }
        return this.players[this.currentPlayer];
    }

    private void startDrawCards() {
        for (Player player : this.players) {
            player.drawCards(this.board.getCardsPackage(), 4);
        }
    }



    private ArrayList<Player> getActivePlayers() {
        ArrayList<Player> activePlayers = new ArrayList<Player>();
        for (Player player: this.players) {
            if (player.isActive()) {
                activePlayers.add(player);
            }
        }
        return activePlayers;
    }

    private void incrementCounter() {
        this.currentPlayer++;
        this.currentPlayer %= this.players.length;
        this.roundCounter++;
    }
}
