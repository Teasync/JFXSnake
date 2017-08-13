package jfxsnake;

public class Piece {

    private int x;
    private int y;

    public Piece(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int[] getXY() {
        int[] dArray = {x, y};
        return dArray;
    }
    
    @Override
    public String toString() {
        return x + ", " + y;
    }
}
