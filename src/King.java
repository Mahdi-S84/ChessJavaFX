public class King extends Piece {
    private boolean didMove=false;
    King(char color , int i , int j){
        super(color,i,j,"king");
    }
    public boolean canGo(int i,int j){
        if((((i-this.i<=1)&&(i-this.i>=-1))&&((j-this.j<=1)&&(j-this.j>=-1)))&&(i-this.i!=0)||(j-this.j!=0)){
            return true;
        }
        return false;
    }
    public boolean isValidMove(int i, int j){
        if(canGo(i,j)){
            return true;
        }
            return false;
    }

    public void move(int fi,int fj, Spaceoccupier[][] board){
        if(isValidMove(fi,fj)){
            board[fi][fj]=this;
            board[this.i][this.j] = new Empty();
            this.i = fi;
            this.j = fj;
            didMove=true;
        }
    }
    public boolean isValidBigBlackCastling(int i, int j,Rook rook,King king,Spaceoccupier[][] board){
        if(!king.didMove
           &&!rook.didMove
           &&i==7
           &&j==5
           &&board[7][4].getName()=="null"
           &&board[7][5].getName()=="null"
           &&board[7][6].getName()=="null"){
            return true;
        }
        return false;
    }
    public boolean isValidBigWhiteCastling(int i, int j,Rook rook,King king,Spaceoccupier[][] board){
        if(!king.didMove
           &&!rook.didMove
           &&i==0
           &&j==5
           &&board[0][4].getName()=="null"
           &&board[0][5].getName()=="null"
           &&board[0][6].getName()=="null"){
           return true;
        }
        return false;
    }
    public boolean isValidShortBlackCastling(int i, int j,Rook rook,King king,Spaceoccupier[][] board){
        if(!king.didMove
           &&!rook.didMove
           &&i==7
           &&j==1
           &&board[7][1].getName()=="null"
           &&board[7][2].getName()=="null"){
           return true;
        }
        return false;
    }
    public boolean isValidShortWhiteCastling(int i, int j,Rook rook,King king,Spaceoccupier[][] board){
        if(!king.didMove
           &&!rook.didMove
           &&i==0
           &&j==1
           &&board[0][1].getName()=="null"
           &&board[0][2].getName()=="null"){
           return true;
        }
        return false;
    }
    public void bigBlackCastling(int fi,int fj, Spaceoccupier[][] board,Rook rook){
        if(isValidBigBlackCastling(fi,fj,rook,this,board)){
            board[fi][fj]=this;
            board[this.i][this.j] = new Empty();
            this.i = fi;
            this.j = fj;
            board[7][4]=rook;
            board[7][7]=new Empty();
            rook.j=4;
            didMove=true;
            rook.didMove=true;
        }
    }
    public void bigWhiteCastling(int fi,int fj, Spaceoccupier[][] board,Rook rook){
        if(isValidBigWhiteCastling(fi,fj,rook,this,board)){
            board[fi][fj]=this;
            board[this.i][this.j] = new Empty();
            this.i = fi;
            this.j = fj;
            board[0][4]=rook;
            board[0][7]=new Empty();
            rook.j=4;
            didMove=true;
            rook.didMove=true;
        }
    }
    public void shortBlackCastling(int fi,int fj, Spaceoccupier[][] board,Rook rook){
        if(isValidShortBlackCastling(fi,fj,rook,this,board)){
            board[fi][fj]=this;
            board[this.i][this.j] = new Empty();
            this.i = fi;
            this.j = fj;
            board[7][2]=rook;
            board[7][0]=new Empty();
            rook.j=2;
            didMove=true;
            rook.didMove=true;
        }
    }
    public void shortWhiteCastling(int fi,int fj, Spaceoccupier[][] board,Rook rook){
        if(isValidShortWhiteCastling(fi,fj,rook,this,board)){
            board[fi][fj]=this;
            board[this.i][this.j] = new Empty();
            this.i = fi;
            this.j = fj;
            board[0][2]=rook;
            board[0][0]=new Empty();
            rook.j=2;
            didMove=true;
            rook.didMove=true;
        }
    }
}
