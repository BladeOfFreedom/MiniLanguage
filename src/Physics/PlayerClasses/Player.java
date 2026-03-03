package Physics.PlayerClasses;

import LexParse.Statements.MoveStatement;
import LexParse.Statements.Statement;
import Physics.objects.RigidBody;

import java.awt.*;
import java.util.List;

public class Player extends RigidBody {
    private static final double PIXELS_PER_METER = 100;
    private double hurtBoxRadius;
    List<Statement> playerStatements;
    public Player(){
        super();
        hurtBoxRadius = 50;
    }

    public Player(List<Statement> statements){
        super();
        hurtBoxRadius = 50;
        playerStatements = statements;
    }

    public void executeStatements(){

        for(Statement s: playerStatements){
            switch (s.execute()){
                case PlayerOperations.MOVE:
                    move_in_X(((MoveStatement)s).getDistance() * PIXELS_PER_METER);
                    break;
                case PlayerOperations.SKIP:
                    break;
            }
        }
    }

    public void move_in_X(double distance) {
            position.x += distance;
    }

    public void draw(Graphics2D g2, Color c) {
        g2.setColor(c);
        g2.fillOval(
                (int)(this.position.x - this.hurtBoxRadius),
                (int)(this.position.y - this.hurtBoxRadius),
                (int)(this.hurtBoxRadius * 2),
                (int)(this.hurtBoxRadius * 2));
    }

    public double getHurtBoxRadius(){
        return hurtBoxRadius;
    }
}
