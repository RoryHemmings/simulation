package dev.IO;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import dev.GUI;

public class MouseManager implements MouseListener {

	private GUI gui;

	public MouseManager(GUI gui) {
		this.gui = gui;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		gui.getRocket().launch();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

}
