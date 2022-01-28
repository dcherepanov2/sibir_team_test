package service;

import models.enums.Combination;
import models.Card;
import models.enums.Nominal;
import models.enums.Suit;

import java.util.*;
import java.util.stream.Collectors;

public class CombinationQualifier {

    private int maxPowerCombination;
    private final CardComparator cardComparator;
    private final List<Card> cardList;
    private final List<Card> modifying;
    private Combination combination;

    public CombinationQualifier(int maxPowerCombination, List<Card> cardList) {
        this.maxPowerCombination = maxPowerCombination;
        this.cardList = cardList;
        this.cardComparator = new CardComparator();
        this.modifying = cardList.stream()
                .sorted(cardComparator)
                .collect(Collectors.toList());
    }

    public CombinationQualifier itsHighCart() {
        maxPowerCombination = cardList.stream().max(cardComparator).get().getPower();
        combination = Combination.HIGH;
        return this;
    }

    public CombinationQualifier itsPairOrThreeOrFour() {
        byte count = 0;
        boolean flag = true;
        Card cardLocal = null;
        for (int i = 0; i < modifying.size() - 1; i++) {
            if (flag && cardComparator.compare(modifying.get(i), modifying.get(i + 1)) == 0) {
                cardLocal = modifying.get(i);
                count++;
                flag = false;
                continue;
            }
            if (!flag && cardComparator.compare(cardLocal, modifying.get(i + 1)) == 0) {
                count++;
            }
        }
        if (cardLocal == null)
            return this;
        if (count == 1){
            maxPowerCombination = Math.max(maxPowerCombination, cardLocal.getPower() *2);//пара
            this.combination = Combination.ONE_PAIR;
        }
        else if (count == 2){
            maxPowerCombination = Math.max(maxPowerCombination, cardLocal.getPower() * 3);//тройка
            this.combination = Combination.THREE_OF_KIND;
        }
        else if (count >= 3){
            maxPowerCombination = Math.max(maxPowerCombination, cardLocal.getPower() * 4);//каре
            this.combination = Combination.FOUR_OF_KIND;
        }
        return this;
    }//можно вынести в разные методы для более понятноого постраения структуры проекта,
    // но в таком случае увеличивается время работы программы

    public CombinationQualifier itsTwoPair() {
        int localPower = 0;
        byte count = 0;
        List<Card> modifyingLocal = new LinkedList<>(modifying);
        CardComparator cardComparator = new CardComparator();

        for (int i = 0; i < modifyingLocal.size() - 1; i++) {
            if (cardComparator.compare(modifyingLocal.get(i),modifyingLocal.get(i + 1)) == 0 &&
                cardComparator.compareNominalPower(modifyingLocal.get(i),modifyingLocal.get(i + 1)) == 0)
            {
                localPower = localPower + modifyingLocal.get(i).getPower() * 2;
                modifyingLocal.removeAll(Arrays.asList(modifyingLocal.get(i), modifyingLocal.get(i + 1)));
                i = -1;
                count++;
            }
        }
        if(count == 2){
            this.combination = Combination.TWO_PAIRS;
            maxPowerCombination = localPower;
        }
        return this;
    }

    public CombinationQualifier itsStraight() {
        int localPower = modifying.get(0).getPower();
        for (int i = 0; i < modifying.size() - 1; i++) {
            if (modifying.get(i + 1).getPower() - modifying.get(i).getPower() == 1)
                localPower += modifying.get(i + 1).getPower();
            else
                return this;
        }
        maxPowerCombination =  localPower;
        this.combination = Combination.STRAIGHT;
        return this;
    }

    public CombinationQualifier itsFlush() {
        int localPower = 0;
        Suit suit = cardList.get(0).getSuit();
        for (Card card : cardList) {
            if (card.getSuit() != suit)
                return this;
            localPower += card.getPower();
        }
        maxPowerCombination = localPower;
        this.combination = Combination.FLUSH;
        return this;
    }

    public CombinationQualifier itsFullHouse() {
        Card pair;
        Card triple;
        if (modifying.size() < 5)
            return this;

        if (modifying.get(0).equals(modifying.get(1)) && modifying.get(1).equals(modifying.get(2))) {
            triple = modifying.get(2);
            if (modifying.get(3).equals(modifying.get(4))) {
                pair = modifying.get(1);
                maxPowerCombination = pair.getPower() * 2 + triple.getPower() * 3;
                this.combination = Combination.FULL_HOUSE;
                return this;
            }
        }

        if (modifying.get(2).equals(modifying.get(3)) && modifying.get(3).equals(modifying.get(4))) {
            triple = modifying.get(4);
            if (modifying.get(0).equals(modifying.get(1))) {
                pair = modifying.get(1);
                maxPowerCombination = pair.getPower() * 2 + triple.getPower() * 3;
                this.combination = Combination.FULL_HOUSE;
            }
        }
        return this;
    }

    public CombinationQualifier itsStraightFlush() {
        int localPower = 0;

        Suit suit = modifying.get(0).getSuit();
        for (int i = 0; i < modifying.size() - 1; i++) {
            Card cardLocal = modifying.get(i);
            if (cardLocal.getSuit() != suit)
                return this;
            if (modifying.get(i + 1).getPower() - cardLocal.getPower() != 1)
                return this;
            localPower += cardLocal.getPower();
        }
        localPower += modifying.get(modifying.size() - 1).getPower();
        maxPowerCombination = localPower;
        this.combination = Combination.STRAIGHT_FLUSH;
        return this;
    }

    public void itsFlushRoyal() {
        if (modifying.get(0).getNominal() != Nominal.TEN)
            return;

        Suit suit = modifying.get(0).getSuit();

        int localPower = modifying.get(0).getPower();
        for (int i = 0; i < modifying.size() - 1; i++) {
            if (cardList.get(i + 1).getPower() - cardList.get(i).getPower() != 1)
                return;
            if (cardList.get(i).getSuit() != suit)
                return;
            localPower += modifying.get(i + 1).getPower();
        }
        maxPowerCombination = localPower;
        this.combination = Combination.ROYAL_FLUSH;
    }

    public int getMaxPowerCombination() {
        return maxPowerCombination;
    }

    public Combination getCombination() {
        return combination;
    }
}
