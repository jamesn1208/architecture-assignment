public class Player {
    private final String name;
    private int position;
    private final String winPosition;
    private final int tailIndex;
    public boolean inLeg = false;

    public Player(String name, int startingPosition, String winPosition, int tailIndex) {
        this.winPosition = winPosition;
        this.tailIndex = tailIndex;
        this.name = name;
        this.position = startingPosition;
    }

    public String getWinPosition() {
        return winPosition;
    }

    public boolean isInLeg() {
        return inLeg;
    }

    public void setInLeg(boolean inLeg) {
        this.inLeg = inLeg;
    }

    public int getTailIndex() {
        return tailIndex;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Player(name=" + name + ", currentPosition=" + position + ", winPosition=" + winPosition + ")";
    }
}
