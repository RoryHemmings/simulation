package dev;

import java.awt.Color;
import java.awt.Graphics;


public class Rocket extends Projectile {

	public Rocket(GUI gui, float x, float y, float power, float angle, Color color) {
		super(gui, x, y, power, -angle, color);
	}

	public void update() {
		//lastY = y;

		if (!hitFloor) {
			if (hitBox.intersects(gui.getMissile().getHitBox())) {
				kill();
				gui.getMissile().kill();
			}

//			deltaY = -(y - lastY) / power;
//			currentRot = (float) Math.asin(deltaY);
//			System.out.println(Math.toDegrees(currentRot)*100);
//			hitBox = new Rectangle((int) x, (int) y, (int) width, (int) height);
		}
	}
}
