package LexParse.Statements;

public class MoveStatement implements Statement{
    private int distance;

    public MoveStatement(int distance){
        this.distance = distance;
    }


    @Override
    public void execute() {
        System.out.println("This is a move statement with a distance of " + distance);
    }
}
