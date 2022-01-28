package models;

import models.enums.Nominal;
import models.enums.Suit;

import java.util.Objects;

public class Card {

    private Nominal nominal;

    private Suit suit;

    private int power;

    public Nominal getNominal() {
        return nominal;
    }

    public void setNominal(Nominal nominal) {
        this.nominal = nominal;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return nominal == card.nominal;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nominal);
    }
}
