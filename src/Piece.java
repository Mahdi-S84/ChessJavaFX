public abstract class Piece extends Spaceoccupier {
    protected int i;
    protected int j;
    protected String name;

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public char getColor() {
        return color;
    }

    Piece(char color, int i, int j, String name) {
        this.color = color;
        this.i = i;
        this.j = j;
        this.name = name;
    }

    @Override
    public String getName() {
        return color + name;
    }

    public abstract void move(int fi, int fj, Spaceoccupier[][] board);

    public abstract boolean canAttack(int targetI, int targetJ, Spaceoccupier[][] board);
}
