package Physics;

import LexParse.Executor;
import LexParse.Statements.Statement;
import Physics.PlayerClasses.*;
import Physics.objects.*;
import Physics.Vector2d.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JPanel;

public class Panel extends JPanel implements Runnable, KeyListener{
	// SCREEN SETTINGS
	final int WIDTH = 1200;
	final int HEIGHT = 800;
	final int FPS = 60;
	boolean scriptDone = false;
	public static CopyOnWriteArrayList<RigidBody> bodies = new CopyOnWriteArrayList<>();
	List<Statement> playerReadyStatements = null;

	Player player;

	Plane2D p1, p2, p3, p4;
	
	Thread engineThread;
	
	public Panel() {
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setBackground(Color.white);
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		this.addKeyListener(this);
		
		
		resetSimulation();
	}
	
	public void resetSimulation() {
		
		bodies.clear();

		player = new Player();

		p1 = new Plane2D(0, 790, 1200, 10, new Vector2D(0, -1));
		p2 = new Plane2D(1190, 0, 10, 800, new Vector2D(-1, 0));
		p3 = new Plane2D(0, 0, 1200, 10, new Vector2D(0, 1));
		p4 = new Plane2D(0, 0, 10, 800, new Vector2D(1, 0));

		bodies.add(player);

		bodies.add(p1);
		bodies.add(p2);
		bodies.add(p3);
		bodies.add(p4);
		
	}
	
	public static CopyOnWriteArrayList<RigidBody> getBodies(){
		return bodies;
	}

	public void startEngineThread() {
		engineThread = new Thread(this);
		engineThread.start();
	}
	
	@Override
	public void run() {

		double updateInterval = (double) 1000000000 /FPS;
		long executeInterval = 1000000000 / 5;
		long lastTime = System.nanoTime();
		long accumulatedTimeUpdate = 0;
		long accumulatedTimeExecute = 0;
		long currentTime;
		int index = 0;

		while(engineThread != null)
		{
			currentTime = System.nanoTime();
			accumulatedTimeUpdate += (currentTime - lastTime);
			accumulatedTimeExecute += (currentTime - lastTime);
			lastTime = currentTime;
			
			while(accumulatedTimeUpdate >= updateInterval) {
				
				//.1 update information such as object position
				if(accumulatedTimeUpdate >= updateInterval){
					update(updateInterval / 1000000000);

					accumulatedTimeUpdate -= (long) updateInterval;
				}

				//EXECUTION
				if(playerReadyStatements != null && index < playerReadyStatements.size() && accumulatedTimeExecute >= executeInterval){
					PlayerControl.executeStatement(playerReadyStatements.get(index), player);
					index++;
					accumulatedTimeExecute = 0;
				}
			}

            //2. draw the screen with the updated information
			repaint();
		}
	}
	
	
	public void update(double dt) {
		//adjust positions depending on the velocity and acceleration
		//We calculate the new acceleration every time we update and reset it so it doesn't stack
		for (RigidBody body : bodies) {
			PhysicsManager.applyGravity(body, dt);
			PhysicsManager.accelerate(body, dt);
			PhysicsManager.moveObjects(body, dt);
			body.resetAcceleration();
			PhysicsManager.checkPlayer(player, bodies);
		}
		
		
		PhysicsManager.checkAllCollisions(bodies);
		
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		for (RigidBody body : bodies) {
			if(body instanceof Circle){
				((Circle) body).draw(g2, Color.pink);
			}

			if(body instanceof Plane2D) {
				((Plane2D) body).draw(g2, Color.cyan);
			}

			if(body instanceof Player){
				((Player) body).draw(g2, Color.black);
			}
		}

		g2.dispose();
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//First send all the parsed statements to the parseToPlayer function so it gets rid of the non player relater statements
		//Then send the player ready statements to the executePlayerReadyStatements
		//also send the player to it to
		if(e.getKeyCode() == KeyEvent.VK_F2 && !scriptDone) {
			playerReadyStatements = PlayerControl.parseToPlayer(Executor.executeInput());
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
