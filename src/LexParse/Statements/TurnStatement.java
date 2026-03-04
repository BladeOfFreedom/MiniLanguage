package LexParse.Statements;

import Physics.PlayerClasses.PlayerOperations;

public class TurnStatement implements Statement{

    @Override
    public PlayerOperations execute() {
        System.out.println("This is a turn statement");
        return PlayerOperations.TURN;
    }
}
