package LexParse.Statements;

import Physics.PlayerClasses.PlayerOperations;

public class MoveStatement implements Statement{
    private int distance;

    public MoveStatement(int distance){
        this.distance = distance;
    }


    @Override
    public PlayerOperations execute() {
        System.out.println("This is a move statement with a distance of " + distance);
        return PlayerOperations.MOVE;
    }

    public int getDistance(){
        return distance;
    }
}
