package LexParse.Statements;

import Physics.PlayerClasses.PlayerOperations;

public class JumpStatement implements Statement{

    @Override
    public PlayerOperations execute() {
        System.out.println("This is a jump statement");
        return PlayerOperations.JUMP;
    }
}
