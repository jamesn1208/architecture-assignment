package Boards;

public abstract class Board {
    public int numberOfTiles;
    public int numberOfTails;
    public int tailSize;

    public Board(int numberOfTiles, int numberOfTails, int tailSize) {
        this.numberOfTiles = numberOfTiles;
        this.numberOfTails = numberOfTails;
        this.tailSize = tailSize;
    }

    @Override
    public String toString() {
        return "Board(numberOfTiles=" + numberOfTiles + ", numberOfTails=" + numberOfTails + ", tailSize=" + tailSize + ")";
    }
}
