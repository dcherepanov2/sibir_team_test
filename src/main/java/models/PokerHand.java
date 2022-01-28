package models;

import models.enums.Combination;
import service.PokerHandService;

import java.util.List;

public class PokerHand {

    private final List<Card> cards;

    private final int power;

    private final Combination combination;

    private final int combinationPower;

    public PokerHand(String cardsFromUser) {
        PokerHand pokerHand = new PokerHandService().createPokerHand(cardsFromUser);
        this.cards = pokerHand.getCards();
        this.power = pokerHand.getPower();
        this.combination = pokerHand.getCombination();
        this.combinationPower = pokerHand.getCombinationPower();
    }

    public PokerHand(List<Card> cards, int power, Combination combination, int combinationPower) {
        this.cards = cards;
        this.power = power;
        this.combination = combination;
        this.combinationPower = combinationPower;
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getPower() {
        return power;
    }

    public Combination getCombination() {
        return combination;
    }

    public int getCombinationPower() {
        return combinationPower;
    }

    @Override
    public String toString() {
        return "PokerHand{" +
                "cards=" + cards +
                ", power=" + power +
                '}';
    }
}
