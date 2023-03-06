package sk.stuba.fei.uim.oop.player;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.cards.blue.BlueCard;
import sk.stuba.fei.uim.oop.cards.blue.Dynamite;
import sk.stuba.fei.uim.oop.cards.blue.Prison;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Random;

public class Player {

    private final String name;
    private int lives;
    private ArrayList<Card> disposalPackage;
    private ArrayList<Card> cards;
    private ArrayList<BlueCard> cardsOnTable;
    private Random random;

    public Player(String name, ArrayList<Card> disposalPackage) {
        this.name = name;
        this.disposalPackage = disposalPackage;
        this.lives = 4;
        this.cards = new ArrayList<>();
        this.cardsOnTable = new ArrayList<>();
        this.random = new Random();
    }

    public void removeCardFromHand(Card card) {
        this.cards.remove(card);
        this.disposalPackage.add(card);
    }

    public void removeCardFromTable(Card card) {
        this.cardsOnTable.remove(card);
        this.disposalPackage.add(card);
    }

    public void addCardOnTable(BlueCard card) {
        this.cardsOnTable.add(card);
    }

    public void lostLife() {
        this.lives -= 1;
    }

    public void lostLife(int numOfLives) {
        this.lives -= numOfLives;
    }

    public void addLife() {
        this.lives += 1;
    }

    public boolean isActive() {
        return this.lives > 0;
    }

    public String getName() {
        return name;
    }

    public int getLives() {
        return lives;
    }

    public ArrayList<Card> getDisposalPackage() {
        return disposalPackage;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public ArrayList<BlueCard> getCardsOnTable() {
        return cardsOnTable;
    }

    public int showCards() {
        int i = 0;
        System.out.println("----------------------------------");
        System.out.println(this.getName() + " Cards on HAND:");
        for (Card card : this.cards) {
            i++;
            System.out.println(i + ". " + card.getName());
        }
        System.out.println((i + 1) + ". End of turn");
        return i;
    }

    public void returnCardsAfterDeath() {
        this.disposalPackage.addAll(this.cards);
        this.cards.clear();
        this.disposalPackage.addAll(this.cardsOnTable);
        this.cardsOnTable.clear();
    }

    public boolean playCards(Player[] players) {
        int numOfCards = this.showCards();
        int numOfCard = ZKlavesnice.readInt("Which Card do you want to play? (enter number)");
        if (0 < numOfCard && numOfCard < numOfCards + 1) {
            this.cards.get(numOfCard - 1).play(this, players);
        } else if (numOfCard == numOfCards + 1) {
            return false;
        } else {
            System.out.println("You enter invalid number of card! Try again!");
        }
        return true;
    }

    public void drawCards(ArrayList<Card> cardsPackage, int number) {
        for (int i = 0; i < number; i++) {
            this.cards.add(cardsPackage.remove(cardsPackage.size()-1));
        }
    }

    private boolean checkDynamite(ArrayList<Player> activePlayers) {
        for (BlueCard card : this.getCardsOnTable()) {
            if (card instanceof Dynamite) {
                return card.controlEffect(this, activePlayers);
            }
        }
        return true;
    }

    private boolean checkPrison(ArrayList<Player> activePlayers) {
        for (BlueCard card : this.getCardsOnTable()) {
            if (card instanceof Prison) {
                return card.controlEffect(this, activePlayers);
            }
        }
        return true;
    }

    public boolean canPlayRound(ArrayList<Player> activePlayers) {
        boolean stillPlay = this.checkDynamite(activePlayers);
        if (stillPlay) {
            stillPlay = this.checkPrison(activePlayers);
        }
        return stillPlay;
    }

    public void dropCards(int i) {
        System.out.println("Dropping " + i + " cards, because you have more cards than lives!");
        for (int j = 0; j < i; j++) {
            Card card = this.cards.get(this.random.nextInt(this.cards.size()-1));
            System.out.println("Dropping " + card.getName());
            this.removeCardFromHand(card);
        }
    }
}
