package service;

import exception.SetCardException;
import models.Card;
import models.enums.Nominal;
import models.enums.Suit;

public class CardService {

    public static Card createCard(String str){
        if(str.length() !=2)
            throw new SetCardException("An invalid string was passed to the input for initialization");
        Card card = new Card();
        String[] split = str.split("");
        for(Nominal nominal: Nominal.values()){
            if(nominal.getNominal().equals(split[0])) {
                card.setNominal(nominal);
                card.setPower(nominal.getPower());
                break;
            }
        }
        for(Suit suit: Suit.values()){
            if(suit.getSuit().equals(split[1])) {
                card.setSuit(suit);
                break;
            }
        }
        if(card.getSuit() == null || card.getNominal() == null)
            throw new SetCardException("An invalid string was passed to the input for initialization");
        return card;
    }

}
