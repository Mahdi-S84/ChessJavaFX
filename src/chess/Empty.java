package chess;
public class Empty extends Spaceoccupier {
    public Empty() {
        this.color = 'e';
    }
    @Override
public String toString() {
    return " ";
}
}
