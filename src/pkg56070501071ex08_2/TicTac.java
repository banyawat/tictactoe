package pkg56070501071ex08_2;

public class TicTac {
    private int[][] holes = new int[3][3];
    private int currentPlayer=0;
    
    public TicTac(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                holes[i][j]=-1;
            }
        }
        
        printCell();
    }

    public void setCurrentPlayer(int player){
        this.currentPlayer=player;
    }

    public void swapCurrentPlayer(){
        if(this.currentPlayer==0)
            this.currentPlayer=1;
        else
            this.currentPlayer=0;
    }


    public int getCurrentPlayer(){
        return this.currentPlayer;
    }
    
    public boolean setHole(int val, int col, int row){
        if(holes[row][col]==-1){
            holes[row][col]=val;
            return true;
        }
        return false;
    }
    
    public int getHole(int col, int row){
        return holes[row][col];
    }
    
    public int checkIfWin(){
        for(int i=0;i<3;i++){
            if((holes[i][0]==holes[i][1])&&(holes[i][1]==holes[i][2])){
                if(holes[i][0]!=-1){
                    System.out.println("Win");
                    return currentPlayer;
                }
            }
            
            if((holes[0][i]==holes[1][i])&&(holes[1][i]==holes[2][i])){
                if(holes[0][i]!=-1){
                    System.out.println("Win");
                    return currentPlayer;
                }
            }
        }

        //Check cross condition
        if((holes[0][0]==holes[1][1])&&(holes[1][1]==holes[2][2])){
            if(holes[0][0]!=-1){
                System.out.println("Win");
                return currentPlayer;
            }
        }

        if((holes[2][0]==holes[1][1])&&(holes[1][1]==holes[0][2])){
            if(holes[2][0]!=-1){
                System.out.println("Win");
                return currentPlayer;
            }
        }

        return -1;
    }

    public boolean checkIfDraw(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(holes[i][j]==-1)
                    return false;
            }
        }
        return true;
    }

    public void printCell(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                System.out.print("[" + holes[i][j] +"]\t");
            }
            System.out.println();
        }
    }
}
