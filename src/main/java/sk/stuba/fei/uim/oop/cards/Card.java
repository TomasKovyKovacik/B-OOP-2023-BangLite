package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

public abstract class Card {
    protected String name;

    public Card(String name) {
        this.name = name;
    }

    public abstract void play(Player player, Player[] players);

    public String getName() {
        return name;
    }

    public Player choosePlayer(Player[] players) {
        showPlayers(players);
        int playerNum;
        do {
            playerNum = ZKlavesnice.readInt("On which player do you want to play card? (enter number)");
            if (0 >= playerNum || playerNum > players.length ) {
                System.out.println("Wrong number of player, please try again!");
            }
        } while (0 >= playerNum || playerNum > players.length);
        return players[playerNum-1];
    }

    public void showPlayers(Player[] players) {
        System.out.println("----------------------------------");
        System.out.println("Active players:");
        int i = 0;
        for (Player player : players) {
            i++;
            System.out.println(i + ". " + player.getName());
        }
    }
}
