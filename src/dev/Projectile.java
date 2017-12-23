package dev;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import dev.physics.Physics;
import dev.physics.Vector;

public class Projectile {

	protected GUI gui;
	protected boolean hasLaunched = false;
	protected float x, y, width = 25, height = 10, startY, startX, frames = 0, angle, currentRot;
	protected float time = 0;
	protected float xVel, yVel;
	protected float lastY, deltaY = 0;
	protected float power;
	protected Rectangle hitBox;
	protected Color color;

	protected ArrayList<Vector> trajectory = new ArrayList<Vector>();

	protected boolean hitFloor = false;

	public Projectile(GUI gui, float x, float y, float power, float angle, Color color) {
		this.gui = gui;
		this.x = x;
		this.y = y;
		lastY = y;
		startX = x;
		startY = y;
		this.angle = angle;
		this.xVel = (float) Math.cos(Math.toRadians(angle));
		this.yVel = (float) -Math.sin(Math.toRadians(angle));
		this.power = power;
		this.color = color;

		hitBox = new Rectangle((int) x, (int) y, (int) width, (int) height);
	}

	public void tick() {
		if (hasLaunched) {
			if (!hitFloor) {
				frames += 1;
				time = frames * gui.getDeltaTime();
				x = getXAt(time);
				y = getYAt(time);

				if ((y + height) > gui.getHeight()) {
					kill();
				}

				if ((int) (frames) % 10 == 0) {
					trajectory.add(new Vector((int) x, (int) y));
				}
			}

			hitBox = new Rectangle((int) x, (int) y, (int) width, (int) height);
		}
	}
	
	public float getXAt(float time) {
		float xSpeed = xVel * power;
		return (xSpeed * time) + startX;
	}
	
	public float getYAt(float time) {
		float ySpeed = yVel * power;
		return (float) startY - (ySpeed * time + ((-Physics.gravity / 2) * (time * time)));
	}

	public void kill() {
		hitFloor = true;
	}

	public void render(Graphics g) {
		g.setColor(color);
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