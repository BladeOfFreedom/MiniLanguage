package Physics.PlayerClasses;

import LexParse.Statements.MoveStatement;
import LexParse.Statements.RepeatStatement;
import LexParse.Statements.Statement;

import java.util.ArrayList;
import java.util.List;

public class PlayerControl {


    //This gets rid of the statements that has nothing to do with the player but manipulates other statements (REPEAT)
    public static List<Statement> parseToPlayer(List<Statement> parsedStatements){
        List<Statement> playerReadyStatements = new ArrayList<Statement>();

        for(Statement s: parsedStatements){
            if(s instanceof RepeatStatement)
                playerReadyStatements.addAll(((RepeatStatement) s).createInnerList());
            else
                playerReadyStatements.add(s);
        }
        return playerReadyStatements;
    }

    public static void executePlayerReadyStatements(List<Statement> playerReadtStatements, Player p){

        for(Statement s: playerReadtStatements){
            switch (s.execute()){
                case PlayerOperations.MOVE -> p.move_in_X(((MoveStatement)s).getDistance());
                case PlayerOperations.TURN -> p.turn_direction();
                case PlayerOperations.JUMP -> p.jump();
            }
        }

    }

}
