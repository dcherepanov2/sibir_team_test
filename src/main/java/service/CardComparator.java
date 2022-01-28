package service;

import models.Card;

import java.util.Comparator;

public class CardComparator implements Comparator<Card> {
    @Override
    public int compare(Card o1, Card o2) {
        return o1.getPower() - o2.getPower();
    }


    public int compareNominalPower(Card o1, Card o2){
        return o1.getNominal().getPower() - o2.getNominal().getPower();
    }
}
