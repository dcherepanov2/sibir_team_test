package pokerHandTest;

import exception.ComparatorException;
import models.PokerHand;
import org.junit.jupiter.api.Test;
import service.PokerHandService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PokerHandServiceTest {

    private final List<PokerHand> forTesting = new LinkedList<>
            (Arrays.asList(
            new PokerHand("KS 2H 5C JD TD"),
            new PokerHand("KS KS 5C JD TD"),
            new PokerHand("KS KS KS JD TD"),
            new PokerHand("2D 3S 4S 5S 6S"),
            new PokerHand("3D 4S 5S 6S 7S"),
            new PokerHand("4D 5S 6S 7S 8S"),
            new PokerHand("5D 6S 7S 8S 9S"),
            new PokerHand("6D 7S 8S 9S TS"),
            new PokerHand("7D 8S 9S TS JS"),
            new PokerHand("8D 9S TS JS QS"),
            new PokerHand("JD JD KS KS KS"),
            new PokerHand("TD JD QD KD AD")
            ));

    @Test
    public void sortedPokerHand() throws ComparatorException {
        List<PokerHand> shuffle = new LinkedList<>(forTesting);
        Collections.shuffle(shuffle);//перемешиваем коллекцию для чистоты эксперемента :)
        PokerHandService sortPokerHand = new PokerHandService();
        Comparator<PokerHand> comparator = Comparator.comparing(PokerHand::getCombinationPower)
                                                     .thenComparing(PokerHand::getPower);
        PokerHand min = forTesting.stream()
                                  .min(comparator)
                                  .orElseThrow(()-> new ComparatorException("Comparator is incorrect"));
        PokerHand max = forTesting.stream()
                                  .max(comparator)
                                  .orElseThrow(()-> new ComparatorException("Comparator is incorrect"));
        List<PokerHand> sortAsc = sortPokerHand.sortAsc(shuffle);
        List<PokerHand> sortDesc = sortPokerHand.sortDesc(shuffle);
        assertEquals(min.getPower(), sortAsc.get(0).getPower());
        assertEquals(max.getPower(), sortAsc.get(sortAsc.size()-1).getPower());
        assertEquals(min.getPower(), sortDesc.get(sortDesc.size()-1).getPower());
        assertEquals(max.getPower(), sortDesc.get(0).getPower());
    }

    @Test
    public void winnerPeak(){
        PokerHand pokerHand1 = forTesting.get(1);
        PokerHandService pokerHandService = new PokerHandService();
        PokerHand winner = pokerHandService.pokerHandWinner(forTesting.get(0), pokerHand1);
        assertEquals(pokerHand1.getPower(),winner.getPower());

        pokerHand1 = forTesting.get(2);
        winner = pokerHandService.pokerHandWinner(forTesting.get(1), pokerHand1);
        assertEquals(pokerHand1.getPower(),winner.getPower());

        pokerHand1 = forTesting.get(3);
        winner = pokerHandService.pokerHandWinner(forTesting.get(2), pokerHand1);
        assertEquals(pokerHand1.getPower(),winner.getPower());

        pokerHand1 = forTesting.get(4);
        winner = pokerHandService.pokerHandWinner(forTesting.get(3), pokerHand1);
        assertEquals(pokerHand1.getPower(),winner.getPower());
    }
}
