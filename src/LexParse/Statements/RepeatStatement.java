package LexParse.Statements;

import java.util.ArrayList;
import java.util.List;

public class RepeatStatement implements Statement{
    private int count;
    List<Statement> statements = new ArrayList<>();

    public RepeatStatement(int count, List<Statement> statements){
        this.count = count;
        this.statements = statements;
    }

    @Override
    public void execute() {
        System.out.println("This is a repeat statement with a count of " + count);
        for(int i = 0; i < count; i++){
            for(Statement s: statements)
                s.execute();
        }
    }
}
