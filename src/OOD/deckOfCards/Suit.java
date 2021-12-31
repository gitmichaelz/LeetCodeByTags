package OOD.deckOfCards;
/* 牌的花色 */
public enum Suit {
    Club (0),
    Diamond (1),
    Heart (2),
    Space (3);

    private int value;
    private Suit(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Suit getSuitFromValue(int value) {
        switch(value) {
            case 0:
                return Suit.Club;
            case 1:
                return Suit.Diamond;
            case 2:
                return Suit.Heart;
            case 3:
                return Suit.Space;
            default:
                return null;
        }
    }
}
