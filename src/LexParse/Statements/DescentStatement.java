package LexParse.Statements;

import Physics.PlayerClasses.PlayerOperations;

public class DescentStatement implements Statement{

    @Override
    public PlayerOperations execute() {
        System.out.println("This is a descent statement");
        return PlayerOperations.DESCENT;
    }
}
