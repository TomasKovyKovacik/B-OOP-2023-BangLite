package sk.stuba.fei.uim.oop.bang;

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
    private ArrayList<Card> cardsPackage;
    private ArrayList<Card> disposalPackage;
    private int currentPlayer;
    private int roundCounter;

    public Bang() {
        this.disposalPackage = new ArrayList<>();
        System.out.println("*** Welcome to Bang game, OOP Version ***");

        int numberPlayers = 0;
        while (numberPlayers < 2 || numberPlayers > 4) {
            numberPlayers = ZKlavesnice.readInt("*** Enter number of players (2-4): ***");
            if (numberPlayers < 2 || numberPlayers > 4) {
                System.out.println(" !!! You enter wrong number of players. Try Again! !!!");
            }
        }
        this.players = new Player[numberPlayers];
        for (int i = 0; i < numberPlayers; i++) {
            this.players[i] = new Player(ZKlavesnice.readString("Enter name for PLAYER " + (i + 1)
                    + " :"), this.disposalPackage);
        }

        this.initializeGame();
        this.startGame();
    }

    private void initializeGame() {
        this.cardsPackage = new ArrayList<>();
        for (int i = 0; i < 30; i++){
            this.cardsPackage.add(new BangCard());
            if (i < 15) {
                this.cardsPackage.add(new Dodge());
            }
            if (i < 8) {
                this.cardsPackage.add(new Beer());
            }
            if (i < 6) {
                this.cardsPackage.add(new CatBalou());
            }
            if (i < 4) {
                this.cardsPackage.add(new Cargo(this.cardsPackage));
            }
            if (i < 3) {
                this.cardsPackage.add(new Indians());
            }
            if (i < 3) {
                this.cardsPackage.add(new Prison());
            }
            if (i < 2) {
                this.cardsPackage.add(new Barrel());
            }
            if (i < 1) {
                this.cardsPackage.add(new Dynamite());
            }
        };
        Collections.shuffle(this.cardsPackage);
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
        }
        System.out.println("--- GAME FINISHED ---");
        System.out.println("And the WINNER is " + getActivePlayers().get(0).getName());
    }

    private void turnDrawCards(Player activePlayer, boolean stillPlay) {
        if (this.cardsPackage.size() < 3 && stillPlay) {
            this.shuffleCards();
            if (this.cardsPackage.size() > 1) {
                activePlayer.drawCards(this.cardsPackage, 2);
            } else {
                System.out.println("Cant draw 2 cards on start of the turn, because there isnt enough cards, drawing " + this.cardsPackage.size() + " cards.");
                activePlayer.drawCards(this.cardsPackage, this.cardsPackage.size());
            }
        } else if (stillPlay) {
            activePlayer.drawCards(this.cardsPackage, 2);
        }
    }

    private void printPlayerInfo(Player activePlayer) {
        System.out.println("--- PLAYER " + activePlayer.getName() + " STARTS TURN ---");
        System.out.println("Players lives: " + activePlayer.getLives());
    }

    private void shuffleCards() {
        this.cardsPackage.addAll(this.disposalPackage);
        this.disposalPackage.clear();
        Collections.shuffle(this.cardsPackage);
    }

    private Player roundCounter() {
        if (this.currentPlayer == 0) {
            System.out.println("--- ROUND " + (this.roundCounter / this.players.length + 1) + " STARTS ---");
        }
        return this.players[this.currentPlayer];
    }

    private void startDrawCards() {
        for (int i = 0; i < this.players.length;i++) {
            this.players[i].drawCards(this.cardsPackage, 4);
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
