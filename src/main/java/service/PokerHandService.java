package service;

import exception.SetCardException;
import models.Card;
import models.enums.Combination;
import models.PokerHand;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PokerHandService {

    private CombinationQualifier combinationQualifier;
    private final Comparator<PokerHand> comparator = Comparator.comparing(PokerHand::getCombinationPower)
                                                               .thenComparing(PokerHand::getPower);

    public List<PokerHand> sortDesc(List<PokerHand> unSorting) {
        return unSorting.stream()
                        .sorted(comparator.reversed())
                        .collect(Collectors.toList());
    }

    public List<PokerHand> sortAsc(List<PokerHand> unSorting) {
        return unSorting.stream()
                        .sorted(comparator)
                        .collect(Collectors.toList());
    }

    public PokerHand pokerHandWinner(PokerHand o1, PokerHand o2) {
        return o1.getPower() > o2.getPower() &&
               o1.getCombination().getPower() > o2.getCombination().getPower()
               ? o1 : o2;
    }

    private void calcAndQualifierCombination(List<Card> cardList) {
        CombinationQualifier combinationQualifier = new CombinationQualifier(0, cardList);
        combinationQualifier.itsHighCart()
                            .itsPairOrThreeOrFour()
                            .itsTwoPair()
                            .itsStraight()
                            .itsFlush()
                            .itsFullHouse()
                            .itsStraightFlush()
                            .itsFlushRoyal();
        this.combinationQualifier = combinationQualifier;
    }

    //ленивая инициализация
    public Combination getCombination(List<Card> cardList) {
        if (combinationQualifier == null) {
            this.calcAndQualifierCombination(cardList);
        }
        return combinationQualifier.getCombination();
    }

    //ленивая инициализация
    public int getPower(List<Card> cardList) {
        if (combinationQualifier == null) {
            this.calcAndQualifierCombination(cardList);
        }
        return combinationQualifier.getMaxPowerCombination();
    }

    public PokerHand createPokerHand(String cardsFromUser){
        List<Card> cards = new ArrayList<>(5);
        String[] cardsSplit = cardsFromUser.split(" ");
        if (cardsSplit.length != 5 && cardsFromUser.length() != 14)
            throw new SetCardException("The number of cards must not be more or less than five");
        for (String card : cardsSplit) {
            cards.add(CardService.createCard(card));
        }
        return new PokerHand(cards,
                             this.getPower(cards),
                             this.getCombination(cards),
                             this.getCombination(cards).getPower());
    }
}
