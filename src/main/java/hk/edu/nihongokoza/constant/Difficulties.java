package hk.edu.nihongokoza.constant;

public enum Difficulties{
    EASY(100),
    MEDIUM(10000),
    HARD(100000),
    EXPERT(100000000);

    private final int bound;

    Difficulties(int bound) {
        this.bound = bound;
    }

    public int getBound() {
        return bound;
    }
}
