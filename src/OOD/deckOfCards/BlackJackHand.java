package OOD.deckOfCards;

import java.util.ArrayList;
import java.util.List;

public class BlackJackHand extends Hand<BlackJackCard> {
    public BlackJackHand(){

    }
    //return max under 21 if possible, otherwise return minOver 21
    public int score() {
        List<Integer> scores = possibleScores();
        int maxUnder = Integer.MIN_VALUE;//maxUnder 21
        int minOver = Integer.MAX_VALUE;//minOver 21
        for(int score : scores) {
            if(score > 21 && score < minOver) {
                minOver = score;
            } else if(score <= 21 && score > maxUnder) {
                maxUnder = score;
            }
        }
        return maxUnder == Integer.MIN_VALUE? minOver : maxUnder;
    }

    private List<Integer> possibleScores() {
        List<Integer> scores = new ArrayList<>();
        if(cards.size() == 0) {
            return scores;
        }
        for(BlackJackCard card : cards) {
            addCardToScoreList(card, scores);
        }
        return scores;
    }

    private void addCardToScoreList(BlackJackCard card, List<Integer> scores) {
        if(scores.size() == 0) {
            scores.add(0);
        }
        int size = scores.size();
        for(int i = 0; i < size; i++) {
            int score = scores.get(i);
            scores.set(i, score + card.minValue());
            if(card.minValue() != card.maxValue()) {
                scores.add(score + card.maxValue());
            }
        }
    }

    public boolean busted() {
        return score() > 21;
    }

    public boolean is21() {
        return score() == 21;
    }

    public boolean isBlackJack() {
        if(cards.size() != 2) {
            return false;
        }

        BlackJackCard first = cards.get(0);
        BlackJackCard second = cards.get(1);
        return (first.isAce() && second.isFaceCard()) ||
                (second.isAce() && first.isFaceCard());
    }
}
