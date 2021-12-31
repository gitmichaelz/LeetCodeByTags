package OOD.callCenter;

public enum Rank {
    Responder(0),
    Manager(1),
    Director(2);

    private int value;

    Rank(int v) {
        value = v;
    }

    public int getValue() {
        return value;
    }
}
