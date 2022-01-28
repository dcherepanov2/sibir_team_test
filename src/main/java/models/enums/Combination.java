package models.enums;

public enum Combination {
    HIGH(1),
    ONE_PAIR(2),
    TWO_PAIRS(3),
    THREE_OF_KIND(4),
    STRAIGHT(5),
    FLUSH(6),
    FULL_HOUSE(7),
    FOUR_OF_KIND(8),
    STRAIGHT_FLUSH(9),
    ROYAL_FLUSH(10);

    private final int power;

    Combination(int power) {
        this.power = power;
    }

    public int getPower() {
        return power;
    }
}
