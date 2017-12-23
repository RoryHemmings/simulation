package dev;

import java.awt.Color;

public class Missile extends Projectile {

	public Missile(GUI gui, float x, float y, float power, float angle, Color color) {
		super(gui, x, y, power, -angle, color);
		hasLaunched = true;
	}

}
