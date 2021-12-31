package OOD.deckOfCards;

import java.util.List;
import java.util.Random;

public class Deck <T extends Card> {//一副牌，一摞牌
    private List<T> cards;
    private int dealtIndex = 0;//marks first undealt Card

    public Deck() {

    }

    public void setDeckOfCards(List<T> deckOfCards) {
        cards = deckOfCards;
    }

    public void shuffle() {
        Random random = new Random();
        for(int i = 0; i < cards.size(); i++) {
            int idx = random.nextInt(i + 1);
            T card1 = cards.get(i);
            T card2 = cards.get(idx);
            cards.set(i, card2);
            cards.set(idx, card1);
        }
    }

    public int remainingCards() {
        return cards.size() - dealtIndex;
    }

    //庄家给玩家们发牌， number: 玩家数量
    public T[] dealHand(int number) {
        if(remainingCards() < number) {
            return null;
        }

        T[] hand =(T[]) new Card[number];
        int count = 0;
        while(count < number) {
            T card = dealCard();
            if(card != null) {
                hand[count] = card;
                count++;
            }
        }
        return hand;
    }

    //从一摞牌中发一张牌
    public T dealCard() {
        if(remainingCards() == 0) {
            return null;
        }
        T card = cards.get(dealtIndex);
        card.markUnavailable();
        dealtIndex++;
        return card;
    }
 }
