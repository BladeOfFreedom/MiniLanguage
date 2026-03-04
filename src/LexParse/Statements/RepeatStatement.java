package LexParse.Statements;

import Physics.PlayerClasses.PlayerOperations;

import java.util.ArrayList;
import java.util.List;

public class RepeatStatement implements Statement{
    private int count;
    List<Statement> statements = new ArrayList<>();

    public RepeatStatement(int count, List<Statement> statements){
        this.count = count;
        this.statements = statements;
    }

    //this is for player
    public List<Statement> createInnerList(){
        List<Statement> innerList = new ArrayList<Statement>();
        for(int i = 0; i < count; i++){
            for(Statement s: statements)
                if(s instanceof RepeatStatement)
                    innerList.addAll(((RepeatStatement) s).createInnerList());
                else
                    innerList.add(s);
        }
        return innerList;
    }


    //this will not be used in the engine maybe for something else
    @Override
    public PlayerOperations execute() {
        System.out.println("This is a repeat statement with a count of " + count);
        for(int i = 0; i < count; i++){
            for(Statement s: statements)
                s.execute();
        }
        return PlayerOperations.SKIP;
    }
}
