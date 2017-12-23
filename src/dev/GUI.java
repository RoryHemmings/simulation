package dev;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import dev.IO.MouseManager;

public class GUI implements Runnable {

	private int width, height;
	private float timePerTick;

	private JFrame frame;
	private Canvas canvas;
	private Thread thread;

	private MouseManager mm;

	private Graphics g;
	private BufferStrategy bs;

	private Rocket rocket;
	private Missile missile;

	public static void main(String[] args) {
		new GUI("Simulation", 1200, 800);
	}

	public GUI(String title, int width, int height) {
		this.width = width;
		this.height = height;

		thread = new Thread(this);

		mm = new MouseManager(this);

		frame = new JFrame();
		frame.setSize(width, height);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false);
		frame.add(canvas);
		frame.pack();

		thread.start();
	}

	public void init() {
		rocket = new Rocket(this, 200, height - 10, 130f, 48f, Color.darkGray);
		missile = new Missile(this, 0, height - 500, 100f, 10f, Color.blue);
		frame.addMouseListener(mm);
		canvas.addMouseListener(mm);
	}

	public void tick() {
		rocket.tick();
		rocket.update();
		missile.tick();
	}

	public void render() {
		if (bs == null)
			canvas.createBufferStrategy(3);
		bs = canvas.getBufferStrategy();
		g = bs.getDrawGraphics();
		g.clearRect(0, 0, width, height);

		rocket.render(g);
		missile.render(g);

		bs.show();
		g.dispose();
	}

	@Override
	public void run() {
		init();

		int fps = 60;
		timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();

		while (true) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			lastTime = now;

			if (delta >= 1) {
				tick();
				render();
				delta--;
			}
		}
	}

	public float getDeltaTime() {
		return timePerTick / 1000000000;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Missile getMissile() {
		return missile;
	}

	public Rocket getRocket() {
		return rocket;
	}
}
