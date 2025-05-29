public abstract class Piece extends Spaceoccupier {
    protected char color;
    protected int i;
    protected int j;
    protected String name;


    Piece(char color , int i , int j , String name){
        this.color = color;
        this.i=i;
        this.j=j;
        this.name=name;
    }
    @Override
    public String getName(){
        return color + name;
    }
    public abstract void move(int fi,int fj,Spaceoccupier[][] board);

}
