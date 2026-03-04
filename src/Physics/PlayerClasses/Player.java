package Physics.PlayerClasses;

import Physics.Vector2d.Vector2D;
import Physics.objects.RigidBody;

import java.awt.*;

public class Player extends RigidBody {
    private static final double PIXELS_PER_METER = 100;
    private final double hurtBoxRadius;

    private Vector2D facingDirection = new Vector2D(1, 0);

    public Player(){
        super();
        hurtBoxRadius = 50;
    }

    public double getHurtBoxRadius(){
        return hurtBoxRadius;
    }

    public void draw(Graphics2D g2, Color c) {
        g2.setColor(c);
        g2.fillOval(
                (int)(this.position.x - this.hurtBoxRadius),
                (int)(this.position.y - this.hurtBoxRadius),
                (int)(this.hurtBoxRadius * 2),
                (int)(this.hurtBoxRadius * 2));
    }

    public void move_in_X(double distance) {
        position.x += distance * PIXELS_PER_METER * facingDirection.x;
        //velocity.x = distance ---- will change the implementation of how this function will work
    }

    public void turn_direction() {
        facingDirection.x *= -1;
    }

    public void jump(){
        // Stupid java has its y-axis reversed so have to multiply with -1 (want to work with positive on jump height)
        position.y += 5 * PIXELS_PER_METER * -1;
    }

    public Vector2D getFacingDirection() {
        return facingDirection;
    }

    public void setFacingDirection(Vector2D facingDirection) {
        this.facingDirection = facingDirection;
    }
}
