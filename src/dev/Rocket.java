package dev;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import dev.physics.Physics;
import dev.physics.Vector;

public class Rocket {

	private GUI gui;
	private float x, y, width, height, startY, frames = 0, angle = 60f;
	private float time = 0;
	private float xVel, yVel;
	private float power;
	private Rectangle hitBox;

	private ArrayList<Vector> trajectory = new ArrayList<Vector>();

	private boolean hitFloor = false;

	public Rocket(GUI gui, float x, float y, float power) {
		this.gui = gui;
		this.x = x;
		this.y = y;
		this.startY = y;
		this.angle = -angle;
		this.xVel = (float) Math.cos(Math.toRadians(angle));
		this.yVel = (float) -Math.sin(Math.toRadians(angle));
		this.power = power;

		width = 25;
		height = 10;
		hitBox = new Rectangle((int) x, (int) y, (int) width, (int) height);
	}

	public void tick() {
		if (!hitFloor) {
			frames += 1;
			time = frames * gui.getDeltaTime();
			float xSpeed = xVel * power;
			float ySpeed = yVel * power;
			System.out.println(xSpeed + " " + ySpeed);
			x = xSpeed * time;
			y = (float) startY - (ySpeed * time + ((-Physics.gravity / 2) * (time*time)));
		}

		if (hitBox.intersects(gui.getMissile().getHitBox())) {
			kill();
			gui.getMissile().kill();
		}

		if ((y + height) > gui.getHeight()) {
			kill();
		}

		if ((int) (frames) % 10 == 0) {
			trajectory.add(new Vector((int) x, (int) y));
		}
		hitBox = new Rectangle((int) x, (int) y, (int) width, (int) height);
	}

	public void kill() {
		hitFloor = true;
		// JOptionPane.showMessageDialog(null, "Missle exploded at " + x + " " + y);
	}

	public void render(Graphics g) {
		g.fillRect((int) x, (int) y, (int) width, (int) height);
		for (Vector i : trajectory) {
			g.fillOval(i.getX(), i.getY(), 3, 3);
		}
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public float getStartY() {
		return startY;
	}

	public float getxVel() {
		return xVel;
	}

	public float getPower() {
		return power;
	}

	public boolean hasHitFloor() {
		return hitFloor;
	}

	public Rectangle getHitBox() {
		return hitBox;
	}
}
