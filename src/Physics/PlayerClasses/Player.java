package Physics.PlayerClasses;

import Physics.PhysicsManager;
import Physics.Vector2d.Vector2D;
import Physics.objects.RigidBody;

import java.awt.*;

public class Player extends RigidBody {
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
        velocity.x = 0;
        velocity.x = distance * PhysicsManager.getPixelsPerMeter() * facingDirection.x;
    }

    public void descent(){
        // Stupid java has its y-axis reversed
        velocity.y = 30 * PhysicsManager.getPixelsPerMeter();
    }

    public void turn_direction() {
        facingDirection.x *= -1;
        velocity.x *= -1;
    }

    public void jump(){
        // Stupid java has its y-axis reversed
        velocity.y = -7 * PhysicsManager.getPixelsPerMeter();
    }

    public Vector2D getFacingDirection() {
        return facingDirection;
    }

    public void setFacingDirection(Vector2D facingDirection) {
        this.facingDirection = facingDirection;
    }
}
