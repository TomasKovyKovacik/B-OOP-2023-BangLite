package sk.stuba.fei.uim.oop.board;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.cards.blue.Barrel;
import sk.stuba.fei.uim.oop.cards.blue.Dynamite;
import sk.stuba.fei.uim.oop.cards.blue.Prison;
import sk.stuba.fei.uim.oop.cards.brown.*;

import java.util.ArrayList;
import java.util.Collections;

public class Board {
    private ArrayList<Card> cardsPackage;
    private ArrayList<Card> disposalPackage;

    public Board() {
        this.disposalPackage = new ArrayList<>();
        this.initializeGame();
    }

    public ArrayList<Card> getCardsPackage() {
        return cardsPackage;
    }

    public ArrayList<Card> getDisposalPackage() {
        return disposalPackage;
    }

    public void shuffleCards() {
        this.cardsPackage.addAll(this.disposalPackage);
        this.disposalPackage.clear();
        Collections.shuffle(this.cardsPackage);
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
                this.cardsPackage.add(new Stagecoach(this));
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
}
