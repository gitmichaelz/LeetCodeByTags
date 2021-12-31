package OOD.deckOfCards;

public abstract class Card {
    private boolean available = true;

    /* number of face that's on card - a number 2 through 10,
     * or 11 for Jack, 12 for Queen, 13 for King, or 1 for Ace
     */
    protected int faceValue;//牌的点数/数值
    protected Suit suit;//花色

    public Card(int faceValue, Suit suit) {
        this.faceValue = faceValue;
        this.suit = suit;
    }

    public abstract int value();//这个是抽象的，因为有的牌例如A，既可以是1，也可以是11

    public Suit suit() {
        return suit;
    }

    /* return whether or not the card is available to be given out to one. */
    public boolean isAvailable() {
        return available;
    }

    public void markUnavailable() {
        available = false;
    }

    public void markAvailable() {
        available = true;
    }


}
