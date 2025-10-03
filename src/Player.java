public class Player {
    private final String name;
    private int position;

    public Player(String name, int startingPosition) {
        this.name = name;
        this.position = startingPosition;
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
        return "Player(name=" + name + ", position=" + position + ")";
    }
}
