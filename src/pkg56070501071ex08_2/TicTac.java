package pkg56070501071ex08_2;

public class TicTac {
    private int[][] holes = new int[3][3];
    
    public TicTac(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                holes[i][j]=-1;
                if(i==2)
                    /*if(j==1)
                        holes[i][j]=1;
                    else*/
                        holes[i][j]=1;
            }
        }
        
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                System.out.print("[" + holes[i][j] +"]\t");
            }
            System.out.println();
        }
    }
    
    public void setHole(int val, int col, int row){
        if(holes[col][row]==0){
            holes[col][row]=val;
        }
    }
    
    public int getHole(int col, int row){
        return holes[col][row];
    }
    
    public void checkIfWin(){
        for(int i=0;i<3;i++){

            if((holes[i][0]==holes[i][1])&&(holes[i][1]==holes[i][2])){
                if(holes[i][0]!=-1)
                    System.out.println("Win");
            }
            
            if((holes[0][i]==holes[1][i])&&(holes[1][i]==holes[2][i])){
                if(holes[0][i]!=-1)
                    System.out.println("Win");
            }
        }
    }
}
