package pokerHandTest;

import models.enums.Combination;
import models.enums.Nominal;
import models.PokerHand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PokerGoodTest {

    //определение старшей карты
    @Test
    public void highCardTest() {
        PokerHand pokerHand = new PokerHand("KS 2H 5C JD TD");
        assertEquals(Nominal.KING.getPower(), pokerHand.getPower());
        assertEquals(Combination.HIGH, pokerHand.getCombination());
    }

    @Test
    public void onePairTest() {
        PokerHand pokerHand = new PokerHand("KS KS 5C JD TD");
        PokerHand pokerHand1 = new PokerHand("5C KS KS JD TD");
        PokerHand pokerHand2 = new PokerHand("5C TD KS KS 2D");
        PokerHand pokerHand3 = new PokerHand("5C TD 2S KS KS");

        assertEquals(Nominal.KING.getPower() * 2, pokerHand.getPower());
        assertEquals(Combination.ONE_PAIR, pokerHand.getCombination());

        assertEquals(Nominal.KING.getPower() * 2, pokerHand1.getPower());
        assertEquals(Combination.ONE_PAIR, pokerHand1.getCombination());

        assertEquals(Nominal.KING.getPower() * 2, pokerHand2.getPower());
        assertEquals(Combination.ONE_PAIR, pokerHand2.getCombination());

        assertEquals(Nominal.KING.getPower() * 2, pokerHand3.getPower());
        assertEquals(Combination.ONE_PAIR, pokerHand3.getCombination());
    }

    @Test
    public void threeOfKindTest() {
        PokerHand pokerHand = new PokerHand("KS KS KS JD TD");
        PokerHand pokerHand1 = new PokerHand("3S KS KS KS TD");
        PokerHand pokerHand2 = new PokerHand("2D 3S KS KS KS");
        assertEquals(Nominal.KING.getPower() * 3, pokerHand.getPower());
        assertEquals(Combination.THREE_OF_KIND, pokerHand.getCombination());

        assertEquals(Nominal.KING.getPower() * 3, pokerHand1.getPower());
        assertEquals(Combination.THREE_OF_KIND, pokerHand1.getCombination());

        assertEquals(Nominal.KING.getPower() * 3, pokerHand2.getPower());
        assertEquals(Combination.THREE_OF_KIND, pokerHand2.getCombination());
    }

    @Test
    public void twoPair() {
        PokerHand pokerHand = new PokerHand("KS KS JD JD TD");
        PokerHand pokerHand1 = new PokerHand("KS KS TD JD JD");
        assertEquals((int) (Nominal.KING.getPower() * 2 + Nominal.JACK.getPower() * 2), pokerHand.getPower());
        assertEquals(Combination.TWO_PAIRS, pokerHand.getCombination());

        assertEquals((int) (Nominal.KING.getPower() * 2 + Nominal.JACK.getPower() * 2), pokerHand1.getPower());
        assertEquals(Combination.TWO_PAIRS, pokerHand1.getCombination());
    }

    //Test case: только с цифрами, с цифрами и буквами, только с буквами
    @Test
    public void straightTest() {
        int resultPower = Nominal.TWO.getPower() +
                Nominal.THREE.getPower() +
                Nominal.FOUR.getPower() +
                Nominal.FIVE.getPower() +
                Nominal.SIX.getPower();
        PokerHand pokerHand = new PokerHand("2D 3S 4S 5S 6S");
        assertEquals(resultPower, pokerHand.getPower());
        assertEquals(Combination.STRAIGHT, pokerHand.getCombination());

        resultPower = Nominal.NINE.getPower() +
                Nominal.TEN.getPower() +
                Nominal.JACK.getPower() +
                Nominal.QUEEN.getPower() +
                Nominal.KING.getPower();
        PokerHand pokerHand1 = new PokerHand("9D TS JS QS KS");
        assertEquals(resultPower, pokerHand1.getPower());
        assertEquals(Combination.STRAIGHT, pokerHand1.getCombination());

        resultPower = Nominal.TEN.getPower() +
                Nominal.JACK.getPower() +
                Nominal.QUEEN.getPower() +
                Nominal.KING.getPower() +
                Nominal.ACE.getPower();
        PokerHand pokerHand2 = new PokerHand("TD JS QS KS AS");
        assertEquals(resultPower, pokerHand2.getPower());
        assertEquals(Combination.STRAIGHT, pokerHand.getCombination());
    }

    @Test
    public void flashCardTest() {
        PokerHand pokerHand = new PokerHand("KS TS 2S 3S 4S");
        PokerHand pokerHand1 = new PokerHand("KD TD 2D 3D 4D");
        PokerHand pokerHand2 = new PokerHand("KH TH 2H 3H 4H");
        PokerHand pokerHand3 = new PokerHand("KC TC 2C 3C 4C");
        int resultPower = Nominal.KING.getPower() +
                Nominal.TEN.getPower() +
                Nominal.TWO.getPower() +
                Nominal.THREE.getPower() +
                Nominal.FOUR.getPower();
        assertEquals(resultPower, pokerHand.getPower());
        assertEquals(Combination.FLUSH, pokerHand.getCombination());

        assertEquals(resultPower, pokerHand1.getPower());
        assertEquals(Combination.FLUSH, pokerHand1.getCombination());

        assertEquals(resultPower, pokerHand2.getPower());
        assertEquals(Combination.FLUSH, pokerHand2.getCombination());

        assertEquals(resultPower, pokerHand3.getPower());
        assertEquals(Combination.FLUSH, pokerHand3.getCombination());
    }

    @Test
    public void fullHouse() {
        PokerHand pokerHand = new PokerHand("KS KS KS JD JD");
        PokerHand pokerHand1 = new PokerHand("JD JD KS KS KS");
        assertEquals(Nominal.KING.getPower() * 3 + Nominal.JACK.getPower() * 2, pokerHand.getPower());
        assertEquals(Combination.FULL_HOUSE, pokerHand.getCombination());

        assertEquals(Nominal.KING.getPower() * 3 + Nominal.JACK.getPower() * 2, pokerHand1.getPower());
        assertEquals(Combination.FULL_HOUSE, pokerHand1.getCombination());
    }

    //Test case: только с цифрами, с цифрами и буквами, только с буквами
    @Test
    public void straightFlashTest() {
        int resultPower = Nominal.TWO.getPower() +
                Nominal.THREE.getPower() +
                Nominal.FOUR.getPower() +
                Nominal.FIVE.getPower() +
                Nominal.SIX.getPower();
        PokerHand pokerHand = new PokerHand("2S 3S 4S 5S 6S");
        assertEquals(resultPower, pokerHand.getPower());
        assertEquals(Combination.STRAIGHT_FLUSH, pokerHand.getCombination());

        resultPower = Nominal.NINE.getPower() +
                Nominal.TEN.getPower() +
                Nominal.JACK.getPower() +
                Nominal.QUEEN.getPower() +
                Nominal.KING.getPower();
        PokerHand pokerHand1 = new PokerHand("9S TS JS QS KS");
        assertEquals(resultPower, pokerHand1.getPower());
        assertEquals(Combination.STRAIGHT_FLUSH, pokerHand1.getCombination());
    }

    @Test
    public void kareTest() {
        int resultPower = Nominal.TWO.getPower() * 4;
        PokerHand pokerHand = new PokerHand("2D 2S 2S 2S 6S");
        assertEquals(resultPower, pokerHand.getPower());
        assertEquals(Combination.FOUR_OF_KIND, pokerHand.getCombination());

        resultPower = Nominal.ACE.getPower() * 4;
        PokerHand pokerHand1 = new PokerHand("AD AS AS AS 6S");
        assertEquals(resultPower, pokerHand1.getPower());
        assertEquals(Combination.FOUR_OF_KIND, pokerHand1.getCombination());
    }

    @Test
    public void flashRoyal() {
        int resultPower =
                Nominal.TEN.getPower() +
                        Nominal.JACK.getPower() +
                        Nominal.QUEEN.getPower() +
                        Nominal.KING.getPower() +
                        Nominal.ACE.getPower();
        PokerHand pokerHand = new PokerHand("TS JS QS KS AS");
        PokerHand pokerHand1 = new PokerHand("TD JD QD KD AD");
        assertEquals(resultPower, pokerHand.getPower());
        assertEquals(Combination.ROYAL_FLUSH, pokerHand.getCombination());
        assertEquals(resultPower, pokerHand1.getPower());
        assertEquals(Combination.ROYAL_FLUSH, pokerHand1.getCombination());
    }
}
